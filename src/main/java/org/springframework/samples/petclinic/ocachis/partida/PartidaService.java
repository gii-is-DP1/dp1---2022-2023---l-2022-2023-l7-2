package org.springframework.samples.petclinic.ocachis.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
@Service
public class PartidaService {
    /* 
    private PartidaRepository partidaRepository;
    
    @Autowired
	public PartidaService(PartidaRepository partidaRepo){
		this.partidaRepository = partidaRepo;
	}

    @Transactional(readOnly = true)
	public Collection<Partida> findAll(){
		return partidaRepository.findAll();
	}
    
    @Transactional(readOnly = true)
	public Collection<Partida> findPartidasJugadas(){
		return partidaRepository.buscarPartidaTerminada();
	}

    @Transactional(readOnly = true)
	public Collection<Partida> findPartidasEnJuego(){
		return partidaRepository.buscarPartidaEnJuego();
	}
    */
}
