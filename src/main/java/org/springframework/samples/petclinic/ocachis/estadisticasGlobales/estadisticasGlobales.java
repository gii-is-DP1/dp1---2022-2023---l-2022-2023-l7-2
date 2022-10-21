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
	private Integer parchisPartidasJugadas;

	@Column(name = "promedioJugadores")
	@NotEmpty
	private Integer parchisPromedioJugadores;

    @Column(name = "duracionMinima")
	@NotEmpty
	private Time parchisDuracionMinima;

	@Column(name = "duracionMaxima")
	@NotEmpty
	private Time parchisDuracionMaxima;

    @Column(name = "duracionMedia")
	@NotEmpty
	private Time parchisDuracionMedia;

	@Column(name = "duracionTotal")
	@NotEmpty
	private Time parchisDuracionTotal;

	@Column(name = "partidasJugadas")
	@NotEmpty
	private Integer ocaPartidasJugadas;

	@Column(name = "promedioJugadores")
	@NotEmpty
	private Integer ocaPromedioJugadores;

    @Column(name = "duracionMinima")
	@NotEmpty
	private Time ocaDuracionMinima;

	@Column(name = "duracionMaxima")
	@NotEmpty
	private Time ocaDuracionMaxima;

    @Column(name = "duracionMedia")
	@NotEmpty
	private Time ocaDuracionMedia;

	@Column(name = "duracionTotal")
	@NotEmpty
	private Time ocaDuracionTotal;

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
