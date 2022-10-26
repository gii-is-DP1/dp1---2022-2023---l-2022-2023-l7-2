package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CasillaOca extends Casilla{
    
    @NotBlank
    private TipoCasillaOca tipoCasillaOca;

    @OneToMany
    private List<FichaOca> fichas;
    
    
    
}
