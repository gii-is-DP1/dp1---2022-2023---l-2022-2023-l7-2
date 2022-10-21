package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
@Entity
@Table(name = "estadisticasGlobales")
public class estadisticasGlobales {
    @Column(name = "partidasJugadas")
	@NotEmpty
	private Integer partidasJugadas;

	@Column(name = "promedioJugadores")
	@NotEmpty
	private Integer promedioJugadores;

    @Column(name = "duracionMinima")
	@NotEmpty
	private Time duracionMinima;

	@Column(name = "duracionMaxima")
	@NotEmpty
	private Time duracionMaxima;

    @Column(name = "duracionMedia")
	@NotEmpty
	private Time duracionMedia;

	@Column(name = "duracionTotal")
	@NotEmpty
	private Time duracionTotal;

	@Column(name = "rankingFichasComidas")
	@NotEmpty
	private List<Jugador> fichasComidas;

	@Column(name = "rankingCaidoEnMuerte")
	@NotEmpty
	private List<Jugador> vecesCaidoEnMuerte;

	@Column(name = "rankingJugadores")
	@NotEmpty
	private List<Jugador> rankingJugadores;
}
