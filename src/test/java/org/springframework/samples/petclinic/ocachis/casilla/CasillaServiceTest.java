package org.springframework.samples.petclinic.ocachis.casilla;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CasillaServiceTest {
    
    @Autowired
    CasillaService casillaService;


    @Test
    void testSaveCasillaOca(){
        CasillaOca casilla = new CasillaOca();
        casilla.setNumero(1);
        casillaService.saveCasillaOca(casilla);
        assertNotNull(casilla.getId());
        assertTrue(casilla.getId()>0);
    }

    @Test
    void testSaveCasillaParchis(){
        CasillaParchis casilla = new CasillaParchis();
        casilla.setNumero(1);
        casillaService.saveCasillaParchis(casilla);
        assertNotNull(casilla.getId());
        assertTrue(casilla.getId()>0);
    }


    @Test
    void testFindCasillaOcaById(){
        assertThrows(ResourceNotFoundException.class,() -> casillaService.findCasillaOcaById(12312));
        CasillaOca casilla = casillaService.findCasillaOcaById(1);
        assertNotNull(casilla.getId());
        assertNotNull(casilla.getNumero());
    }
}
