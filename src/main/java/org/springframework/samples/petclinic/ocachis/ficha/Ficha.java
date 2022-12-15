package org.springframework.samples.petclinic.ocachis.ficha;


import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Color;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Ficha extends BaseEntity {
	
	
	protected Color color;
}
