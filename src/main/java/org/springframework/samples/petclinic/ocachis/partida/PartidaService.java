package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    private PartidaRepository partidaRepository;
    
    @Autowired
	public PartidaService(PartidaRepository partidaRepo){
		this.partidaRepository = partidaRepo;
	}

	public Collection<Partida> findAll(){
		return partidaRepository.findAll();
    }

    public Partida save(Partida p){
        return partidaRepository.save(p);
    }
}