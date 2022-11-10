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

	private Integer parchisDuracionTotal;

	private Integer parchisDuracionMinima;

	private Integer parchisDuracionMaxima;

	private Integer parchisDuracionMedia;

	private Integer parchisFichasComidas;

	private Integer ocaPartidasJugadas;

	private Integer ocaPartidasGanadas;

	private Integer ocaDuracionTotal;

	private Integer ocaDuracionMinima;

	private Integer ocaDuracionMaxima;

	private Integer ocaDuracionMedia; 

	private Integer ocaVecesCaidoEnMuerte;
}
