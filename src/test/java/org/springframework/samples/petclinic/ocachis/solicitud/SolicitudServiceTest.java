package org.springframework.samples.petclinic.ocachis.solicitud;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@RunWith(SpringRunner.class)
public class SolicitudServiceTest {

    
    @Autowired
    SolicitudService solicitudService;


    private static final Integer SOLICITUD_ID = 1;
    


    @Test
	public void injectionsTests() {
    assertNotNull(solicitudService, "No injecta el servicio");
    }


    

    @Test
    public void saveSolicitud(){
        int tamañoInicial = this.solicitudService.findAllSolicitudes().size();
        Solicitud s = new Solicitud();
        s.setTipoEstado(TipoEstadoSolicitud.RECHAZADA);
        this.solicitudService.save(s);

        int tamañoFinal = this.solicitudService.findAllSolicitudes().size();
        assertTrue(tamañoFinal == tamañoInicial +1, "No ha guardado la solicitud");
        assertThat(s.getId().longValue()).isNotEqualTo(0);
        assertThat(s.getTipoEstado()==TipoEstadoSolicitud.RECHAZADA);
    }

    @Test
    public void findById(){
        Solicitud s = solicitudService.findById(SOLICITUD_ID);
        assertThat(s.getTipoEstado()==TipoEstadoSolicitud.ENVIADA);
        assertThat(s.getUsuarioInvitado().getId()==3);
        assertThat(s.getUsuarioSolicitud().getId()==4);
    }

    @Test
	public void shouldFindAllSolicitudes() {
		Collection<Solicitud> solicitudes = this.solicitudService.findAllSolicitudes();
        assertTrue(solicitudes.size()==7, "No encuentra todas las solicitudes de la base de datos");
        Solicitud s = EntityUtils.getById(solicitudes, Solicitud.class, 1);
		assertTrue(s.getUsuarioInvitado().getId()==3 && s.getUsuarioSolicitud().getId()==4, "No encuentra las solicitudes de la base de datos");
	}

    @Test
    public void findAllAmigos(){
        Integer usuarioSolicitante =1;
        Integer usuarioSolicitado = 3;

        Map<Usuario,Boolean> amigos =  this.solicitudService.findAllAmigos(usuarioSolicitado);
        assertTrue(amigos.entrySet().stream().filter(entry-> entry.getKey().getId()==usuarioSolicitante).findFirst().get().getValue(), "No encuentra los amigos de un usuario");
        assertTrue(amigos.size()==1,"Encuentra mas de un amigo para el usuario");
    } 

    @Test
    public void aceptarSolicitud(){
        Solicitud s = this.solicitudService.findById(SOLICITUD_ID);
        TipoEstadoSolicitud estadoInicial = s.getTipoEstado();
        assertThat(estadoInicial==TipoEstadoSolicitud.ENVIADA);

        this.solicitudService.aceptarSolicitud(SOLICITUD_ID);
        assertThat(estadoInicial==TipoEstadoSolicitud.ACEPTADA);     

    }

    @Test
    public void rechazarSolicitud(){
        Solicitud s = this.solicitudService.findById(SOLICITUD_ID);
        TipoEstadoSolicitud estadoInicial = s.getTipoEstado();
        assertThat(estadoInicial==TipoEstadoSolicitud.ENVIADA);

        this.solicitudService.rechazarSolicitud(SOLICITUD_ID);
        assertThat(estadoInicial==TipoEstadoSolicitud.RECHAZADA);     

    }

    @Test
    public void crearSolicitud(){
        Integer usuario1Id = 1;
        Integer usuario4Id = 4;
        int tamañoInicial = this.solicitudService.findAllSolicitudes().size();
        solicitudService.agregarUsuario( usuario1Id, usuario4Id);
        int tamañoFinal = this.solicitudService.findAllSolicitudes().size();
        assertTrue(tamañoFinal == tamañoInicial +1, "No ha guardado la solicitud");
    }

    @Test
    public void testSonAmigos(){
        Integer usuarioAmigo1Id = 1;
        Integer usuarioAmigo2Id = 3;
        Integer usuarioNoAmigo = 5;
        assertTrue(solicitudService.sonAmigos(usuarioAmigo1Id, usuarioAmigo2Id));
        assertFalse(solicitudService.sonAmigos(usuarioAmigo1Id, usuarioNoAmigo));
    }
}
