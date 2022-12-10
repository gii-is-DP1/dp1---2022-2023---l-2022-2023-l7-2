package org.springframework.samples.petclinic.ocachis.partida;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaService;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaService;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import antlr.collections.List;
@Service
public class PartidaService {
	private PartidaOcaRepository partidaOcaRepository;
    private PartidaParchisRepository partidaParchisRepository;
	private FichaService fichaService;
	private CasillaService casillaService;

    
    @Autowired
	public PartidaService(PartidaOcaRepository partidaOcaRepository, PartidaParchisRepository partidaParchisRepository,
	FichaService fichaService, CasillaService casillaService){
		this.partidaOcaRepository = partidaOcaRepository;
        this.partidaParchisRepository = partidaParchisRepository;
		this.fichaService = fichaService;
		this.casillaService = casillaService;
	}


	
	public void inicializarCasillasOca(PartidaOca partida){
		List<Integer> numerosCasillasOca = new ArrayList<>();
		numerosCasillasOca.add(5); numerosCasillasOca.add(9); numerosCasillasOca.add(14);
		numerosCasillasOca.add(18); numerosCasillasOca.add(23); numerosCasillasOca.add(27);
		numerosCasillasOca.add(32); numerosCasillasOca.add(36); numerosCasillasOca.add(41);
		numerosCasillasOca.add(45); numerosCasillasOca.add(50); numerosCasillasOca.add(54);
		numerosCasillasOca.add(59);
		List<CasillaOca> casillas = new ArrayList<CasillaOca>();
		
		for(int i = 1; i<=63; i++){
			CasillaOca casilla = new CasillaOca();
			casilla.setNumero(i);
			casilla.setFichas(new ArrayList<FichaOca>());
			if(numerosCasillasOca.contains(i)) casilla.setTipoCasillaOca(TipoCasillaOca.OCA);
			else if(i == 6 || i==12) casilla.setTipoCasillaOca(TipoCasillaOca.PUENTE);
			else if(i == 19) casilla.setTipoCasillaOca(TipoCasillaOca.POSADA);
			else if(i == 26 || i==53) casilla.setTipoCasillaOca(TipoCasillaOca.DADOS);
			else if(i == 31) casilla.setTipoCasillaOca(TipoCasillaOca.POZO);
			else if(i == 42) casilla.setTipoCasillaOca(TipoCasillaOca.LABERINTO);
			else if(i == 52) casilla.setTipoCasillaOca(TipoCasillaOca.CARCEL);
			else if(i == 58) casilla.setTipoCasillaOca(TipoCasillaOca.MUERTE);
			else if(i == 63) casilla.setTipoCasillaOca(TipoCasillaOca.FINAL);
			else casilla.setTipoCasillaOca(TipoCasillaOca.NORMAL);
			casilla = casillaService.saveCasillaOca(casilla);
			casillas.add(casilla);
		}

		partida.setCasillas(casillas);
	}

	@Transactional
	public PartidaOca crearPartidaOca(Integer maxJugadores){
		PartidaOca partida = new PartidaOca();
		partida.setMaxJugadores(maxJugadores);
		partida.setCodigoPartida(Partida.getNuevoCodigoPartida());
		partida.setColorJugadorActual(Color.ROJO);
		partida.setEstado(TipoEstadoPartida.CREADA);
		partida.setJugadores(new ArrayList<Jugador>());
		partida.setUsuariosObservadores(new ArrayList<>());
		inicializarCasillasOca(partida);

		partida = partidaOcaRepository.save(partida);
		return partida;
	}

