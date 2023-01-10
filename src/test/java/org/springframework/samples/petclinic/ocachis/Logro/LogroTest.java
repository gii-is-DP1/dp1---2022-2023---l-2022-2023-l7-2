package org.springframework.samples.petclinic.ocachis.Logro;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.logro.LogroRepository;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LogroTest {

    @Autowired
    LogroRepository lr;

    @Test
    public void logroTest(){
        repositoryExists();
        testConstraints();
       
    }
    
    public void repositoryExists(){
       assertNotNull(lr,"The repository was not injected into the tests, its autowired value was null");
    }


     void testConstraints(){
        Logro  logro=new Logro();
        logro.setId(9);
        logro.setNombre("      ");
        logro.setDescripcion("Juega 50 partidas de parchís");
        logro.setEstadisticasACumplir(null);
        assertThrows(ConstraintViolationException.class,() -> lr.save(logro),
        "No se comprueba si el nombre es una cadena vacía");
        
        logro.setNombre("En un lugar de la mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.");
        logro.setDescripcion("Juego 50 partida de parchís");
        assertThrows(ConstraintViolationException.class,() ->lr.save(logro),
        "No se comprueba la longitud máxima del nombre"
        );
        logro.setDescripcion(" ");
        assertThrows(ConstraintViolationException.class,() -> lr.save(logro),
        "No se comprueba si la descripción es una cadena vacía");
        logro.setDescripcion("En un lugar de la mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.");
        assertThrows(ConstraintViolationException.class, () -> lr.save(logro),
        "No se comprueba la longitud máxima de la descripción");
        
    }

}




