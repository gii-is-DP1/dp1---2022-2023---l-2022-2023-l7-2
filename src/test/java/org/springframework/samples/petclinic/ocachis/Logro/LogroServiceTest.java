package org.springframework.samples.petclinic.ocachis.Logro;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.ocachis.logro.LogroService;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MetaNegativaException;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MultiplesMetasDefinidasException;
import org.springframework.samples.petclinic.util.EntityUtils;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LogroServiceTest {
    @Autowired
    LogroService ls;

    @Test
	void shouldFindLogroWithCorrectId() {
		Logro logro1 = this.ls.findById(1);
		assertThat(logro1.getNombre()).isEqualTo("Jugador Oca Junior");
		assertThat(logro1.getDescripcion()).isEqualTo("Juega 10 partidas de la Oca");
	}
    @Test
	void shouldFindAllLogros() {
		Collection<Logro> logros = this.ls.findAllLogros();
        Logro logro = EntityUtils.getById(logros, Logro.class, 1);
		assertThat(logro.getNombre()).isEqualTo("Jugador Oca Junior");
		assertThat(logro.getDescripcion()).isEqualTo("Juega 10 partidas de la Oca");
	}
    @Test 
    void shouldSaveLogro() {
        int numeroLogrosInicial = this.ls.findAllLogros().size();
		Logro logro = new Logro();
		logro.setNombre("Test Logro Correcto");
		logro.setDescripcion("Logro de prueba para inserción");
		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setOcaPartidasGanadas(1);
		logro.setEstadisticasACumplir(estadisticas);
		try {
            this.ls.saveLogro(logro);
        } catch (IllegalAccessException | MultiplesMetasDefinidasException | MetaNegativaException e) {
            e.printStackTrace();
        }
		            
	
		assertThat(logro.getId().longValue()).isNotEqualTo(0);
        int numeroLogrosFinal = this.ls.findAllLogros().size();
		assertThat(numeroLogrosFinal).isEqualTo(numeroLogrosInicial +1);        
    }
    
    @Test
    void shouldThrowMultiplesMetas(){  
		Logro logro = new Logro();
		logro.setNombre("Test Logro Incorrecto");
		logro.setDescripcion("Logro de prueba para inserción");
		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setOcaPartidasGanadas(1);
        estadisticas.setOcaPartidasJugadas(2);
		logro.setEstadisticasACumplir(estadisticas);
		assertThrows(MultiplesMetasDefinidasException.class, () -> ls.saveLogro(logro),"Permite establecer varias metas");
    }
    
    @Test
    void shouldThrowMetaNegativa(){
 
		Logro logro = new Logro();
		logro.setNombre("Test Logro Incorrecto");
		logro.setDescripcion("Logro de prueba para inserción");
		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setOcaPartidasGanadas(-1);
        
		logro.setEstadisticasACumplir(estadisticas);
		assertThrows(MetaNegativaException.class, () -> ls.saveLogro(logro),"Permite establecer metas negativas");
        
    }




}
