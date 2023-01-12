package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.logro.LogroRepository;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EstadisticasGlobalesTest {
    
    @Autowired
    EstadisticasGlobalesRepository egr;


    @Test
    public void testEstadisticasGlobales(){
        checkRepositoryExists();
        testConstraints();
    }

    public void checkRepositoryExists(){
        assertNotNull(egr, "No injecta el repositorio correctamente");

    }


    void testConstraints(){
        Estadisticas e = new Estadisticas();
        EstadisticasGlobales eg = new EstadisticasGlobales();
        Usuario u1 = new Usuario();
        u1.setId(500);
        u1.setNombre("Nombre");

        Usuario u2 = new Usuario();
        u2.setId(501);
        u2.setNombre("Nombre");

        Usuario u3 = new Usuario();
        u3.setId(502);
        u3.setNombre("Nombre");

        Usuario u4 = new Usuario();
        u4.setId(503);
        u4.setNombre("Nombre");

        Usuario u5 = new Usuario();
        u5.setId(504);
        u5.setNombre("Nombre");

        Usuario u6 = new Usuario();
        u6.setId(505);
        u6.setNombre("Nombre");
        
        List<Usuario> us = new ArrayList<Usuario>();
        us.add(u1);
        us.add(u2);
        us.add(u3);
        us.add(u4);
        us.add(u5);
        us.add(u6);
        eg.setEstadisticasGlobales(e);
        eg.setOcaRankingJugadores(us);
        eg.setOcaVecesCaidoEnMuerte(new ArrayList<Usuario>());
        eg.setParchisRankingJugadores(new ArrayList<Usuario>());
        eg.setParchisFichasComidas(new ArrayList<Usuario>());
        assertThrows(ConstraintViolationException.class,()->egr.save(eg));
    }
}
