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

	public Integer parchisPartidasJugadas;

	public Integer parchisPartidasGanadas;

	public Integer parchisDuracionTotal;

	public Integer parchisDuracionMinima;

	public Integer parchisDuracionMaxima;

	public Integer parchisDuracionMedia;

	public Integer parchisFichasComidas;

	public Integer ocaPartidasJugadas;

	public Integer ocaPartidasGanadas;

	public Integer ocaDuracionTotal;

	public Integer ocaDuracionMinima;

	public Integer ocaDuracionMaxima;

	public Integer ocaDuracionMedia; 

	public Integer ocaVecesCaidoEnMuerte;
}
