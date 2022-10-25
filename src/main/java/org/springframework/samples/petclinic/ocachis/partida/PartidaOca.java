package org.springframework.samples.petclinic.ocachis.partida;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class PartidaOca extends Partida{

	@OneToMany
	List<CasillaOca> casillas;
}
