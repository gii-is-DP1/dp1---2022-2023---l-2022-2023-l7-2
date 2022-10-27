package org.springframework.samples.petclinic.ocachis.logro;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Logro extends BaseEntity{
	@NotEmpty
	private String nombre;

	@NotEmpty
	private String descripcion;

	@Embedded
	private Estadisticas estadisticasACumplir;   
}
