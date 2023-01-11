package org.springframework.samples.petclinic.ocachis.jugador;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.logro.LogroRepository;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;






@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorTest {

    @Autowired
    JugadorRepository jr;
    @Autowired
    PartidaService ps;


	PartidaOca partidaOca;
	PartidaParchis partidaParchis;

    @BeforeEach
	public void setup(){
		partidaOca = this.ps.findPartidaOcaById(3);
		partidaOca.setEstado(TipoEstadoPartida.JUGANDO);
		partidaParchis = this.ps.findPartidaParchisById(1);
	}
    @Test
    public void jugadorTest(){
        repositoryExists();
        
       
    }
    
    public void repositoryExists(){
       assertNotNull(jr,"The repository was not injected into the tests, its autowired value was null");
    }
    
    @Test
    void añadirFichaParchisJugador(){
        FichaParchis fichaParchis = new FichaParchis();
        fichaParchis.setId(40);
        Jugador jugador = new Jugador();
        jugador.setColor(Color.ROJO);
        jugador.setFichasParchis(new ArrayList<>());
        jugador.addFichaParchis(fichaParchis);
        
        
        assertEquals(jugador.getFichasParchis().size(), 1);

    }
    @Test
    void eliminarFichaParchisJugador(){
        FichaParchis fichaParchis = new FichaParchis();
        fichaParchis.setId(40);
        CasillaParchis casilla = new CasillaParchis();
        List<FichaParchis> fichas = new ArrayList<>();
        fichas.add(fichaParchis);

        casilla.setFichas(fichas);
        fichaParchis.setCasillaActual(casilla);
        Jugador jugador = new Jugador();
        jugador.setColor(Color.ROJO);
        jugador.setFichasParchis(fichas);
        jugador.deleteFichaParchis(fichaParchis);
        assertEquals(jugador.getFichasParchis().size(), 0);
        
    }


    @Test
    void testFinalizarPartidaParchis(){
        Jugador jugador = new Jugador();
        Usuario usuario = new Usuario();
        usuario.setEstadisticas(new Estadisticas());
        jugador.setUsuario(usuario);
        jugador.setFichasComidas(10);
        jugador.setEsGanador(true);

        jugador.finalizarPartidaParchis(5);

        assertTrue(jugador.getUsuario().getEstadisticas().getParchisDuracionTotal()==5,"No se actualiza correctamente la duracion total");
        assertTrue(jugador.getUsuario().getEstadisticas().getParchisDuracionMedia()==5,"No se actualiza correctamente la duracion media");
        assertTrue(jugador.getUsuario().getEstadisticas().getParchisDuracionMinima()==5,"No se actualiza correctamente la duracion minima");
        assertTrue(jugador.getUsuario().getEstadisticas().getParchisDuracionMaxima()==5,"No se actualiza correctamente la duracion maxima");
        assertTrue(jugador.getUsuario().getEstadisticas().getParchisFichasComidas()==10,"No se actualiza correctamente las fichas comidas");
        assertTrue(jugador.getUsuario().getEstadisticas().getParchisPartidasGanadas()==1,"No se actualiza correctamente las partidas ganadas");
        assertTrue(jugador.getUsuario().getEstadisticas().getParchisPartidasJugadas()==1,"No se actualiza correctamente las partidas jugadas");
        
      

        
    }
    @Test
    void testFinalizarPartidaOca(){
        Jugador jugador = new Jugador();
        Usuario usuario = new Usuario();
        usuario.setEstadisticas(new Estadisticas());
        jugador.setUsuario(usuario);
        jugador.setVecesCaidoEnMuerte(2);
        jugador.setEsGanador(true);
        jugador.finalizarPartidaOca(5);

        assertTrue(jugador.getUsuario().getEstadisticas().getOcaVecesCaidoEnMuerte()==2,"No se actualiza correctamente las veces caida en muerte");
        assertTrue(jugador.getUsuario().getEstadisticas().getOcaPartidasGanadas()==1,"No se actualiza correctamente las partida de Oca ganadas");    
        assertTrue(jugador.getUsuario().getEstadisticas().getOcaDuracionTotal()==5,"No se actualiza correctamente la duracion total");
        assertTrue(jugador.getUsuario().getEstadisticas().getOcaDuracionMedia()==5,"No se actualiza correctamente la duracion media");
        assertTrue(jugador.getUsuario().getEstadisticas().getOcaDuracionMinima()==5,"No se actualiza correctamente la duracion minima");
        assertTrue(jugador.getUsuario().getEstadisticas().getOcaDuracionMaxima()==5,"No se actualiza correctamente la duracion maxima");   
        assertTrue(jugador.getUsuario().getEstadisticas().getOcaPartidasJugadas()==1,"No se actualiza correctamente las partidas jugadas");        
    }

    @Test
    public void testGetFichasQuePuedenMoverse(){
        Jugador jugador = partidaParchis.getJugadores().stream().filter(j->j.getColor()==Color.ROJO).findAny().get();
        Integer dado = 5;
        List<FichaParchis> fichasPuedenMoverse = jugador.getFichasQuePuedenMoverse(dado);
        assertTrue(fichasPuedenMoverse.size()==1,"No calcula correctamente las fichas que pueden moverse");
        dado = 1;
        fichasPuedenMoverse = jugador.getFichasQuePuedenMoverse(dado);
        assertTrue(fichasPuedenMoverse.size()==3,"No calcula correctamente las fichas que pueden moverse");

        dado = 3;
        fichasPuedenMoverse = jugador.getFichasQuePuedenMoverse(dado);
        assertTrue(fichasPuedenMoverse.size()==2,"No calcula correctamente las fichas que pueden moverse");
    }
  
  

}






