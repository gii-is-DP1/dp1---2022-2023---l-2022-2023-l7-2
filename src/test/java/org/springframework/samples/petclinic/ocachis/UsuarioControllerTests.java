package org.springframework.samples.petclinic.ocachis;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.solicitud.Solicitud;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.user.UserService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioController;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UsuarioController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),excludeAutoConfiguration = SecurityConfiguration.class)
class UsuarioControllerTests {
	
	
	
	
	
	private static final int TEST_USUARIO_ID = 100;
	
	@MockBean
	private UsuarioService usuarioService;
	
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

	
	
	@Test   
	void testInitUpdateUsuarioForm() throws Exception {
		mockMvc.perform(get("/usuarios/1/edit")
				.with(SecurityMockMvcRequestPostProcessors.user("usuario").password("usuario").roles("jugador")))
		
		
		.andExpect(status().isOk())
		.andExpect(view().name("usuarios/createOrUpdateUsuarioForm"));
		
		
		
//				.andExpect(model().attributeExists("usuario"));
//				.andExpect(model().attribute("usuario", hasProperty("nombre", is("Auron"))))
//				.andExpect(model().attribute("usuario", hasProperty("apellido", is("Play"))))
//				.andExpect(model().attribute("usuario", hasProperty("Estadisticas", is("null"))))
//				.andExpect(model().attribute("usuario", hasProperty("Logros", is("null"))))
//				.andExpect(model().attribute("usuario", hasProperty("SolicitudesEnvidas", is("null"))))
//				.andExpect(model().attribute("usuario", hasProperty("SolicitudesRecibidas", is("null"))))
//				.andExpect(model().attribute("usuario", hasProperty("PartidasJugadas", is("null"))))
			
	}

//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateUsuarioFormSuccess() throws Exception {
//		mockMvc.perform(post("/usuarios/{usuarioId}/edit", TEST_USUARIO_ID).with(csrf()).param("nombre", "Auron")
//				.param("apellido", "Play")).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/usuario/{usuarioId}/edit"));
//	}

//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateUsuarioFormHasErrors() throws Exception {
//		mockMvc.perform(post("/usuarios/{usuarioId}/edit", TEST_USUARIO_ID).with(csrf()).param("nombre", "Auron")).andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("usuario"))
//				.andExpect(model().attributeHasFieldErrors("usuario", "Apellido"))
////				.andExpect(model().attributeHasFieldErrors("usuario", "Logros"))
////				.andExpect(model().attributeHasFieldErrors("usuario", "SolicitudesEnvidas"))
////				.andExpect(model().attributeHasFieldErrors("usuario", "SolicitudesRecibidas"))
////				.andExpect(model().attributeHasFieldErrors("usuario", "PartidasJugadas"))
//				.andExpect(view().name("usuarios/createOrUpdateUsuarioForm"));
//	}

}
