package org.springframework.samples.petclinic.ocachis.ficha;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaService;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FichaServiceTest {

    @Autowired
    PartidaService ps;

    @Autowired
    FichaService fs;

    @MockBean
    UsuarioService usuarioService;

    @Autowired
    CasillaService cs;

    PartidaOca partidaOca;
	PartidaParchis partidaParchis;
    
    @BeforeEach
	public void setup(){
		partidaOca = this.ps.findPartidaOcaById(3);
		partidaParchis = this.ps.findPartidaParchisById(1);
	}

    @Test
    void testRemoveFichaOca(){
        FichaOca ficha = new FichaOca();
        ficha.setId(40);
        fs.removeFichaOca(ficha);
        assertThrows(ResourceNotFoundException.class,() -> fs.findFichaOca(40));
    }

    @Test
    void testRemoverFichaParchis(){
        FichaParchis ficha = new FichaParchis();
        ficha.setId(40);
        fs.removeFichaParchis(ficha);
        assertThrows(ResourceNotFoundException.class,() -> fs.findFichaOca(40));
    }
   @Test 
   void testFindFichaOca(){
    assertThrows(ResourceNotFoundException.class, ()->fs.findFichaOca(1000), "No lanza la excepcion");
    assertNotNull(fs.findFichaOca(1),"No encuentra la ficha");
   }

   @Test 
   void testFindFichaParchis(){
    assertThrows(ResourceNotFoundException.class, ()->fs.findFichaParchis(1000), "No lanza la excepcion");
    assertNotNull(fs.findFichaParchis(1),"No encuentra la ficha");
   }
    
    @Test
    void testSaveFichaOca(){
        FichaOca ficha = new FichaOca();
        CasillaOca casilla = new CasillaOca();
        casilla.setNumero(2);
        cs.saveCasillaOca(casilla);
        ficha.setCasillaActual(casilla);
        
        fs.saveFichaOca(ficha);
        assertTrue(ficha.getId()!=null,"No ha guardado la ficha de Oca");

    }

    @Test
    void testSaveFichaParchis(){
        FichaParchis ficha = new FichaParchis();
        CasillaParchis casilla = new CasillaParchis();
       
        casilla.setNumero(2);
        cs.saveCasillaParchis(casilla);
        ficha.setCasillaActual(casilla);
        
        fs.saveFichaParchis(ficha);
        assertTrue(ficha.getId()!=null,"No ha guardado la ficha de Oca");
    }

    @Test
    void testMoverFichaOca(){
        FichaOca ficha = new FichaOca();
        CasillaOca casilla = new CasillaOca();
       
        casilla.setNumero(30);
        cs.saveCasillaOca(casilla);
        List<Jugador> jugadores = (List<Jugador>) partidaOca.getJugadores();
		Jugador j = jugadores.get(0);
        fs.moverFichaOca(ficha, casilla, j);
        assertThat(ficha.getCasillaActual()==casilla);
        assertThat(j.getFichaOca()==ficha);
    }
    @Test
    void testCreateFichaOca(){
        List<Jugador> jugadores = (List<Jugador>) partidaOca.getJugadores();
		Jugador jugador = jugadores.get(0);
        FichaOca ficha = fs.createFichaOca(jugador, partidaOca);
        assertThat(ficha.getColor()==Color.ROJO);
        assertThat(ficha.getCasillaActual().getNumero()==1);


    }

    @Test
    void testCreateFichasParchis(){
        List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador jugador = jugadores.get(0);
        List<FichaParchis> fichas = fs.createFichasParchis(jugador, partidaParchis);
        assertThat(fichas.size()==4);
        for (FichaParchis ficha : fichas){
            assertThat(ficha.isEstaEnCasa()==true);
            assertThat(ficha.isEstaEnLaMeta()==false);
            
        }
    }

    @Test
    void testMoverFichaParchis(){
        List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
        List<FichaParchis> fichasIniciales = new ArrayList<>((List<FichaParchis>) j.getFichasParchis());
        FichaParchis ficha = fichasIniciales.get(0);
        CasillaParchis casillaFinal = partidaParchis.getCasillaConNumero(30);   
        fs.moverFichaParchis(ficha, casillaFinal, j);
        List<FichaParchis> fichasFinales = (List<FichaParchis>)j.getFichasParchis();
        FichaParchis fichaNueva = fichasFinales.get(3); 
        assertFalse(j.getFichasParchis().contains(ficha));
        assertThat(fichaNueva.getCasillaActual()==casillaFinal);
        
    }
}


   

    

   

