
package org.springframework.samples.petclinic.ocachis.usuario;


import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.solicitud.Solicitud;
import org.springframework.samples.petclinic.ocachis.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {
	
	@NotEmpty
    private String nombre;

//  @NotEmpty
    private String apellido;

	@Embedded
    private Estadisticas estadisticas;

    @ManyToMany
    private Collection<Logro> logros;

    @OneToMany
    private Collection<Solicitud> solicitudesEnvidas;

    @OneToMany
    private Collection<Solicitud> solicitudesRecibidas;

    @OneToMany(mappedBy="usuario")
    private Collection<Jugador> partidasJugadas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre +
				", apellido=" + apellido +
//				", estadisticas=" + estadisticas.toString() +
				", user=" + user.getUsername() + " | " + user.getPassword() +
			"]";
	}

}
