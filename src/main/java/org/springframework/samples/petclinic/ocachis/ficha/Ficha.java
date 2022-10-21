package org.springframework.samples.petclinic.ocachis.ficha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;


import lombok.Getter;

@Getter

@Entity
@Table(name = "Ficha")
public class Ficha extends BaseEntity {
   
    @Column(name = "color")
	@NotEmpty
	private Color color;
}
