package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOcaRepository;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchisRepository;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaTests {

    @Autowired
    PartidaOcaRepository or;
    @Autowired
    PartidaParchisRepository pr;

    @Test
    public void partidaTest(){
        ocarepositoryExists();
        parchisrepositoryExists();
        testConstraints();
       
    }
    
    public void ocarepositoryExists(){
        assertNotNull(or,"The repository was not injected into the tests, its autowired value was null");
    }
    public void parchisrepositoryExists(){
        assertNotNull(pr,"The repository was not injected into the tests, its autowired value was null");
    }



     void testConstraints(){
        PartidaOca po=new PartidaOca();
        PartidaParchis pp=new PartidaParchis();
        po.setId(4);
        po.setMaxJugadores(1);
        po.setEstado(TipoEstadoPartida.CREADA);
        assertThrows(ConstraintViolationException.class,() -> or.save(po),
        "El mínimo de jugadores es 2");

        po.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> or.save(po),
        "El máximo de jugadores es 4");

        pp.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        "El máximo de jugadores es 4");
        
    }

}