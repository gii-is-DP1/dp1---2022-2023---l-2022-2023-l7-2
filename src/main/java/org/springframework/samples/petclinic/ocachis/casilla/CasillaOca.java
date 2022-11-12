package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class CasillaOca extends Casilla{
    
    @NotBlank
    private TipoCasillaOca tipoCasillaOca;

    @OneToMany(mappedBy="casillaActual")
    private List<FichaOca> fichas;    
}