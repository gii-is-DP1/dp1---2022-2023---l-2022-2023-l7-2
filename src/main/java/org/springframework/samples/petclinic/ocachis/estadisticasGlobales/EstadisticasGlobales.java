package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import java.util.List;


import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EstadisticasGlobales extends BaseEntity{
	@Embedded
	private Estadisticas estadisticasGlobales;

	@Column(name = "rankingFichasComidas")
	@OneToMany
	@Size(min=0, max=5)
	private List<Usuario> parchisFichasComidas;

	@Column(name = "rankingCaidoEnMuerte")
	@OneToMany
	@Size(min=0, max=5)
	private List<Usuario> ocaVecesCaidoEnMuerte;
 
	@Column(name = "rankingJugadoresParchis")
	@OneToMany
	@Size(min=0, max=5)
	private List<Usuario> parchisRankingJugadores;
	
	@Column(name = "rankingJugadoresOca")
	@OneToMany
	@Size(min=0, max=5)
	private List<Usuario> ocaRankingJugadores;
}

