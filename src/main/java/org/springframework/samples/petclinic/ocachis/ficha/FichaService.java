package org.springframework.samples.petclinic.ocachis.ficha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FichaService {
    private fichaOcaRepository fichaOcaRepository;

    @Autowired
    public FichaService(fichaOcaRepository fichaOcaRepository){
        this.fichaOcaRepository = fichaOcaRepository;
    }

    @Transactional
    public FichaOca saveFichaOca(FichaOca ficha){
        return fichaOcaRepository.save(ficha);
    }

    @Transactional
    public void moverFichaOca(FichaOca ficha,  CasillaOca casillaFinal, Jugador j){
        //Borramos ficha antigua
		j.setFichaOca(null);
		removeFichaOca(ficha);

		//Creamos ficha nueva
		FichaOca f = new FichaOca(j.getColor(), casillaFinal);
		f = saveFichaOca(f);
		j.setFichaOca(f);	
    }


    public FichaOca findFichaOca(Integer id){
        return fichaOcaRepository.findById(id).get();
    }

	public void removeFichaOca(FichaOca ficha) {
        fichaOcaRepository.delete(ficha);
	}

    public FichaOca createFichaOca(Jugador jugador, PartidaOca partida) {
        FichaOca ficha = new FichaOca();
        ficha.setCasillaActual(partida.getCasillaConNumero(1));
        ficha.setColor(jugador.getColor());
        ficha = saveFichaOca(ficha);
        return ficha;
    }
}
