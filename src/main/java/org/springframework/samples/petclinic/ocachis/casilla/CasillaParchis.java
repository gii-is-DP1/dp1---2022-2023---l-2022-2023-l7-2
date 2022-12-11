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

        //casa verde
        posiciones.put(104,new Coordenadas(60,510));
        //casa amarilla
        posiciones.put(101, new Coordenadas(517,510));
        //casa azul
        posiciones.put(102, new Coordenadas(517,56));
        //casa roja
        posiciones.put(103, new Coordenadas(60,56));
        
        //salida verde
        posiciones.put(56,new Coordenadas(145,407));
        //salida amarilla
        posiciones.put(5, new Coordenadas(400, 530));
        //salida roja
        posiciones.put(39, new Coordenadas(240,143));
        
        //1-8
        posiciones.put(1, new Coordenadas(400, 662));
        posiciones.put(2, new Coordenadas(400, 630));
        posiciones.put(3, new Coordenadas(400, 598));
        posiciones.put(4, new Coordenadas(400, 566));
        posiciones.put(6, new Coordenadas(400, 500));
        posiciones.put(7, new Coordenadas(400, 468));
        posiciones.put(8, new Coordenadas(400, 436));

        //9-16
         posiciones.put(9, new Coordenadas(240, 436));
        posiciones.put(10, new Coordenadas(212, 436));
        posiciones.put(11, new Coordenadas(180, 436));
        posiciones.put(12, new Coordenadas(145, 436));
        posiciones.put(13, new Coordenadas(114, 436));
        posiciones.put(14, new Coordenadas(79, 436));
        posiciones.put(15, new Coordenadas(45, 436));
        posiciones.put(16, new Coordenadas(15, 436));
        posiciones.put(17, new Coordenadas(15, 436));

        //18-25
        posiciones.put(18, new Coordenadas(400, 662));
        posiciones.put(19, new Coordenadas(400, 630));
        posiciones.put(20, new Coordenadas(400, 598));
        posiciones.put(21, new Coordenadas(400, 566));
        posiciones.put(22, new Coordenadas(400, 500));
        posiciones.put(23, new Coordenadas(400, 468));
        posiciones.put(24, new Coordenadas(400, 436));

        //26-33
        posiciones.put(26, new Coordenadas(400, 242));
        posiciones.put(27, new Coordenadas(400, 210));
        posiciones.put(28, new Coordenadas(400, 175));
        posiciones.put(29, new Coordenadas(400, 143));
        posiciones.put(30, new Coordenadas(400, 112));
        posiciones.put(31, new Coordenadas(400, 80));
        posiciones.put(32, new Coordenadas(400, 47));
        posiciones.put(33, new Coordenadas(400, 15));
        posiciones.put(34, new Coordenadas(320, 15));

        //35-42
        posiciones.put(35, new Coordenadas(240,15));
        posiciones.put(36, new Coordenadas(240,47));
        posiciones.put(37, new Coordenadas(240,80));
        posiciones.put(38, new Coordenadas(240,112));
        posiciones.put(40, new Coordenadas(240,175));
        posiciones.put(41, new Coordenadas(240,210));
        posiciones.put(42, new Coordenadas(240,242));

        //43-50
        posiciones.put(43, new Coordenadas(240, 242));
        posiciones.put(44, new Coordenadas(212, 242));
        posiciones.put(45, new Coordenadas(180, 242));
        posiciones.put(46, new Coordenadas(145, 242));
        posiciones.put(47, new Coordenadas(114, 242));
        posiciones.put(48, new Coordenadas(79, 242));
        posiciones.put(49, new Coordenadas(45, 242));
        posiciones.put(50, new Coordenadas(15, 242));
        posiciones.put(51, new Coordenadas(15, 325));

        //52-59
        posiciones.put(52,new Coordenadas(15,407));
        posiciones.put(53,new Coordenadas(45,407));
        posiciones.put(54,new Coordenadas(79,407));
        posiciones.put(55,new Coordenadas(114,407));
        posiciones.put(57,new Coordenadas(180,407));
        posiciones.put(58,new Coordenadas(212,407));
        posiciones.put(59,new Coordenadas(240,407));

        //60-67
        posiciones.put(60, new Coordenadas(248, 436));
        posiciones.put(61, new Coordenadas(248, 468));
        posiciones.put(62, new Coordenadas(248, 500));
        posiciones.put(63, new Coordenadas(248, 530));
        posiciones.put(64, new Coordenadas(248, 566));
        posiciones.put(65, new Coordenadas(248, 598));
        posiciones.put(66, new Coordenadas(248, 630));
        posiciones.put(67, new Coordenadas(248, 662));
        posiciones.put(68, new Coordenadas(324, 662));

        return posiciones;
	 }

     public Coordenadas getCoordenadas(){
        return coordenadas.getOrDefault(this.numero, new Coordenadas(0,0));
     }


     public String getOrientacion(){
        if(  (numero>= 1 && numero <=8) || (numero>= 26 && numero <=42) || (numero>= 60 && numero <=68))
            return "horizontal";
        else return "vertical";
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
