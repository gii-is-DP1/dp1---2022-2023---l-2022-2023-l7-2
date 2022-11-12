package org.springframework.samples.petclinic.ocachis.jugador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JugadorController {

    private JugadorService jugadorService;

    private static final String VIEWS_SALAS = "partidas/salaList";

    @Autowired
	public JugadorController(JugadorService jugadorService) {
		this.jugadorService=jugadorService;
	}
    
    @PostMapping(value = "/sala/")
	public String createEnJoinSala(@Valid Jugador jugador, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_SALAS;
		}
		else {
			this.jugadorService.save(jugador);
			return "welcome";
		}
	}

    @PostMapping(value = "/sala/create")
	public String createEnCreateSala(@Valid Jugador jugador, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_SALAS;
		}
		else {
			this.jugadorService.save(jugador);
			return "welcome";
		}
	}
}
