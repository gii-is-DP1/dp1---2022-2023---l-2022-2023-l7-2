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

import org.springframework.samples.petclinic.ocachis.Usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.ficha.Color;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.partida.Partida;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="jugador")
public class Jugador { 
    
@Column(name="color")
@NotBlank
private Color color;

@Column (name="dado")
private Integer dado;

@ManyToOne
@JoinColumn(name="usuario_id")
private Usuario usuario;

@OneToOne(cascade = CascadeType.ALL,mappedBy = "jugador")
private FichaOca fichasOca;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "jugador")
private Collection<FichaParchis> fichasParchis;

@ManyToOne
@JoinColumn(name="partida_id")
private Partida partida;


}


