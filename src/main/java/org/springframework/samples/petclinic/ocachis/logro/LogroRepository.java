package org.springframework.samples.petclinic.ocachis.logro;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro,Integer>{

    @Query("SELECT logro FROM Logro logro where logro not in :logros")
    Collection<Logro> findLogrosNoCompletados(@Param ("logros") Collection<Logro> logros);

    @Query("SELECT u.logros FROM Usuario u where u.id=:id")
    Collection<Logro> findAllLogrosForUsuario(@Param("id") Integer usuarioId);

    Collection<Logro> findAll();

}
