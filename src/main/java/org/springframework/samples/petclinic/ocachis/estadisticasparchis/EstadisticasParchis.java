package org.springframework.samples.petclinic.ocachis.estadisticasparchis;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class EstadisticasParchis extends Estadisticas{
    
    private int fichasComidas;

    public EstadisticasParchis(){
        super();
    }
    
}
