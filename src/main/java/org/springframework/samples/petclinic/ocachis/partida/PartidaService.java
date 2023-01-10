package org.springframework.samples.petclinic.ocachis.partida;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaService;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaParchis;
import org.springframework.samples.petclinic.ocachis.estadisticasGlobales.EstadisticasGlobalesService;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.ficha.FichaService;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.logro.LogroService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

//import antlr.collections.List;
@Service
public class PartidaService {
	private PartidaOcaRepository partidaOcaRepository;
	private PartidaParchisRepository partidaParchisRepository;
	private FichaService fichaService;
	private CasillaService casillaService;
	private JugadorService jugadorService;
	private LogroService logroService;
	private EstadisticasGlobalesService estadisticasGlobalesService;

	private static final Integer DURACION_TURNO_MILIS = 30000;

	@Autowired
	public PartidaService(PartidaOcaRepository partidaOcaRepository, PartidaParchisRepository partidaParchisRepository,
			FichaService fichaService, CasillaService casillaService, JugadorService jugadorService,
			LogroService logroService,
			EstadisticasGlobalesService estadisticasGlobalesService) {
		this.partidaOcaRepository = partidaOcaRepository;
		this.partidaParchisRepository = partidaParchisRepository;
		this.fichaService = fichaService;
		this.casillaService = casillaService;
		this.jugadorService = jugadorService;
		this.logroService = logroService;
		this.estadisticasGlobalesService = estadisticasGlobalesService;
	}

	public void inicializarCasillasOca(PartidaOca partida) {
		List<Integer> numerosCasillasOca = new ArrayList<>();
		numerosCasillasOca.add(5);
		numerosCasillasOca.add(9);
		numerosCasillasOca.add(14);
		numerosCasillasOca.add(18);
		numerosCasillasOca.add(23);
		numerosCasillasOca.add(27);
		numerosCasillasOca.add(32);
		numerosCasillasOca.add(36);
		numerosCasillasOca.add(41);
		numerosCasillasOca.add(45);
		numerosCasillasOca.add(50);
		numerosCasillasOca.add(54);
		numerosCasillasOca.add(59);
		List<CasillaOca> casillas = new ArrayList<CasillaOca>();

		for (int i = 1; i <= 63; i++) {
			CasillaOca casilla = new CasillaOca();
			casilla.setNumero(i);
			casilla.setFichas(new ArrayList<FichaOca>());
			if (numerosCasillasOca.contains(i))
				casilla.setTipoCasillaOca(TipoCasillaOca.OCA);
			else if (i == 6 || i == 12)
				casilla.setTipoCasillaOca(TipoCasillaOca.PUENTE);
			else if (i == 19)
				casilla.setTipoCasillaOca(TipoCasillaOca.POSADA);
			else if (i == 26 || i == 53)
				casilla.setTipoCasillaOca(TipoCasillaOca.DADOS);
			else if (i == 31)
				casilla.setTipoCasillaOca(TipoCasillaOca.POZO);
			else if (i == 42)
				casilla.setTipoCasillaOca(TipoCasillaOca.LABERINTO);
			else if (i == 52)
				casilla.setTipoCasillaOca(TipoCasillaOca.CARCEL);
			else if (i == 58)
				casilla.setTipoCasillaOca(TipoCasillaOca.MUERTE);
			else if (i == 63)
				casilla.setTipoCasillaOca(TipoCasillaOca.FINAL);
			else
				casilla.setTipoCasillaOca(TipoCasillaOca.NORMAL);
			casilla = casillaService.saveCasillaOca(casilla);
			casillas.add(casilla);
		}

		partida.setCasillas(casillas);
	}

	@Transactional
	public PartidaOca crearPartidaOca(Integer maxJugadores) {
		PartidaOca partida = new PartidaOca();
		partida.setMaxJugadores(maxJugadores);
		partida.setCodigoPartida(Partida.getNuevoCodigoPartida());
		partida.setColorJugadorActual(Color.ROJO);
		partida.setEstado(TipoEstadoPartida.CREADA);
		partida.setJugadores(new ArrayList<Jugador>());
		partida.setUsuariosObservadores(new ArrayList<>());
		partida.setFechaCreacion(LocalDateTime.now());
		inicializarCasillasOca(partida);

		partida = partidaOcaRepository.save(partida);
		return partida;
	}

