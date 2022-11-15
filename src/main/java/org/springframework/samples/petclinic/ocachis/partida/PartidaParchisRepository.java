package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaParchisRepository extends CrudRepository<PartidaParchis, Integer> {

    Collection<PartidaParchis> findAll();
    PartidaParchis findById(int id);
    
    @Query("SELECT parchis from PartidaParchis parchis where parchis.estado=0 OR parchis.estado=1")
    Collection<PartidaParchis> findEsperaParchis();
    
    PartidaParchis save(PartidaParchis p);

    
}