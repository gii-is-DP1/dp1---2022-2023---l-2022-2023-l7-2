package org.springframework.samples.petclinic.ocachis.partida;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.Usuario.Usuario;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
@Table(name="partida")
public class Partida extends BaseEntity{

	@Column(name="codigoPartida")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoPartida;
	
	@Column(name ="fechaCreacion")
	@NotEmpty
	private Date fechaCreacion;
	
	@Column(name="haEmpezado")
	private Boolean haEmpezado;
	
	@Column(name="enPartida")
	private Boolean enPartida;
	
	@Column(name="haTerminado")
	private Boolean haTerminado;

	@Column(name="cancelada")
	private Boolean cancelada;
	
	/*
	 * La duracion de la partida en mintuos. Se computa al final de la partida*/
	@Column(name="duracion")
	private Integer duracion;
	
	@Column(name="ganador")
	@OneToOne
	private Usuario ganador;
	
	@Column(name="numeroTurnos")
	private Integer numeroTurnos;
	
	@Column(name="maxJugadores")
	@Min(value=2)
	@Max(value=4)
	private Integer maxJugadores;	
	
}

