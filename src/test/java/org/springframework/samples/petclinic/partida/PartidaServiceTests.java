package org.springframework.samples.petclinic.partida;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.ficha.Ficha;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.util.EntityUtils;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaServiceTests {
    @Autowired
    PartidaService ls;

    @Test
	void shouldFindPartidatWithCorrectId() {
		PartidaOca po = this.ls.findByIdOca(1);
		assertThat(po.getCodigoPartida()).isEqualTo(100);
		assertThat(po.getMaxJugadores()).isEqualTo(3);

		PartidaParchis pp = this.ls.findByIdParchis(1);
		assertThat(pp.getCodigoPartida()).isEqualTo(103);
		assertThat(pp.getMaxJugadores()).isEqualTo(4);
	}
    @Test
	void shouldFindAllPartidasOca() {
		Collection<PartidaOca> pos = this.ls.findAllOca();
        PartidaOca po = EntityUtils.getById(pos, PartidaOca.class, 1);
		assertThat(po.getCodigoPartida()).isEqualTo(100);
		assertThat(po.getMaxJugadores()).isEqualTo(3);
	}
	@Test
	void shouldFindAllPartidasParchis() {
		Collection<PartidaParchis> pps = this.ls.findAllParchis();
		PartidaParchis pp = EntityUtils.getById(pps, PartidaParchis.class, 1);
		assertThat(pp.getCodigoPartida()).isEqualTo(103);
		assertThat(pp.getMaxJugadores()).isEqualTo(4);
	}
    @Test 
    void shouldSavePartidaOca() {
        int numeroPartidaOcaInicial = this.ls.findAllOca().size();
		PartidaOca p = new PartidaOca();
		p.setCodigoPartida(03);
		p.setEstado(TipoEstadoPartida.CREADA);
		p.setMaxJugadores(3);
		this.ls.saveOca(p);
		            
	
		assertThat(p.getId().longValue()).isNotEqualTo(0);
        int numeroPartidaOcaFinal = this.ls.findAllOca().size();
		assertThat(numeroPartidaOcaFinal).isEqualTo(numeroPartidaOcaInicial +1);
    }
	@Test 
    void shouldSavePartidaParchis() {
        int numeroPartidaParchisInicial = this.ls.findAllParchis().size();
		PartidaParchis p = new PartidaParchis();
		p.setCodigoPartida(03);
		p.setEstado(TipoEstadoPartida.CREADA);
		p.setMaxJugadores(3);
		this.ls.saveParchis(p);
		            
	
		assertThat(p.getId().longValue()).isNotEqualTo(0);
        int numeroPartidaParchisFinal = this.ls.findAllParchis().size();
		assertThat(numeroPartidaParchisFinal).isEqualTo(numeroPartidaParchisInicial +1);
    }

	@Test
	void shouldInitializePartidaOca(){
		PartidaOca po = this.ls.crearPartidaOca(2);
		assertThat(po.getEstado().equals(TipoEstadoPartida.CREADA));
		assertThat(po.getMaxJugadores().equals(2));
		assertThat(po.getColorJugadorActual().equals(Color.ROJO));
		assertThat(po.getCasillas().size() == 63);
	}

	@Test
	void shouldInitializePartidaParchis(){
		PartidaParchis pp = this.ls.crearPartidaParchis(2);
		assertThat(pp.getEstado().equals(TipoEstadoPartida.CREADA));
		assertThat(pp.getMaxJugadores().equals(2));
		assertThat(pp.getColorJugadorActual().equals(Color.ROJO));
		assertThat(pp.getCasillas().size() == 104);
	}

	@Test 
	void shouldReturnFalseHayAlguienEnElPozo(){
		PartidaOca po = this.ls.crearPartidaOca(2);
		assertThat(this.ls.hayAlguienEnElPozo(po).equals(false));
	}

	@Test
	void shouldReturnTrueHayAlguienEnElPozo(){
		PartidaOca po = this.ls.crearPartidaOca(2);
		po.getCasillaConNumero(31).a??adirFicha(new FichaOca());
		assertThat(this.ls.hayAlguienEnElPozo(po).equals(true));
	}

	@Test
	void shouldLiberarFichasDelPozo(){
		PartidaOca po = this.ls.crearPartidaOca(2);
		Jugador j = new Jugador();
		j.setColor(Color.ROJO);
		j.setNumTurnosBloqueadoRestantesOca(100);
		FichaOca f = new FichaOca();
		f.setColor(Color.ROJO);
		po.getCasillaConNumero(31).a??adirFicha(f);
		this.ls.liberarFichasPozo(po);
		assertThat(j.getNumTurnosBloqueadoRestantesOca() == 0);
	}

}