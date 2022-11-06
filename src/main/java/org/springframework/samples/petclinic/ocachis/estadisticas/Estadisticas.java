package org.springframework.samples.petclinic.ocachis.estadisticas;

import java.sql.Time;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class Estadisticas{

	private Integer parchisPartidasJugadas;

	private Integer parchisPartidasGanadas;

	private Time parchisDuracionTotal;

	private Time parchisDuracionMinima;

	private Time parchisDuracionMaxima;

	private Time parchisDuracionMedia;

	private Integer parchisFichasComidas;

	@Positive
	private Integer ocaPartidasJugadas;

	private Integer ocaPartidasGanadas;

	private Time ocaDuracionTotal;

	private Time ocaDuracionMinima;

	private Time ocaDuracionMaxima;

	private Time ocaDuracionMedia; 

	private Integer ocaVecesCaidoEnMuerte;
}
