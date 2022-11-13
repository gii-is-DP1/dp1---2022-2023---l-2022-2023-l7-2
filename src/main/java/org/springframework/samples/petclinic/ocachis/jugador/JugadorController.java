package org.springframework.samples.petclinic.ocachis.jugador;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.samples.petclinic.ocahis.user.UserService;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JugadorController {

    private JugadorService jugadorService;
    private PartidaService partidaService;
	private UsuarioService usuarioService;

    private static final String VIEWS_SALAS = "partidas/salaList";

    @Autowired
	public JugadorController(JugadorService jugadorService) {
		this.jugadorService=jugadorService;
        this.partidaService=partidaService;
		this.usuarioService = usuarioService;
	}
}
