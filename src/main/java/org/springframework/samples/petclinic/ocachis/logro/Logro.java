package org.springframework.samples.petclinic.ocachis.logro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "Logro")
public class Logro {
    @Column(name = "nombre")
	@NotEmpty
	private String nombre;

	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;

	@Column(name = "tipoEstadistica")
	@NotEmpty
	private TipoEstadistica tipoEstadistica;

	@Column(name = "valor")
	@NotEmpty
	private Integer valor;

    
}
