package org.springframework.samples.petclinic.ocachis.casillaoca;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.petclinic.ocachis.fichaoca.FichaOca;

@Entity
@Table(name="casillasoca")
public class CasillaOca extends Casilla{
    
    @Column(name="tipoCasillaOca")
    @NotBlank
    private TipoCasillaOca tipoCasillaOca;


    public TipoCasillaOca getTipoCasillaOca() {
        return tipoCasillaOca;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casillaoca")
    private List<FichaOca> fichas;

    @ManyToOne
	@JoinColumn(name = "partidaOca_id")
	private PartidaOca partidaOca;

    public void setTipoCasillaOca(TipoCasillaOca tipoCasillaOca) {
        this.tipoCasillaOca = tipoCasillaOca;
    }

    public List<FichaOca> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaOca> fichas) {
        this.fichas = fichas;
    }

    
}
