
package  org.springframework.samples.petclinic.ocachis.jugador;


import java.util.Collection;
import java.util.stream.Collectors;
import java.util.ArrayList; 
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Jugador  extends BaseEntity{ 
    
private Color color;


//Estadisticas//

@ManyToOne
private Usuario usuario;

private Integer fichasComidas=0;

private Integer vecesCaidoEnMuerte=0;	

private Boolean esGanador=false;

private Integer numTurnosBloqueadoRestantesOca = 0;


//Fichas//
@OneToOne(cascade = CascadeType.ALL, optional = true)
private FichaOca fichaOca;

@OneToMany(cascade = CascadeType.ALL)

private Collection<FichaParchis> fichasParchis;


//Partida//
@ManyToOne(optional = true)
private PartidaParchis partidaParchis;

@ManyToOne(optional = true)
private PartidaOca partidaOca;


@Override
public String toString(){
    return "usuarioId: " + usuario.getId() + " color: " + color.toString();
}

    public void addFichaParchis(FichaParchis f){
        this.fichasParchis.add(f);
    }

    public void deleteFichaParchis(FichaParchis f){
        this.fichasParchis.remove(f);
        f.getCasillaActual().quitarFicha(f);
    }

    public void finalizarPartidaOca(Integer duracion) {
        this.usuario.actualizarEstadisticasOca(duracion, this.esGanador, this.vecesCaidoEnMuerte);
    }

    public List<FichaParchis> getFichasQuePuedenMoverse(Integer dado){
        List<FichaParchis> result = new ArrayList<>();
        if(dado == 5 && fichasParchis.stream().anyMatch(fp->fp.isEstaEnCasa() && partidaParchis.sePuedeMover(fp,dado))){
            result = fichasParchis.stream().filter(fp->fp.isEstaEnCasa() && partidaParchis.sePuedeMover(fp,dado)).collect(Collectors.toList());
        }else if(dado == 6 && fichasParchis.stream().anyMatch(fp->fp.getCasillaActual().getBloqueada() && partidaParchis.sePuedeMover(fp,dado))){
            result = fichasParchis.stream().filter(fp->fp.getCasillaActual().getBloqueada() && partidaParchis.sePuedeMover(fp,dado)).collect(Collectors.toList());
        }

        if(result.size()==0){//no se puede sacar ficha ni mover bloqueo
            for(FichaParchis fp: fichasParchis){
                if(partidaParchis.sePuedeMover(fp,dado)) result.add(fp);
            }
        }
        return result;
    }

    public void finalizarPartidaParchis(Integer duracion) {
        this.usuario.actualizarEstadisticasParchis(duracion, this.esGanador, this.fichasComidas);
    }                           
}   


