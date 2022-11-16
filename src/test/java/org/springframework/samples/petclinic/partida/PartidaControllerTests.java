package org.springframework.samples.petclinic.partida;

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
import org.springframework.samples.petclinic.ocachis.partida.PartidaController;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers = PartidaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class PartidaControllerTests {

	private static final int TEST_LOGRO_ID = 41;

	@Autowired
	private PartidaController partidaController;

	@MockBean
	private PartidaService partidaService;



	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private PartidaOca po;

	@BeforeEach
	void setup() {

		po = new PartidaOca();
		po.setId(TEST_LOGRO_ID);
		po.setCodigoPartida(30);
		po.setEstado(TipoEstadoPartida.CREADA);
		given(this.partidaService.findByIdOca(TEST_LOGRO_ID)).willReturn(po);

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/sala/create")).andExpect(status().isOk()).andExpect(model().attributeExists("partidaOca"))
				.andExpect(view().name("partidas/createPartidaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/sala/createOca").param("estado", "0").param("maxJugadores", "3").with(csrf()))
				.andExpect(status().is3xxRedirection());
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/sala/createOca").with(csrf()).param("estado", "0").param("maxJugadores", "3")
				).andExpect(status().isOk()).andExpect(model().attributeHasErrors("partidaOca"))
				.andExpect(model().attributeHasFieldErrors("estado", "maxJugadores"))
				
				.andExpect(view().name("partidas/createPartidaForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSalaListHtml() throws Exception {
		mockMvc.perform(get("/sala/")).andExpect(status().isOk()).andExpect(model().attributeExists("partidaOca"))
				.andExpect(view().name("partidas/salaList"));
	}


}