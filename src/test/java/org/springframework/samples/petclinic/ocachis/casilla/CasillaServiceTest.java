package org.springframework.samples.petclinic.ocachis.casilla;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaOca;
import org.springframework.samples.petclinic.ocachis.casilla.TipoCasillaParchis;
import org.springframework.samples.petclinic.ocachis.ficha.Ficha;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.util.EntityUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CasillaServiceTest {
    
    @Autowired
    CasillaService casillaService;

    private static final Integer PARTIDA_OCA_ID = 3;
    private static final Integer PARTIDA_PARCHIS_ID = 1;


    @Test
    void testSaveCasillaOca(){
        CasillaOca casilla = new CasillaOca();
        casilla.setNumero(1);
        casillaService.saveCasillaOca(casilla);
        assertNotNull(casilla.getId());
        assertTrue(casilla.getId()>0);
    }

    @Test
    void testSaveCasillaParchis(){
        CasillaParchis casilla = new CasillaParchis();
        casilla.setNumero(1);
        casillaService.saveCasillaParchis(casilla);
        assertNotNull(casilla.getId());
        assertTrue(casilla.getId()>0);
    }


    @Test
    void testFindCasillaOcaById(){
        assertThrows(ResourceNotFoundException.class,() -> casillaService.findCasillaOcaById(12312));
        CasillaOca casilla = casillaService.findCasillaOcaById(1);
        assertNotNull(casilla.getId());
        assertNotNull(casilla.getNumero());
    }
}
