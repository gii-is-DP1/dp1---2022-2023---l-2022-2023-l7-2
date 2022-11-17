package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaOcaRepository extends CrudRepository<PartidaOca, Integer> {

    Collection<PartidaOca> findAll();
    @Query("SELECT pO FROM PartidaOca pO WHERE pO.estado = 0 OR pO.estado = 1")
    Collection<PartidaOca> findEsperaOca();
    PartidaOca findById(int id);
    PartidaOca save(PartidaOca p);
}