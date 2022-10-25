package ocachis.estadisticas;

import javax.persistence.Entity;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Entity
//@Table(name="EstadisticasGlobalesParchis")
public class EstadisticasGlobalesParchis extends EstadisticasGlobales{
	
	private int rankingFichasComidas = 0;
	
	@ManagedOperation
	public void reset() {
		this.rankingFichasComidas = 0;
	}

	@ManagedAttribute
	public int setrankingFichasComidas() {
		return rankingFichasComidas;
	}
}