	@Transactional(readOnly = true)
	public Collection<PartidaOca> findAllOca() {
		return this.partidaOcaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Page<PartidaOca> findAllPageableOca(Pageable p) {
		return this.partidaOcaRepository.findAllPageable(p);
	}

	@Transactional(readOnly = true)
	public Page<PartidaParchis> findAllPageableParchis(Pageable p) {
		return this.partidaParchisRepository.findAllPageable(p);
	}

	@Transactional(readOnly = true)
	public Collection<PartidaParchis> findAllParchis() {
		return this.partidaParchisRepository.findAll();
	}

	public Page<PartidaOca> findEsperaOca(Pageable p) {
		return partidaOcaRepository.findEsperaOca(p);
	}

	public Page<PartidaParchis> findEsperaParchis(Pageable p) {
		return partidaParchisRepository.findEsperaParchis(p);
	}

	public PartidaOca saveOca(PartidaOca p) {
		return partidaOcaRepository.save(p);
	}

	public PartidaParchis saveParchis(PartidaParchis p) {
		return partidaParchisRepository.save(p);
	}

	@Transactional
	public void borrarPartidaOca(int id) {
		this.partidaOcaRepository.deleteById(id);
	}

	@Transactional
	public void borrarPartidaParchis(int id) {
		this.partidaParchisRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public PartidaOca findPartidaOcaById(int id) {
		return this.partidaOcaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public PartidaParchis findPartidaParchisById(int id){
		Optional<PartidaParchis> partida =this.partidaParchisRepository.findById(id);
		if(!partida.isPresent()) return null;
		return partida.get();
	}

	private CasillaOca funcionOca(PartidaOca partida, CasillaOca casillaInicial, Jugador j) {

		CasillaOca casillaFinal = null;
		switch (casillaInicial.getNumero()) {
			case 5:
				casillaFinal = partida.getCasillaConNumero(9);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 9");
				return casillaFinal;
			case 9:
				casillaFinal = partida.getCasillaConNumero(14);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 14");
				return casillaFinal;
			case 14:
				casillaFinal = partida.getCasillaConNumero(18);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 18");
				return casillaFinal;
			case 18:
				casillaFinal = partida.getCasillaConNumero(23);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 23");
				return casillaFinal;
			case 23:
				casillaFinal = partida.getCasillaConNumero(27);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 27");
				return casillaFinal;
			case 27:
				casillaFinal = partida.getCasillaConNumero(32);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 32");
				return casillaFinal;
			case 32:
				casillaFinal = partida.getCasillaConNumero(36);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 36");
				return casillaFinal;
			case 36:
				casillaFinal = partida.getCasillaConNumero(41);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 41");
				return casillaFinal;
			case 41:
				casillaFinal = partida.getCasillaConNumero(45);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 45");
				return casillaFinal;
			case 45:
				casillaFinal = partida.getCasillaConNumero(50);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 50");
				return casillaFinal;
			case 50:
				casillaFinal = partida.getCasillaConNumero(54);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 54");
				return casillaFinal;
			case 54:

				casillaFinal = partida.getCasillaConNumero(59);
				partida.addLog(
						"'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 59");
				return casillaFinal;
			case 59:
				casillaFinal = partida.getCasillaConNumero(59);
				partida.addLog("'Tiro porque me toca' - El jugador " + j.getColor() + " vuelve a tirar");
				return casillaFinal;
		}
		return casillaFinal;
	}

	public Boolean hayAlguienEnElPozo(PartidaOca partida) {
		return partida.getCasillaConNumero(31).getFichas().size() > 0;
	}

	public void liberarFichasPozo(PartidaOca partida) {
		List<Color> coloresEnElPozo = new ArrayList<Color>();
		CasillaOca pozo = partida.getCasillaConNumero(31);

		for (FichaOca ficha : pozo.getFichas()) {
			coloresEnElPozo.add(ficha.getColor());
		}

		for (Jugador j : partida.getJugadores()) {
			if (coloresEnElPozo.contains(j.getColor()))
				j.setNumTurnosBloqueadoRestantesOca(0);
			partida.addLog("El jugador " + j.getColor() + " ha salido del pozo");
		}

	}

	public void pasarTurnoOca(PartidaOca partida) {
		switch (partida.getColorJugadorActual()) {
			case ROJO:
				partida.setColorJugadorActual(Color.AMARILLO);

				break;
			case AMARILLO:
				if (partida.getJugadores().size() == 2)
					partida.setColorJugadorActual(Color.ROJO);
				else
					partida.setColorJugadorActual(Color.VERDE);
				break;
			case VERDE:
				if (partida.getJugadores().size() == 3)
					partida.setColorJugadorActual(Color.ROJO);
				else
					partida.setColorJugadorActual(Color.AZUL);
				break;
			case AZUL:
				partida.setColorJugadorActual(Color.ROJO);
				break;
		}
		partida.addLog("TURNO DEL JUGADOR " + partida.getColorJugadorActual());
	}

	@Transactional
	public void jugarOca(PartidaOca partida, FichaOca ficha, Jugador j) {
		partida.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());

		if (j.getNumTurnosBloqueadoRestantesOca() > 0) {
			j.setNumTurnosBloqueadoRestantesOca(j.getNumTurnosBloqueadoRestantesOca() - 1);
			if (j.getNumTurnosBloqueadoRestantesOca() == 0)
				partida.addLog("El jugador " + j.getColor() + " ya no está bloqueado y puede volver a jugar");
			else if (j.getNumTurnosBloqueadoRestantesOca() == 1)
				partida.addLog("Al jugador " + j.getColor() + " le queda 1 turno bloqueado");
			else
				partida.addLog("Al jugador " + j.getColor() + " le quedan " + j.getNumTurnosBloqueadoRestantesOca()
						+ " turnos bloqueados");
				pasarTurnoOca(partida);
			return;
		}

		Boolean volverATirar = false;
		CasillaOca casillaInicial = ficha.getCasillaActual();

		Integer numeroCasillaInicial = casillaInicial.getNumero();

		int dado = this.TirarNumDado();
		partida.addLog("El jugador " + j.getColor() + " ha sacado " + dado + " en el dado");

		Integer numeroCasillaInicialMasDado = numeroCasillaInicial + dado;
		if (numeroCasillaInicialMasDado > 63) {
			int dif = numeroCasillaInicialMasDado - 63;
			numeroCasillaInicialMasDado = 63 - dif;
			partida.addLog(
					"El jugador " + j.getColor() + " ha rebotado hasta la casilla " + numeroCasillaInicialMasDado);
		}

		CasillaOca casillaInicialMasDado = partida.getCasillaConNumero(numeroCasillaInicialMasDado);
		CasillaOca casillaFinal = null;

		switch (casillaInicialMasDado.getTipoCasillaOca()) {
			case NORMAL:
				casillaFinal = casillaInicialMasDado;
				break;

			case OCA:
				casillaFinal = funcionOca(partida, casillaInicialMasDado, j);
				volverATirar = true;
				break;
			case PUENTE:
				casillaFinal = partida.getCasillaConNumero(19);
				j.setNumTurnosBloqueadoRestantesOca(1);
				partida.addLog(
						"El jugador " + j.getColor() + " ha caido en puente y va a la posada. Está 1 turno bloqueado");
				break;

			case POSADA:
				casillaFinal = casillaInicialMasDado;
				j.setNumTurnosBloqueadoRestantesOca(1);
				partida.addLog("El jugador " + j.getColor() + " ha caido en la posada. Está 1 turno bloqueado");
				break;

			case LABERINTO:
				casillaFinal = partida.getCasillaConNumero(30);
				partida.addLog("El jugador " + j.getColor() + " ha caido en el laberinto y va a la casilla 30");
				break;

			case POZO:
				casillaFinal = casillaInicialMasDado;
				j.setNumTurnosBloqueadoRestantesOca(4);
				partida.addLog("El jugador " + j.getColor()
						+ " ha caido en el pozo. Está 4 turnos bloqueado o hasta que otro jugador pase por la casilla.");
				break;

			case DADOS:
				casillaFinal = partida.getCasillaConNumero(numeroCasillaInicialMasDado + 8);
				partida.addLog("El jugador " + j.getColor()
						+ " ha caido en dados, avanza la suma de los digitos de la casilla dados");
				break;

			case MUERTE:
				casillaFinal = partida.getCasillaConNumero(1);
				j.setVecesCaidoEnMuerte(j.getVecesCaidoEnMuerte() + 1);
				partida.addLog("El jugador " + j.getColor() + " ha muerto ඞ. Vuelve a la casilla 1");
				break;

			case CARCEL:
				casillaFinal = casillaInicialMasDado;
				j.setNumTurnosBloqueadoRestantesOca(2);
				partida.addLog("El jugador " + j.getColor() + " ha caido en la cárcel. Está 2 turnos bloqueado");
				break;

			case FINAL:
				casillaFinal = casillaInicialMasDado;
				partida.setEstado(TipoEstadoPartida.TERMINADA);
				partida.addLog("El jugador " + j.getColor() + " ha ganado la partida");
				finalizarPartidaOca(partida, j);
		}

		fichaService.moverFichaOca(ficha, casillaFinal, j);

		if (hayAlguienEnElPozo(partida)) {
			Integer numeroCasillaFinal = casillaFinal.getNumero();
			if (numeroCasillaInicial < 31 && numeroCasillaFinal > 31) {
				liberarFichasPozo(partida);
			}
		}

		if (!volverATirar) {
			pasarTurnoOca(partida);
		}
	}

	public void finalizarPartidaOca(PartidaOca partida, Jugador jugadorGanador) {
		partida.setGanador(jugadorGanador.getUsuario());
		jugadorGanador.setEsGanador(true);
		partida.setFechaFinalizacion(LocalDateTime.now());
		Duration duracion = Duration.between(partida.getFechaCreacion(), partida.getFechaFinalizacion());
		Integer duracionInMinutes = (int) duracion.getSeconds() / 60;
		partida.setDuracion(duracionInMinutes);
		for (Jugador j : partida.getJugadores()) {
			j.finalizarPartidaOca(duracionInMinutes);
		}
		try {
			logroService.actualizarLogrosOca(partida);
		} catch (IllegalAccessException e) {
		}
		estadisticasGlobalesService.updateEstadisticasGlobalesPartidaOca(partida);
	}

	public int TirarNumDado() {
		int numDado = (int) (Math.random() * 6 + 1);
		//numDado = 1;
		return numDado;
	}

	public PartidaParchis crearPartidaParchis(Integer maxJugadores) {
		PartidaParchis partida = new PartidaParchis();
		partida.setMaxJugadores(maxJugadores);
		partida.setCodigoPartida(Partida.getNuevoCodigoPartida());
		partida.setColorJugadorActual(Color.ROJO);
		partida.setEstado(TipoEstadoPartida.CREADA);
		partida.setFechaCreacion(LocalDateTime.now());
		partida.setJugadores(new ArrayList<Jugador>());
		partida.setUsuariosObservadores(new ArrayList<>());
		partida.setUltimoSacado6(false);
		inicializarCasillasParchis(partida);
		partida = partidaParchisRepository.save(partida);
		return partida;
	}

	private void inicializarCasillasParchis(PartidaParchis partida) {
		ArrayList<CasillaParchis> casillas = new ArrayList<>();
		for (int i = 1; i <= 104; i++) {
			CasillaParchis casilla = new CasillaParchis();
			casilla.setNumero(i);
			casilla.setBloqueada(false);
			casilla.setFichas(new ArrayList<FichaParchis>());
			if (i <= 68) {
				if (i == 5)
					casilla.setTipoCasillaParchis(TipoCasillaParchis.INCIOAMARILLO);
				else if (i == 22)
					casilla.setTipoCasillaParchis(TipoCasillaParchis.INCIOAZUL);
				else if (i == 39)
					casilla.setTipoCasillaParchis(TipoCasillaParchis.INCIOROJO);
				else if (i == 56)
					casilla.setTipoCasillaParchis(TipoCasillaParchis.INCIOVERDE);
				else if (i == 12 || i == 17 || i == 29 || i == 34 || i == 46 || i == 51 || i == 63 || i == 68)
					casilla.setTipoCasillaParchis(TipoCasillaParchis.SEGURO);
				else
					casilla.setTipoCasillaParchis(TipoCasillaParchis.NORMAL);
			} else if (i >= 69 && i <= 75)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.PASILLOAMARILLO);
			else if (i >= 77 && i <= 83)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.PASILLOAZUL);
			else if (i >= 85 && i <= 91)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.PASILLOROJO);
			else if (i >= 93 && i <= 99)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.PASILLOVERDE);

