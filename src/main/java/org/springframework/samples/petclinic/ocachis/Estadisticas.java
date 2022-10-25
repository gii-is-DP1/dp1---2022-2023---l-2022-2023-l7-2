package org.springframework.samples.petclinic.ocachis;

import java.sql.Time;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;


@Getter
@Entity
public class Estadisticas {

	@NotEmpty
	private Integer parchisPartidasJugadas;

    @NotEmpty
	private Integer parchisPartidasGanadas;

	@NotEmpty
	private Time parchisDuracionTotal;

	@NotEmpty
	private Time parchisDuracionMinima;

	@NotEmpty
	private Time parchisDuracionMaxima;

	@NotEmpty
	private Time parchisDuracionMedia;

    @NotEmpty
	private Integer parchisFichasComidas;

    @NotEmpty
	private Integer ocaPartidasJugadas;

    @NotEmpty
	private Integer ocaPartidasGanadas;

	@NotEmpty
	private Time ocaDuracionTotal;

	@NotEmpty
	private Time ocaDuracionMinima;

	@NotEmpty
	private Time ocaDuracionMaxima;

	@NotEmpty
	private Time ocaDuracionMedia;

	@NotEmpty
	private Integer ocaVecesCaidoEnMuerte;
}
