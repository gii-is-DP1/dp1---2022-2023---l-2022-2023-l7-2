package org.springframework.samples.petclinic.ocachis.usuario;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.solicitud.SolicitudService;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UsuarioController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),excludeAutoConfiguration = SecurityConfiguration.class)
class UsuarioControllerTests {	
	
	private static final int TEST_USUARIO_ID = 100;
	
	@Autowired
	private UsuarioController usuarioController;

	@MockBean
	private UsuarioService usuarioService;

	@MockBean
	private SolicitudService solicitudService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private AuthoritiesService authoritiesService;
       
	@Autowired
	private MockMvc mockMvc;
	private Usuario auronplay;


	@BeforeEach
	void setup() {

		auronplay = new Usuario();
		auronplay.setId(TEST_USUARIO_ID);
		auronplay.setNombre("Auron");
		auronplay.setApellido("Play");
		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setOcaVecesCaidoEnMuerte(10);
		auronplay.setEstadisticas(estadisticas);
		given(this.usuarioService.findUsuarioById(TEST_USUARIO_ID)).willReturn(auronplay);		
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/usuarios/nuevo"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("usuario"))
		.andExpect(view().name("usuarios/createOrUpdateUsuarioForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/usuarios/nuevo")
				.param("nombre", "Auron")
				.param("apellido", "Play")
				.param("user.password", "usuario1233")
				.param("user.username", "usuario123")
				.with(csrf()))
		.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormInvalid() throws Exception {
		mockMvc.perform(post("/usuarios/nuevo")
					.param("nombre", "")
					.param("apellido", "apellido")
					.param("user.username","usernameNoRepetido")
					.param("user.password","passwordValida")
					.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("usuario"))
				.andExpect(model().attributeHasFieldErrors("usuario", "nombre"))
				.andExpect(view().name("usuarios/createOrUpdateUsuarioForm"));
	}
}
