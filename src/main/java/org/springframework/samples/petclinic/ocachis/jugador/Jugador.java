
package  org.springframework.samples.petclinic.ocachis.jugador;


import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.partida.Partida;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Jugador  extends BaseEntity{ 
    
@NotBlank
private Color color;


//Estadisticas//

@ManyToOne
private Usuario usuario;

private Integer fichasComidas=0;

private Integer vecesCaidoEnMuerte=0;	

private Boolean esGanador=false;


//Fichas//
@OneToOne(cascade = CascadeType.ALL, optional = true)
private FichaOca fichaOca;

@OneToMany(cascade = CascadeType.ALL)
private Collection<FichaParchis> fichasParchis;


//Partida//
@ManyToOne(optional = true)
private PartidaParchis partidaParchis;

@ManyToOne (optional = true)
private PartidaOca partidaOca;



}


