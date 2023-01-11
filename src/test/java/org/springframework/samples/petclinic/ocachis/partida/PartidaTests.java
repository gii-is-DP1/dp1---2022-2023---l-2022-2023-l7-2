package org.springframework.samples.petclinic.ocachis.partida;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaTests {
    @Autowired
    PartidaOcaRepository or;

    @Autowired
    PartidaParchisRepository pr;

     @Autowired
    PartidaService ps;

    PartidaOca partidaOca;
    PartidaParchis partidaParchis;

    @Test
    public void partidaTest(){
        ocarepositoryExists();
        parchisrepositoryExists();
        // testConstraints();
        testPasarTurnoParchisRojoAmarillo();
        testPasarTurnoParchisAmarilloVerde();
        testPasarTurnoParchisVerdeAzul();
        testPasarTurnoOcaRojoAmarillo();
        testPasarTurnoOcaAmarilloVerde();
        testPasarTurnoOcaVerdeAzul();
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
        po.setEstado(TipoEstadoPartida.CREADA);
        po.setMaxJugadores(1);
        po.setCasillas(new ArrayList<>());
        po.setChatOca(new ArrayList<>());
        po.setLog(new ArrayList<String>());
        po.setCodigoPartida(null);
        po.setColorJugadorActual(Color.ROJO);
        po.setDuracion(1);
        po.setFechaCreacion(LocalDateTime.now());
        po.setFechaHoraUltimoMovimiento(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        po.setGanador(null);
        po.setJugadores(new ArrayList<Jugador>());

        // assertDoesNotThrow(()-> or.save(po));
        assertThrows(ConstraintViolationException.class,() -> or.save( po),
        "EL codigo partida no puede ser nulo");
        

        assertThrows(ConstraintViolationException.class,() -> or.save( po),
        "El mínimo de jugadores es 2");
        
        po.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> or.save(po),
        "El máximo de jugadores es 4");
        
        pp.setId(4);
        pp.setMaxJugadores(1);
        pp.setEstado(TipoEstadoPartida.CREADA);
        
        assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        "El mínimo de jugadores es 2");

        pp.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        "El máximo de jugadores es 4");
    }

    
    void testPasarTurnoParchisRojoAmarillo(){
        PartidaParchis pp = new PartidaParchis();
        pp.setColorJugadorActual(Color.ROJO);
        pp.pasarTurno();
        assertEquals(Color.AMARILLO,pp.getColorJugadorActual());
    }

    void testPasarTurnoParchisAmarilloVerde(){
        PartidaParchis pp = new PartidaParchis();
        List<Jugador> js = new ArrayList<Jugador>();
        js.add(new Jugador());
        js.add(new Jugador());
        js.add(new Jugador());
        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.AMARILLO);
        pp.pasarTurno();
        assertEquals(Color.VERDE,pp.getColorJugadorActual());
    }

    void testPasarTurnoParchisVerdeAzul(){
        PartidaParchis pp = new PartidaParchis();
        List<Jugador> js = new ArrayList<Jugador>();
        js.add(new Jugador());
        js.add(new Jugador());
        js.add(new Jugador());
        js.add(new Jugador());
        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.VERDE);
        pp.pasarTurno();
        assertEquals(Color.AZUL,pp.getColorJugadorActual());
    }

    void testPasarTurnoOcaRojoAmarillo(){
        PartidaOca po = new PartidaOca();
        po.setColorJugadorActual(Color.ROJO);
        po.pasarTurno();
        assertEquals(Color.AMARILLO,po.getColorJugadorActual());
    }

    void testPasarTurnoOcaAmarilloVerde(){
        PartidaOca pp = new PartidaOca();
        List<Jugador> js = new ArrayList<Jugador>();
        js.add(new Jugador());
        js.add(new Jugador());
        js.add(new Jugador());
        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.AMARILLO);
        pp.pasarTurno();
        assertEquals(Color.VERDE,pp.getColorJugadorActual());
    }

    void testPasarTurnoOcaVerdeAzul(){
        PartidaOca pp = new PartidaOca();
        List<Jugador> js = new ArrayList<Jugador>();
        js.add(new Jugador());
        js.add(new Jugador());
        js.add(new Jugador());
        js.add(new Jugador());
        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.VERDE);
        pp.pasarTurno();
        assertEquals(Color.AZUL,pp.getColorJugadorActual());
    }

    @Test 
	void testEnviarMensajeOca(){
		PartidaOca po = this.ps.findPartidaOcaById(3);
		List<Jugador> jugadores = (List<Jugador>) partidaOca.getJugadores();
		Jugador j = jugadores.get(0);
		
		
		po.addMensaje("prueba", j);
		
		assertThat(po.printChatOca().equals("Pepe(ROJO): prueba"));
	}
    
    @Test 
	void testEnviarMensajeParchis(){
		PartidaParchis pp = this.ps.findPartidaParchisById(1);
		List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
		Integer tamañoInicial = pp.getChatParchis().size();
		
		pp.addMensaje("prueba", j);
		Integer tamañoFinal = pp.getChatParchis().size();
		assertThat(tamañoFinal == tamañoInicial+1);
	}

}