package org.springframework.samples.petclinic.ocachis.casilla;


import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Casilla extends BaseEntity {
    
    @NotNull
    protected Integer numero;
}
