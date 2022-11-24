package org.springframework.samples.petclinic.ocachis.ficha;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.Coordenadas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class FichaOca extends Ficha {

	@ManyToOne
	private CasillaOca casillaActual;


	public Coordenadas getCoordenadas(){
		return casillaActual.getCoordenadas();
	}
}
