package org.springframework.samples.petclinic.ocachis.partida;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaCreacion;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaFinalizacion;
	/* 
	@NotEmpty*/
	private TipoEstadoPartida estado = TipoEstadoPartida.CREADA;
	
	/*
	 * La duracion de la partida en mintuos. Se computa al final de la partida
	 * */
	private Integer duracion;
	
	@OneToOne
	private Usuario ganador;
	
	private Color ColorJugadorActual=Color.ROJO;
	
	@NotNull
	@Min(2)
	@Max(4)
	private Integer maxJugadores;

	@ManyToMany
	private List<Usuario> usuariosObservadores;


	private static Integer generadorCodigoPartida=115;
	public static Integer getNuevoCodigoPartida(){ 
		return generadorCodigoPartida++;
	}

	protected Long fechaHoraUltimoMovimiento;	



	public String printFechaCreacion(){
		return fechaCreacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
	}

	public String printFechaFinalizacion(){
		return fechaFinalizacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
	}
}

