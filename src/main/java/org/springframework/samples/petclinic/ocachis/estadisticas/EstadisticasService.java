package org.springframework.samples.petclinic.ocachis.estadisticas;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasService {
    
private JugadorRepository jugadorRepository;

@Autowired
public EstadisticasService(JugadorRepository jugadorRepository){
    this.jugadorRepository = jugadorRepository;

}

public Estadisticas getEstadisticasUsuario(Integer usuarioId){
    Collection<Jugador> jugadores = this.jugadorRepository.findAllJugadoresForUsuario(usuarioId);
    Estadisticas estadisticaUsuario = new Estadisticas();
    estadisticaUsuario.parchisPartidasJugadas = (int) jugadores.stream().filter(j->j.getPartidaParchis()!=null).count();
    estadisticaUsuario.parchisPartidasGanadas = (int) jugadores.stream().filter(j->j.getPartidaParchis()!=null && j.getEsGanador()).count();
    estadisticaUsuario.parchisFichasComidas = (int) jugadores.stream().filter(j->j.getPartidaParchis()!=null).mapToInt(j->j.getFichasComidas()).sum();
    estadisticaUsuario.ocaPartidasJugadas = (int) jugadores.stream().filter(j->j.getPartidaOca()!=null).count();
    estadisticaUsuario.ocaPartidasGanadas = (int) jugadores.stream().filter(j->j.getPartidaOca()!=null && j.getEsGanador()).count();
    estadisticaUsuario.ocaVecesCaidoEnMuerte = jugadores.stream().filter(j->j.getPartidaOca()!=null).mapToInt(j->j.getVecesCaidoEnMuerte()).sum();
    return estadisticaUsuario;


}





}
