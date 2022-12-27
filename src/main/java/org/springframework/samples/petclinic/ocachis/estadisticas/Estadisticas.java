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

	public void updateEstadisticasOca(Integer duracion, Boolean esGanador, Integer vecesCaidoEnMuerte){
		this.ocaPartidasJugadas++;
		if(esGanador) this.ocaPartidasGanadas++;
		this.ocaDuracionTotal += duracion;
		if(duracion > this.ocaDuracionMaxima) this.ocaDuracionMaxima = duracion;
		if(duracion < this.ocaDuracionMinima || this.ocaDuracionMinima == 0) this.ocaDuracionMinima = duracion;
		this.ocaDuracionMedia = this.ocaDuracionTotal / this.ocaPartidasJugadas;
		this.ocaVecesCaidoEnMuerte += vecesCaidoEnMuerte;
	}
	
	

    public void updateEstadisticasParchis(Integer duracion, Boolean esGanador, Integer fichasComidas) {
		this.parchisPartidasJugadas++;
		if(esGanador) this.parchisPartidasGanadas++;
		this.parchisDuracionTotal += duracion;
		if(duracion > this.parchisDuracionMaxima) this.parchisDuracionMaxima = duracion;
		if(duracion < this.parchisDuracionMinima || this.parchisDuracionMinima == 0) this.parchisDuracionMinima = duracion;
		this.parchisDuracionMedia = this.parchisDuracionTotal / this.parchisPartidasJugadas;
		this.parchisFichasComidas += fichasComidas;
    }



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

