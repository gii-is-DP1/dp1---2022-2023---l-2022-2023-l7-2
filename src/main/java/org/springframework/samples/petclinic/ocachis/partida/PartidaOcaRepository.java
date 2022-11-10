package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaOcaRepository extends CrudRepository<PartidaOca, Integer> {

    Collection<PartidaOca> findAll();
    Partida findById(int id);
    @Query("SELECT pO FROM PartidaOca pO WHERE pO.estado = :estado")
    List<PartidaOca> getPartidas(@Param("estado") TipoEstadoPartida estado);
    Partida save(Partida p);
}