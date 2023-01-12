package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
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

    
    @Autowired
EstadisticasGlobalesService egs;


    @Test
    void testEstadisticasGlobales(){
        checkRepositoryExists();
        testConstraints();
    }
    void checkRepositoryExists(){
        assertNotNull(egr, "No injecta el repositorio correctamente");

    }


    void testConstraints(){
        EstadisticasGlobales eg = egs.getEstadisticasGlobales();
        assertTrue(eg.getId()==1);
        List<Usuario> prueba = eg.getOcaRankingJugadores();
        prueba.add(eg.getOcaVecesCaidoEnMuerte().get(3));
        eg.setOcaRankingJugadores(prueba);
        assertThrows(ConstraintViolationException.class,()->egr.save(eg));

        
        

    }
}
