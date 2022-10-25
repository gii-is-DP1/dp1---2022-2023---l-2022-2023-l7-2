package org.springframework.samples.petclinic.ocachis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import antlr.collections.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "Casilla")
public class Casilla extends BaseEntity {
    
    @NotEmpty
    private Integer numero;

    @NotEmpty
    private boolean fichas;
    
    @ManyToOne
	private Partida partida;

    @OneToMany
    private List<FichaParchis> FichaParchis;

    @OneToMany
    private List<FichaOCa> FichaOCa;

}
