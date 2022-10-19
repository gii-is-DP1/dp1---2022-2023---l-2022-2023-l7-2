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

    public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
