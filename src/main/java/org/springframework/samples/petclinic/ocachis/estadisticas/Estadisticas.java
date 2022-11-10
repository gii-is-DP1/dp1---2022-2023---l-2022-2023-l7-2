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

	public Estadisticas() {
		this.parchisPartidasJugadas = 0;
		this.parchisPartidasGanadas = 0;
		this.parchisFichasComidas = 0;
		this.ocaPartidasJugadas = 0;
		this.ocaPartidasGanadas = 0;
		this.ocaVecesCaidoEnMuerte = 0;
		this.parchisDuracionTotal = new Time(0) ;
		this.parchisDuracionMinima = new Time(0);
		this.parchisDuracionMaxima = new Time(0);
		this.parchisDuracionMedia = new Time(0);
		this.ocaDuracionTotal = new Time(0);
		this.ocaDuracionMinima = new Time(0);
		this.ocaDuracionMaxima = new Time(0);
		this.ocaDuracionMedia = new Time(0);
	}

	private Integer parchisPartidasJugadas;

	private Integer parchisPartidasGanadas;

	private Time parchisDuracionTotal;

	private Time parchisDuracionMinima;

	private Time parchisDuracionMaxima;

	private Time parchisDuracionMedia;

	private Integer parchisFichasComidas;

	private Integer ocaPartidasJugadas;

	private Integer ocaPartidasGanadas;

	private Time ocaDuracionTotal;

	private Time ocaDuracionMinima;

	private Time ocaDuracionMaxima;

	private Time ocaDuracionMedia; 

	private Integer ocaVecesCaidoEnMuerte;
	
	
	
	@Override
	public String toString() {
		return "Estadisticas [parchisPartidasJugadas=" + parchisPartidasJugadas + ", parchisPartidasGanadas="
				+ parchisPartidasGanadas + ", parchisDuracionTotal=" + parchisDuracionTotal + ", parchisDuracionMinima="
				+ parchisDuracionMinima + ", parchisDuracionMaxima=" + parchisDuracionMaxima + ", parchisDuracionMedia="
				+ parchisDuracionMedia + ", parchisFichasComidas=" + parchisFichasComidas + ", ocaPartidasJugadas="
				+ ocaPartidasJugadas + ", ocaPartidasGanadas=" + ocaPartidasGanadas + ", ocaDuracionTotal="
				+ ocaDuracionTotal + ", ocaDuracionMinima=" + ocaDuracionMinima + ", ocaDuracionMaxima="
				+ ocaDuracionMaxima + ", ocaDuracionMedia=" + ocaDuracionMedia + ", ocaVecesCaidoEnMuerte="
				+ ocaVecesCaidoEnMuerte + "]";
	}
}
