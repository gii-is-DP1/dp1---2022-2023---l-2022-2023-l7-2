package org.springframework.samples.petclinic.ocachis.estadisticas;

import javax.persistence.Embeddable;
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
		this.parchisDuracionTotal = 0;
		this.parchisDuracionMinima = 0;
		this.parchisDuracionMaxima = 0;
		this.parchisDuracionMedia = 0;
		this.ocaDuracionTotal = 0;
		this.ocaDuracionMinima = 0;
		this.ocaDuracionMaxima = 0;
		this.ocaDuracionMedia = 0;
	}

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

