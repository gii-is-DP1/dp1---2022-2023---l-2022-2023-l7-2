package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaParchisRepository extends CrudRepository<PartidaParchis, Integer> {

    Collection<PartidaParchis> findAll();

    PartidaParchis findById(int id);

    PartidaParchis save(PartidaParchis p);

}