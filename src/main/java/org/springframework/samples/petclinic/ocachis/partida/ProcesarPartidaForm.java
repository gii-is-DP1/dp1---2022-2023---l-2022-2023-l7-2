package org.springframework.samples.petclinic.ocachis.partida;

import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
@Setter
public class ProcesarPartidaForm {
    
    @Min(value = 2)
    @Max(value = 4)
    private Integer numJugador;
    @NotEmpty
    private String tipo;


}