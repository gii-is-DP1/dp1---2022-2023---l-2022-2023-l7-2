package org.springframework.samples.petclinic.ocachis.partida;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

@Repository
public interface PartidaRepository{
    
    /* 
    Collection<Partida> findAll();
    
    @Query("SELECT partida FROM Partida partida WHERE partida.estado = 'JUGANDO'")
    Collection<Partida> buscarPartidaEnJuego();
    
    @Query("SELECT partida FROM Partida partida WHERE partida.estado = 'TERMINADA'")
    Collection<Partida> buscarPartidaTerminada();*/

}
