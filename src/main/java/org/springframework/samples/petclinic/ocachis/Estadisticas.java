package org.springframework.samples.petclinic.ocachis;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
@Entity
@Table(name = "estadisticas")
public class Estadisticas {
    @Column(name = "estadisticas")
    @NotEmpty
    private Map<TipoEstadistica, valor> estadisticas;
}
