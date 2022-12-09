package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CasillaParchis extends Casilla{

	@NotBlank
    private TipoCasillaParchis tipoCasillaParchis;

    @OneToMany(mappedBy="casillaActual")
    private List<FichaParchis> fichas;

	private Boolean bloqueada;
}
