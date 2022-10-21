package ocachis.ficha;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.jmx.export.annotation.ManagedAttribute;


@Entity
//@Table(name="FichaParchis")
public class FichaParchis extends ficha{
	

	@Column(name="estaEnCasa")
	private boolean estaEnCasa;

	@Column(name="estaEnLaMeta")
	private boolean estaEnLaMeta;
	
	@ManagedAttribute
	public boolean estaEnCasa() {
		return estaEnCasa;
	}

	@ManagedAttribute
	public void setestaEnCasa(boolean estaEnCasa) {
		this.estaEnCasa = estaEnCasa;
	}
	
	@ManagedAttribute
	public boolean estaEnLaMeta() {
		return estaEnLaMeta;
	}
	
	@ManagedAttribute
	public void setestaEnLaMeta(boolean estaEnLaMeta) {
		this.estaEnLaMeta = estaEnLaMeta;
	}
}
