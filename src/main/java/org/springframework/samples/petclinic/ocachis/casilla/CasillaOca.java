package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @NotBlank
    private TipoCasillaOca tipoCasillaOca;

    @OneToMany(mappedBy="casillaActual")
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
            posiciones.put(10,new Coordenadas(609,567 )); //referencia
            posiciones.put(11,new Coordenadas(609,509));
            posiciones.put(12,new Coordenadas(609,451));
            posiciones.put(13,new Coordenadas(609,393));
            posiciones.put(14,new Coordenadas(609,335));
            posiciones.put(15,new Coordenadas(609,277));
            posiciones.put(16,new Coordenadas(609,219));
            posiciones.put(17,new Coordenadas(609,161));
            posiciones.put(18,new Coordenadas(609,110));


            
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


            posiciones.put(29,new Coordenadas(15,121));
            posiciones.put(30,new Coordenadas(15,177));
            posiciones.put(31,new Coordenadas(15,235));
            posiciones.put(32,new Coordenadas(15,293));
            posiciones.put(33,new Coordenadas(15,351));
            posiciones.put(34,new Coordenadas(15,409));
            posiciones.put(35,new Coordenadas(15,467));
            posiciones.put(36,new Coordenadas(15,512));


            posiciones.put(37,new Coordenadas(91,515));
            posiciones.put(38,new Coordenadas(142,515));
            posiciones.put(39,new Coordenadas(199,515));
            posiciones.put(40,new Coordenadas(256,515));
            posiciones.put(41,new Coordenadas(313,515));
            posiciones.put(42,new Coordenadas(370,515));
            posiciones.put(43,new Coordenadas(427,515));
            posiciones.put(44,new Coordenadas(484,515));


            posiciones.put(45,new Coordenadas(517,502));
            posiciones.put(46,new Coordenadas(517,454));
            posiciones.put(47,new Coordenadas(517,395));
            posiciones.put(48,new Coordenadas(517,336));
            posiciones.put(49,new Coordenadas(517,277));
            posiciones.put(50,new Coordenadas(517,218));


            posiciones.put(51,new Coordenadas(477,109));
            posiciones.put(52,new Coordenadas(417,109));
            posiciones.put(53,new Coordenadas(360,109));
            posiciones.put(54,new Coordenadas(302,109));
            posiciones.put(55,new Coordenadas(243,109));
            posiciones.put(56,new Coordenadas(195,109));


            posiciones.put(57,new Coordenadas(113,229));
            posiciones.put(58,new Coordenadas(113,293));
            posiciones.put(59,new Coordenadas(113,353));
            posiciones.put(60,new Coordenadas(113,410));


            posiciones.put(61,new Coordenadas(199,418));
            posiciones.put(62,new Coordenadas(251,418));

            posiciones.put(63,new Coordenadas(322,381));


        return posiciones;
	 }
}