	@Transactional(readOnly = true)
	public Collection<PartidaOca> findAllOca(){
		return this.partidaOcaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<PartidaParchis> findAllParchis(){
		return this.partidaParchisRepository.findAll();
	}

    public Collection<PartidaOca> findEsperaOca(){
        return partidaOcaRepository.findEsperaOca();
    }
    public Collection<PartidaParchis> findEsperaParchis(){
        return partidaParchisRepository.findEsperaParchis();
    }
    public PartidaOca findByIdOca(int id){
        return partidaOcaRepository.findById(id);
    }
   
    public PartidaParchis findByIdParchis(int id){
        return partidaParchisRepository.findById(id);
    }
    public PartidaOca saveOca(PartidaOca p){
        return partidaOcaRepository.save(p);
    }
    public PartidaParchis saveParchis(PartidaParchis p){
        return partidaParchisRepository.save(p);
    }

	@Transactional
	public void borrarPartidaOca(int id){
		this.partidaOcaRepository.deleteById(id);
	}

	@Transactional
	public void borrarPartidaParchis(int id){
		this.partidaParchisRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public PartidaOca findPartidaOcaById(int id){
		return this.partidaOcaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public PartidaParchis findPartidaParchisById(int id){
		return this.partidaParchisRepository.findById(id);
	}

	private CasillaOca funcionOca(PartidaOca partida, CasillaOca casillaInicial, Jugador j){
		
		CasillaOca casillaFinal = null;
		switch(casillaInicial.getNumero()){
			case 5:
				casillaFinal = partida.getCasillaConNumero(9);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 9");
				return casillaFinal;
			case 9:
				casillaFinal = partida.getCasillaConNumero(14);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 14");
				return casillaFinal;
			case 14:
				casillaFinal = partida.getCasillaConNumero(18);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 18");	
				return casillaFinal;
			case 18:
				casillaFinal = partida.getCasillaConNumero(23);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 23");
				return casillaFinal;
			case 23:
				casillaFinal = partida.getCasillaConNumero(27);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 27");
				return casillaFinal;
			case 27:
				casillaFinal = partida.getCasillaConNumero(32);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 32");
				return casillaFinal;
			case 32:
				casillaFinal = partida.getCasillaConNumero(36);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 36");
				return casillaFinal;
			case 36:
				casillaFinal = partida.getCasillaConNumero(41);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 41");
				return casillaFinal;
			case 41:
				casillaFinal = partida.getCasillaConNumero(45);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 45");
				return casillaFinal;
			case 45:
				casillaFinal = partida.getCasillaConNumero(50);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 50");
				return casillaFinal;
			case 50:
				casillaFinal = partida.getCasillaConNumero(54);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 54");
				return casillaFinal;
			case 54:
			
				casillaFinal = partida.getCasillaConNumero(59);
				partida.addLog("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 59");
				return casillaFinal;
			case 59:
				casillaFinal = partida.getCasillaConNumero(59);
				partida.addLog("'Tiro porque me toca' - El jugador " + j.getColor() + " vuelve a tirar");
				return casillaFinal;
		}
		return casillaFinal;
	}

	public Boolean hayAlguienEnElPozo(PartidaOca partida){
		return partida.getCasillaConNumero(31).getFichas().size() > 0;
	}

	public void liberarFichasPozo(PartidaOca partida){
		List<Color> coloresEnElPozo = new ArrayList<Color>();
		CasillaOca pozo = partida.getCasillaConNumero(31);
		
		for(FichaOca ficha : pozo.getFichas()){
			coloresEnElPozo.add(ficha.getColor());
		}

		for(Jugador j: partida.getJugadores()){
			if(coloresEnElPozo.contains(j.getColor())) j.setNumTurnosBloqueadoRestantesOca(0);
			partida.addLog("El jugador " + j.getColor() + " ha salido del pozo");
		}
	
	}

	public void pasarTurno(PartidaOca partida){
		switch(partida.getColorJugadorActual()){
			case ROJO:
				partida.setColorJugadorActual(Color.AMARILLO);
				
				break;
			case AMARILLO:
				if(partida.getJugadores().size()==2) partida.setColorJugadorActual(Color.ROJO);
				else partida.setColorJugadorActual(Color.VERDE);
				break;
			case VERDE:
				if(partida.getJugadores().size()==3) partida.setColorJugadorActual(Color.ROJO);
				else partida.setColorJugadorActual(Color.AZUL);
				break;
			case AZUL:
				partida.setColorJugadorActual(Color.ROJO);
				break;
		}
		partida.addLog("TURNO DEL JUGADOR " + partida.getColorJugadorActual());
	}

	@Transactional
    public void jugarOca(PartidaOca partida, FichaOca ficha, Jugador j) {
			
		
		if(j.getNumTurnosBloqueadoRestantesOca()>0){
			j.setNumTurnosBloqueadoRestantesOca(j.getNumTurnosBloqueadoRestantesOca()-1);
			if(j.getNumTurnosBloqueadoRestantesOca()==0) partida.addLog("El jugador " + j.getColor() + " ya no está bloqueado y puede volver a jugar");
			else if(j.getNumTurnosBloqueadoRestantesOca()==1) partida.addLog("Al jugador " + j.getColor() + " le queda 1 turno bloqueado");
			else partida.addLog("Al jugador " + j.getColor() + " le quedan "+ j.getNumTurnosBloqueadoRestantesOca() + " turnos bloqueados");
			pasarTurno(partida);
			return;
		}

		Boolean volverATirar = false;
		CasillaOca casillaInicial =  ficha.getCasillaActual();

		Integer numeroCasillaInicial = casillaInicial.getNumero();

		int dado =(int) (Math.random()*6+1);
		partida.addLog("El jugador " + j.getColor() + " ha sacado " + dado + " en el dado");

		Integer numeroCasillaInicialMasDado = numeroCasillaInicial + dado;
		if(numeroCasillaInicialMasDado > 63){
			int dif = numeroCasillaInicialMasDado -63;
			numeroCasillaInicialMasDado = 63-dif;
			partida.addLog("El jugador " + j.getColor() + " ha rebotado hasta la casilla " + numeroCasillaInicialMasDado);
		}
		
		CasillaOca casillaInicialMasDado = partida.getCasillaConNumero(numeroCasillaInicialMasDado);
		CasillaOca casillaFinal = null;
		
		switch(casillaInicialMasDado.getTipoCasillaOca()){
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
				partida.addLog("El jugador " + j.getColor() + " ha caido en puente y va a la posada. Está 1 turno bloqueado");
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
				partida.addLog("El jugador " + j.getColor() + " ha caido en el pozo. Está 4 turnos bloqueado o hasta que otro jugador pase por la casilla.");
				break;
				
			case DADOS: 
				casillaFinal = partida.getCasillaConNumero(numeroCasillaInicialMasDado + 8);
				partida.addLog("El jugador " + j.getColor() + " ha caido en dados, avanza la suma de los digitos de la casilla dados");
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
				finalizarPartidaOca(partida,j);
				
				partida.addLog("El jugador " + j.getColor() + " ha ganado la partida");
		}

		fichaService.moverFichaOca(ficha, casillaFinal, j);

		if(hayAlguienEnElPozo(partida)){
			Integer numeroCasillaFinal = casillaFinal.getNumero();
			if(numeroCasillaInicial < 31 && numeroCasillaFinal > 31){
				liberarFichasPozo(partida);
			}
		}
		
		if(!volverATirar){
			pasarTurno(partida);
		}
	}		

	public void finalizarPartidaOca(PartidaOca partida, Jugador jugadorGanador){
		partida.setGanador(jugadorGanador.getUsuario());
		partida.setFechaFinalizacion(LocalDateTime.now());
		Duration duracion =Duration.between(partida.getFechaCreacion(), partida.getFechaFinalizacion());
		Integer duracionInMinutes = (int)duracion.getSeconds() /60;
		partida.setDuracion(duracionInMinutes);
	}

	public int TirarNumDado(){
		int numDado =(int) (Math.random()*6 +1);
		return numDado;
	}

	
}