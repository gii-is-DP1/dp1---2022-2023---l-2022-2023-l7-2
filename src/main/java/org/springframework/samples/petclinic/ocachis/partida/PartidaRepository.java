package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer> {

    Collection<Partida> findAll();
    Partida findById(int id);
    @Query("SELECT p FROM Partida p WHERE p.estado = :estado")
    List<Partida> getPartidas(@Param("estado") TipoEstadoPartida estado);
    Partida save(Partida p);
}