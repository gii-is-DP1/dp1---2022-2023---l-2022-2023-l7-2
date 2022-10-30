package org.springframework.samples.petclinic.ocachis.partida;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;
@Repository
public interface PartidaOcaRepository extends CrudRepository<PartidaOca,Integer>{
    
    Collection<PartidaOca> findAll();
    

}
