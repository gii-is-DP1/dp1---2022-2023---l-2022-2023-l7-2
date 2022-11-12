package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {
    private JugadorRepository jugadorRepository;

    
    @Autowired
	public JugadorService(JugadorRepository jugadorRepository){
		this.jugadorRepository = jugadorRepository;
	}

	public Collection<Jugador> findAll(){
		return jugadorRepository.findAll();
    }

    public Optional<Jugador> findById(Integer id){
		return jugadorRepository.findById(id);
    }
    
    public Jugador save(Jugador j){
        return jugadorRepository.save(j);
    }
}
