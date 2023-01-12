package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

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
public class pruebaTest {

    @Autowired
    pruebaRepository pr;

    

    @Test
    public void testConstraints(){
       prueba prueba = new prueba();
       prueba.setNombre("dsdsdsdsdsdsdsdsdsddsadsadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdsadasdasdasdasdas");
       assertThrows(ConstraintViolationException.class,()->pr.save(prueba));

       prueba.setNombre("Holass");
       prueba.setNumero(1);
       
    }
}
