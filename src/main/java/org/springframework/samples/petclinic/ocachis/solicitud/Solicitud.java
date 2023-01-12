package org.springframework.samples.petclinic.ocachis.solicitud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Solicitud extends BaseEntity{

@Column(name="estado")
private TipoEstadoSolicitud tipoEstado;



//En principio no hacen falta los usuarios porque no hay navegabilidad


@OneToOne
private Usuario usuarioInvitado;

@OneToOne
private Usuario usuarioSolicitud;


}