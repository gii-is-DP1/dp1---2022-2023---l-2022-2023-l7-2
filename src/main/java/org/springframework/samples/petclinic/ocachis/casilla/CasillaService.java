package org.springframework.samples.petclinic.ocachis.casilla;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CasillaService {

    private CasillaOcaRepository casillaOcaRepository;
    private CasillaParchisRepository casillaParchisRepository;

    @Autowired
    public CasillaService(CasillaOcaRepository casillaOcaRepository, CasillaParchisRepository casillaParchisRepository){
        this.casillaOcaRepository = casillaOcaRepository;
        this.casillaParchisRepository =  casillaParchisRepository;
    }


    @Transactional
    public CasillaOca saveCasillaOca(CasillaOca casilla){
        return casillaOcaRepository.save(casilla);
    }

    @Transactional
    public CasillaParchis saveCasillaParchis(CasillaParchis casilla){
        return casillaParchisRepository.save(casilla);
    }

    @Transactional(readOnly = true)
    public CasillaOca findCasillaOcaById(Integer casillaOcaId) {
        Optional<CasillaOca> optCasilla = casillaOcaRepository.findById(casillaOcaId);
        if(optCasilla.isEmpty()) throw new ResourceNotFoundException("Casilla no encontrada");
        return optCasilla.get();
    }
}
