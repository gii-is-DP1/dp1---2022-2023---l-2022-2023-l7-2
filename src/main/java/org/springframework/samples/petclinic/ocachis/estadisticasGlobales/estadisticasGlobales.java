package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

//Scope("Singleton") le dice a Spring que es un Singleton y que siempre inyecte la misma instancia
@Scope("singleton")
public class estadisticasGlobales extends BaseEntity{
	@Embedded
	private Estadisticas estadisticasGlobales;

	@Column(name = "rankingFichasComidas")
	@OneToMany
	private Collection<Usuario> parchisFichasComidas;

	@Column(name = "rankingCaidoEnMuerte")
	@OneToMany
	private Collection<Usuario> ocaVecesCaidoEnMuerte;

	@Column(name = "rankingJugadoresParchis")
	@OneToMany
	private Collection<Usuario> parchisRankingJugadores;
	
	@Column(name = "rankingJugadoresOca")
	@OneToMany
	private Collection<Usuario> ocaRankingJugadores;

}