			else if (i == 76)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.FINALAMARILLO);
			else if (i == 84)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.FINALAZUL);
			else if (i == 92)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.FINALROJO);
			else if (i == 100)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.FINALVERDE);

			else if (i == 101)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.CASAAMARILLO);
			else if (i == 102)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.CASAAZUL);
			else if (i == 103)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.CASAROJO);
			else if (i == 104)
				casilla.setTipoCasillaParchis(TipoCasillaParchis.CASAVERDE);
			// this.casillaService.saveCasillaParchis(casilla);
			casillas.add(casilla);
		}
		partida.setCasillas(casillas);
	}

	// *****************************************JUGAR
	// PARCHIS*****************************************

	@Transactional
	public void jugarParchis(PartidaParchis partida, Integer fichaId, Integer jugadorId, Integer dado) {
		partida.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
		if (fichaId == -1) { // pasar turno
			partida.pasarTurno();
			partida.addLog("Pasa turno");
			return;
		}
		FichaParchis ficha = fichaService.findFichaParchis(fichaId);
		Jugador jugador = jugadorService.findById(jugadorId).get();
		CasillaParchis casillaFinal = partida.getCasillaFinal(ficha, dado);
		Boolean haComido = false;
		Boolean haMetidoFicha = false;

		if (dado == 6) {
			partida.setVecesSacado6(partida.getVecesSacado6() + 1);
			partida.setUltimoSacado6(true);
		}
		if (dado != 20 && dado != 10 && dado != 6) {
			partida.setUltimoSacado6(false);
		}

		if (partida.getVecesSacado6() == 3) {
			if (!partida.getUltimaFichaMovida().getCasillaActual().esPasillo()) {
				mandarFichaACasa(partida, partida.getUltimaFichaMovida()); // la ultima ficha en mover vuelve a casa
			}
			partida.addLog("Pierde turno por sacar 6 tres veces");
			partida.pasarTurno();
			return;
		}

	if(puedeComerFicha(casillaFinal,ficha)){
			partida.addLog("Se ha comido una ficha en la casilla " + ficha.getCasillaActual() + "! Se cuenta 20");

			comerFicha(casillaFinal, ficha, partida);
			jugador.setFichasComidas(jugador.getFichasComidas() + 1);
			partida.setDado(20);
			haComido=true;
		}

		fichaService.moverFichaParchis(ficha, casillaFinal, jugador);
		partida.addLog("Mueve una ficha hasta la casilla " + casillaFinal.getNumero());
		if(casillaFinal.esMeta()){
			partida.addLog("Ha metido una ficha en casa! Se cuenta 10");
			if(verificarFinalPartida(jugador)){
				finalizarPartidaParchis(partida,jugador);
			}
			partida.setDado(10);
			haMetidoFicha = true;
		}

		
		if(!haComido && !haMetidoFicha){
			if(dado!=6 && !partida.getUltimoSacado6()){
				partida.pasarTurno();			
			}
		partida.setDado(null);
		}


	}

	private void finalizarPartidaParchis(PartidaParchis partida, Jugador jugadorGanador) {
		partida.addLog("El jugador " +jugadorGanador.getColor() + " ha ganado la partida! ENHORABUENA!!");
		partida.setEstado(TipoEstadoPartida.TERMINADA);
		partida.setGanador(jugadorGanador.getUsuario());
		jugadorGanador.setEsGanador(true);
		partida.setFechaFinalizacion(LocalDateTime.now());
		Duration duracion =Duration.between(partida.getFechaCreacion(), partida.getFechaFinalizacion());
		Integer duracionInMinutes = (int)duracion.getSeconds() /60;
		partida.setDuracion(duracionInMinutes);
		for(Jugador j:partida.getJugadores()){
			j.finalizarPartidaParchis(duracionInMinutes);
		}
		try {
			logroService.actualizarLogrosParchis(partida);
		} catch (IllegalAccessException e) {
		}

		estadisticasGlobalesService.updateEstadisticasGlobalesPartidaParchis(partida);

	}

	private boolean verificarFinalPartida(Jugador jugador) {
		for (FichaParchis ficha : jugador.getFichasParchis()) {
			if (!ficha.isEstaEnLaMeta())
				return false;
		}
		return true;
	}

	public boolean puedeComerFicha(CasillaParchis casillaFinal, FichaParchis ficha) {
		switch (casillaFinal.getTipoCasillaParchis()) {
			case NORMAL:
				if (casillaFinal.getNumeroFichas() == 1 &&
						casillaFinal.getFichas().get(0).getColor() != ficha.getColor())
					return true;
				break;
			case INCIOAMARILLO:
				if (ficha.getColor() == Color.AMARILLO && casillaFinal.getNumeroFichas() == 2) {
					for (FichaParchis f : casillaFinal.getFichas()) {
						if (f.getColor() != ficha.getColor())
							return true;
					}
				}
				break;
			case INCIOAZUL:
				if (ficha.getColor() == Color.AZUL && casillaFinal.getNumeroFichas() == 2) {
					for (FichaParchis f : casillaFinal.getFichas()) {
						if (f.getColor() != ficha.getColor())
							return true;
					}
				}
				break;
			case INCIOROJO:
				if (ficha.getColor() == Color.ROJO && casillaFinal.getNumeroFichas() == 2) {
					for (FichaParchis f : casillaFinal.getFichas()) {
						if (f.getColor() != ficha.getColor())
							return true;
					}
				}
				break;
			case INCIOVERDE:
				if (ficha.getColor() == Color.VERDE && casillaFinal.getNumeroFichas() == 2) {
					for (FichaParchis f : casillaFinal.getFichas()) {
						if (f.getColor() != ficha.getColor())
							return true;
					}
				}
				break;
			default:
				return false;
		}

		return false;

	}

	@Transactional
	private void comerFicha(CasillaParchis casilla, FichaParchis ficha, PartidaParchis partida) {
		for (FichaParchis f : casilla.getFichas()) {
			if (f.getColor() != ficha.getColor()) {
				mandarFichaACasa(partida, f);
				break;
			}
		}
	}

	@Transactional
	public void mandarFichaACasa(PartidaParchis partida, FichaParchis ficha) {
		CasillaParchis casa = null;
		Jugador jugador = null;
		for (Jugador j : partida.getJugadores()) {
			if (j.getFichasParchis().contains(ficha)) {
				jugador = j;
				break;
			}
		}
		switch (ficha.getColor()) {
			case AMARILLO:
				casa = partida.getCasillaConNumero(101);
				break;
			case AZUL:
				casa = partida.getCasillaConNumero(102);
				break;
			case ROJO:
				casa = partida.getCasillaConNumero(103);
				break;
			case VERDE:
				casa = partida.getCasillaConNumero(104);
				break;
		}
		fichaService.moverFichaParchis(ficha, casa, jugador);

	}

	@Transactional
	public int tirarDado(PartidaParchis partida) {
		if (partida.getDado() == null) {
			partida.setDado((int)(Math.random()*6 +1));
			//partida.setDado(6);
			partida.addLog("Ha sacado " + partida.getDado());
		}

		return partida.getDado();
	}

	@Transactional(readOnly = true)
	public Optional<PartidaParchis> findParchisByCodigo(Integer codigo) {
		return this.partidaParchisRepository.findByCodigo(codigo);
	}

	@Transactional(readOnly = true)
	public Optional<PartidaOca> findOcaByCodigo(Integer codigo) {
		return this.partidaOcaRepository.findByCodigo(codigo);
	}

	@Transactional
    public void checkIntegridadPartidaParchis(PartidaParchis partida) {
		Long now = 	LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
		Long ultimoMov = partida.getFechaHoraUltimoMovimiento();
		if(ultimoMov == null){
			ultimoMov = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
		}
		Long diferencia = now-ultimoMov;
		if(diferencia>DURACION_TURNO_MILIS){
			partida.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
			partida.addLog("Ha perdido el turno por inactividad");
			partida.pasarTurno();
			
		}
    	
	}

	@Transactional
    public void checkIntegridadPartidaOca(PartidaOca partida) {
		Long now = 	LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
		Long ultimoMov = partida.getFechaHoraUltimoMovimiento();
		if(ultimoMov == null){
			ultimoMov = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
		}
		Long diferencia = now-ultimoMov;
		if(diferencia>DURACION_TURNO_MILIS){
			partida.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
			partida.addLog("Ha perdido el turno por inactividad");
			pasarTurnoOca(partida);
		}
	}


	@Transactional 
	public void iniciarPartidaOca(PartidaOca partidaOca){
		partidaOca.setEstado(TipoEstadoPartida.JUGANDO);
		partidaOca.setFechaCreacion(LocalDateTime.now());
		partidaOca.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
		this.saveOca(partidaOca);
	}

	@Transactional 
	public void iniciarPartidaParchis(PartidaParchis partida){
		partida.setEstado(TipoEstadoPartida.JUGANDO);
		partida.setFechaCreacion(LocalDateTime.now());
		partida.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
		this.saveParchis(partida);
	}
	public String redirigirPartida(Usuario usuario){
		Collection<Jugador> jugadoresUsuario = jugadorService.findAllJugadoresForUsuario(usuario.getId());
		String redireccion = "";
			for (Jugador jugador : jugadoresUsuario) {
			if (jugador.getPartidaOca() != null && jugador.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO) {
				var partidaActual = jugador.getPartidaOca().getId();
				
				redireccion=  "redirect:/partida/oca/" + partidaActual + "/jugar";
			} else if (jugador.getPartidaOca() != null && jugador.getPartidaOca().getEstado() == TipoEstadoPartida.CREADA) {
				var partidaActual = jugador.getPartidaOca().getId();
				
				redireccion=  "redirect:/partida/oca/" + partidaActual + "/espera";
			} else if (jugador.getPartidaParchis() != null && (jugador.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
				var partidaActual = jugador.getPartidaParchis().getId();
				
				redireccion=  "redirect:/partida/parchis/" + partidaActual + "/jugar";
			} else if (jugador.getPartidaParchis() != null && (jugador.getPartidaParchis().getEstado() == TipoEstadoPartida.CREADA)) {
				var partidaActual = jugador.getPartidaParchis().getId();
				
				redireccion=  "redirect:/partida/parchis" + partidaActual + "/espera";
			}}
			return redireccion;
	}
	@Transactional
	public String enviarMensajeOca (PartidaOca partida,String mensaje, Jugador jugador){
		partida.addMensaje(mensaje,jugador);
		this.saveOca(partida);
		return "redirect:/partida/oca/"+partida.getId()+"/jugar";
	}
	@Transactional
	public String enviarMensajeParchis(PartidaParchis partida,String mensaje, Jugador jugador){
		partida.addMensaje(mensaje,jugador);
		this.saveParchis(partida);
		return "redirect:/partida/parchis/"+partida.getId()+"/jugar";
	}
	
}
