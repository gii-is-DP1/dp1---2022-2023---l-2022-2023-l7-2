package org.springframework.samples.petclinic.ocachis.ficha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FichaService {
    private FichaOcaRepository fichaOcaRepository;
    private FichaParchisRepository fichaParchisRepository;

    @Autowired
    public FichaService(FichaOcaRepository fichaOcaRepository, FichaParchisRepository fichaParchisRepository){
        this.fichaOcaRepository = fichaOcaRepository;
        this.fichaParchisRepository = fichaParchisRepository;
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

    public List<FichaParchis> createFichasParchis(Jugador jugador, PartidaParchis partida) {
        ArrayList<FichaParchis> fichas = new ArrayList<>();
        for(int i = 1; i<=4; i++){
            FichaParchis ficha = new FichaParchis();
            ficha.setColor(jugador.getColor());
            switch(ficha.getColor()){
                case ROJO:
                    ficha.setCasillaActual(partida.getCasillaConNumero(103));
                    break;
                case AMARILLO:
                    ficha.setCasillaActual(partida.getCasillaConNumero(101));
                    break;
                case VERDE:
                    ficha.setCasillaActual(partida.getCasillaConNumero(104));
                    break;
                case AZUL:
                    ficha.setCasillaActual(partida.getCasillaConNumero(102));
                    break;
            }
            ficha.setEstaEnCasa(true);
            ficha.setEstaEnLaMeta(false);
            ficha = saveFichaParchis(ficha);
            fichas.add(ficha);
        }
        return fichas;
    }

    @Transactional
    public FichaParchis saveFichaParchis(FichaParchis ficha) {
        return fichaParchisRepository.save(ficha);
    }

    @Transactional(readOnly = true)
    public FichaParchis findFichaParchis(Integer id){
        return fichaParchisRepository.findById(id).get();
    }
    

    @Transactional
    public void moverFichaParchis(FichaParchis ficha, CasillaParchis casillaFinal, Jugador jugador) {
        
        //Borramos ficha antigua
		jugador.deleteFichaParchis(ficha);
        jugador.getPartidaParchis().setUltimaFichaMovida(null);
		removeFichaParchis(ficha);

		//Creamos ficha nueva
		FichaParchis f = new FichaParchis(jugador.getColor(), casillaFinal);
		f = saveFichaParchis(f);
		jugador.addFichaParchis(f);	
        jugador.getPartidaParchis().setUltimaFichaMovida(f);
    }

    @Transactional
    private void removeFichaParchis(FichaParchis ficha) {
        fichaParchisRepository.delete(ficha);
    }


}
