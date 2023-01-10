package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaOcaRepository extends CrudRepository<PartidaOca, Integer> {

    Collection<PartidaOca> findAll();

    @Query("SELECT pO FROM PartidaOca pO WHERE pO.estado = 0 OR pO.estado = 1")
    Page<PartidaOca> findEsperaOca(Pageable p);
  
    @Query("SELECT oca FROM PartidaOca oca Where oca.codigoPartida=:codigo")
    Optional<PartidaOca> findByCodigo(@Param("codigo") int codigo);

    @Query("SELECT oca FROM PartidaOca oca")
    Page<PartidaOca> findAllPageable(Pageable p);

}