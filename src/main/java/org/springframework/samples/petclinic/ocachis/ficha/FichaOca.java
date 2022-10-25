package org.springframework.samples.petclinic.ocachis.ficha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="fichasoca")
public class FichaOca extends Ficha {

	@Column(name="casillaActual")
	private CasillaOca casillaActual;
    
    @ManyToOne
	@JoinColumn(name = "casillaOca_id")
	private CasillaOca casillaOca;

    @ManyToOne
	@JoinColumn(name = "partidaOca_id")
	private PartidaOca partidaOca;
    
}
