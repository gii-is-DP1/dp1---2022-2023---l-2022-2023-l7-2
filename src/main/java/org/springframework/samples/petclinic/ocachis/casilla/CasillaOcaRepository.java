package org.springframework.samples.petclinic.ocachis.casilla;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CasillaOcaRepository extends CrudRepository<CasillaOca, Integer>{


    // @Query("SELECT c FROM casilla_oca c, partida_oca_casillas pc WHERE c.id = pc.casillas_id AND pc.partida_oca_id = :partidaOcaId")
	// Collection<CasillaOca> findAllForPartidaOca(@Param("partidaOcaId") Integer partidaOcaId);
    
  
}
