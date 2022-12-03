package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


	@Transactional
    public void jugar(PartidaOca partida, FichaOca ficha) {
		
		CasillaOca casillaInicial =  ficha.getCasillaActual();
		Integer numeroCasillaInicial = casillaInicial.getNumero();
		int dado =(int) (Math.random()*6+1);
		Integer numeroCasillaFinal = numeroCasillaInicial + dado;

		CasillaOca casillaFinal = partida.getCasillaConNumero(numeroCasillaFinal);

		// casillaInicial.quitarFicha(ficha);
		// casillaFinal.añadirFicha(ficha);
		// casillaInicial = casillaService.saveCasillaOca(casillaInicial);
		// casillaFinal = casillaService.saveCasillaOca(casillaFinal);
		fichaService.moverFichaOca(ficha, casillaInicial,  casillaFinal);
		// casillaFinal.setFichas(null);
		// ficha.setCasillaActual(casillaFinal);


		switch(partida.getColorJugadorActual()){
			case ROJO:
				partida.setColorJugadorActual(Color.AMARILLO);
				break;
			case AMARILLO:
			partida.setColorJugadorActual(Color.VERDE);
				break;
			case VERDE:
			partida.setColorJugadorActual(Color.AZUL);
				break;
			case AZUL:
			partida.setColorJugadorActual(Color.ROJO);
				break;
		}
		//partida.setColorJugadorActual(Color.ROJO);
	
		
    }

}
