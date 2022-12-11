package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CasillaParchis extends Casilla{

    private static Map<Integer,Coordenadas> coordenadas = inicializarMapa();

    private TipoCasillaParchis tipoCasillaParchis;

    @OneToMany(mappedBy="casillaActual")
    private List<FichaParchis> fichas;

	private Boolean bloqueada;
    
    private static Map<Integer,Coordenadas> inicializarMapa(){
		Map<Integer,Coordenadas> posiciones = new HashMap<>();
        posiciones.put(104,new Coordenadas(82,400));
        posiciones.put(101, new Coordenadas(1,1));
        posiciones.put(102, new Coordenadas(100,100));
        posiciones.put(103, new Coordenadas(150,1));
        return posiciones;
	 }

     public Coordenadas getCoordenadas(){
        return coordenadas.getOrDefault(this.numero, new Coordenadas(0,0));
     }


     public String getOrientacion(){
        if(  (numero<= 1 && numero <=8) || (numero<= 26 && numero <=42) || (numero<= 60 && numero <=68))
            return "vertical";
        else return "horizontal";
    }
    public Boolean esMeta(){
       if(tipoCasillaParchis == TipoCasillaParchis.CASAROJO || 
       tipoCasillaParchis == TipoCasillaParchis.CASAAMARILLO || 
       tipoCasillaParchis == TipoCasillaParchis.CASAVERDE || 
       tipoCasillaParchis == TipoCasillaParchis.CASAAZUL)
        return true;
        return false;
    }
}
