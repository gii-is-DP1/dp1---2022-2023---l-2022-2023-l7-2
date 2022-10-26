package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import java.sql.Time;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.context.annotation.Scope;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;

@Getter
@Entity

//Scope("Singleton") le dice a Spring que es un Singleton y que siempre inyecte la misma instancia
@Scope("singleton")
public class estadisticasGlobales extends BaseEntity{
	@OneToOne
	private Estadisticas estadisticasGlobales;

	@Column(name = "rankingFichasComidas")
	@OneToMany
	private Collection<Jugador> parchisFichasComidas;

	@Column(name = "rankingCaidoEnMuerte")
	@OneToMany
	private Collection<Jugador> ocaVecesCaidoEnMuerte;

	@Column(name = "rankingJugadoresParchis")
	@OneToMany
	private Collection<Jugador> parchisRankingJugadores;
	
	@Column(name = "rankingJugadoresOca")
	@OneToMany
	private Collection<Jugador> ocaRankingJugadores;
}
