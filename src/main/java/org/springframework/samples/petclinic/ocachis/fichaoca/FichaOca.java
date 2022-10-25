package org.springframework.samples.petclinic.ocachis.fichaoca;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.ocachis.casillaoca.CasillaOca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="fichasoca")
public class FichaOca extends Ficha {
    
    @ManyToOne
	@JoinColumn(name = "casillaOca_id")
	private CasillaOca casillaOca;

    @ManyToOne
	@JoinColumn(name = "partidaOca_id")
	private PartidaOca partidaOca;
    
}
