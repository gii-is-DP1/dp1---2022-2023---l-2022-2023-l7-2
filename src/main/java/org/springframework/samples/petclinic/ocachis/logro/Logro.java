package org.springframework.samples.petclinic.ocachis.logro;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Logro extends BaseEntity{

	@NotBlank
	@Size(min=3,max=50)
	private String nombre;

	@NotBlank
	@Size(min=10,max=150)
	private String descripcion;

	@Embedded
	public Estadisticas estadisticasACumplir;   
}
