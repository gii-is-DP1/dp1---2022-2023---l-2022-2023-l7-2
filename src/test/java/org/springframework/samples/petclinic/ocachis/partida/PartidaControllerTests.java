package org.springframework.samples.petclinic.ocachis.partida;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@WebMvcTest(controllers = PartidaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@WebAppConfiguration
class PartidaControllerTests {


	private static final int TEST_PARTIDAOCA_ID = 41;
	private static final int TEST_PARTIDAPARCHIS_ID = 42;
	private static final String TEST_NUMEROPAGINA = "0";

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
	private Usuario u;

	@BeforeEach
	void setup() {

		po = new PartidaOca();
		po.setId(TEST_PARTIDAOCA_ID);
		po.setCodigoPartida(30);
		po.setEstado(TipoEstadoPartida.CREADA);
		po.setColorJugadorActual(Color.ROJO);
		po.setJugadores(new ArrayList<>());
		given(this.partidaService.findPartidaOcaById(TEST_PARTIDAOCA_ID)).willReturn(po);
		
		pp = new PartidaParchis();
		pp.setId(TEST_PARTIDAPARCHIS_ID);
		pp.setCodigoPartida(31);
		pp.setEstado(TipoEstadoPartida.CREADA);
		given(this.partidaService.findPartidaParchisById(TEST_PARTIDAPARCHIS_ID)).willReturn(pp);

		u = new Usuario();
		u.setId(500);
		u.setNombre("Prueba");
		u.setApellido("Prueba");
		u.setEstadisticas(new Estadisticas());
		u.setPartidasJugadas(new ArrayList<Jugador>());
		given(this.usuarioService.getLoggedUsuario()).willReturn(u);

		Page<PartidaOca> resultsOca = Page.empty();     
		 given(this.partidaService.findEsperaOca(PageRequest.of(0,5))).willReturn(resultsOca);

		 Page<PartidaParchis> resultsParchis = Page.empty();     
		 given(this.partidaService.findEsperaParchis(PageRequest.of(0,5))).willReturn(resultsParchis);


	}

	
	@Test
	@WithMockUser(value = "spring")
	void testcrearPartidaHtml() throws Exception {
		mockMvc.perform(get("/partida/crear")).andExpect(status().isOk()).andExpect(model().attributeExists("procesarPartidaForm"))
				.andExpect(view().name("partidas/createPartidaForm"));
	}

    // @WithMockUser(value = "spring")
	// @Test
	// void testProcessCrearPartidaSuccess() throws Exception {
	// 	mockMvc.perform(post("/partida/crear").param("tipo", "oca").param("maxJugadores", "3").with(csrf()))
	// 	.andExpect(status().is3xxRedirection());
	// }
	@WithMockUser(value = "spring")
	@Test
	void testProcessCrearPartidaSuccess() throws Exception { 
		mockMvc.perform(post("/partida/crear").param("tipo", "parchis").param("maxJugadores", "3").with(csrf()))
		.andExpect(status().isOk());
		}

    @WithMockUser(value = "spring")
	@Test
	void testProcessCrearPartidaHasErrors() throws Exception {
		mockMvc.perform(post("/partida/crear").with(csrf()).param("maxJugadores", "4")
				).andExpect(status().isOk()).andExpect(model().attributeHasFieldErrors("procesarPartidaForm", "tipo"))			.andExpect(view().name("partidas/createPartidaForm"));
	}

	@WithMockUser(value = "spring") 
	@Test
	void testShowSalaOcaListHtml() throws Exception {
		mockMvc.perform(get("/partida/oca/listar/{numeroPagina}",TEST_NUMEROPAGINA)).andExpect(status().isOk()).andExpect(model().attributeExists("partidaOca"))
				.andExpect(view().name("partidas/salaOcaList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSalaParchisListHtml() throws Exception {

		mockMvc.perform(get("/partida/parchis/listar/{numPagina}",TEST_NUMEROPAGINA)).andExpect(status().isOk()).andExpect(model().attributeExists("partidaParchis"))
				.andExpect(view().name("partidas/salaParchisList"));
	}
	/*
	@WithMockUser(value = "spring")
	@Test
	void testcreateEnJoinSalaOcaSuccess() throws Exception {
		mockMvc.perform(post("/partida/oca/{partidaOcaId}/entrar",TEST_PARTIDAOCA_ID).with(csrf()).param("jugadorId", "6")
				.param("color", "ROJO")
				).andExpect(status().is2xxSuccessful());
	}
	*/
	@WithMockUser(value = "spring")
	@Test
	void testShowOcaHtml() throws Exception {
		mockMvc.perform(get("/partida/oca/{partidaOcaId}/espera", TEST_PARTIDAOCA_ID)).andExpect(status().isOk())
			.andExpect(model().attributeExists("partidaOca"))
				.andExpect(view().name("partidas/espera"));
	}
	/*
	@WithMockUser(value = "spring")
	@Test
	void testcreateEnJoinSalaParchisSuccess() throws Exception {
		mockMvc.perform(get("/partida/parchis/{partidaParchisId}/entrar", 2)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/partida/parchis/2/entrar")).andExpect(status().isFound());
	}
	*/
	@WithMockUser(value = "spring")
	@Test
	void testShowParchisHtml() throws Exception {
		mockMvc.perform(get("/partida/parchis/{partidaParchisId}/espera", TEST_PARTIDAPARCHIS_ID)).andExpect(status().isOk())
			.andExpect(model().attributeExists("partidaParchis"))
				.andExpect(view().name("partidas/espera"));
	}
 @WithMockUser(value = "spring")
	@Test
	void testEmpezarPartidaParchis() throws Exception {
		mockMvc.perform(get("/partida/parchis/{partidaParchisId}/empezar", TEST_PARTIDAPARCHIS_ID)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/partida/parchis/"+TEST_PARTIDAPARCHIS_ID+"/jugar")).andExpect(status().isFound());
	}
 @WithMockUser(value = "spring")
	@Test
	void testEmpezarPartidaOca() throws Exception {
		mockMvc.perform(get("/partida/oca/{partidaOcaId}/empezar", TEST_PARTIDAOCA_ID)).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/partida/oca/" + TEST_PARTIDAOCA_ID + "/jugar")).andExpect(status().isFound());
	}
}