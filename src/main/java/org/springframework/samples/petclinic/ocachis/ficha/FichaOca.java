package org.springframework.samples.petclinic.ocachis.ficha;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.Coordenadas;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class FichaOca extends Ficha {
	private static final Coordenadas TRANSFORMACION_VERTICAL_1 = new Coordenadas(18,18);
	private static final Coordenadas TRANSFORMACION_VERTICAL_2 = new Coordenadas(0,30);
	private static final Coordenadas TRANSFORMACION_VERTICAL_3 = new Coordenadas(18,48);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL_1 = new Coordenadas(18,-18);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL_2 = new Coordenadas(30,0);
	private static final Coordenadas TRANSFORMACION_HORIZONTAL_3 = new Coordenadas(48,-18);

	@ManyToOne(optional = false)
	private CasillaOca casillaActual;

	public FichaOca(){
	};

	public FichaOca(Color color, CasillaOca casillaActual) {
		this.color = color;
		this.casillaActual = casillaActual;
    }

    public Coordenadas getCoordenadas(){
		Coordenadas coordCasillaActual = this.getCasillaActual().getCoordenadas();
		Coordenadas result = new Coordenadas(coordCasillaActual.getX(),coordCasillaActual.getY());
		Integer posicionDentroDeLaCasilla = casillaActual.getFichas().indexOf(this);
		if(casillaActual.getOrientacion() == "vertical"){
			switch(posicionDentroDeLaCasilla){
				case(0):
				return result;
				case(1):
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL_1);
				return result;
				case(2):
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL_2);
				return result;
				case(3):
				result.sumarCoordenadas(TRANSFORMACION_VERTICAL_3);
				return result;
			}
		}
		if(casillaActual.getOrientacion()=="horizontal"){
			switch(posicionDentroDeLaCasilla){
				case(0):
				return result;
				case(1):
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL_1);
				return result;
				case(2):
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL_2);
				return result;
				case(3):
				result.sumarCoordenadas(TRANSFORMACION_HORIZONTAL_3);
				return result;
				
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
