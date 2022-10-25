package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.ocachis.Estadisticas;
import org.springframework.samples.petclinic.ocachis.Jugador.Jugador;

import lombok.Getter;

@Getter
@Entity
@Table(name = "estadisticasGlobales")
public class estadisticasGlobales {
    @Column(name = "estadisticasGlobales")
	@NotEmpty
	private Estadisticas estadisticasGlobales;

	@Column(name = "rankingFichasComidas")
	@NotEmpty
	private List<Jugador> parchisFichasComidas;

	@Column(name = "rankingCaidoEnMuerte")
	@NotEmpty
	private List<Jugador> ocaVecesCaidoEnMuerte;

	@Column(name = "rankingJugadores")
	@NotEmpty
	private List<Jugador> parchisRankingJugadores;
	
	@Column(name = "rankingJugadores")
	@NotEmpty
	private List<Jugador> ocaRankingJugadores;
}
