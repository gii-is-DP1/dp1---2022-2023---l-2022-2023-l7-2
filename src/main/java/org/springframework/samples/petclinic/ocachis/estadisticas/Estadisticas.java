package org.springframework.samples.petclinic.ocachis.estadisticas;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class Estadisticas{

	@Column
	private Integer parchisPartidasJugadas;

	@Column
	private Integer parchisPartidasGanadas;

	@Column
	private Time parchisDuracionTotal;

	@Column
	private Time parchisDuracionMinima;

	@Column
	private Time parchisDuracionMaxima;

	@Column
	private Time parchisDuracionMedia;

	@Column
	private Integer parchisFichasComidas;

	@Column
	private Integer ocaPartidasJugadas;

	@Column
	private Integer ocaPartidasGanadas;

	@Column
	private Time ocaDuracionTotal;

	@Column
	private Time ocaDuracionMinima;

	@Column
	private Time ocaDuracionMaxima;

	@Column
	private Time ocaDuracionMedia; 

	@Column
	private Integer ocaVecesCaidoEnMuerte;
}
