package org.springframework.samples.petclinic.ocachis.ficha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.ficha.*;

import lombok.Getter;

@Getter

@Entity
@Table(name = "Ficha")
public class Ficha extends Jugador {
   
	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;

    @Column(name = "color")
	@NotEmpty
	private Color color;
}
