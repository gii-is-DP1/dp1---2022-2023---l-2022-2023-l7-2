package ocachis.estadisticas;

import javax.persistence.Entity;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Entity
//@Table(name="EstadisticasOca")
public class EstadisticasOca extends Estadisticas{
	
	private int caidoEnMuerte = 0;
	
	@ManagedOperation
	public void reset() {
		this.caidoEnMuerte = 0;
	}

	@ManagedAttribute
	public int getcaidoEnMuerte() {
		return caidoEnMuerte;
	}
}
