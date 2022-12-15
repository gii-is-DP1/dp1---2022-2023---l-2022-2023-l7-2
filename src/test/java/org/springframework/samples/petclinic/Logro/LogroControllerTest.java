package org.springframework.samples.petclinic.Logro;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
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
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.logro.LogroController;
import org.springframework.samples.petclinic.ocachis.logro.LogroService;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers = LogroController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class LogroControllerTest {

	private static final int TEST_LOGRO_ID = 41;

	@Autowired
	private LogroController logroController;

	@MockBean
	private LogroService logroService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private Logro logro;

	@BeforeEach
	void setup() {
		logro = new Logro();
		logro.setId(TEST_LOGRO_ID);
		logro.setNombre("Test Logro Controller");
		logro.setDescripcion("Logro de prueba para inserción");
		Estadisticas estadisticas = new Estadisticas();
		estadisticas.setOcaPartidasGanadas(1);
		given(this.logroService.findById(TEST_LOGRO_ID)).willReturn(logro);

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/logro/new")).andExpect(status().isOk()).andExpect(model().attributeExists("logro"))
				.andExpect(view().name("logro/createOrUpdateLogroForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/logro/new").param("nombre", "Logro Test Controller").param("descripcion", "Juega 50 partidas Oca")
				.param("estadisticasACumplir.parchisPartidasJugadas", "1").with(csrf()))
				.andExpect(status().is3xxRedirection());
	}
    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/logro/new").with(csrf()).param("nombre", "Logro Controller Test")
				).andExpect(status().isOk()).andExpect(model().attributeHasErrors("logro"))
				.andExpect(model().attributeHasFieldErrors("logro", "descripcion"))
				
				.andExpect(view().name("logro/createOrUpdateLogroForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateLogroForm() throws Exception {
		mockMvc.perform(get("/logro/{logroId}/edit", TEST_LOGRO_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("logro"))
				.andExpect(model().attribute("logro", hasProperty("nombre", is("Test Logro Controller"))))
				.andExpect(model().attribute("logro", hasProperty("descripcion", is("Logro de prueba para inserción"))))
				
				.andExpect(view().name("logro/createOrUpdateLogroForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateLogroFormHasErrors() throws Exception {
		mockMvc.perform(post("/logro/{logroId}/edit", TEST_LOGRO_ID).with(csrf()).param("nombre", "Test Logro Controller")
				).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("logro"))
				
				.andExpect(model().attributeHasFieldErrors("logro", "descripcion"))
				.andExpect(view().name("logro/createOrUpdateLogroForm"));
	}
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateLogroFormSuccess() throws Exception {
		mockMvc.perform(post("/logro/{logroId}/edit", TEST_LOGRO_ID).with(csrf()).param("nombre", "Test Logro Controller")
				.param("descripcion", "Juega 50 partidas")
				.param("estadisticasACumplir.parchisPartidasJugadas","1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/logro/listLogros"));
	}
	@WithMockUser(value = "spring")
	@Test
	void testShowLogroListHtml() throws Exception {
		mockMvc.perform(get("/logro/listLogros")).andExpect(status().isOk()).andExpect(model().attributeExists("logros"))
				.andExpect(view().name("logro/listLogros"));
	}


}