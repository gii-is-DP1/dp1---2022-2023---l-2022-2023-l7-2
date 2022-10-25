package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;

@Entity
//@Table(name="casillaParchis")
public class CasillaParchis extends Casilla{
	
	@Column(name="tipoCasilla")
	@NotEmpty
	private TipoCasillaParchis tipoCasilla;
	
	@Column(name="bloqueada")
	private Boolean bloqueada;
	
	@OneToMany
	private List<FichaParchis> fichas;

	public TipoCasillaParchis getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(TipoCasillaParchis tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}

	public Boolean getBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(Boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public List<FichaParchis> getFichas() {
		return fichas;
	}

	public void setFichas(List<FichaParchis> fichas) {
		this.fichas = fichas;
	}
	
	
}
