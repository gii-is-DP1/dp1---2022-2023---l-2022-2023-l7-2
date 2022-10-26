package org.springframework.samples.petclinic.ocachis.solicitud;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.samples.petclinic.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Solicitud extends BaseEntity{

@Column(name="estado")
private TipoEstadoSolicitud tipoEstado;



//En principio no hacen falta los usuarios porque no hay navegabilidad


//@ManyToOne
//private Usuario usuarioInvitado;
//
//@OneToOne
//private Usuario usuarioSolicitud;

}