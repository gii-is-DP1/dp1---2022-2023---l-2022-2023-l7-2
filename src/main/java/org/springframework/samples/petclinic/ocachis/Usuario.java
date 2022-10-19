package org.springframework.samples.petclinic.ocachis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
@Table(name="usuario")
public class Usuario extends BaseEntity {
    
    @Column(name = "nombre")
    @NotBlank
    private String nombre;

    @Column(name = "apellido")
    @NotBlank
    private String apellido;

    @Column(name = "avatar")
    private ImageIcon avatar;

    @Column(name = "tipoUsuario")
    @NotBlank
    private TipoUsuario tipoUsuario;

    public TipoUsuario getTipoUsuario(){
        return tipoUsuario;
    }
}
