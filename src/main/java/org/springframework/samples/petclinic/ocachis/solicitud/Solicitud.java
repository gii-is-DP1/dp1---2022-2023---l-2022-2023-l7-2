package org.springframework.samples.petclinic.ocachis.solicitud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne; 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="solicitud")
public class Solicitud {

@Column(name="estado")
private TipoEstado tipoEstado;


@ManyToOne
@JoinColumn(name="usuario_id")
private Usuario usuarioInvitado;

@OneToOne(mappedBy = "usuario")
private Usuario usuarioSolicitud;