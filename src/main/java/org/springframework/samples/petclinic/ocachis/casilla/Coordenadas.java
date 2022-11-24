package org.springframework.samples.petclinic.ocachis.casilla;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordenadas {
    private Integer x;
    private Integer y;

    public Coordenadas(Integer x, Integer y){
        this.x = x; 
        this.y = y;
    }
}
