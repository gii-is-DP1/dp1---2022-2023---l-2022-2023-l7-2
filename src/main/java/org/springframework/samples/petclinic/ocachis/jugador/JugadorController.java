package org.springframework.samples.petclinic.ocachis.jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;

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
