package org.springframework.samples.petclinic.ocachis.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
    
    Collection<Usuario> findAll();

    
}
