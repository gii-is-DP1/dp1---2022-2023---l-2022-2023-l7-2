package org.springframework.samples.petclinic.ocachis.partida;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.h2.command.ddl.CreateAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sala")
public class PartidaController {

	private PartidaService partidaService;
	private UsuarioService usuarioService;
	private JugadorService jugadorService;

	@Autowired
	public PartidaController(PartidaService partidaService, UsuarioService usuarioService,
			JugadorService jugadorService) {
		this.partidaService = partidaService;
		this.usuarioService = usuarioService;
		this.jugadorService = jugadorService;
	}

	private static final String VIEWS_SALAS = "partidas/salaList";
	private static final String VIEWS_ESPERA = "partidas/espera";
	private static final String CREATE_SALAS = "partidas/createPartidaForm";

	@GetMapping("/")
	public ModelAndView showSalaList() {
		ModelAndView mav = new ModelAndView(VIEWS_SALAS);
		mav.addObject("partidaOca", partidaService.findEsperaOca());
		mav.addObject("partidaParchis", partidaService.findEsperaParchis());
		return mav;
	}

	@GetMapping("/{partidaOcaId}/ocaJoin")
	public String unirsePartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model) {
		PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
		Jugador newJugador = new Jugador();
		model.put("jugadores", partidaOca.getJugadores());
		model.put("jugador", newJugador);
		model.put("partidaOcaId", partidaOcaId);
		// return VIEWS_ESPERA;
		return createEnJoinSalaOca(partidaOcaId, newJugador, model);
	}

	@PostMapping("/{partidaOcaId}/ocaJoin")
	public String createEnJoinSalaOca(@PathVariable("partidaOcaId") int partidaOcaId, @Valid Jugador jugador,
			ModelMap model) {
		PartidaOca p = partidaService.findByIdOca(partidaOcaId);
		Collection<Jugador> jugadores = p.getJugadores();
		Boolean dentro = false;
		// Crear jugador
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User loggedUser = null;
		if (auth.isAuthenticated()) {
			loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		} else {
			return "redirect:/noAccess";
		}

		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		Collection<Jugador> jugadoresUsuario = jugadorService.findAllJugadoresForUsuario(u.getId());
			
		Boolean estaJugando = false;
		for (Jugador j : jugadoresUsuario){
			if(j.getPartidaOca()!=null &&
			 (j.getPartidaOca().getEstado()==TipoEstadoPartida.JUGANDO ||
			  j.getPartidaOca().getEstado()==TipoEstadoPartida.CREADA)){
				estaJugando=true;
			  }
			
		}
		if(estaJugando){
			model.put("message","Estas jugando ya en una partida");
			model.put("partidaOca", partidaService.findEsperaOca());
			model.put("partidaParchis", partidaService.findEsperaParchis());
			return VIEWS_SALAS;
			
		}


		for (Jugador j : jugadores) {
			if (j.getUsuario().getId().equals(u.getId())) {
				dentro = true;
			}
		}
		if (dentro) {
			return "redirect:/sala/{partidaOcaId}/showOca";
		} else if (p.getJugadores().size() == p.getMaxJugadores()) {
			model.put("message", "La partida esta llena");
			model.put("partidaOca", partidaService.findEsperaOca());
			model.put("partidaParchis", partidaService.findEsperaParchis());
			return VIEWS_SALAS;
		} else {
			jugador.setUsuario(u);
			jugador.setPartidaOca(p);
			List<Color> colores = new ArrayList<Color>();
			for (Jugador j : p.getJugadores()) {
				colores.add(j.getColor());
			}
			if (!(colores.contains(Color.AMARILLO))) {
				jugador.setColor(Color.AMARILLO);
			} else if (!(colores.contains(Color.VERDE))) {
				jugador.setColor(Color.VERDE);
			} else {
				jugador.setColor(Color.AZUL);
			}
			this.jugadorService.save(jugador);
		}
		return "redirect:/sala/{partidaOcaId}/showOca";
	}

	@GetMapping("/{partidaOcaId}/showOca")
	public String showPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model) {
		PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
		model.put("jugadores", partidaOca.getJugadores());
		model.put("partidaOca", partidaOca);
		return VIEWS_ESPERA;
	}

	@GetMapping("/{partidaParchisId}/parchisJoin")
	public String unirsePartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {
		PartidaParchis partidaParchis = partidaService.findByIdParchis(partidaParchisId);
		Jugador newJugador = new Jugador();
		model.put("jugadores", partidaParchis.getJugadores());
		model.put("jugador", newJugador);
		model.put("partidaParchisId", partidaParchisId);
		// return VIEWS_ESPERA;
		return createEnJoinSalaParchis(partidaParchisId, newJugador, model);
	}

	@PostMapping("/{partidaParchisId}/parchisJoin")
	public String createEnJoinSalaParchis(@PathVariable("partidaParchisId") int partidaParchisId,
			@Valid Jugador jugador, ModelMap model) {
		PartidaParchis p = partidaService.findByIdParchis(partidaParchisId);
		Collection<Jugador> jugadores = p.getJugadores();
		Boolean dentro = false;
		// Crear jugador
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User loggedUser = null;
		if (auth.isAuthenticated()) {
			loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		} else {
			return "redirect:/noAccess";
		}

		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		Collection<Jugador> jugadoresUsuario = jugadorService.findAllJugadoresForUsuario(u.getId());
			
			Boolean estaJugando = false;
			for (Jugador j : jugadoresUsuario){
				if(j.getPartidaParchis()!=null &&
				 (j.getPartidaParchis().getEstado()==TipoEstadoPartida.JUGANDO ||
				  j.getPartidaParchis().getEstado()==TipoEstadoPartida.CREADA)){
					estaJugando=true;
				  }
				
			}
			if(estaJugando){
				model.put("message","Estas jugando ya en una partida");
				model.put("message","Estas jugando ya en una partida");
				model.put("partidaOca", partidaService.findEsperaOca());
				model.put("partidaParchis", partidaService.findEsperaParchis());
				return VIEWS_SALAS;
			}

		for (Jugador j : jugadores) {
			if (j.getUsuario().getId().equals(u.getId())) {
				dentro = true;
			}
		}
		if (dentro) {
			return "redirect:/sala/{partidaParchisId}/showParchis";
		} else if (p.getJugadores().size() == p.getMaxJugadores()) {
			model.put("message", "La partida esta llena");
			model.put("partidaOca", partidaService.findEsperaOca());
			model.put("partidaParchis", partidaService.findEsperaParchis());
			return VIEWS_SALAS;
		} else {
			jugador.setUsuario(u);
			jugador.setPartidaParchis(p);
			List<Color> colores = new ArrayList<Color>();
			for (Jugador j : p.getJugadores()) {
				colores.add(j.getColor());
			}
			if (!(colores.contains(Color.AMARILLO))) {
				jugador.setColor(Color.AMARILLO);
			} else if (!(colores.contains(Color.VERDE))) {
				jugador.setColor(Color.VERDE);
			} else {
				jugador.setColor(Color.AZUL);
			}
			this.jugadorService.save(jugador);
		}
		return "redirect:/sala/{partidaParchisId}/showParchis";
	}

	@GetMapping("/{partidaParchisId}/showParchis")
	public String showPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {
		PartidaParchis partidaParchis = partidaService.findByIdParchis(partidaParchisId);
		model.put("jugadores", partidaParchis.getJugadores());
		model.put("partidaParchis", partidaParchis);
		return VIEWS_ESPERA;
	}

	@GetMapping("/{partidaParchisId}/startParchis")
	public String initEmpezarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {
		PartidaParchis partidaParchis = partidaService.findByIdParchis(partidaParchisId);
		model.put("partidaParchis", partidaParchis);
		return processEmpezarPartidaParchis(partidaParchisId, partidaParchis, model);
	}

	@PostMapping("/{partidaParchisId}/startParchis")
	public String processEmpezarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId,
			PartidaParchis partida, ModelMap model) {
		partida.setEstado(TipoEstadoPartida.JUGANDO);
		partidaService.saveParchis(partida);
		return "redirect:/sala/{partidaParchisId}/showParchis";
	}

	@GetMapping("/{partidaOcaId}/startOca")
	public String initEmpezarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model) {
		PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
		model.put("partidaOca", partidaOca);
		return processEmpezarPartidaOca(partidaOcaId, partidaOca, model);
	}

	@PostMapping("/{partidaOcaId}/startOca")
	public String processEmpezarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, PartidaOca partida,
			ModelMap model) {
		partida.setEstado(TipoEstadoPartida.JUGANDO);
		partidaService.saveOca(partida);
		return "redirect:/sala/{partidaOcaId}/showOca";
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/create")
	public String crearPartida(ModelMap model) {
		ProcesarPartidaForm proceso = new ProcesarPartidaForm();
		model.put("procesarPartidaForm", proceso);
		return CREATE_SALAS;
	}

	@PostMapping("/create")
	public String processCrearPartida(@Valid ProcesarPartidaForm procesarPartidaForm, BindingResult result,
			ModelMap model) throws IllegalAccessException {

		String tipo = procesarPartidaForm.getTipo();
		if (result.hasErrors()) {

			return CREATE_SALAS;
		}

		
			org.springframework.security.core.userdetails.User loggedUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
			Jugador jugador = new Jugador();
			
			//verificar que el jugador no esta jugando
			Collection<Jugador> jugadores = jugadorService.findAllJugadoresForUsuario(u.getId());
			
			Boolean estaJugando = false;
			for (Jugador j : jugadores){
				if(j.getPartidaOca()!=null &&
				 (j.getPartidaOca().getEstado()==TipoEstadoPartida.JUGANDO ||
				  j.getPartidaOca().getEstado()==TipoEstadoPartida.CREADA)){
					estaJugando=true;
					break;
				  }
				else if (j.getPartidaParchis()!=null &&
				(j.getPartidaParchis().getEstado() == TipoEstadoPartida.CREADA ||
				 j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)){
					estaJugando = true;
					break;
				 }
			}
			if(estaJugando){
				model.put("message","Estas jugando ya en una partida");
				return CREATE_SALAS;
			}

		// Caso Oca
		if (tipo.equals("oca")) {
			// Crear partida
			PartidaOca partidaOca = new PartidaOca();
			partidaOca.setMaxJugadores(procesarPartidaForm.getNumJugador());
			partidaOca.setCodigoPartida(Partida.getNuevoCodigoPartida());
			this.partidaService.saveOca(partidaOca);

			// Crear jugador		
			jugador.setUsuario(u);
			jugador.setPartidaOca(partidaOca);
			jugador.setColor(Color.ROJO);
			this.jugadorService.save(jugador);
			return "redirect:/sala/" + partidaOca.getId() + "/showOca";
			// Caso Parchís
		} else if (tipo.equals("parchis")) {
			// Crear partida
			PartidaParchis partidaParchis = new PartidaParchis();
			partidaParchis.setMaxJugadores(procesarPartidaForm.getNumJugador());
			partidaParchis.setCodigoPartida(Partida.getNuevoCodigoPartida());
			this.partidaService.saveParchis(partidaParchis);

			jugador.setUsuario(u);
			jugador.setPartidaParchis(partidaParchis);
			jugador.setColor(Color.ROJO);
			this.jugadorService.save(jugador);
			return "redirect:/sala/" + partidaParchis.getId() + "/showParchis";
		
		}else{ //ni oca ni parchis
			return "redirect:/sala/";
		}

		
	}
	@GetMapping("/{partidaParchisId}/abandonarParchis")
	public String abandonarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User loggedUser = null;
		if (auth.isAuthenticated()) {
			loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		} else {
			return "redirect:/noAccess";
		}
		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		
		
		Jugador jugadorAEliminar = jugadorService.findJugadorParchis(u.getId(), partidaParchisId);
	
		jugadorService.delete(jugadorAEliminar);
		if(jugadorAEliminar.getColor()==Color.ROJO){
			partidaService.borrarPartidaParchis(partidaParchisId);
		}
		return "redirect:/sala/";


	}
	@GetMapping("/{partidaOcaId}/abandonarOca")
	public String abandonarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User loggedUser = null;
		if (auth.isAuthenticated()) {
			loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		} else {
			return "redirect:/noAccess";
		}
		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		
		
		Jugador jugadorAEliminar = jugadorService.findJugadorOca(u.getId(), partidaOcaId);
		
		jugadorService.delete(jugadorAEliminar);
		if(jugadorAEliminar.getColor()==Color.ROJO){
			partidaService.borrarPartidaOca(partidaOcaId);
		}
	
		return "redirect:/sala/";


	}
}