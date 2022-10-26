package org.springframework.samples.petclinic.ocachis.ficha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Color;

import lombok.Getter;

@Getter

@MappedSuperclass
public class Ficha extends BaseEntity {
    @Column(name = "color")
	@NotEmpty
	private Color color;
}
