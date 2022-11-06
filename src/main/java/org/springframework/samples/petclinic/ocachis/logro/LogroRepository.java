package org.springframework.samples.petclinic.ocachis.logro;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro,Integer>{

    Collection<Logro> findAll();
    Logro findById(int id);
    Logro save (Logro l);
}
