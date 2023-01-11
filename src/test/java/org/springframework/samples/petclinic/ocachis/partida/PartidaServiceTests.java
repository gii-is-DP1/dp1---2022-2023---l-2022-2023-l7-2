package org.springframework.samples.petclinic.ocachis.partida;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaParchis;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

		@Test 
		void testInicializarCasillasOca(){
			PartidaOca partida = new PartidaOca();
			ps.inicializarCasillasOca(partida);
			assertNotNull(partida.getCasillas());
			assertTrue(partida.getCasillas().size()==63);
			assertTrue(partida.getCasillaConNumero(6).getTipoCasillaOca()==TipoCasillaOca.PUENTE);
			assertTrue(partida.getCasillaConNumero(12).getTipoCasillaOca()==TipoCasillaOca.PUENTE);
			assertTrue(partida.getCasillaConNumero(19).getTipoCasillaOca()==TipoCasillaOca.POSADA);
			assertTrue(partida.getCasillaConNumero(26).getTipoCasillaOca()==TipoCasillaOca.DADOS);
			assertTrue(partida.getCasillaConNumero(53).getTipoCasillaOca()==TipoCasillaOca.DADOS);
			assertTrue(partida.getCasillaConNumero(31).getTipoCasillaOca()==TipoCasillaOca.POZO);
			assertTrue(partida.getCasillaConNumero(42).getTipoCasillaOca()==TipoCasillaOca.LABERINTO);
			assertTrue(partida.getCasillaConNumero(52).getTipoCasillaOca()==TipoCasillaOca.CARCEL);
			assertTrue(partida.getCasillaConNumero(58).getTipoCasillaOca()==TipoCasillaOca.MUERTE);
			assertTrue(partida.getCasillaConNumero(63).getTipoCasillaOca()==TipoCasillaOca.FINAL);
		}

		@Test

		void testFuncionOca(){
			ArrayList<Integer> numeros = new ArrayList<>();
			numeros.add(5);numeros.add(9);numeros.add(14);	numeros.add(18);	numeros.add(23);	numeros.add(27);	numeros.add(32);	numeros.add(36);
			numeros.add(41);	numeros.add(45);	numeros.add(50);	numeros.add(54);	numeros.add(59);
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			
			for(Integer num: numeros){
				CasillaOca casillaInicial = partidaOca.getCasillaConNumero(num);
				CasillaOca casillaFinal = this.ps.funcionOca(partidaOca, casillaInicial, jugador);
				assertTrue(casillaFinal.getTipoCasillaOca()==TipoCasillaOca.OCA);
			}
		}

		@Test
		void testBorrarOca(){
			Integer partidaId = 3;
			PartidaOca partida = ps.findPartidaOcaById(partidaId);
			assertNotNull(partida.getId());

			ps.borrarPartidaOca(partida.getId());
			assertThrows(ResourceNotFoundException.class,() -> ps.findPartidaOcaById(partidaId));
		}


		@Test
		void testFinalizarPartidaOca(){
			partidaOca.setEstado(TipoEstadoPartida.JUGANDO);
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.finalizarPartidaOca(partidaOca, jugador);
			assertTrue(partidaOca.getEstado() == TipoEstadoPartida.TERMINADA, "No actualiza las estadisticas");
			assertTrue(partidaOca.getGanador()==jugador.getUsuario());
			assertNotNull(partidaOca.getDuracion());
		}



	// ************************************************************************PARCHIS************************************************************************

	@Test
	void testFinalizarPartidaParchis(){
		TipoEstadoPartida estadoInicial = partidaParchis.getEstado();
		Jugador jugador = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
		ps.finalizarPartidaParchis(partidaParchis, jugador);
		assertTrue(partidaParchis.getEstado()==TipoEstadoPartida.TERMINADA && partidaParchis.getEstado()!=estadoInicial,"No actualiza las estadisticas");
		assertTrue(partidaParchis.getGanador()==jugador.getUsuario());
		assertNotNull(partidaParchis.getDuracion());
	}

	@Test
	void testBorrarParchis(){
		Integer partidaId = 1;
		PartidaParchis partida = ps.findPartidaParchisById(partidaId);
		assertNotNull(partida.getId());

		ps.borrarPartidaParchis(partida.getId());
		assertThrows(ResourceNotFoundException.class,() -> ps.findPartidaParchisById(partidaId));
	}

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

	@Test 
		void testInicializarCasillasParchis(){
			PartidaParchis partida = new PartidaParchis();
			ps.inicializarCasillasParchis(partida);
			assertNotNull(partida.getCasillas());
			assertTrue(partida.getCasillas().size()==104);
			assertTrue(partida.getCasillaConNumero(101).getTipoCasillaParchis()==TipoCasillaParchis.CASAAMARILLO);
			assertTrue(partida.getCasillaConNumero(102).getTipoCasillaParchis()==TipoCasillaParchis.CASAAZUL);
			assertTrue(partida.getCasillaConNumero(103).getTipoCasillaParchis()==TipoCasillaParchis.CASAROJO);
			assertTrue(partida.getCasillaConNumero(104).getTipoCasillaParchis()==TipoCasillaParchis.CASAVERDE);
			assertTrue(partida.getCasillaConNumero(5).getTipoCasillaParchis()==TipoCasillaParchis.INCIOAMARILLO);
			assertTrue(partida.getCasillaConNumero(22).getTipoCasillaParchis()==TipoCasillaParchis.INCIOAZUL);
			assertTrue(partida.getCasillaConNumero(39).getTipoCasillaParchis()==TipoCasillaParchis.INCIOROJO);
			assertTrue(partida.getCasillaConNumero(56).getTipoCasillaParchis()==TipoCasillaParchis.INCIOVERDE);
			assertTrue(partida.getCasillaConNumero(76).getTipoCasillaParchis()==TipoCasillaParchis.FINALAMARILLO);
			assertTrue(partida.getCasillaConNumero(84).getTipoCasillaParchis()==TipoCasillaParchis.FINALAZUL);
			assertTrue(partida.getCasillaConNumero(92).getTipoCasillaParchis()==TipoCasillaParchis.FINALROJO);
			assertTrue(partida.getCasillaConNumero(100).getTipoCasillaParchis()==TipoCasillaParchis.FINALVERDE);
		}

		@Test
		void testVerificarFinalPartida(){
			Jugador jugador = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			jugador.getFichasParchis().stream().findAny().get().setEstaEnLaMeta(false);
			assertFalse(ps.verificarFinalPartida(jugador));

			jugador.getFichasParchis().stream().forEach(f->f.setEstaEnLaMeta(true));
			assertTrue(ps.verificarFinalPartida(jugador));
		}


		@Test
		void testPuedeComerFicha(){
			CasillaParchis casilla = new CasillaParchis();
			casilla.setFichas(new ArrayList<>());
			casilla.setTipoCasillaParchis(TipoCasillaParchis.NORMAL);

			FichaParchis ficha = new FichaParchis();
			ficha.setColor(Color.ROJO);

			FichaParchis ficha1 = new FichaParchis();
			ficha1.setColor(Color.VERDE);
			casilla.añadirFicha(ficha1);
			assertTrue(ps.puedeComerFicha(casilla, ficha));

			FichaParchis ficha2 = new FichaParchis();
			ficha2.setColor(Color.VERDE);
			casilla.añadirFicha(ficha2);
			assertFalse(ps.puedeComerFicha(casilla, ficha));

			FichaParchis ficha3 = new FichaParchis();
			ficha3.setColor(Color.ROJO);
			casilla.setFichas(new ArrayList<>());
			casilla.añadirFicha(ficha3);
			assertFalse(ps.puedeComerFicha(casilla, ficha));
		}


		@Test
		void testComerFicha(){
			CasillaParchis casilla6 = partidaParchis.getCasillaConNumero(6);
			assertTrue(casilla6.getFichas().size()==1);
			Jugador jugador = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			
			FichaParchis fichaRoja = jugador.getFichasParchis().stream().filter(f->f.getCasillaActual().getNumero()==3).findAny().get();

			
			ps.comerFicha(casilla6, fichaRoja, partidaParchis);

			assertTrue(casilla6.getFichas().size()==0);

		}
}