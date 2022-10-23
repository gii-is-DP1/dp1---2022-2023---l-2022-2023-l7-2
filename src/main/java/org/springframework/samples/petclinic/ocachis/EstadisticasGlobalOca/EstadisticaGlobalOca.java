package org.springframework.samples.petclinic.ocachis.EstadisticasGlobalOca;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name="estadisticaGlobalesOca")
public class EstadisticaGlobalOca extends EstadisticasGlobales {
    
}
