package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    private PartidaOcaRepository partidaOcaRepository;
    private PartidaParchisRepository partidaParchisRepository;

    
    @Autowired
	public PartidaService(PartidaOcaRepository partidaOcaRepository, PartidaParchisRepository partidaParchisRepository){
		this.partidaOcaRepository = partidaOcaRepository;
        this.partidaParchisRepository = partidaParchisRepository;
	}

	public Collection<PartidaOca> findAllOca(){
		return partidaOcaRepository.findAll();
    }

    public Collection<PartidaParchis> findAllParchis(){
		return partidaParchisRepository.findAll();
    }
    public PartidaOca saveOca(PartidaOca p){
        return partidaOcaRepository.save(p);
    }
    public PartidaParchis saveParchis(PartidaParchis p){
        return partidaParchisRepository.save(p);
    }
}