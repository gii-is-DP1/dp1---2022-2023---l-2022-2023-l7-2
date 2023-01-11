package org.springframework.samples.petclinic.ocachis.jugador;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.ocachis.logro.LogroService;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MetaNegativaException;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MultiplesMetasDefinidasException;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.ocachis.partida.exceptions.PartidaLlenaException;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTest {

    @Autowired
    PartidaService ps;

    @Autowired
    JugadorService js;

    @MockBean
    UsuarioService usuarioService;

    PartidaOca partidaOca;
	PartidaParchis partidaParchis;
    
    @BeforeEach
	public void setup(){
		partidaOca = this.ps.findPartidaOcaById(3);
		partidaParchis = this.ps.findPartidaParchisById(1);
	}
    

    @Test
    void testFindById(){
    assertThrows(ResourceNotFoundException.class, ()->js.findById(1000), "No lanza la excepcion");
    assertNotNull(js.findById(6),"No encuentra el jugador");
    }

    @Test
    void testDeleteJugador(){
        Jugador jugador = new Jugador();
        jugador.setId(1);
        js.delete(jugador);
        assertThrows(ResourceNotFoundException.class, ()->js.findById(1), "El jugador no se borrar correctamente");
        
      
    }


    @Test
    void testSave(){
        Jugador j = new Jugador();
        js.save(j);
        j.setUsuario(new Usuario());
        assertTrue(j.getId()!=null,"No ha guardado el jugador");      
    }
     
    @Test
    void testFindAllJugadoresForUsuario(){
        Collection<Jugador> jugadores = js.findAllJugadoresForUsuario(3);
        assertTrue(jugadores.size()==2,"No encuentra todos los jugadores del usuario");
    }


    @Test
    void testFindJugadorParchis(){
        List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
      Jugador jugadorParchis = js.findJugadorParchis(j.getUsuario().getId(), partidaParchis.getId());
      assertTrue(jugadorParchis==j,"No se encuentra al jugador de Parchis correctamente");
        
    }
    

    @Test
    void testFindJugadorOca(){
        List<Jugador> jugadores = (List<Jugador>) partidaOca.getJugadores();
		Jugador j = jugadores.get(0);
      Jugador jugadorOca = js.findJugadorOca(j.getUsuario().getId(), partidaOca.getId());
      assertTrue(jugadorOca==j,"No se encuentra al jugador de Parchis correctamente");
    }

    @Test
    void testCreateJugadorOca() throws PartidaLlenaException{
        Usuario usuario = usuarioService.findUsuarioById(1);
        given(this.usuarioService.getLoggedUsuario()).willReturn(usuario);
        Jugador j = js.createJugadorOca(partidaOca);
        assertNotNull(j.getId());
        assertTrue(j.getColor()==Color.VERDE);
        assertTrue(j.getUsuario()==usuario);
        assertTrue(j.getPartidaOca()==partidaOca);
    }
    @Test
    void testCreateJugadorParchis() throws PartidaLlenaException{
        Usuario usuario = usuarioService.findUsuarioById(1);
        given(this.usuarioService.getLoggedUsuario()).willReturn(usuario);
        Jugador j = js.createJugadorParchis(partidaParchis);
        assertNotNull(j.getId());
        assertTrue(j.getColor()==Color.VERDE);
        assertTrue(j.getUsuario()==usuario);
        assertTrue(j.getPartidaParchis()==partidaParchis);
    }
    
    @Test
    void testGetColorNuevoJugadorOca(){
        Color color = js.getColorNuevoJugadorOca(partidaOca);
        assertTrue(color == Color.VERDE,"No escoge bien el nuevo color");
    }
    @Test
    void testGetColorNuevoJugadorParchis(){
        Color color = js.getColorNuevoJugadorParchis(partidaParchis);
        assertTrue(color == Color.VERDE,"No escoge bien el nuevo color");
    }
    
    
    @Test
    void testEstaDentroDePartida(){
        Integer usuarioId = 1;
        assertTrue(js.estaDentroPartida(usuarioId)); //Partida JUGANDO

        usuarioId = 3;
        assertFalse(js.estaDentroPartida(usuarioId)); //Partida CREADA

        usuarioId = 5;
        assertFalse(js.estaDentroPartida(usuarioId)); //No en partida
    }
    

    @Test
    void testEstaJugando(){
        Integer usuarioId = 1;
        assertTrue(js.estaJugando(usuarioId)); //Partida JUGANDO

        usuarioId = 3;
        assertTrue(js.estaJugando(usuarioId)); //Partida CREADA

        usuarioId = 5;
        assertFalse(js.estaJugando(usuarioId)); //No en partida
    }

    @Test
    void testFindIdPartidaEspectar(){
        List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
        Map<String,Integer> partidaEspectar = js.findIdPartidaEspectar(j.getUsuario().getId());
        assertTrue(partidaEspectar.get("parchis")==1,"no encuentra la partida a Espectar");

        
    }
}
