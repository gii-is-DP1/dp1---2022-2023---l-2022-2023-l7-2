package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;

import org.springframework.samples.petclinic.ocachis.jugador.Jugador;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PartidaParchis extends Partida{

	@OneToMany
	private List<CasillaParchis> casillas;
	
	@OneToMany(mappedBy="partidaParchis")
	private Collection<Jugador> jugadores;
}