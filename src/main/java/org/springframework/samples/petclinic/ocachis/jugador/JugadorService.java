package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.ficha.FichaService;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.ocachis.partida.exceptions.PartidaLlenaException;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorService {
    private JugadorRepository jugadorRepository;
    private UsuarioService usuarioService;
    private FichaService fichaService;

    
    @Autowired
	  public JugadorService(JugadorRepository jugadorRepository, UsuarioService usuarioService, FichaService fichaService){
	  	this.jugadorRepository = jugadorRepository;
      this.usuarioService = usuarioService;
      this.fichaService = fichaService;
	  }
    
    @Transactional(readOnly=true)
	  public Collection<Jugador> findAll(){
  		return jugadorRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Jugador findById(Integer id){
      Optional<Jugador> optJugador = jugadorRepository.findById(id);
      if(optJugador.isEmpty()) throw new ResourceNotFoundException("Jugador no encontrado");
		  return optJugador.get();
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

    
    @Transactional
    public Jugador createJugadorOca(PartidaOca partida) throws PartidaLlenaException {
      if(partida.getJugadores().size()==partida.getMaxJugadores()) throw new PartidaLlenaException();
      Jugador jugador = new Jugador();
      jugador.setUsuario(usuarioService.getLoggedUsuario());
			jugador.setPartidaOca(partida);
			jugador.setColor(getColorNuevoJugadorOca(partida));
      FichaOca ficha = this.fichaService.createFichaOca(jugador, partida);
      jugador.setFichaOca(ficha);
			jugador = save(jugador);
      return jugador;
    }
    
    public Color getColorNuevoJugadorOca(PartidaOca partida){
      List<Color> colores = new ArrayList<Color>();
      for (Jugador j : partida.getJugadores()) {
        colores.add(j.getColor());
      }

      if (!(colores.contains(Color.ROJO))) return Color.ROJO;
      else if (!(colores.contains(Color.AMARILLO))) return Color.AMARILLO;
      else if (!(colores.contains(Color.VERDE))) return Color.VERDE;
      else return Color.AZUL;
			
    }

    public Color getColorNuevoJugadorParchis(PartidaParchis partida){
      List<Color> colores = new ArrayList<Color>();
      for (Jugador j : partida.getJugadores()) {
        colores.add(j.getColor());
      }
      if (!(colores.contains(Color.ROJO))) return Color.ROJO;
      else if (!(colores.contains(Color.AMARILLO))) return Color.AMARILLO;
      else if (!(colores.contains(Color.VERDE))) return Color.VERDE;
      else return Color.AZUL;
			
    }

    @Transactional
    public Jugador createJugadorParchis(PartidaParchis partida) throws PartidaLlenaException {
      if(partida.getJugadores().size()==partida.getMaxJugadores()) throw new PartidaLlenaException();
      Jugador jugador = new Jugador();
      jugador.setUsuario(usuarioService.getLoggedUsuario());
			jugador.setPartidaParchis(partida);
			jugador.setColor(getColorNuevoJugadorParchis(partida));
      List<FichaParchis> fichas = this.fichaService.createFichasParchis(jugador, partida);
      jugador.setFichasParchis(fichas);
			jugador = save(jugador);
      return jugador;
    }

    public Boolean estaDentroPartida(Integer usuarioId) {
      Collection<Jugador> jugadores = this.findAllJugadoresForUsuario(usuarioId);
      for (Jugador j : jugadores) {
        if (j.getPartidaOca() != null && (j.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO)) {
            return true;
        } else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
            return true;
        }
      }
      return false;
    }

    public Map<String,Integer> findIdPartidaEspectar(Integer usuarioId) {
      Collection<Jugador> jugadores = this.findAllJugadoresForUsuario(usuarioId);
      Map<String,Integer> partidaEspectar = new HashMap<String,Integer>();
      for (Jugador j : jugadores) {
        if (j.getPartidaOca() != null && (j.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO)) {
           partidaEspectar.put("oca", j.getPartidaOca().getId());
        } else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
           partidaEspectar.put("parchis", j.getPartidaParchis().getId());
        }
      }
      return partidaEspectar;
    }


  @Transactional(readOnly = true)
	public Boolean estaJugando(Integer usuarioId) {
	  Integer numJugadoresOca = this.jugadorRepository.contarJugadoresOcaJugando(usuarioId);
    Integer numJugadoresParchis = this.jugadorRepository.contarJugadoresParchisJugando(usuarioId);
    if(numJugadoresOca + numJugadoresParchis ==0) return false;
    return true;
  }
}

