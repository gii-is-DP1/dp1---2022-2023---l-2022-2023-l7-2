package org.springframework.samples.petclinic.ocachis.partida;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.petclinic.model.BaseEntity;

import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Partida extends BaseEntity{

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoPartida;
	
	@NotEmpty
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	
	@NotEmpty
	private TipoEstadoPartida estado = TipoEstadoPartida.CREADA;
	/*
	 * La duracion de la partida en mintuos. Se computa al final de la partida*/
	private Integer duracion;
	
	@OneToOne
	private Usuario ganador;
	
	private Integer numeroTurnos=0;
	
	@Min(value=2)
	@Max(value=4)
	private Integer maxJugadores;	

	@ManyToMany
	private Collection<Usuario> usuariosObservadores;
}

