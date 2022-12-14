package org.springframework.samples.petclinic.ocachis.casilla;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasillaParchisRepository extends CrudRepository<CasillaParchis,Integer>{
    
}
