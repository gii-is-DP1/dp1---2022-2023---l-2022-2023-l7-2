package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaParchisRepository extends CrudRepository<PartidaParchis,Integer> {
    
    
    Collection<PartidaParchis> findAll();
    
    /*
    @Query("SELECT partida FROM Partida partida WHERE partida.estado = 'JUGANDO'")
    Collection<Partida> buscarPartidaEnJuego();
    
    @Query("SELECT partida FROM Partida partida WHERE partida.estado = 'TERMINADA'")
    Collection<Partida> buscarPartidaTerminada();*/

    
}