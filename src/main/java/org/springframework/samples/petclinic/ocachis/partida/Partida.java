package org.springframework.samples.petclinic.ocachis.partida;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
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
	
//	@Column(name="ganador")
//	private Usuario ganador;
	
	@Column(name="numeroTurnos")
	private Integer numeroTurnos;
	
	@Column(name="maxJugadores")
	@Min(value=2)
	@Max(value=4)
	private Integer maxJugadores;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getHaEmpezado() {
		return haEmpezado;
	}

	public void setHaEmpezado(Boolean haEmpezado) {
		this.haEmpezado = haEmpezado;
	}

	public Boolean getEnPartida() {
		return enPartida;
	}

	public void setEnPartida(Boolean enPartida) {
		this.enPartida = enPartida;
	}

	public Boolean getHaTerminado() {
		return haTerminado;
	}

	public void setHaTerminado(Boolean haTerminado) {
		this.haTerminado = haTerminado;
	}

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getNumeroTurnos() {
		return numeroTurnos;
	}

	public void setNumeroTurnos(Integer numeroTurnos) {
		this.numeroTurnos = numeroTurnos;
	}

	public Integer getMaxJugadores() {
		return maxJugadores;
	}

	public void setMaxJugadores(Integer maxJugadores) {
		this.maxJugadores = maxJugadores;
	}

	public Integer getCodigoPartida() {
		return codigoPartida;
	}
	
	
	
	
}

