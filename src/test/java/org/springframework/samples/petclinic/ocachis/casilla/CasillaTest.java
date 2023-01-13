package org.springframework.samples.petclinic.ocachis.casilla;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CasillaTest{

    @Autowired
    CasillaOcaRepository ocaRepository;
    @Autowired
    CasillaParchisRepository parchisRepository;

    @Test
    void casillaTest(){
        repositoriesExist();
        casillaOcaTest();
        casillaParrchisTest();
    }

    private void casillaParrchisTest() {
        CasillaParchis casilla = new CasillaParchis();
        casilla.setNumero(4);
        casilla.setFichas(new ArrayList<>());
        casilla.setTipoCasillaParchis(TipoCasillaParchis.CASAAMARILLO);
        assertTrue(casilla.esCasa());

        casilla.setTipoCasillaParchis(TipoCasillaParchis.FINALAMARILLO);
        assertTrue(casilla.esMeta());

        casilla.setTipoCasillaParchis(TipoCasillaParchis.PASILLOROJO);
        assertTrue(casilla.esPasillo());
        
        casilla.setTipoCasillaParchis(TipoCasillaParchis.NORMAL);

        FichaParchis ficha = new FichaParchis();
        FichaParchis ficha2 = new FichaParchis();

        casilla.añadirFicha(ficha);
        assertTrue(casilla.getNumeroFichas()==1);

        casilla.añadirFicha(ficha2);

        assertTrue(casilla.getBloqueada());

        casilla.quitarFicha(ficha);
        assertTrue(casilla.getNumeroFichas()==1);
        assertFalse(casilla.getBloqueada());
        
        assertTrue(casilla.getCoordenadas().getX()==400 && casilla.getCoordenadas().getY()==566);
        assertTrue(casilla.getOrientacion().equals("horizontal"));
    }

    private void casillaOcaTest() {
        assertTrue(CasillaOca.coordenadas.size()==63);
        
        CasillaOca casilla = new CasillaOca();
        casilla.setNumero(4);
        casilla.setFichas(new ArrayList<>());
        
        FichaOca ficha = new FichaOca();

        casilla.añadirFicha(ficha);
        assertTrue(casilla.getNumeroFichas()==1);
        casilla.quitarFicha(ficha);
        assertTrue(casilla.getNumeroFichas()==0);

        
        assertTrue(casilla.getCoordenadas().getX()==358 && casilla.getCoordenadas().getY()==609);
        assertTrue(casilla.getOrientacion().equals("vertical"));
        

    }

    private void repositoriesExist() {
        assertNotNull(ocaRepository, "No injecta correctamente el repositorio");
        assertNotNull(parchisRepository, "No injecta correctamente el repositorio");
    }
}