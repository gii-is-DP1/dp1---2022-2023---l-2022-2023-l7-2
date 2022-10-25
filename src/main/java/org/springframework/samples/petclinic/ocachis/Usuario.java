package org.springframework.samples.petclinic.ocachis;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario extends BaseEntity {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    private ImageIcon avatar;

    @NotEmpty
    private TipoUsuario tipoUsuario;

    public TipoUsuario getTipoUsuario(){
        return tipoUsuario;
    }

    @NotEmpty
    private Estadisticas estadisticas;

    @NotEmpty
    private List<Logro> logros;

    private List<Solicitud> solicitudesEnvidas;

    private List<Solicitud> solicitudesRecibidas;

    @NotEmpty
    private List<Jugador> partidasJugadas;

}
