package org.springframework.samples.petclinic.ocachis.partida;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOcaRepository;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchisRepository;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))


public class PartidaTests {
    @Autowired
    PartidaOcaRepository or;

    @Autowired
    PartidaParchisRepository pr;

    @Test
    public void partidaTest(){
        ocarepositoryExists();
        parchisrepositoryExists();
        testConstraints();
        testPasarTurnoRojoAmarillo();
        testPasarTurnoAmarilloVerde();
        testPasarTurnoVerdeAzul();
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
        po.setMaxJugadores(1);
        po.setEstado(TipoEstadoPartida.CREADA);
        
        assertThrows(ConstraintViolationException.class,() -> or.save(po),
        "El mínimo de jugadores es 2");
        
        po.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> or.save(po),
        "El máximo de jugadores es 4");
        
        pp.setId(4);
        pp.setMaxJugadores(1);
        pp.setEstado(TipoEstadoPartida.CREADA);
        
        // assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        // "El mínimo de jugadores es 2");

        pp.setMaxJugadores(5);
        assertThrows(ConstraintViolationException.class,() -> pr.save(pp),
        "El máximo de jugadores es 4");
    }
/*
    void testConstraints(){
        RecoveryRoom room=new RecoveryRoom();
        room.setId(7);
        room.setName("Comfy open bed");
        room.setSize(-10.50);
        assertThrows(ConstraintViolationException.class,() -> rr.save(room),
        "You are not constraining "+
        "room size to positive values");
        room.setSize(10.0);
        room.setName("ja");
        assertThrows(ConstraintViolationException.class,() -> rr.save(room),
        "You are not constraining name size to at least 3 characters"
        );
        room.setName("En un lugar de la mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.");
        assertThrows(ConstraintViolationException.class,() -> rr.save(room),
        "you are not constraining name size to less than 51 characters");
    }
*/
    void testPasarTurnoRojoAmarillo(){
        PartidaParchis pp = new PartidaParchis();
        pp.setColorJugadorActual(Color.ROJO);
        pp.pasarTurno();
        assertEquals(Color.AMARILLO,pp.getColorJugadorActual());
    }

    void testPasarTurnoAmarilloVerde(){
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

    void testPasarTurnoVerdeAzul(){
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

}