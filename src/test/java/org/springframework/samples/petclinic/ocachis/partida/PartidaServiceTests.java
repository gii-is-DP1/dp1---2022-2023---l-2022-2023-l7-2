package org.springframework.samples.petclinic.ocachis.partida;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.ficha.Ficha;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.util.EntityUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaServiceTests {
    @Autowired
    PartidaService ps;


	PartidaOca partidaOca;
	PartidaParchis partidaParchis;

	private static final Integer CODIGO_OCA = 102;
	private static final Integer CODIGO_PARCHIS = 103;
	
	@BeforeEach
	public void setup(){
		partidaOca = this.ps.findPartidaOcaById(3);
		partidaOca.setEstado(TipoEstadoPartida.JUGANDO);
		partidaParchis = this.ps.findPartidaParchisById(1);
	}


    @Test
	void shouldFindPartidatWithCorrectId() {
		PartidaOca po = this.ps.findPartidaOcaById(1);
		assertThat(po.getCodigoPartida()).isEqualTo(100);
		assertThat(po.getMaxJugadores()).isEqualTo(3);

		PartidaParchis pp = this.ps.findPartidaParchisById(1);
		assertThat(pp.getCodigoPartida()).isEqualTo(103);
		assertThat(pp.getMaxJugadores()).isEqualTo(4);
	}
    @Test
	void shouldFindAllPartidasOca() {
		Collection<PartidaOca> pos = this.ps.findAllOca();
        PartidaOca po = EntityUtils.getById(pos, PartidaOca.class, 1);
		assertThat(po.getCodigoPartida()).isEqualTo(100);
		assertThat(po.getMaxJugadores()).isEqualTo(3);
	}
	@Test
	void shouldFindAllPartidasParchis() {
		Collection<PartidaParchis> pps = this.ps.findAllParchis();
		PartidaParchis pp = EntityUtils.getById(pps, PartidaParchis.class, 1);
		assertThat(pp.getCodigoPartida()).isEqualTo(103);
		assertThat(pp.getMaxJugadores()).isEqualTo(4);
	}
    @Test 
    void shouldSavePartidaOca() {
        int numeroPartidaOcaInicial = this.ps.findAllOca().size();
		PartidaOca p = new PartidaOca();
		p.setCodigoPartida(03);
		p.setEstado(TipoEstadoPartida.CREADA);
		p.setMaxJugadores(3);
		this.ps.saveOca(p);
		            
	
		assertThat(p.getId().longValue()).isNotEqualTo(0);
        int numeroPartidaOcaFinal = this.ps.findAllOca().size();
		assertThat(numeroPartidaOcaFinal).isEqualTo(numeroPartidaOcaInicial +1);
    }
	@Test 
    void shouldSavePartidaParchis() {
        int numeroPartidaParchisInicial = this.ps.findAllParchis().size();
		PartidaParchis p = new PartidaParchis();
		p.setCodigoPartida(03);
		p.setEstado(TipoEstadoPartida.CREADA);
		p.setMaxJugadores(3);
		this.ps.saveParchis(p);
		            
	
		assertThat(p.getId().longValue()).isNotEqualTo(0);
        int numeroPartidaParchisFinal = this.ps.findAllParchis().size();
		assertThat(numeroPartidaParchisFinal).isEqualTo(numeroPartidaParchisInicial +1);
    }

	@Test
	void shouldInitializePartidaOca(){
		PartidaOca po = this.ps.crearPartidaOca(2);
		assertThat(po.getEstado().equals(TipoEstadoPartida.CREADA));
		assertThat(po.getMaxJugadores().equals(2));
		assertThat(po.getColorJugadorActual().equals(Color.ROJO));
		assertThat(po.getCasillas().size() == 63);
	}

    
	@Test
	void shouldInitializePartidaParchis(){
		PartidaParchis pp = this.ps.crearPartidaParchis(2);
		assertThat(pp.getEstado().equals(TipoEstadoPartida.CREADA));
		assertThat(pp.getMaxJugadores().equals(2));
		assertThat(pp.getColorJugadorActual().equals(Color.ROJO));
		assertThat(pp.getCasillas().size() == 104);
	}

	@Test
	void shouldStartPartidaOca(){
		partidaOca.setEstado(TipoEstadoPartida.CREADA);
		this.ps.iniciarPartidaOca(partidaOca);
		assertThat(partidaOca.getEstado().equals(TipoEstadoPartida.JUGANDO));
	}

	@Test
	void shouldStartPartidaParchis(){
		partidaParchis.setEstado(TipoEstadoPartida.CREADA);
		this.ps.iniciarPartidaParchis(partidaParchis);
		assertThat(partidaParchis.getEstado().equals(TipoEstadoPartida.JUGANDO));
	}
	
// ************************************************************************OCA************************************************************************

	@Test 
	void shouldReturnFalseHayAlguienEnElPozo(){
		PartidaOca po = this.ps.crearPartidaOca(2);
		assertThat(this.ps.hayAlguienEnElPozo(po).equals(false));
	}

	@Test
	void shouldReturnTrueHayAlguienEnElPozo(){
		PartidaOca po = this.ps.crearPartidaOca(2);
		po.getCasillaConNumero(31).añadirFicha(new FichaOca());
		assertThat(this.ps.hayAlguienEnElPozo(po).equals(true));
	}

	@Test
	void shouldLiberarFichasDelPozo(){
		PartidaOca po = this.ps.crearPartidaOca(2);
		Jugador j = new Jugador();
		j.setColor(Color.ROJO);
		j.setNumTurnosBloqueadoRestantesOca(100);
		FichaOca f = new FichaOca();
		f.setColor(Color.ROJO);
		po.getCasillaConNumero(31).añadirFicha(f);
		this.ps.liberarFichasPozo(po);
		assertThat(j.getNumTurnosBloqueadoRestantesOca() == 0);
	}
	
	@Test 
	void shouldEnviarMensajeOca(){
		List<Jugador> jugadores = (List<Jugador>) partidaOca.getJugadores();
		Jugador j = jugadores.get(0);
		this.ps.enviarMensajeOca(partidaOca, "Prueba", j);
		assertThat(partidaOca.getChatOca().get(0).equals("Pepe(ROJO): Prueba"));
	}


	
	
	
	@Test 
	void shouldEnviarMensajeParchis(){
		List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
		this.ps.enviarMensajeParchis(partidaParchis, "Hola", j);
		assertThat(partidaParchis.getChatParchis().get(0).equals("usuario(ROJO): Prueba"));
	}
	
	@Test
	public void shouldEncontrarOcaPorCodigo(){
		PartidaOca po = this.ps.findOcaByCodigo(CODIGO_OCA);
		assertTrue(po == partidaOca);

	}


	@Test
	public void shouldchekIntegridadPartidaOcaPasaTurno(){
		partidaOca.setFechaHoraUltimoMovimiento(40000L);
		Color colorInicial = partidaOca.getColorJugadorActual();
		Long ultimoMovInicial = partidaOca.getFechaHoraUltimoMovimiento();
		this.ps.checkIntegridadPartidaOca(partidaOca);
		Color colorFinal = partidaOca.getColorJugadorActual();
		Long ultimoMovFinal = partidaOca.getFechaHoraUltimoMovimiento();
		assertTrue(colorFinal!=colorInicial, "No ha pasado de turno");
		assertTrue(ultimoMovFinal!=ultimoMovInicial, "No ha actualizado el ultimo movimiento");
	}


	@Test
	public void shouldchekIntegridadPartidaOcaNoPasaTurno(){
			partidaOca.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
			Color colorInicial = partidaOca.getColorJugadorActual();
			Long ultimoMovInicial = partidaOca.getFechaHoraUltimoMovimiento();
			this.ps.checkIntegridadPartidaOca(partidaOca);
			Color colorFinal = partidaOca.getColorJugadorActual();
			Long ultimoMovFinal = partidaOca.getFechaHoraUltimoMovimiento();
			assertTrue(colorFinal==colorInicial, "Ha pasado de turno");
			assertTrue(ultimoMovFinal==ultimoMovInicial, "Ha actualizado el ultimo movimiento");
	
		}


	// ************************************************************************PARCHIS************************************************************************

	@Test
	void shouldTirarDadoParchis(){
		if (partidaParchis.getDado() == null) {
			int d = this.ps.tirarDado(partidaParchis);

			assertThat(partidaParchis.getDado() != null);
			for(int i=0;i<5;i++){
				int e = this.ps.tirarDado(partidaParchis);
				assertThat(d==e);
			}
		}
	}
	
	@Test
	public void shouldchekIntegridadPartidaParchisPasaTurno(){
		partidaParchis.setFechaHoraUltimoMovimiento(40000L);
		Color colorInicial = partidaParchis.getColorJugadorActual();
		Long ultimoMovInicial = partidaParchis.getFechaHoraUltimoMovimiento();
		this.ps.checkIntegridadPartidaParchis(partidaParchis);
		Color colorFinal = partidaParchis.getColorJugadorActual();
		Long ultimoMovFinal = partidaParchis.getFechaHoraUltimoMovimiento();
		assertTrue(colorFinal!=colorInicial, "No ha pasado de turno");
		assertTrue(ultimoMovFinal!=ultimoMovInicial, "No ha actualizado el ultimo movimiento");
	}


	@Test
	public void shouldchekIntegridadPartidaParchisNoPasaTurno(){
			partidaParchis.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
			Color colorInicial = partidaParchis.getColorJugadorActual();
			Long ultimoMovInicial = partidaParchis.getFechaHoraUltimoMovimiento();
			this.ps.checkIntegridadPartidaParchis(partidaParchis);
			Color colorFinal = partidaParchis.getColorJugadorActual();
			Long ultimoMovFinal = partidaParchis.getFechaHoraUltimoMovimiento();
			assertTrue(colorFinal==colorInicial, "Ha pasado de turno");
			assertTrue(ultimoMovFinal==ultimoMovInicial, "Ha actualizado el ultimo movimiento");
	
		}

		@Test
	public void shouldEncontrarParchisPorCodigo(){
		PartidaParchis pp = this.ps.findParchisByCodigo(CODIGO_PARCHIS);
		assertTrue(pp == partidaParchis);

	}

	
	@Test
	public void testMandarFichaACasa(){
		FichaParchis ficha = null;
		Integer numFichasEnCasaInicial = 0;
		for(Jugador j: partidaParchis.getJugadores()){
			if(j.getColor()==Color.ROJO){	
				for(FichaParchis f: j.getFichasParchis()){
					if(f.isEstaEnCasa()==false){
						ficha = f;
					}
					else{
						numFichasEnCasaInicial++;
					}
				}
			}
		}
		CasillaParchis casillaInicial = ficha.getCasillaActual();
		Integer numFichasEnCasillaInicialInicial = casillaInicial.getNumeroFichas();
		
		ps.mandarFichaACasa(partidaParchis, ficha);

		Integer numFichasEnCasillaInicialFinal = casillaInicial.getNumeroFichas();

		Integer numFichasEnCasaFinal = 0;
		for(Jugador j: partidaParchis.getJugadores()){
			if(j.getColor()==Color.ROJO){
				
				for(FichaParchis f: j.getFichasParchis()){
					if(f.isEstaEnCasa()==false){
						ficha = f;
						
					}else{
						numFichasEnCasaFinal++;
					}
				}
			}
		}
		assertTrue(numFichasEnCasillaInicialFinal == numFichasEnCasillaInicialInicial -1 , "No ha eliminado la ficha de la casillaInicial");
		assertTrue(numFichasEnCasaFinal == numFichasEnCasaInicial+1, "No manda la ficha a casa");


		
		
	}

}