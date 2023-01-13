package org.springframework.samples.petclinic.ocachis.partida;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaParchis;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.exceptions.MinimoDeJugadoresNoAlcanzadoException;
import org.springframework.samples.petclinic.util.EntityUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runners.MethodSorters;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PartidaServiceTests {
	
    @Autowired
    PartidaService ps;
	PartidaOca partidaOca;
	PartidaParchis partidaParchis;

	;

	private static final Integer CODIGO_OCA = 102;
	private static final Integer CODIGO_PARCHIS = 103;

	

	@AfterEach
	public void dropDown(){
	}
	
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
	void shouldStartPartidaOca() throws MinimoDeJugadoresNoAlcanzadoException{
		partidaOca.setEstado(TipoEstadoPartida.CREADA);
		this.ps.iniciarPartidaOca(partidaOca);
		assertThat(partidaOca.getEstado().equals(TipoEstadoPartida.JUGANDO));
	}

	@Test
	void shouldStartPartidaParchis() throws MinimoDeJugadoresNoAlcanzadoException{
		partidaParchis.setEstado(TipoEstadoPartida.CREADA);
		this.ps.iniciarPartidaParchis(partidaParchis);
		assertThat(partidaParchis.getEstado().equals(TipoEstadoPartida.JUGANDO));
	}

	@Test
	void testRedirigirPartida(){
		List<Jugador> jugadores = (List<Jugador>) partidaOca.getJugadores();
		Jugador j = jugadores.get(0);
		String res = "redirect:/partida/oca/" + 3 + "/jugar";
		String redireccion = ps.redirigirPartida(j.getUsuario());
		assertThat(res==redireccion);
	}
	
// ************************************************************************OCA************************************************************************

	@Test
	void testfindAllPageableOca(){
		Pageable pageable = PageRequest.of(0,5);
		Page<PartidaOca> partidasOca =ps.findAllPageableOca(pageable);
		assertThat(partidasOca.getSize()==1);
	}
	
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
	public void shouldCheckIntegridadPartidaOcaPasaTurno(){
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
	public void shouldCheckIntegridadPartidaOcaNoPasaTurno(){
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

		@Test
		void testJugarOcaAvanzarUnaCasilla(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(1).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			Integer casillaInicial = jugador.getFichaOca().getCasillaActual().getNumero();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);
			assertThat(jugador.getFichaOca().getCasillaActual().getNumero().equals(casillaInicial+1));
			Mockito.reset(spy);
			
		}

		@Test
		void testJugarOcaCaerEnPuente(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(5).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getFichaOca().getCasillaActual().getNumero().equals(19));
			assertThat(jugador.getNumTurnosBloqueadoRestantesOca().equals(1));
			Mockito.reset(spy);

			
		}
		
		@Test
		void testJugarOcaCaerEnPosada(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(18).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getFichaOca().getCasillaActual().getNumero().equals(19));
			assertThat(jugador.getNumTurnosBloqueadoRestantesOca().equals(1));
			Mockito.reset(spy);
			
		}

		@Test
		void testJugarOcaCaerEnOca(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(4).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getFichaOca().getCasillaActual().getNumero().equals(9));
			assertThat(partidaOca.getColorJugadorActual().equals(Color.ROJO));
			Mockito.reset(spy);
			
		}

		@Test
		void testJugarOcaCaerEnDado(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(25).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getFichaOca().getCasillaActual().getNumero().equals(33));
			Mockito.reset(spy);
			
		}

		@Test
		void testJugarOcaCaerEnPozo(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(30).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getNumTurnosBloqueadoRestantesOca().equals(4));
			Mockito.reset(spy);
			
		}

		@Test
		void testJugarOcaCaerEnLaberinto(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(41).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getFichaOca().getCasillaActual().getNumero().equals(30));
			Mockito.reset(spy);
		}
		@Test
		void testJugarOcaCaerEnMuerte(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(57).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(jugador.getFichaOca().getCasillaActual().getNumero()==1);
			Mockito.reset(spy);
			
			
		}
		@Test
		void testOcaEntrarEnMeta(){
			PartidaService spy = Mockito.spy(new PartidaService());
			Mockito.doReturn(62).when(spy).TirarNumDado();
			Jugador jugador = partidaOca.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			ps.jugarOca(partidaOca, jugador.getFichaOca(), jugador);

			assertThat(partidaOca.getEstado().equals(TipoEstadoPartida.TERMINADA));
			Mockito.reset(spy);
		}

	// ************************************************************************PARCHIS************************************************************************
	@Test
	void testfindAllPageableParchis(){
		Pageable pageable = PageRequest.of(0,5);
		Page<PartidaParchis> partidasParchis =ps.findAllPageableParchis(pageable);
		assertThat(partidasParchis.getSize()==2);
	}
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
	public void shouldCheckIntegridadPartidaParchisPasaTurno(){
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
	public void shouldCheckIntegridadPartidaParchisNoPasaTurno(){
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

		@Test
		void testJugarParchisMoverFicha(){
			Jugador jugador = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			FichaParchis fichaRoja = jugador.getFichasParchis().stream().filter(f->f.getCasillaActual().getNumero()==3).findAny().get();
			Integer casillaInicialNum = 3;
			CasillaParchis casillaInicial = partidaParchis.getCasillaConNumero(3); 
			CasillaParchis casillaFinal = partidaParchis.getCasillaConNumero(7); 
			Integer numFichasCasillaInicialAntiguo = casillaInicial.getNumeroFichas();
			Integer numFichasCasillaFinalAntiguo = casillaFinal.getNumeroFichas();
			ps.jugarParchis(partidaParchis, fichaRoja.getId() ,jugador.getId() ,4 );
			FichaParchis fichaRojaNueva = ((List<FichaParchis>) jugador.getFichasParchis()).get(3);
			
			assertTrue(casillaInicialNum + 4 == fichaRojaNueva.getCasillaActual().getNumero());
			assertTrue(casillaInicial.getNumeroFichas() == numFichasCasillaInicialAntiguo -1 );
			assertTrue(casillaFinal.getNumeroFichas() == numFichasCasillaFinalAntiguo +1 );
		}

		@Test
		void testJugarParchisPasarTurno(){
			Jugador jugador = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			
			List<FichaParchis> fichasAntiguas = new ArrayList((List<FichaParchis>) jugador.getFichasParchis());
			Color colorJugadorInicial = partidaParchis.getColorJugadorActual();
			
			ps.jugarParchis(partidaParchis, -1,jugador.getId(), 4);

			Color colorJugadorFinal = partidaParchis.getColorJugadorActual();
			List<FichaParchis> fichasNuevas = new ArrayList((List<FichaParchis>) jugador.getFichasParchis());
			
			assertTrue(fichasNuevas.equals(fichasAntiguas));
			assertTrue(colorJugadorFinal != colorJugadorInicial);
		}

		@Test
		void testJugarParchisComerFicha(){
			Integer numCasillaInicial = 3;
			Jugador jugadorRojo = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findFirst().get();
			Jugador jugadorAmarillo = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.AMARILLO).findFirst().get();
			Integer numFichasAmarillasEnCasaAntiguo = 0;
			for(FichaParchis f : jugadorAmarillo.getFichasParchis()){
				if(f.isEstaEnCasa())  numFichasAmarillasEnCasaAntiguo++;
			}
			FichaParchis fichaRoja = jugadorRojo.getFichasParchis().stream().filter(f->f.getCasillaActual().getNumero()==numCasillaInicial).findAny().get();
			Integer fichaComidasAntiguo = jugadorRojo.getFichasComidas();
			
			Integer casillaInicialNum = numCasillaInicial;
			
			CasillaParchis casillaInicial = partidaParchis.getCasillaConNumero(numCasillaInicial); 
			CasillaParchis casillaFinal = partidaParchis.getCasillaConNumero(7); 
			Integer numFichasCasillaInicialAntiguo = casillaInicial.getNumeroFichas();	
			Color colorJugadorInicial = partidaParchis.getColorJugadorActual();

			ps.jugarParchis(partidaParchis, fichaRoja.getId() ,jugadorRojo.getId() ,3);

			Integer fichaComidasNueva = jugadorRojo.getFichasComidas();
			Color colorJugadorFinal = partidaParchis.getColorJugadorActual();
			FichaParchis fichaRojaNueva = ((List<FichaParchis>) jugadorRojo.getFichasParchis()).get(3);
			Integer numFichasAmarillasEnCasaNuevo = 0;
			for(FichaParchis f : jugadorAmarillo.getFichasParchis()){
				if(f.isEstaEnCasa())  numFichasAmarillasEnCasaNuevo++;
			}
			
			assertTrue(fichaComidasNueva == fichaComidasAntiguo +1 );
			assertTrue(casillaInicialNum + 3 == fichaRojaNueva.getCasillaActual().getNumero());
			assertTrue(casillaInicial.getNumeroFichas() == numFichasCasillaInicialAntiguo -1 );
			assertTrue(partidaParchis.getDado()==20);
			assertTrue(colorJugadorFinal == colorJugadorInicial);		
			assertTrue(numFichasAmarillasEnCasaNuevo == numFichasAmarillasEnCasaAntiguo +1);			
		}

		@Test
		void testJugarParchisMeterFichaEnMeta(){
			Jugador jugadorAmarillo = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.AMARILLO).findFirst().get();
			FichaParchis fichaAmarilla = jugadorAmarillo.getFichasParchis().stream().filter(f->f.getCasillaActual().getNumero()==74).findAny().get();
			Integer numFichasAmarillasEnMetaAntiguo = 0;
			for(FichaParchis f : jugadorAmarillo.getFichasParchis()){
				if(f.isEstaEnLaMeta())  numFichasAmarillasEnMetaAntiguo++;
			}
			ps.jugarParchis(partidaParchis, fichaAmarilla.getId(), jugadorAmarillo.getId(), 2);
			Integer numFichasAmarillasEnMetaNuevo = 0;
			for(FichaParchis f : jugadorAmarillo.getFichasParchis()){
				if(f.isEstaEnLaMeta())  numFichasAmarillasEnMetaNuevo++;
			}
			FichaParchis fichaAmarillaNueva = ((List<FichaParchis>) jugadorAmarillo.getFichasParchis()).get(3);
			
			assertThat(fichaAmarillaNueva.isEstaEnCasa());
			assertTrue(numFichasAmarillasEnMetaNuevo == numFichasAmarillasEnMetaAntiguo +1);
		}

		@Test
		void testJugarParchisTriple6(){
			Jugador jugadorAmarillo = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.AMARILLO).findFirst().get();
			Color colorJugadorInicial = partidaParchis.getColorJugadorActual();
			FichaParchis fichaAmarillaInicial = jugadorAmarillo.getFichasParchis().stream().filter(f->f.getCasillaActual().getNumero()==6).findAny().get();
			Integer numFichasAmarillasEnCasaAntiguo = 0;
			for(FichaParchis f : jugadorAmarillo.getFichasParchis()){
				if(f.isEstaEnCasa())  numFichasAmarillasEnCasaAntiguo++;
			}
			
			ps.jugarParchis(partidaParchis, fichaAmarillaInicial.getId(), jugadorAmarillo.getId(), 6);
			assertTrue(partidaParchis.getVecesSacado6()==1);
			
			FichaParchis fichaAmarilla1 = ((List<FichaParchis>) jugadorAmarillo.getFichasParchis()).get(3);
			ps.jugarParchis(partidaParchis, fichaAmarilla1.getId(), jugadorAmarillo.getId(), 6);
			assertTrue(partidaParchis.getVecesSacado6()==2);
			
			FichaParchis fichaAmarilla2 = ((List<FichaParchis>) jugadorAmarillo.getFichasParchis()).get(3);
			ps.jugarParchis(partidaParchis, fichaAmarilla2.getId(), jugadorAmarillo.getId(), 6);
			
			Integer numFichasAmarillasEnCasaNuevo = 0;
			for(FichaParchis f : jugadorAmarillo.getFichasParchis()){
				if(f.isEstaEnCasa())  numFichasAmarillasEnCasaNuevo++;
			}
			
			FichaParchis fichaAmarilla3 = ((List<FichaParchis>) jugadorAmarillo.getFichasParchis()).get(3);
			
			assertTrue(partidaParchis.getColorJugadorActual() != colorJugadorInicial);
			assertTrue(fichaAmarilla3.isEstaEnCasa());
			assertTrue(numFichasAmarillasEnCasaNuevo == numFichasAmarillasEnCasaAntiguo +1);
		}


}