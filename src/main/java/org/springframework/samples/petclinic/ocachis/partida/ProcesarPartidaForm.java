package org.springframework.samples.petclinic.ocachis.partida;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ProcesarPartidaForm {
    private Integer numJugador;
    private String tipo;
    /* 
    public String getTipo() {
        return tipo;
    }
 
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 
    public Integer getNumJugadores() {
        return numJugadores;
    }
 
    public void setNumJugadores(Integer numJugadores) {
        this.numJugadores = numJugadores;
    }
    */
}