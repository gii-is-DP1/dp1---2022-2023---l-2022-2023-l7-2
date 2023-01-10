package org.springframework.samples.petclinic.ocachis.partida;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaCreacion;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
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

	protected Long fechaHoraUltimoMovimiento;	



	public String printFechaCreacion(){
		return fechaCreacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
	}

	public String printFechaFinalizacion(){
		return fechaFinalizacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
	}
}

