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
    Optional<PartidaParchis> findById(int id);
    @Query("SELECT parchis from PartidaParchis parchis where parchis.estado=0 OR parchis.estado=1")
    Collection<PartidaParchis> findEsperaParchis();
   
    @Query("SELECT parchis from PartidaParchis parchis where parchis.id=:id")
    PartidaParchis findByIdNoOptional(@Param("id") int id);
    @Query("SELECT parchis from PartidaParchis parchis where parchis.codigoPartida=:codigo")
    Optional<PartidaParchis> findByCodigo(@Param("codigo") int codigo);
    PartidaParchis save(PartidaParchis p);
    
}