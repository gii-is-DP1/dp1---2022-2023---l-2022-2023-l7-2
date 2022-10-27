package org.springframework.samples.petclinic.ocachis.logro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "Logro")
public class Logro extends BaseEntity{
    @Column(name = "nombre")
	@NotEmpty
	private String nombre;

	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;

	@Column(name = "estadisticasACumplir")
	@NotEmpty
	private Estadisticas estadisticasACumplir;   
}
