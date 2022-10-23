package org.springframework.samples.petclinic.ocachis.Jugador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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

@OneToMany(cascade = CascadeType.ALL,mappedBy = "jugador")
private List<FichaOca> fichasOca;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "jugador")
private List<FichaParchis> fichasParchis;

@ManyToOne
@JoinColumn(name="partida_id")
private Partida partida;


}


