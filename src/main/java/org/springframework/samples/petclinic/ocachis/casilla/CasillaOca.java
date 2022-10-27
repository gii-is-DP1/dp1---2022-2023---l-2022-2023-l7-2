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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="casillasoca")
public class CasillaOca extends Casilla{
    
    @Column(name="tipoCasillaOca")
    @NotBlank
    private TipoCasillaOca tipoCasillaOca;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casillaoca")
    private List<FichaOca> fichas;

    @ManyToOne
	@JoinColumn(name = "partidaOca_id")
	private PartidaOca partidaOca;

    
}
