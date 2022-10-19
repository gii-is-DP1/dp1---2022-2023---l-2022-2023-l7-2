package org.springframework.samples.petclinic.ocachis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import antlr.collections.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "Casilla")
public class Casilla extends Partida {
    
    @Column(name = "numero")
    @NotEmpty
    private Integer numero;

    public Integer getNumero(){
        return this.numero;
    }
    
    @ManyToOne
	@JoinColumn(name = "partida_id")
	private Partida partida;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "casilla")
    private List<FichaParchis> FichaParchis;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "casilla")
    private List<FichaOCa> FichaOCa;

}
