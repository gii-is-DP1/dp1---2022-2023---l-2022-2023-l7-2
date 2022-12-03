package org.springframework.samples.petclinic.ocachis.ficha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
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
    public void moverFichaOca(FichaOca ficha, CasillaOca nuevaCasilla){
        ficha.setCasillaActual(nuevaCasilla);
        fichaOcaRepository.save(ficha);
    }
}
