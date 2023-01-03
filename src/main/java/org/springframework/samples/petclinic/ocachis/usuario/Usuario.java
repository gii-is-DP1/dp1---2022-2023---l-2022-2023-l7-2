
package org.springframework.samples.petclinic.ocachis.usuario;


import java.util.Collection;
import java.lang.reflect.Field;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.solicitud.Solicitud;
import javax.persistence.Table;
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

    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL)
    private Collection<Jugador> partidasJugadas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public void actualizarEstadisticasOca(Integer duracion, Boolean esGanador, Integer vecesCaidoEnMuerte) {
        estadisticas.updateEstadisticasOca(duracion, esGanador, vecesCaidoEnMuerte);
}

    public void actualizarEstadisticasParchis(Integer duracion, Boolean esGanador, Integer fichasComidas) {
        estadisticas.updateEstadisticasParchis(duracion, esGanador, fichasComidas);
    }

    public boolean cumpleLogro(Logro l) throws IllegalAccessException{
        Boolean res = false;
        Field[] campos = l.getEstadisticasACumplir().getClass().getDeclaredFields();
        for(Field c : campos){
            if(
            ((Integer) c.get(l.getEstadisticasACumplir()) != null) && 
            ((Integer) c.get(l.getEstadisticasACumplir()) != 0) && 
             (Integer) c.get(this.estadisticas) >= (Integer) c.get(l.getEstadisticasACumplir())){
                res = true;
            }
            
            
        }

        return res;
    }

    public void addLogro(Logro l) {
        this.logros.add(l);
    }
}
