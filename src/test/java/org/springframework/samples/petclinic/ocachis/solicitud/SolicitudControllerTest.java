package org.springframework.samples.petclinic.ocachis.solicitud;



import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.expression.MapAccessor;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = SolicitudController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class SolicitudControllerTest {

    private static final int TEST_SOLICITUD_ID = 41;
    private static final int TEST_USUARIO_SOLICITANTE_ID = 42;
    private static final int TEST_USUARIO_SOLICITADO_ID = 43;

	@Autowired
	private SolicitudController solicitudController;

	@MockBean
	private UsuarioService usuarioService;
    
    @MockBean
	private JugadorService jugadorService;

	@MockBean
	private SolicitudService solicitudService;

	@Autowired
	private MockMvc mockMvc;

	private Solicitud solicitud;
    private Usuario usuarioSolicitante;
    private Usuario usuarioSolicitado;

	@BeforeEach
	public void setup(){
		solicitud = new Solicitud();
        solicitud.setId(TEST_SOLICITUD_ID);
        solicitud.setTipoEstado(TipoEstadoSolicitud.ACEPTADA);

        Collection<Solicitud> solicitudes  = new ArrayList<>();
        solicitudes.add(solicitud);

        usuarioSolicitante = new Usuario();
        usuarioSolicitante.setId(TEST_USUARIO_SOLICITANTE_ID);
        usuarioSolicitante.setSolicitudesEnvidas(solicitudes);
        
        usuarioSolicitado = new Usuario();
        usuarioSolicitado.setId(TEST_USUARIO_SOLICITADO_ID);
        usuarioSolicitado.setSolicitudesRecibidas(solicitudes); 

        solicitud.setUsuarioSolicitud(usuarioSolicitante);
        solicitud.setUsuarioInvitado(usuarioSolicitado);

        when(this.usuarioService.getLoggedUsuario()).thenReturn(usuarioSolicitado);
        given(this.usuarioService.findFiltroApodo("usuario", usuarioSolicitado.getId())).willReturn(new ArrayList<>());

        given(this.solicitudService.findAllAmigos(usuarioSolicitado.getId())).willReturn(new HashMap<Usuario,Boolean>());
		given(this.solicitudService.findById(TEST_SOLICITUD_ID)).willReturn(solicitud);
        given(this.solicitudService.findAllSolicitudes()).willReturn(solicitudes);
        given(this.solicitudService.findAllSolicitudesPendientes(TEST_USUARIO_SOLICITADO_ID)).willReturn(solicitudes);
        given(this.solicitudService.obtenerUrlEspectar(any(Map.class))).willReturn("URLMOCK");
        
        doNothing().when(this.solicitudService).aceptarSolicitud(TEST_SOLICITUD_ID);
        doNothing().when(this.solicitudService).rechazarSolicitud(TEST_SOLICITUD_ID);
        doNothing().when(this.solicitudService).agregarUsuario(TEST_USUARIO_SOLICITANTE_ID, TEST_USUARIO_SOLICITADO_ID);

        given(this.jugadorService.estaDentroPartida(usuarioSolicitante.getId())).willReturn(false);
        given(this.jugadorService.estaDentroPartida(usuarioSolicitado.getId())).willReturn(false);
        given(this.jugadorService.findIdPartidaEspectar(TEST_USUARIO_SOLICITANTE_ID)).willReturn(new HashMap<>());
	}

    @WithMockUser(value="spring")
    @Test
    public void testFindAmigos() throws Exception{
        mockMvc.perform(get("/solicitud/amigos"))
            .andExpect(status().isOk())
			.andExpect(view().name("solicitud/amigos"))
            .andExpect(model().attributeExists("amigos"));
    }

    @WithMockUser(value="spring")
    @Test
    public void testFindAmigosApodoFiltro() throws Exception{
        mockMvc.perform(get("/solicitud/amigos?apodoFiltro=usuario"))
            .andExpect(status().isOk())
			.andExpect(view().name("solicitud/amigos"))
            .andExpect(model().attributeExists("filtrados"))
            .andExpect(model().attributeExists("amigos"));
    }

    @WithMockUser(value="spring")
    @Test
    public void testsolicitudesPendientes() throws Exception{
        mockMvc.perform(get("/solicitud/pendientes"))
            .andExpect(status().isOk())
            .andExpect(view().name("solicitud/pendientes"))
            .andExpect(model().attributeExists("pendientes"));
    }

    @WithMockUser(value="spring")
    @Test
    public void testAceptarSolicitud() throws Exception{
        mockMvc.perform(get("/solicitud/pendientes/" + TEST_SOLICITUD_ID + "/aceptar"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/solicitud/pendientes"));
           
    }

    @WithMockUser(value="spring")
    @Test
    public void testRechazarSolicitud() throws Exception{
        mockMvc.perform(get("/solicitud/pendientes/"+ TEST_SOLICITUD_ID +"/rechazar"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/solicitud/pendientes"));
            
    }

    
    @WithMockUser(value="spring")
    @Test
    public void testAgregarUsuario() throws Exception{
        mockMvc.perform(get("/solicitud/amigos/"+ TEST_USUARIO_SOLICITADO_ID +"/agregar"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/solicitud/amigos"));
            
    }

    @WithMockUser(value="spring")
    @Test
    public void testEspectarUsuario() throws Exception{
        given(this.solicitudService.sePuedeEspectar(any(Integer.class), any(Map.class))).willReturn(true);
        mockMvc.perform(get("/solicitud/amigos/"+ TEST_USUARIO_SOLICITADO_ID +"/espectar"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:URLMOCK"));
            
    }

    @WithMockUser(value="spring")
    @Test
    public void testNoEspectarUsuario() throws Exception{
        given(this.solicitudService.sePuedeEspectar(any(Integer.class), any(Map.class))).willReturn(false);
        mockMvc.perform(get("/solicitud/amigos/"+ TEST_USUARIO_SOLICITADO_ID +"/espectar"))
            .andExpect(status().isOk())
            .andExpect(view().name("solicitud/amigos"))
            .andExpect(model().attribute("message", "No eres amigo de todos los jugadores de la partida"));            
    }
}
