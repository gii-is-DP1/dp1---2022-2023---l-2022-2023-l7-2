package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class CasillaOca extends Casilla{
    public static Map<Integer,Coordenadas> coordenadas = inicializarMapa();

    private TipoCasillaOca tipoCasillaOca;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="casillaActual")
    private List<FichaOca> fichas;    


    public String getOrientacion(){
        if( (this.getNumero() >= 2 && this.numero <=8) || (numero >= 19 && numero <=28)
            || (numero >= 37 && numero <=44) || (numero >= 51 && numero <=56)
            || (numero >= 61 && numero <=62)) 
            return "vertical";
        else return "horizontal";
    }

    public Coordenadas getCoordenadas(){
        return coordenadas.get(numero);
    }

    public Integer getNumeroFichas(){
        return fichas.size();
    }

    private static Map<Integer,Coordenadas> inicializarMapa(){
		Map<Integer,Coordenadas> posiciones = new HashMap<>();


        //incremento 58px

            posiciones.put(1,new Coordenadas(82,659));
            posiciones.put(2,new Coordenadas(240,609)); //referencia
            posiciones.put(3,new Coordenadas(299,609));
            posiciones.put(4,new Coordenadas(358,609));
            posiciones.put(5,new Coordenadas(417,609));
            posiciones.put(6,new Coordenadas(476,609));
            posiciones.put(7,new Coordenadas(535,609));
            posiciones.put(8,new Coordenadas(584,609));

            posiciones.put(9,new Coordenadas(619,596));
            posiciones.put(10,new Coordenadas(619,547)); //referencia
            posiciones.put(11,new Coordenadas(619,489));
            posiciones.put(12,new Coordenadas(619,430));
            posiciones.put(13,new Coordenadas(619,373));
            posiciones.put(14,new Coordenadas(619,313));
            posiciones.put(15,new Coordenadas(619,263));
            posiciones.put(16,new Coordenadas(619,203));
            posiciones.put(17,new Coordenadas(619,147));
            posiciones.put(18,new Coordenadas(619,89));


            
            posiciones.put(19,new Coordenadas(580,14));
            posiciones.put(20,new Coordenadas(527,14));
            posiciones.put(21,new Coordenadas(471,14));
            posiciones.put(22,new Coordenadas(415,14));
            posiciones.put(23,new Coordenadas(359,14));
            posiciones.put(24,new Coordenadas(303,14));
            posiciones.put(25,new Coordenadas(247,14));
            posiciones.put(26,new Coordenadas(191,14));
            posiciones.put(27,new Coordenadas(135,14));
            posiciones.put(28,new Coordenadas(79,14));


            posiciones.put(29,new Coordenadas(15,101));
            posiciones.put(30,new Coordenadas(15,150));
            posiciones.put(31,new Coordenadas(15,209));
            posiciones.put(32,new Coordenadas(15,268));
            posiciones.put(33,new Coordenadas(15,325));
            posiciones.put(34,new Coordenadas(15,384));
            posiciones.put(35,new Coordenadas(15,443));
            posiciones.put(36,new Coordenadas(15,505));


            posiciones.put(37,new Coordenadas(84,515));
            posiciones.put(38,new Coordenadas(132,515));
            posiciones.put(39,new Coordenadas(193,515));
            posiciones.put(40,new Coordenadas(250,515));
            posiciones.put(41,new Coordenadas(307,515));
            posiciones.put(42,new Coordenadas(363,515));
            posiciones.put(43,new Coordenadas(421,515));
            posiciones.put(44,new Coordenadas(478,515));


            posiciones.put(45,new Coordenadas(517,492));
            posiciones.put(46,new Coordenadas(517,434));
            posiciones.put(47,new Coordenadas(517,375));
            posiciones.put(48,new Coordenadas(517,316));
            posiciones.put(49,new Coordenadas(517,257));
            posiciones.put(50,new Coordenadas(517,198));


            posiciones.put(51,new Coordenadas(477,109));
            posiciones.put(52,new Coordenadas(417,109));
            posiciones.put(53,new Coordenadas(360,109));
            posiciones.put(54,new Coordenadas(302,109));
            posiciones.put(55,new Coordenadas(243,109));
            posiciones.put(56,new Coordenadas(185,109));


            posiciones.put(57,new Coordenadas(113,209));
            posiciones.put(58,new Coordenadas(113,273));
            posiciones.put(59,new Coordenadas(113,333));
            posiciones.put(60,new Coordenadas(113,390));


            posiciones.put(61,new Coordenadas(189,418));
            posiciones.put(62,new Coordenadas(241,418));

            posiciones.put(63,new Coordenadas(322,381));


        return posiciones;
	 }

    public void quitarFicha(FichaOca ficha) {
        fichas.remove(ficha);
    }

    public void añadirFicha(FichaOca ficha) {
        getFichas().add(ficha);
    }
}
