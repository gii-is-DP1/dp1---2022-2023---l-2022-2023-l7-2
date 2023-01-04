package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EstadisticasGlobalesRepository extends CrudRepository<EstadisticasGlobales,Integer>{
    @Query("Select ea FROM EstadisticasGlobales ea WHERE ea.id = 1")
    EstadisticasGlobales get();
}
