package org.springframework.samples.petclinic.ocachis.ficha;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.Coordenadas;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaParchis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FichaParchis extends Ficha{


	private static final Coordenadas TRANSFORMACION_VERTICAL = new Coordenadas(0,30);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL = new Coordenadas(30,0);

	private static final Coordenadas TRANSFORMACION_CASA1 = new Coordenadas(100,0);
	private static final Coordenadas TRANSFORMACION_CASA2 = new Coordenadas(0,100);
	private static final Coordenadas TRANSFORMACION_CASA3 = new Coordenadas(100,100);

	private static final Coordenadas TRANSFORMACION_FINAL1 = new Coordenadas(24,0);
	private static final Coordenadas TRANSFORMACION_FINAL2 = new Coordenadas(0,24);
	private static final Coordenadas TRANSFORMACION_FINAL3 = new Coordenadas(24,24);
	
	private boolean estaEnCasa;

	private boolean estaEnLaMeta;

	@ManyToOne
	private CasillaParchis casillaActual;


	public Coordenadas getCoordenadas(){
		Coordenadas coordCasillaActual = this.getCasillaActual().getCoordenadas();
		Coordenadas result = new Coordenadas(coordCasillaActual.getX(),coordCasillaActual.getY());
		Integer posicionDentroDeLaCasilla = casillaActual.getFichas().indexOf(this);
		TipoCasillaParchis tipoCasilla = casillaActual.getTipoCasillaParchis();
		if(tipoCasilla == TipoCasillaParchis.CASAROJO || tipoCasilla == TipoCasillaParchis.CASAAMARILLO || tipoCasilla == TipoCasillaParchis.CASAVERDE || tipoCasilla == TipoCasillaParchis.CASAAZUL){
			switch(posicionDentroDeLaCasilla){
				case 0:
					break;
				case 1:
					result.sumarCoordenadas(TRANSFORMACION_CASA1);
					break;
				case 2:
					result.sumarCoordenadas(TRANSFORMACION_CASA2);
					break;
				case 3:
					result.sumarCoordenadas(TRANSFORMACION_CASA3);
					break;
			}
		}else if(tipoCasilla == TipoCasillaParchis.FINALROJO || tipoCasilla == TipoCasillaParchis.FINALAMARILLO || tipoCasilla == TipoCasillaParchis.FINALVERDE || tipoCasilla == TipoCasillaParchis.FINALAZUL){
			switch(posicionDentroDeLaCasilla){
				case 0:
					break;
				case 1:
					result.sumarCoordenadas(TRANSFORMACION_FINAL1);
					break;
				case 2:
					result.sumarCoordenadas(TRANSFORMACION_FINAL2);
					break;
				case 3:
					result.sumarCoordenadas(TRANSFORMACION_FINAL3);
					break;
			}
		}else if(tipoCasilla == TipoCasillaParchis.PASILLOROJO || tipoCasilla == TipoCasillaParchis.PASILLOAMARILLO && posicionDentroDeLaCasilla == 1){
			result.sumarCoordenadas(TRANSFORMACION_VERTICAL);
		}else if(tipoCasilla == TipoCasillaParchis.PASILLOVERDE || tipoCasilla == TipoCasillaParchis.PASILLOAZUL && posicionDentroDeLaCasilla == 1){
			result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL);
		}
		else{
			if(casillaActual.getOrientacion()=="horizontal" && posicionDentroDeLaCasilla == 1){
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL);
			}else if(casillaActual.getOrientacion()=="vertical" && posicionDentroDeLaCasilla == 1){
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL);
			}
		}
		return result;
	}
	

	public String toString(){
		String result="";
		result +="FICHA: ";
		result += getColor().toString();
		result+= " Posicion en la casilla: " + casillaActual.getFichas().indexOf(this);
		result += " Coordenadas: " + getCoordenadas().getX() + " | " + getCoordenadas().getY();
		return result;
	}

}
