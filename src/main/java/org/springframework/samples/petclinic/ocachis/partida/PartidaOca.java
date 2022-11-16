package org.springframework.samples.petclinic.ocachis.partida;


import java.util.Collection;
import java.util.List;


import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;

import org.springframework.samples.petclinic.ocachis.jugador.Jugador;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class PartidaOca extends Partida{


	@OneToMany(cascade = CascadeType.ALL)
	private List<CasillaOca> casillas;
	
	@OneToMany(mappedBy="partidaOca", cascade = CascadeType.ALL)
	private Collection<Jugador> jugadores;

}
