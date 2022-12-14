package org.springframework.samples.petclinic.ocachis.casilla;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(readOnly = true)
    public Iterable<CasillaOca> findAllForPartidaOca(Integer partidaOcaId){
        // return casillaOcaRepository.findAllForPartidaOca(partidaOcaId);
        return casillaOcaRepository.findAll();
    }

    @Transactional
    public CasillaOca saveCasillaOca(CasillaOca casilla){
        CasillaOca c = casillaOcaRepository.save(casilla);
        return c;
    }

    @Transactional
    public CasillaParchis saveCasillaParchis(CasillaParchis casilla){
        CasillaParchis c = casillaParchisRepository.save(casilla);
        return c;
    }

    @Transactional(readOnly = true)
    public CasillaOca findCasillaOcaById(Integer casillaOcaId) {
        CasillaOca c = casillaOcaRepository.findById(casillaOcaId).get();
        return c;
    }


    
}
