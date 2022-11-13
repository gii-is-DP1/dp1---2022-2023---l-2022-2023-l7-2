package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer>{
    Collection<Jugador> findAll();
    Jugador findById(int id);
    Jugador save(Jugador j);
}
