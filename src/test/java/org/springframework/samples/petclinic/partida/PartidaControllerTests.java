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
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.partida.PartidaController;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers = PartidaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class PartidaControllerTests {

	private static final int TEST_PARTIDAOCA_ID = 41;
	private static final int TEST_PARTIDAPARCHIS_ID = 41;

	@Autowired
	private PartidaController partidaController;

	@MockBean
	private PartidaService partidaService;
	
	@MockBean
	private UsuarioService usuarioService;
	
	@MockBean
	private JugadorService jugadorService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private PartidaOca po;
	private PartidaParchis pp;

	@BeforeEach
	void setup() {

		po = new PartidaOca();
		po.setId(TEST_PARTIDAOCA_ID);
		po.setCodigoPartida(30);
		po.setEstado(TipoEstadoPartida.CREADA);
		given(this.partidaService.findByIdOca(TEST_PARTIDAOCA_ID)).willReturn(po);
		
		pp = new PartidaParchis();
		pp.setId(TEST_PARTIDAPARCHIS_ID);
		pp.setCodigoPartida(30);
		pp.setEstado(TipoEstadoPartida.CREADA);
		given(this.partidaService.findByIdParchis(TEST_PARTIDAPARCHIS_ID)).willReturn(pp);

	}

	@WithMockUser(value = "spring")
	@Test
	void testcrearPartidaHtml() throws Exception {
		mockMvc.perform(get("/sala/create")).andExpect(status().isOk()).andExpect(model().attributeExists("proceso"))
				.andExpect(view().name("partidas/createPartidaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCrearPartidaSuccess() throws Exception {
		mockMvc.perform(post("/sala/create").param("estado", "0").param("maxJugadores", "3").with(csrf()))
		.andExpect(status().is2xxSuccessful());
	}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCrearPartidaHasErrors() throws Exception {
		mockMvc.perform(post("/sala/create").with(csrf()).param("estado", "0").param("maxJugadores", "5")
				).andExpect(status().isOk()).andExpect(model().attributeHasFieldErrors("result","maxJugadores"))
				.andExpect(view().name("partidas/createPartidaForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSalaListHtml() throws Exception {
		mockMvc.perform(get("/sala/")).andExpect(status().isOk()).andExpect(model().attributeExists("partidaOca"))
				.andExpect(view().name("partidas/salaList"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testcreateEnJoinSalaOcaSuccess() throws Exception {
		mockMvc.perform(post("/sala/{partidaOcaId}/ocaJoin",TEST_PARTIDAOCA_ID).with(csrf()).param("jugadorId", "6")
				.param("color", "ROJO")
				).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("partidas/espera"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testcreateEnJoinSalaOcaHasErrors() throws Exception {
		mockMvc.perform(post("/sala/{partidaOcaId}/ocaJoin",TEST_PARTIDAOCA_ID).with(csrf()).param("jugadorId","1")
				).andExpect(status().isOk())
				 .andExpect(model().attributeHasErrors("jugador"))
		
				.andExpect(model().attributeHasFieldErrors("jugador", "color"))
				.andExpect(view().name("partidas/createPartidaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowOcaHtml() throws Exception {
		mockMvc.perform(get("/sala/{partidaOcaId}/showOca", TEST_PARTIDAOCA_ID)).andExpect(status().isOk())
			.andExpect(model().attributeExists("jugador"))
			.andExpect(model().attributeExists("partidaOca"))
				.andExpect(view().name("partidas/espera"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testcreateEnJoinSalaParchisSuccess() throws Exception {
		mockMvc.perform(post("/sala/{partidaParchisId}/parchisJoin",TEST_PARTIDAPARCHIS_ID).with(csrf()).param("jugadorId", "6")
				.param("color", "ROJO")
				).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("redirect:/sala/{partidaParchisId}/showParchis"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testcreateEnJoinSalaParchisHasErrors() throws Exception {
		mockMvc.perform(post("/sala/{partidaParchisId}/ocaJoin",TEST_PARTIDAPARCHIS_ID).with(csrf()).param("jugadorId","1")
				).andExpect(status().isOk())
				 .andExpect(model().attributeHasErrors("jugador"))
		
				.andExpect(model().attributeHasFieldErrors("jugador", "color"))
				.andExpect(view().name("partidas/createPartidaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowParchisHtml() throws Exception {
		mockMvc.perform(get("/sala/{partidaParchisId}/showParchis", TEST_PARTIDAPARCHIS_ID)).andExpect(status().isOk())
			.andExpect(model().attributeExists("jugadores"))
			.andExpect(model().attributeExists("partidaParchis"))
				.andExpect(view().name("partidas/espera"));
	}


}