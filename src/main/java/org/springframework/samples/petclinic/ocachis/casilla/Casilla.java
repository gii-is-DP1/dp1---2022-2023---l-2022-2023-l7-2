package org.springframework.samples.petclinic.ocachis.casilla;


import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Casilla extends BaseEntity {
    
    @NotEmpty
    protected Integer numero;
}
