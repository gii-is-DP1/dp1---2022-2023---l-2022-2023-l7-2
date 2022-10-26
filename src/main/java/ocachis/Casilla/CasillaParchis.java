package ocachis.Casilla;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import ocachis.ficha.FichaParchis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class CasillaParchis extends Casilla{

	@NotEmpty
	private TipoCasillaParchis tipoCasilla;
	
	private Boolean bloqueada;
	
	@OneToMany
	private List<FichaParchis> fichas;	
}
