package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void delete(Jugador j){
     jugadorRepository.delete(j);
    }

    @Transactional
    public Jugador save(Jugador j){
      return jugadorRepository.save(j);
    }
    @Transactional(readOnly=true)
    public Collection<Jugador> findAllJugadoresForUsuario(Integer usuarioId){
      return jugadorRepository.findAllJugadoresForUsuario(usuarioId);
    }
    @Transactional(readOnly = true)
    public Jugador findJugadorParchis(Integer usuarioId, Integer parchisId){
      return jugadorRepository.findJugadorParchis(usuarioId, parchisId);
    }
    @Transactional(readOnly = true)
    public Jugador findJugadorOca(Integer usuarioId, Integer ocaId){
      return jugadorRepository.findJugadorOca(usuarioId, ocaId);
    }
    
    
}
