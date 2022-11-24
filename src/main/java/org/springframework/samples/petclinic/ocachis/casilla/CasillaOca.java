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


    public String getOrientacion(){
        if( (this.getNumero() >= 2 && numero <=8) || (numero >= 19 && numero <=28)
            || (numero >= 37 && numero <=44) || (numero >= 51 && numero <=56)
            || (numero >= 61 && numero <=62)) 
            return "vertical";
        else return "horizontal";
    }

    public Coordenadas getCoordenadas(){
        //switch DE 64 CASOS YUJÚUUUUU
        return null;
    }

    public Integer getNumeroFichas(){
        return fichas.size();
    }
}
