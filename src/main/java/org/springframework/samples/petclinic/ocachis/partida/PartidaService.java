package org.springframework.samples.petclinic.ocachis.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
@Service
public class PartidaService {
    
    private PartidaOcaRepository partidaOcaRepository;
	private PartidaParchisRepository partidaParchisRepository;
    
    @Autowired
	public PartidaService(PartidaOcaRepository partidaOcaRepository, PartidaParchisRepository partidaParchisRepository){
		this.partidaOcaRepository = partidaOcaRepository;
		this.partidaParchisRepository = partidaParchisRepository;
	}
   

	@Transactional(readOnly = true)
	public Collection<PartidaOca> findAllOca(){
		return this.partidaOcaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<PartidaParchis> findAllParchis(){
		return this.partidaParchisRepository.findAll();
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
}
