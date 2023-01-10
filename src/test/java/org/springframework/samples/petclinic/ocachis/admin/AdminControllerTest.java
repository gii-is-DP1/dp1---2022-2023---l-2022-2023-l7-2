package org.springframework.samples.petclinic.ocachis.admin;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AdminController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AdminControllerTest {

    private static final int TEST_ID = 100;


    @MockBean
	private AuthoritiesService authoritiesService;

    @MockBean
	private PartidaService partidaService;

    @MockBean
	private UsuarioService usuarioService;

	@Autowired
	private MockMvc mockMvc;

    private Usuario usuario;

    @BeforeEach
	void setup() {
        
        usuario = new Usuario();
        usuario.setId(TEST_ID);
        given(this.usuarioService.findUsuarioById(TEST_ID)).willReturn(usuario);
		doNothing().when(this.partidaService).borrarPartidaOca(TEST_ID);
        doNothing().when(this.partidaService).borrarPartidaParchis(TEST_ID);
        doNothing().when(this.usuarioService).deleteUsuarioById(TEST_ID);

	}

    @WithMockUser(value = "spring")
	@Test
	void testShowPartidaListHtml() throws Exception {
		mockMvc.perform(get("/admin/listPartidas")).andExpect(status().isOk()).andExpect(model().attributeExists("oca")).andExpect(model().attributeExists("parchis"))
				.andExpect(view().name("admin/listPartidas"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testShowUsuarioListHtml() throws Exception {
		mockMvc.perform(get("/admin/listUsuarios")).andExpect(status().isOk()).andExpect(model().attributeExists("selections"))
				.andExpect(view().name("admin/listUsuarios"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testDeleteUsuario() throws Exception {
		mockMvc.perform(get("/admin/listUsuarios/{usuarioId}/delete", TEST_ID).with(csrf())).andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/admin/listUsuarios"));
	}
    
}
