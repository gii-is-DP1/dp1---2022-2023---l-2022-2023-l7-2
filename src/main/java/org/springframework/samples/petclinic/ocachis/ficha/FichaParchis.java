package org.springframework.samples.petclinic.ocachis.ficha;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FichaParchis extends Ficha{
	
	private boolean estaEnCasa;

	private boolean estaEnLaMeta;

	@ManyToOne
	private CasillaParchis casillaActual;
}
