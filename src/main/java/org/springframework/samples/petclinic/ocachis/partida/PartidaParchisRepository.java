package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaParchisRepository extends CrudRepository<PartidaParchis, Integer> {

    Collection<PartidaParchis> findAll();
    Partida findById(int id);
    @Query("SELECT pP FROM PartidaParchis pP WHERE pP.estado = :estado")
    List<PartidaParchis> getPartidas(@Param("estado") TipoEstadoPartida estado);
    Partida save(Partida p);
}