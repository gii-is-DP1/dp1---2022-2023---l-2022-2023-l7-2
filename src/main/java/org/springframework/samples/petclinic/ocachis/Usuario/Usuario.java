package org.springframework.samples.petclinic.ocachis.Usuario;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.Jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.solicitud.Solicitud;
import org.springframework.samples.petclinic.user.User;

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

    @OneToMany
    private Collection<Logro> logros;

    private Collection<Solicitud> solicitudesEnvidas;

    private Collection<Solicitud> solicitudesRecibidas;

    @OneToMany
    private Collection<Jugador> partidasJugadas;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

}
