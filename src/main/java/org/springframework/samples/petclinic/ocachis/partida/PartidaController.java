package org.springframework.samples.petclinic.ocachis.partida;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.ficha.FichaOca;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.partida.exceptions.PartidaLlenaException;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sala")
public class PartidaController {

	private String REFRESH_SEECONDS = "1";
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

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	private static final String VIEWS_SALAS = "partidas/salaList";
	private static final String VIEWS_ESPERA = "partidas/espera";
	private static final String CREATE_SALAS = "partidas/createPartidaForm";
	private static final String VIEWS_JUGAR_OCA = "partidas/ocaGame";
	private static final String VIEWS_JUGAR_PARCHIS = "partidas/parchisGame";
	private static final String VIEWS_PARTIDAOCA_TERMINADA = "partidas/resumenPartidaOca";



	private Boolean estaJugando(Integer usuarioId) {
		Collection<Jugador> jugadores = jugadorService.findAllJugadoresForUsuario(usuarioId);
		for (Jugador j : jugadores) {
			if (j.getPartidaOca() != null && (j.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO
					|| j.getPartidaOca().getEstado() == TipoEstadoPartida.CREADA)) {
					return true;
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.CREADA
					|| j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
					return true;
			}
		}
		return false;
	}

	
	//lista de salas
	
	@GetMapping("/")
	public ModelAndView showSalaList() {
		ModelAndView mav = new ModelAndView(VIEWS_SALAS);
		mav.addObject("partidaOca", partidaService.findEsperaOca());
		mav.addObject("partidaParchis", partidaService.findEsperaParchis());
		return mav;
	}

	//create
	
	@GetMapping("/create")
	public String crearPartida(ModelMap model) {
		ProcesarPartidaForm proceso = new ProcesarPartidaForm();
		model.put("procesarPartidaForm", proceso);
		return CREATE_SALAS;
	}

	@PostMapping("/create")
	public String processCrearPartida(@Valid ProcesarPartidaForm procesarPartidaForm, BindingResult result,
			ModelMap model, RedirectAttributes redirectAttributes) throws IllegalAccessException {
		String tipo = procesarPartidaForm.getTipo();
		if (result.hasErrors()) {
			return CREATE_SALAS;
		}
		
		//al refactorizar parchis y crear el jugador en jugadorService, u ya no es necesario en esta funcion
		Usuario u = usuarioService.getLoggedUsuario();
		Jugador jugador = null;
		
		if (estaJugando(u.getId())) {
			model.put("message", "Estas jugando ya en una partida");
			return CREATE_SALAS;
		}

		// Caso Oca
		if (tipo.equals("oca")) {
			PartidaOca partida = partidaService.crearPartidaOca(procesarPartidaForm.getNumJugador());
			try{
				jugador = jugadorService.createJugadorOca(partida);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida est?? llena");
				return "redirect:/sala/";
			}
			return "redirect:/sala/" + partida.getId() + "/showOca";
		
			// Caso Parch??s
		} else if (tipo.equals("parchis")) {
			PartidaParchis partida = partidaService.crearPartidaParchis(procesarPartidaForm.getNumJugador());
			try{
				jugador = jugadorService.createJugadorParchis(partida);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida est?? llena");
				return "redirect:/sala/";
			}
			return "redirect:/sala/" + partida.getId() + "/showParchis";
		} else { // ni oca ni parchis
			return "redirect:/sala/";
		}

	}

	
	//oca
	
