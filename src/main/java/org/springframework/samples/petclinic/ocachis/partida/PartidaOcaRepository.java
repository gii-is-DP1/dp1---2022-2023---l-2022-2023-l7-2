package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaOcaRepository extends CrudRepository<PartidaOca, Integer> {

    Collection<PartidaOca> findAll();

    PartidaOca findById(@Param("id") int id);

    PartidaOca save(PartidaOca p);
}
