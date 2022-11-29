package org.springframework.samples.petclinic.ocachis.ficha;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.Coordenadas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class FichaOca extends Ficha {
	private static final Coordenadas TRANSFORMACION_VERTICAL_1 = new Coordenadas(17,18);
	private static final Coordenadas TRANSFORMACION_VERTICAL_2 = new Coordenadas(0,33);
	private static final Coordenadas TRANSFORMACION_VERTICAL_3 = new Coordenadas(17,53);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL_1 = new Coordenadas(18,-17);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL_2 = new Coordenadas(33,0);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL_3 = new Coordenadas(53,-17);


	@ManyToOne
	private CasillaOca casillaActual;

	public Coordenadas getCoordenadas(){
		Coordenadas coordCasillaActual = this.getCasillaActual().getCoordenadas();
		Coordenadas result = new Coordenadas(coordCasillaActual.getX(),coordCasillaActual.getY());
		Integer posicionDentroDeLaCasilla = casillaActual.getFichas().indexOf(this);
		if(casillaActual.getOrientacion() == "vertical"){
			switch(posicionDentroDeLaCasilla){
				case(1):
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL_1);
				case(2):
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL_2);
				case(3):
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL_3);
				case(0):
			}
		}
		if(casillaActual.getOrientacion()=="horizontal"){
			switch(posicionDentroDeLaCasilla){
				case(1):
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL_1);
				case(2):
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL_2);
				case(3):
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL_3);
				case(0):
			}
		}
		return result;
	}

	@Override
	public String toString(){
		String result="";
		result +="FICHA: ";
		result += getColor().toString();
		result+= " Posicion en la casilla: " + casillaActual.getFichas().indexOf(this);
		result += " Coordenadas: " + getCoordenadas().getX() + " | " + getCoordenadas().getY();
		return result;
	}
}
