package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

public class PartidaService {
    private PartidaRepository partidaRepository;
    private PartidaParchisRepository partidaParchisRepository;
    private PartidaOcaRepository partidaOcaRepository;
    
    @Autowired
	public PartidaService(PartidaRepository partidaRepo, PartidaParchisRepository partidaParchisRepo, PartidaOcaRepository partidaOcaRepo){
		this.partidaRepository = partidaRepo;
        this.partidaParchisRepository = partidaParchisRepo;
        this.partidaOcaRepository = partidaOcaRepo;
	}

	public Collection<Partida> findAll(){
		return partidaRepository.findAll();
    }

	public Collection<PartidaOca> findAllOca(){
		return this.partidaOcaRepository.findAll();
	}

	public Collection<PartidaParchis> findAllParchis(){
		return this.partidaParchisRepository.findAll();
	}

	public PartidaOca findPartidaOcaById(int id){
		return this.partidaOcaRepository.findById(id);
	}
	
	public PartidaParchis findPartidaParchisById(int id){
		return this.partidaParchisRepository.findById(id);
	}

    public List<Partida> findPartidaByEstado(String estado) {
        return partidaRepository.getPartidas(estado);
    }

    public Partida save(Partida p){
        return partidaRepository.save(p);
    }
}
