package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.logro.LogroRepository;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EstadisticasGlobalesServiceTest {

    @Autowired
    EstadisticasGlobalesService egs;
   

    @Test
    void TestGetEstadisticasGlobales(){
        EstadisticasGlobales eg = egs.getEstadisticasGlobales();
        assertNotNull(eg);
        assertTrue(eg.getId()==1);
    }
    @Test
    void testSaveEstadisticasGlobales(){
        EstadisticasGlobales eg = new EstadisticasGlobales();
        eg.setEstadisticasGlobales(new Estadisticas());
        eg.setId(1);
        egs.save(eg);
        EstadisticasGlobales eg2 = egs.getEstadisticasGlobales();
        assertNull(eg2.getOcaRankingJugadores());
        assertTrue(eg2.getId()==1);

    }

    @Test
    void testUpdateEstadisticasGlobalesPartidaParchis(){
        Integer duracionGlobalAntigua = egs.getEstadisticasGlobales().getEstadisticasGlobales().getParchisDuracionTotal();
        Integer fichasComidasAntigua = egs.getEstadisticasGlobales().getEstadisticasGlobales().getParchisFichasComidas();
        PartidaParchis partida = new PartidaParchis();
        partida.setDuracion(500);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador j1 = new Jugador();
        j1.setFichasComidas(30);
        jugadores.add(j1);
        Jugador j2 = new Jugador();
        j2.setFichasComidas(30);
        jugadores.add(j2);
        Jugador j3 = new Jugador();
        j3.setFichasComidas(30);
        jugadores.add(j3);
        Jugador j4 = new Jugador();
        j4.setFichasComidas(30);
        jugadores.add(j4);
        partida.setJugadores(jugadores);

        egs.updateEstadisticasGlobalesPartidaParchis(partida);

        Integer duracionGlobalNueva = egs.getEstadisticasGlobales().getEstadisticasGlobales().getParchisDuracionTotal();
        Integer fichasComidasNueva = egs.getEstadisticasGlobales().getEstadisticasGlobales().getParchisFichasComidas();
        
        
        assertTrue(egs.getEstadisticasGlobales().getEstadisticasGlobales().getParchisDuracionMaxima() == 500);
        assertTrue(duracionGlobalNueva == duracionGlobalAntigua + 500);
        assertTrue(fichasComidasNueva == fichasComidasAntigua + 120);
    }
    


    @Test 
    void testUpdateEstadisticasGlobalesPartidaOca(){
        PartidaOca partida = new PartidaOca();
        partida.setDuracion(500);
        EstadisticasGlobales eg = egs.getEstadisticasGlobales();
        Integer duracionTotalAntigua = eg.getEstadisticasGlobales().getOcaDuracionTotal();
        Integer vecesCaidasEnMuerteAntigua = eg.getEstadisticasGlobales().getOcaVecesCaidoEnMuerte();
        
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador j1 = new Jugador();
        j1.setVecesCaidoEnMuerte(2);
        jugadores.add(j1);

        Jugador j2 = new Jugador();
        j2.setVecesCaidoEnMuerte(3);
        jugadores.add(j2);

        partida.setJugadores(jugadores);

        egs.updateEstadisticasGlobalesPartidaOca(partida);

        
        Integer duracionTotalNueva = egs.getEstadisticasGlobales().getEstadisticasGlobales().getOcaDuracionTotal();
        Integer vecesCaidasEnMuerteNueva = egs.getEstadisticasGlobales().getEstadisticasGlobales().getOcaVecesCaidoEnMuerte();
        
        assertTrue(egs.getEstadisticasGlobales().getEstadisticasGlobales().getOcaDuracionMaxima() == 500);
        assertTrue(duracionTotalNueva == duracionTotalAntigua+500);
        assertTrue(vecesCaidasEnMuerteNueva == vecesCaidasEnMuerteAntigua+5);
        
        
        
    }



    
}
