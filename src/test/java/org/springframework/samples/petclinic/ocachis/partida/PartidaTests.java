package org.springframework.samples.petclinic.ocachis.partida;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.assertThat;

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
	@BeforeEach
	public void setup(){
		partidaOca = this.ps.findPartidaOcaById(3);
		partidaOca.setEstado(TipoEstadoPartida.JUGANDO);
		partidaParchis = this.ps.findPartidaParchisById(1);
	}
    @Test
    public void partidaTest(){
        ocarepositoryExists();
        parchisrepositoryExists();
        testConstraints();
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
        
        po.setId(400);
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
        
        assertThrows(ConstraintViolationException.class,() -> or.save( po),
        "El mínimo de jugadores es 2");
        
        po.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> or.save(po),
        "El máximo de jugadores es 4");



        PartidaParchis pp = new PartidaParchis();
        pp.setId(400);
        pp.setMaxJugadores(1);

        assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        "El mínimo de jugadores es 2");

        pp.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        "El máximo de jugadores es 4");
        
    }

    
    void testPasarTurnoParchisRojoAmarillo(){
        PartidaParchis pp = new PartidaParchis();

        List<Jugador> js = new ArrayList<Jugador>();
        Jugador j1 = new Jugador();
        j1.setColor(Color.ROJO);
        js.add(j1);

        Jugador j2 = new Jugador();
        j2.setColor(Color.AMARILLO);
        js.add(j2);

        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.ROJO);
        pp.pasarTurno();
        assertEquals(Color.AMARILLO,pp.getColorJugadorActual());
    }

    void testPasarTurnoParchisAmarilloVerde(){
        PartidaParchis pp = new PartidaParchis();
        List<Jugador> js = new ArrayList<Jugador>();
        
        Jugador j1 = new Jugador();
        j1.setColor(Color.ROJO);
        js.add(j1);

        Jugador j2 = new Jugador();
        j2.setColor(Color.AMARILLO);
        js.add(j2);

        Jugador j3 = new Jugador();
        j3.setColor(Color.VERDE);
        js.add(j3);

        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.AMARILLO);
        pp.pasarTurno();
        assertEquals(Color.VERDE,pp.getColorJugadorActual());
    }


    void testPasarTurnoParchisVerdeAzul(){
        PartidaParchis pp = new PartidaParchis();
        List<Jugador> js = new ArrayList<Jugador>();
        
        Jugador j1 = new Jugador();
        j1.setColor(Color.ROJO);
        js.add(j1);

        Jugador j2 = new Jugador();
        j2.setColor(Color.AMARILLO);
        js.add(j2);

        Jugador j3 = new Jugador();
        j3.setColor(Color.VERDE);
        js.add(j3);

        Jugador j4 = new Jugador();
        j4.setColor(Color.AZUL);
        js.add(j4);

        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.VERDE);
        pp.pasarTurno();
        assertEquals(Color.AZUL,pp.getColorJugadorActual());
    }

    void testPasarTurnoOcaRojoAmarillo(){
        PartidaOca po = new PartidaOca();
        po.setColorJugadorActual(Color.ROJO);
       
        List<Jugador> js = new ArrayList<Jugador>();
        Jugador j1 = new Jugador();
        j1.setColor(Color.ROJO);
        js.add(j1);

        Jugador j2 = new Jugador();
        j2.setColor(Color.AMARILLO);
        js.add(j2);

        po.setJugadores(js);


        po.pasarTurno();
        assertEquals(Color.AMARILLO,po.getColorJugadorActual());
    }

    void testPasarTurnoOcaAmarilloVerde(){
        PartidaOca pp = new PartidaOca();
        List<Jugador> js = new ArrayList<Jugador>();
        
        Jugador j1 = new Jugador();
        j1.setColor(Color.ROJO);
        js.add(j1);

        Jugador j2 = new Jugador();
        j2.setColor(Color.AMARILLO);
        js.add(j2);

        Jugador j3 = new Jugador();
        j3.setColor(Color.VERDE);
        js.add(j3);

        pp.setJugadores(js);
        pp.setColorJugadorActual(Color.AMARILLO);
        pp.pasarTurno();
        assertEquals(Color.VERDE,pp.getColorJugadorActual());
    }

    void testPasarTurnoOcaVerdeAzul(){
        PartidaOca pp = new PartidaOca();
        List<Jugador> js = new ArrayList<Jugador>();
        
        Jugador j1 = new Jugador();
        j1.setColor(Color.ROJO);
        js.add(j1);

        Jugador j2 = new Jugador();
        j2.setColor(Color.AMARILLO);
        js.add(j2);

        Jugador j3 = new Jugador();
        j3.setColor(Color.VERDE);
        js.add(j3);

        Jugador j4 = new Jugador();
        j4.setColor(Color.AZUL);
        js.add(j4);

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
		
		pp.addMensaje("prueba", j);
		assertThat(pp.printChatParchis().equals("usuario(ROJO): prueba"));
	}
    @Test
    void testLogOca(){
        PartidaOca po = this.ps.findPartidaOcaById(3);
		List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
        CasillaOca casillaFinal = po.getCasillaConNumero(8);
        po.addLog("Mueve una ficha hasta la casilla " + casillaFinal.getNumero());
        assertThat(po.printLog().equals("Mueve una ficha hasta la casilla " + casillaFinal.getNumero()));

    }

    @Test
    void testLogParchis(){
        PartidaParchis pp = this.ps.findPartidaParchisById(1);
		List<Jugador> jugadores = (List<Jugador>) partidaParchis.getJugadores();
		Jugador j = jugadores.get(0);
        CasillaParchis casillaFinal = pp.getCasillaConNumero(8);
        pp.addLog("Mueve una ficha hasta la casilla " + casillaFinal.getNumero());
        assertThat(pp.printLog().equals("Mueve una ficha hasta la casilla " + casillaFinal.getNumero()));

    }

}