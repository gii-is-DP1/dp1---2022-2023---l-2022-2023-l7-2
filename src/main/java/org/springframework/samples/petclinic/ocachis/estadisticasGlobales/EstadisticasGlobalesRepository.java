package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadisticasGlobalesRepository extends CrudRepository<EstadisticasGlobales,Integer>{
    @Query("Select ea FROM EstadisticasGlobales ea WHERE ea.id = 1")
    EstadisticasGlobales get();

    EstadisticasGlobales save(EstadisticasGlobales est);
}
