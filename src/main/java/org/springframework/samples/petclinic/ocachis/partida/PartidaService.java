package org.springframework.samples.petclinic.ocachis.partida;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
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

    
    @Autowired
	public PartidaService(PartidaOcaRepository partidaOcaRepository, PartidaParchisRepository partidaParchisRepository,
	FichaService fichaService){
		this.partidaOcaRepository = partidaOcaRepository;
        this.partidaParchisRepository = partidaParchisRepository;
		this.fichaService = fichaService;
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

	private CasillaOca funcionOca(PartidaOca partida, CasillaOca casillaInicial, List<String> log, Jugador j){
		
		CasillaOca casillaFinal = null;
		switch(casillaInicial.getNumero()){
			case 5:
				casillaFinal = partida.getCasillaConNumero(9);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 9");
				return casillaFinal;
			case 9:
				casillaFinal = partida.getCasillaConNumero(14);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 14");
				return casillaFinal;
			case 14:
				casillaFinal = partida.getCasillaConNumero(18);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 18");	
				return casillaFinal;
			case 18:
				casillaFinal = partida.getCasillaConNumero(23);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 23");
				return casillaFinal;
			case 23:
				casillaFinal = partida.getCasillaConNumero(27);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 27");
				return casillaFinal;
			case 27:
				casillaFinal = partida.getCasillaConNumero(32);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 32");
				return casillaFinal;
			case 32:
				casillaFinal = partida.getCasillaConNumero(36);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 36");
				return casillaFinal;
			case 36:
				casillaFinal = partida.getCasillaConNumero(41);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 41");
				return casillaFinal;
			case 41:
				casillaFinal = partida.getCasillaConNumero(45);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 45");
				return casillaFinal;
			case 45:
				casillaFinal = partida.getCasillaConNumero(50);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 50");
				return casillaFinal;
			case 50:
				casillaFinal = partida.getCasillaConNumero(54);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 54");
				return casillaFinal;
			case 54:
			
				casillaFinal = partida.getCasillaConNumero(59);
				log.add("'De oca en oca y tiro porque me toca' - El jugador " + j.getColor() + " va a la casilla 59");
				return casillaFinal;
			case 59:
				casillaFinal = partida.getCasillaConNumero(59);
				log.add("'Tiro porque me toca' - El jugador " + j.getColor() + " vuelve a tirar");
				return casillaFinal;
		}
		return casillaFinal;
	}

	public Boolean hayAlguienEnElPozo(PartidaOca partida){
		return partida.getCasillaConNumero(31).getFichas().size() > 0;
	}


	public void liberarFichasPozo(PartidaOca partida, List<String> log){
		List<Color> coloresEnElPozo = new ArrayList<Color>();
		CasillaOca pozo = partida.getCasillaConNumero(31);
		
		for(FichaOca ficha : pozo.getFichas()){
			coloresEnElPozo.add(ficha.getColor());
		}

		for(Jugador j: partida.getJugadores()){
			if(coloresEnElPozo.contains(j.getColor())) j.setNumTurnosBloqueadoRestantesOca(0);
			log.add("El jugador " + j.getColor() + " ha salido del pozo");
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
	}

	@Transactional
    public List<String> jugar(PartidaOca partida, FichaOca ficha, Jugador j) {
		List<String> log = new ArrayList<>();
		
		if(j.getNumTurnosBloqueadoRestantesOca()>0){
			j.setNumTurnosBloqueadoRestantesOca(j.getNumTurnosBloqueadoRestantesOca()-1);
			pasarTurno(partida);
			if(j.getNumTurnosBloqueadoRestantesOca()==0) log.add("El jugador " + j.getColor() + " ya no está bloqueado y puede volver a jugar");
			else if(j.getNumTurnosBloqueadoRestantesOca()==1)log.add("Al jugador " + j.getColor() + " le queda 1 turno bloqueado");
			else log.add("Al jugador " + j.getColor() + " le quedan "+ j.getNumTurnosBloqueadoRestantesOca() + " turnos bloqueados");
			return log;
		}

		Boolean volverATirar = false;
		CasillaOca casillaInicial =  ficha.getCasillaActual();

		Integer numeroCasillaInicial = casillaInicial.getNumero();

		int dado =(int) (Math.random()*6+1);
		log.add("El jugador " + j.getColor() + " ha sacado " + dado + " en el dado");

		Integer numeroCasillaInicialMasDado = numeroCasillaInicial + dado;
		if(numeroCasillaInicialMasDado > 63){
			int dif = numeroCasillaInicialMasDado -63;
			numeroCasillaInicialMasDado = 63-dif;
			log.add("El jugador " + j.getColor() + " ha rebotado hasta la casilla " + numeroCasillaInicialMasDado);
		}
		
		CasillaOca casillaInicialMasDado = partida.getCasillaConNumero(numeroCasillaInicialMasDado);
		CasillaOca casillaFinal = null;
		
		switch(casillaInicialMasDado.getTipoCasillaOca()){
			case NORMAL:
				casillaFinal = casillaInicialMasDado;
				break;
				
			case OCA: 
				casillaFinal = funcionOca(partida, casillaInicialMasDado, log, j);
				volverATirar = true;
				break;
			case PUENTE:
				casillaFinal = partida.getCasillaConNumero(19);
				j.setNumTurnosBloqueadoRestantesOca(1);
				log.add("El jugador " + j.getColor() + " ha caido en puente y va a la posada. Está 1 turno bloqueado");
				break;

			case POSADA:
				casillaFinal = casillaInicialMasDado;
				j.setNumTurnosBloqueadoRestantesOca(1);
				log.add("El jugador " + j.getColor() + " ha caido en la posada. Está 1 turno bloqueado");
				break;
				
			case LABERINTO: 
				casillaFinal = partida.getCasillaConNumero(30);
				log.add("El jugador " + j.getColor() + " ha caido en el laberinto y va a la casilla 30");
				break;

			case POZO:
				casillaFinal = casillaInicialMasDado;
				j.setNumTurnosBloqueadoRestantesOca(4);
				log.add("El jugador " + j.getColor() + " ha caido en el pozo. Está 4 turnos bloqueado o hasta que otro jugador pase por la casilla.");
				break;
				
			case DADOS: 
				casillaFinal = partida.getCasillaConNumero(numeroCasillaInicialMasDado + 8);
				log.add("El jugador " + j.getColor() + " ha caido en dados, avanza la suma de los digitos de la casilla dados");
				break;

			case MUERTE: 
				casillaFinal = partida.getCasillaConNumero(1);
				log.add("El jugador " + j.getColor() + " ha muerto ඞ. Vuelve a la casilla 1");
				break;
			
			case CARCEL:
				casillaFinal = casillaInicialMasDado;
				j.setNumTurnosBloqueadoRestantesOca(2);
				log.add("El jugador " + j.getColor() + " ha caido en la cárcel. Está 2 turnos bloqueado");
				break;

			case FINAL:
				break;
		}

		fichaService.moverFichaOca(ficha, casillaFinal, j);

		if(hayAlguienEnElPozo(partida)){
			Integer numeroCasillaFinal = casillaFinal.getNumero();
			if(numeroCasillaInicial < 31 && numeroCasillaFinal > 31){
				liberarFichasPozo(partida, log);
			}
		}
		


		if(!volverATirar){
			pasarTurno(partida);
		}
	return log;
	}		
}