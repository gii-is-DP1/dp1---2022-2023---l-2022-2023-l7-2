package org.springframework.samples.petclinic.ocachis.partida;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Partida extends BaseEntity{

	private Integer codigoPartida;
	
	//@NotEmpty
	private LocalDateTime fechaCreacion;

	private LocalDateTime fechaFinalizacion;
	/* 
	@NotEmpty*/
	private TipoEstadoPartida estado = TipoEstadoPartida.CREADA;
	
	/*
	 * La duracion de la partida en mintuos. Se computa al final de la partida*/
	private Integer duracion;
	
	@OneToOne
	private Usuario ganador;
	
	private Color ColorJugadorActual=Color.ROJO;
	
	@Min(value=2)
	@Max(value=4)
	private Integer maxJugadores;

	@ManyToMany
	private List<Usuario> usuariosObservadores;


	private static Integer generadorCodigoPartida=115;
	public static Integer getNuevoCodigoPartida(){
		return generadorCodigoPartida++;
	}
	
}

