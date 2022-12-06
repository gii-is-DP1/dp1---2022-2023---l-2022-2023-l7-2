package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
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

    @Transactional
    public Jugador save(Jugador j){
      return jugadorRepository.save(j);
    }

    
}