	@GetMapping("/{partidaOcaId}/ocaJoin")
	public String unirsePartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model, RedirectAttributes redirectAttributes) {
		PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
		Jugador newJugador = new Jugador();
		model.put("jugadores", partidaOca.getJugadores());
		model.put("jugador", newJugador);
		model.put("partidaOcaId", partidaOcaId);
		return createEnJoinSalaOca(partidaOcaId, newJugador, model, redirectAttributes);
	}

	@PostMapping("/{partidaOcaId}/ocaJoin")
	public String createEnJoinSalaOca(@PathVariable("partidaOcaId") int partidaOcaId, @Valid Jugador jugador,
			ModelMap model, RedirectAttributes redirectAttributes) {
		PartidaOca p = partidaService.findByIdOca(partidaOcaId);
		Collection<Jugador> jugadores = p.getJugadores();
		Boolean dentro = false;
		// Crear jugador
		


		Usuario u = usuarioService.getLoggedUsuario();
		Collection<Jugador> jugadores1 = jugadorService.findAllJugadoresForUsuario(u.getId());

		// Detecta si el usuario est?? en una partida, y si lo est??, al tratar de unirse a una sala lo redirig?? a la sala o partida en el que ya se encuentra
		if (estaJugando(u.getId())) {
			for (Jugador j : jugadores1) {
			if (j.getPartidaOca() != null && j.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO) {
				var partidaActual = j.getPartidaOca().getId();
				redirectAttributes.addFlashAttribute("message", "Estas jugando ya en una partida. Se te ha redirigido a ella");
				return "redirect:/sala/" + partidaActual + "/playOca";
			} else if (j.getPartidaOca() != null && j.getPartidaOca().getEstado() == TipoEstadoPartida.CREADA) {
				var partidaActual = j.getPartidaOca().getId();
				redirectAttributes.addFlashAttribute("message", "Ya estas en una sala. Se te ha redirigido a ella");
				return "redirect:/sala/" + partidaActual + "/showOca";
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
				var partidaActual = j.getPartidaParchis().getId();
				redirectAttributes.addFlashAttribute("message", "Estas jugando ya en una partida. Se te ha redirigido a ella");
				return "redirect:/sala/" + partidaActual + "/showOca";
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.CREADA)) {
				var partidaActual = j.getPartidaParchis().getId();
				redirectAttributes.addFlashAttribute("message", "Ya estas en una sala. Se te ha redirigido a ella");
				return "redirect:/sala/" + partidaActual + "/showOca";
			}}
		}

		for (Jugador j : jugadores) {
			if (j.getUsuario().getId().equals(u.getId())) {
				dentro = true;
			}
		}

		if(dentro) {
			return "redirect:/sala/" + partidaOcaId + "/showOca";

		}else{
			try{
				jugador = jugadorService.createJugadorOca(p);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida est?? llena");
				return "redirect:/sala/";
			}
		}
		return "redirect:/sala/" + partidaOcaId + "/showOca";
	}

	@GetMapping("/{partidaOcaId}/showOca")
	public String showPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model,  HttpServletResponse response) {
		PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
		response.addHeader("Refresh", REFRESH_SEECONDS);
		model.put("jugadores", partidaOca.getJugadores());
		model.put("usuarioAutenticado", usuarioService.getLoggedUsuario());
		model.put("partidaOca", partidaOca);
		return VIEWS_ESPERA;
	}

	
	//parchis
	
	@GetMapping("/{partidaParchisId}/parchisJoin")
	public String unirsePartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model, RedirectAttributes redirectAttributes) {
		PartidaParchis partidaParchis = partidaService.findByIdParchis(partidaParchisId);
		Jugador newJugador = new Jugador();
		model.put("jugadores", partidaParchis.getJugadores());
		model.put("jugador", newJugador);
		model.put("partidaParchisId", partidaParchisId);
		// return VIEWS_ESPERA;
		return createEnJoinSalaParchis(partidaParchisId, newJugador, model, redirectAttributes);
	}

	@PostMapping("/{partidaParchisId}/parchisJoin")
	public String createEnJoinSalaParchis(@PathVariable("partidaParchisId") int partidaParchisId,
			@Valid Jugador jugador, ModelMap model, RedirectAttributes redirectAttributes) {
		
		PartidaParchis p = partidaService.findByIdParchis(partidaParchisId);
		Collection<Jugador> jugadores = p.getJugadores();
		Boolean dentro = false;
		// Crear jugador
		
		Usuario u = usuarioService.getLoggedUsuario();
		
		if(estaJugando(u.getId())) {
			model.put("message", "Estas jugando ya en una partida");
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
		}  else {
			try{
				jugador = jugadorService.createJugadorParchis(p);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida est?? llena");
				return "redirect:/sala/";
			}
		}
		return "redirect:/sala/{partidaParchisId}/showParchis";
	}

	@GetMapping("/{partidaParchisId}/showParchis")
	public String showPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model, HttpServletResponse response) {
		PartidaParchis partidaParchis = partidaService.findByIdParchis(partidaParchisId);
		response.addHeader("Refresh", REFRESH_SEECONDS);
		model.put("jugadores", partidaParchis.getJugadores());
		model.put("usuarioAutenticado", usuarioService.getLoggedUsuario());
		model.put("partidaParchis", partidaParchis);
		return VIEWS_ESPERA;
	}


	
	//empezarPartida
	@GetMapping("/{partidaOcaId}/startOca")
	public String initEmpezarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model, HttpServletResponse response) {
		response.addHeader("Refresh", REFRESH_SEECONDS);
		PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
		model.put("partidaOca", partidaOca);
		return processEmpezarPartidaOca(partidaOcaId, partidaOca, model);
	}

	@PostMapping("/{partidaOcaId}/startOca")
	public String processEmpezarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, PartidaOca partida,
			ModelMap model) {
		partida.setEstado(TipoEstadoPartida.JUGANDO);
		partida.setFechaCreacion(LocalDateTime.now());
		partidaService.saveOca(partida);
		return "redirect:/sala/{partidaOcaId}/playOca";
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
				partida.setFechaCreacion(LocalDateTime.now());
				partidaService.saveParchis(partida);
				return "redirect:/sala/{partidaParchisId}/playParchis";
	}


	//abandonar
	
	@GetMapping("/{partidaOcaId}/abandonarOca")
	public String abandonarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model) {
		Usuario u = usuarioService.getLoggedUsuario();
		Jugador jugadorAEliminar = jugadorService.findJugadorOca(u.getId(), partidaOcaId);
		jugadorService.delete(jugadorAEliminar);
		if (jugadorAEliminar.getColor() == Color.ROJO) {
			partidaService.borrarPartidaOca(partidaOcaId);
		}
		return "redirect:/sala/";
	}
	
	@GetMapping("/{partidaParchisId}/abandonarParchis")
	public String abandonarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {	
		Usuario u = usuarioService.getLoggedUsuario();
		Jugador jugadorAEliminar = jugadorService.findJugadorParchis(u.getId(), partidaParchisId);
		jugadorService.delete(jugadorAEliminar);
		if (jugadorAEliminar.getColor() == Color.ROJO) {
			partidaService.borrarPartidaParchis(partidaParchisId);
		}
		return "redirect:/sala/";
	}
	


	//jugar OCA
	@GetMapping(value="/{partidaOcaId}/playOca")
	public String jugarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model,HttpServletResponse response, RedirectAttributes redirectAttributes){
		PartidaOca partida = partidaService.findByIdOca(partidaOcaId);
		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorOca(u.getId(), partida.getId());
		model.put("partidaOca", partida);

		if(partida.getEstado()==TipoEstadoPartida.TERMINADA){
			return "redirect:/sala/" + partidaOcaId + "/resumen";

		}else if(partida.getEstado()==TipoEstadoPartida.CREADA){
			redirectAttributes.addFlashAttribute("message", "La partida aun no ha empezado");
			return "redirect:/sala/" + partidaOcaId + "/showOca";
		}

		if(j!=null){
			model.put("modo","jugador");
			model.put("jugadorAutenticado", j);
		}else if(partida.getUsuariosObservadores().contains(u)){
			model.put("modo","observador");
		}
		response.addHeader("Refresh", REFRESH_SEECONDS);
		model.put("now", new Date());
		return VIEWS_JUGAR_OCA;
	}


	@PostMapping(value="/{partidaOcaId}/playOca")
	public String tirarDadosPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId,
					 ModelMap model, HttpServletResponse response){
		
		PartidaOca partida = partidaService.findByIdOca(partidaOcaId);
		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorOca(u.getId(), partida.getId());
		FichaOca ficha = j.getFichaOca();

		if(j.getColor() != partida.getColorJugadorActual()){
				return "redirect:/noAccess";
		}		
		partidaService.jugarOca(partida, ficha, j);
		return "redirect:/sala/" + partidaOcaId + "/playOca";
	}

	
	//jugar PARCHIS
	@GetMapping("/{partidaParchisId}/playParchis")
	public String jugarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model,HttpServletResponse response,RedirectAttributes redirectAttributes){
		PartidaParchis partida = partidaService.findByIdParchis(partidaParchisId);
		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorParchis(u.getId(), partida.getId());
		model.put("partidaParchis", partida);

		if(partida.getEstado()==TipoEstadoPartida.TERMINADA){
			return "redirect:/sala/" + partidaParchisId + "/resumen";

		}else if(partida.getEstado()==TipoEstadoPartida.CREADA){
			redirectAttributes.addFlashAttribute("message", "La partida aun no ha empezado");
			return "redirect:/sala/" + partidaParchisId + "/showParchis";
		}

		if(j!=null){
			model.put("modo","jugador");
			model.put("jugadorAutenticado", j);
			if(partida.getColorJugadorActual()!=j.getColor()) response.addHeader("Refresh", REFRESH_SEECONDS);
		}else if(partida.getUsuariosObservadores().contains(u)){
			model.put("modo","observador");
			response.addHeader("Refresh", REFRESH_SEECONDS);
		}
		model.put("now", new Date());
		model.put("modo", "Parchis");
		return VIEWS_JUGAR_PARCHIS;


	}
	
	@PostMapping(value="/{partidaParchisId}/playParchis")
	public String tirarDadosPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId,
					 ModelMap model, HttpServletResponse response,  RedirectAttributes redirectAttributes){
		

		PartidaParchis partida = partidaService.findByIdParchis(partidaParchisId);
		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorParchis(u.getId(), partida.getId());
		model.put("partidaParchis", partida);
		if(j.getColor() != partida.getColorJugadorActual()){
				return "redirect:/noAccess";
		}

		Collection<FichaParchis> fichas = j.getFichasParchis();
		int dado = partidaService.TirarNumDado();
		List<FichaParchis> fichasQuePuedenMoverse = j.getFichasQuePuedenMoverse(dado);

		model.put("fichasQueSePuedenMover", fichasQuePuedenMoverse);
		model.put("now", new Date());
		model.put("dado", dado);
		model.put("modo", "Parchis");
		model.put("modo","jugador");
		model.put("jugadorAutenticado", j);
		//partidaService.jugarParchis(partida, fichas, j);
		return VIEWS_JUGAR_PARCHIS;
	}

	@GetMapping("/{partidaOcaId}/resumen")
	public String terminadaPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model, HttpServletResponse response){
		PartidaOca partida = partidaService.findByIdOca(partidaOcaId);
		
		model.put("partidaOca", partida);
		return VIEWS_PARTIDAOCA_TERMINADA;
	}
}