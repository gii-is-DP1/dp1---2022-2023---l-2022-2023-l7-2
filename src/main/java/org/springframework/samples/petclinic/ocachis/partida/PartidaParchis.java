package org.springframework.samples.petclinic.ocachis.partida;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PartidaParchis extends Partida{

	@OneToMany
	List<CasillaParchis> casillas;
}
