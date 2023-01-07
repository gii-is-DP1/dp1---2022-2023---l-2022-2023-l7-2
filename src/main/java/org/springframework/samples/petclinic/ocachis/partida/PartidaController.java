package org.springframework.samples.petclinic.ocachis.partida;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/partida")
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

	private static final String VIEWS_OCA_SALAS = "partidas/salaOcaList";
	private static final String VIEWS_PARCHIS_SALAS = "partidas/salaParchisList";
	private static final String VIEWS_ESPERA = "partidas/espera";
	private static final String CREATE_SALAS = "partidas/createPartidaForm";
	private static final String VIEWS_JUGAR_OCA = "partidas/ocaGame";
	private static final String VIEWS_JUGAR_PARCHIS = "partidas/parchisGame";
	private static final String VIEWS_PARTIDAOCA_TERMINADA = "partidas/resumenPartida";



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
	
	@GetMapping("/oca/listar/{numPagina}")
	public String showSalaOcaList(@Param("codigo") Integer codigo,ModelMap model,RedirectAttributes redirectAttributes,@PathVariable("numPagina") Integer numPagina) {
		if(!(codigo==null)){
			Optional<PartidaOca> partidaOca = this.partidaService.findOcaByCodigo(codigo);
			if(partidaOca.isPresent()){
				
				Integer idPartidaOca = partidaOca.get().getId();
				
				return unirsePartidaOca(idPartidaOca,  model, redirectAttributes);
			} else{
				Pageable pageable = PageRequest.of(0,5);
				Page<PartidaOca> partidasOca = partidaService.findEsperaOca(pageable);
				Integer numPaginas = partidasOca.getTotalPages();
				model.put("partidaOca", partidaService.findEsperaOca(pageable));
				model.put("message","La partida a la que estás intentando unirte no existe.");
				model.put("numTotalPaginas",numPaginas);
				return VIEWS_OCA_SALAS;
				
			}
		}
		Pageable pageable = PageRequest.of(numPagina,5);
		Page<PartidaOca> partidasOca = partidaService.findEsperaOca(pageable);
		model.put("partidaOca", partidaService.findEsperaOca(pageable));
		Integer numPaginas = partidasOca.getTotalPages();
		model.put("numTotalPaginas",numPaginas);
		return VIEWS_OCA_SALAS;
	}

	@GetMapping("/parchis/listar/{numPagina}")
	public String showSalaParchisList(@Param("codigo") Integer codigo,ModelMap model,RedirectAttributes redirectAttributes,@PathVariable("numPagina") Integer numPagina) {
		if(!(codigo==null)){
			Optional<PartidaParchis> partidaParchis = this.partidaService.findParchisByCodigo(codigo);
			if(partidaParchis.isPresent()){
				Integer idPartidaParchis = partidaParchis.get().getId();
				
				return unirsePartidaParchis(idPartidaParchis, model, redirectAttributes);
			} else{
				Pageable pageable = PageRequest.of(0,5);
				Page<PartidaParchis> partidasParchis = partidaService.findEsperaParchis(pageable);
				Integer numPaginas = partidasParchis.getTotalPages();
				model.put("partidaParchis", partidaParchis);
				model.put("message","La partida a la que estás intentando unirte no existe.");
				model.put("numTotalPaginas",numPaginas);
				return VIEWS_PARCHIS_SALAS;
			}
		}
		Pageable pageable = PageRequest.of(numPagina,5);
		Page<PartidaParchis> partidasParchis = partidaService.findEsperaParchis(pageable);
		Integer numPaginas = partidasParchis.getTotalPages();
		model.put("partidaParchis", partidasParchis);
		model.put("numTotalPaginas",numPaginas);
		return VIEWS_PARCHIS_SALAS;
	}

	//create
	
	@GetMapping("/crear")
	public String crearPartida(ModelMap model) {
		ProcesarPartidaForm proceso = new ProcesarPartidaForm();
		model.put("procesarPartidaForm", proceso);
		return CREATE_SALAS;
	}

	@PostMapping("/crear")
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
				redirectAttributes.addFlashAttribute("message", "La partida está llena");
				return "redirect:/partida/oca/listar/0";
			}
			return "redirect:/partida/oca/" + partida.getId() + "/espera";
		
			// Caso Parchís
		} else if (tipo.equals("parchis")) {
			PartidaParchis partida = partidaService.crearPartidaParchis(procesarPartidaForm.getNumJugador());
			try{
				jugador = jugadorService.createJugadorParchis(partida);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida está llena");
				return "redirect:/partida/parchis/listar/0";
			}
			return "redirect:/partida/parchis/" + partida.getId() + "/espera";
		} else { // ni oca ni parchis
			return "redirect:/";
		}

	}

	
	//oca
	@GetMapping("/oca/{partidaOcaId}/entrar")
	public String unirsePartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model, RedirectAttributes redirectAttributes) {
		PartidaOca partidaOca = partidaService.findPartidaOcaById(partidaOcaId);

		if(partidaOca==null) throw new ResourceNotFoundException("La partida no existe");

		Jugador newJugador = new Jugador();
		model.put("jugadores", partidaOca.getJugadores());
		model.put("jugador", newJugador);
		model.put("partidaOcaId", partidaOcaId);
		return createEnJoinSalaOca(partidaOcaId, newJugador, model, redirectAttributes);
	}

	@PostMapping("/oca/{partidaOcaId}/entrar")
	public String createEnJoinSalaOca(@PathVariable("partidaOcaId") int partidaOcaId, @Valid Jugador jugador,
			ModelMap model, RedirectAttributes redirectAttributes) {
		PartidaOca p = partidaService.findPartidaOcaById(partidaOcaId);
		if(p==null) throw new ResourceNotFoundException("La partida no existe");
		
		Collection<Jugador> jugadores = p.getJugadores();
		Boolean dentro = false;
		
		
		Usuario u = usuarioService.getLoggedUsuario();
		
		// Detecta si el usuario está en una partida, y si lo está, al tratar de unirse a una sala lo redirigá a la sala o partida en el que ya se encuentra
		if (estaJugando(u.getId())) {
			Collection<Jugador> jugadoresUsuario = jugadorService.findAllJugadoresForUsuario(u.getId());
			for (Jugador j : jugadoresUsuario) {
			if (j.getPartidaOca() != null && j.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO) {
				var partidaActual = j.getPartidaOca().getId();
				redirectAttributes.addFlashAttribute("message", "Estas jugando ya en una partida. Se te ha redirigido a ella");
				return "redirect:/partida/oca/" + partidaActual + "/jugar";
			} else if (j.getPartidaOca() != null && j.getPartidaOca().getEstado() == TipoEstadoPartida.CREADA) {
				var partidaActual = j.getPartidaOca().getId();
				redirectAttributes.addFlashAttribute("message", "Ya estas en una sala. Se te ha redirigido a ella");
				return "redirect:/partida/oca/" + partidaActual + "/espera";
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
				var partidaActual = j.getPartidaParchis().getId();
				redirectAttributes.addFlashAttribute("message", "Estas jugando ya en una partida. Se te ha redirigido a ella");
				return "redirect:/partida/parchis/" + partidaActual + "/jugar";
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.CREADA)) {
				var partidaActual = j.getPartidaParchis().getId();
				redirectAttributes.addFlashAttribute("message", "Ya estas en una sala. Se te ha redirigido a ella");
				return "redirect:/partida/parchis" + partidaActual + "/espera";
			}}
		}

		for (Jugador j : jugadores) {
			if (j.getUsuario().getId().equals(u.getId())) {
				dentro = true;
			}
		}

		if(dentro) {
			return "redirect:/partida/oca/" + partidaOcaId + "/espera";

		}else{
			try{
				jugador = jugadorService.createJugadorOca(p);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida está llena");
				return "redirect:/partida/oca/listar/0";
			}
		}
		return "redirect:/partida/oca/" + partidaOcaId + "/espera";
	}

	@GetMapping("/oca/{partidaOcaId}/espera")
	public String showPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model,  HttpServletResponse response) {
		PartidaOca partidaOca = partidaService.findPartidaOcaById(partidaOcaId);
		if(partidaOca==null) throw new ResourceNotFoundException("La partida no existe");
		response.addHeader("Refresh", REFRESH_SEECONDS);
		model.put("jugadores", partidaOca.getJugadores());
		model.put("usuarioAutenticado", usuarioService.getLoggedUsuario());
		model.put("partidaOca", partidaOca);
		return VIEWS_ESPERA;
	}

	
	//parchis
	@GetMapping("/parchis/{partidaParchisId}/entrar")
	public String unirsePartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model, RedirectAttributes redirectAttributes) {
		PartidaParchis partidaParchis = partidaService.findPartidaParchisById(partidaParchisId);
		if(partidaParchis==null) throw new ResourceNotFoundException("La partida no existe");

		Jugador jugador = new Jugador();
		Collection<Jugador> jugadores = partidaParchis.getJugadores();
		Boolean dentro = false;
		// Crear jugador
		Usuario u = usuarioService.getLoggedUsuario();
		if (estaJugando(u.getId())) {
			Collection<Jugador> jugadoresUsuario = jugadorService.findAllJugadoresForUsuario(u.getId());
			for (Jugador j : jugadoresUsuario) {
			if (j.getPartidaOca() != null && j.getPartidaOca().getEstado() == TipoEstadoPartida.JUGANDO) {
				var partidaActual = j.getPartidaOca().getId();
				redirectAttributes.addFlashAttribute("message", "Estas jugando ya en una partida. Se te ha redirigido a ella");
				return "redirect:/partida/oca/" + partidaActual + "/jugar";
			} else if (j.getPartidaOca() != null && j.getPartidaOca().getEstado() == TipoEstadoPartida.CREADA) {
				var partidaActual = j.getPartidaOca().getId();
				redirectAttributes.addFlashAttribute("message", "Ya estas en una sala. Se te ha redirigido a ella");
				return "redirect:/partida/oca/" + partidaActual + "/espera";
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.JUGANDO)) {
				var partidaActual = j.getPartidaParchis().getId();
				redirectAttributes.addFlashAttribute("message", "Estas jugando ya en una partida. Se te ha redirigido a ella");
				return "redirect:/partida/parchis/" + partidaActual + "/jugar";
			} else if (j.getPartidaParchis() != null && (j.getPartidaParchis().getEstado() == TipoEstadoPartida.CREADA)) {
				var partidaActual = j.getPartidaParchis().getId();
				redirectAttributes.addFlashAttribute("message", "Ya estas en una sala. Se te ha redirigido a ella");
				return "redirect:/partida/parchis" + partidaActual + "/espera";
			}}
		}

		if(estaJugando(u.getId())) {
			Pageable pageable = PageRequest.of(0,1);
			model.put("message", "Estas jugando ya en una partida");
			model.put("partidaOca", partidaService.findEsperaOca(pageable));
			model.put("partidaParchis", partidaService.findEsperaParchis(pageable));
			return VIEWS_PARCHIS_SALAS;
		}
		for (Jugador j : jugadores) {
			if (j.getUsuario().getId().equals(u.getId())) {
				dentro = true;
			}
		}
		if (dentro) {
			return "redirect:/partida/parchis/" +partidaParchisId + "/espera";
		}  else {
			try{
				jugador = jugadorService.createJugadorParchis(partidaParchis);
			}
			catch(PartidaLlenaException e){
				redirectAttributes.addFlashAttribute("message", "La partida está llena");
				return "redirect:/partida/parchis/listar/0";
			}
		}
		return "redirect:/partida/parchis/" +partidaParchisId + "/espera";
	}

	@GetMapping("/parchis/{partidaParchisId}/espera")
	public String showPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model, HttpServletResponse response) {
		PartidaParchis partidaParchis = partidaService.findPartidaParchisById(partidaParchisId);
		if(partidaParchis==null) throw new ResourceNotFoundException("La partida no existe");
		response.addHeader("Refresh", REFRESH_SEECONDS);
		model.put("jugadores", partidaParchis.getJugadores());
		model.put("usuarioAutenticado", usuarioService.getLoggedUsuario());
		model.put("partidaParchis", partidaParchis);
		return VIEWS_ESPERA;
	}


	
	//empezarPartida
	@GetMapping("/oca/{partidaOcaId}/empezar")
	public String initEmpezarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model, HttpServletResponse response) {
		PartidaOca partidaOca = partidaService.findPartidaOcaById(partidaOcaId);
		if(partidaOca==null) throw new ResourceNotFoundException("La partida no existe");
		
		partidaOca.setEstado(TipoEstadoPartida.JUGANDO);
		partidaOca.setFechaCreacion(LocalDateTime.now());
		partidaService.saveOca(partidaOca);
		return "redirect:/partida/oca/" + partidaOcaId + "/jugar";
	}
	
	@GetMapping("/parchis/{partidaParchisId}/empezar")
	public String initEmpezarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {
		PartidaParchis partidaParchis = partidaService.findPartidaParchisById(partidaParchisId);
		if(partidaParchis==null) throw new ResourceNotFoundException("La partida no existe");

		model.put("partidaParchis", partidaParchis);
		partidaParchis.setEstado(TipoEstadoPartida.JUGANDO);
		partidaParchis.setFechaCreacion(LocalDateTime.now());
		partidaService.saveParchis(partidaParchis);
		return "redirect:/partida/parchis/{partidaParchisId}/jugar";
	}


	//abandonar
	
	@GetMapping("/oca/{partidaOcaId}/abandonar")
	public String abandonarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model) {
		Usuario u = usuarioService.getLoggedUsuario();
		Jugador jugadorAEliminar = jugadorService.findJugadorOca(u.getId(), partidaOcaId);
		jugadorService.delete(jugadorAEliminar);
		if (jugadorAEliminar.getColor() == Color.ROJO) {
			partidaService.borrarPartidaOca(partidaOcaId);
		}
		return "redirect:/partida/oca/listar/0";
	}
	
	@GetMapping("/parchis/{partidaParchisId}/abandonar")
	public String abandonarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model) {	
		Usuario u = usuarioService.getLoggedUsuario();
		Jugador jugadorAEliminar = jugadorService.findJugadorParchis(u.getId(), partidaParchisId);
		jugadorService.delete(jugadorAEliminar);
		if (jugadorAEliminar.getColor() == Color.ROJO) {
			partidaService.borrarPartidaParchis(partidaParchisId);
		}
		return "redirect:/partida/parchis/listar/0";
	}
	



	//jugar OCA
	@GetMapping(value="/oca/{partidaOcaId}/jugar")
	public String jugarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model,HttpServletResponse response, RedirectAttributes redirectAttributes,@Param("mensaje") String mensaje){
		
		PartidaOca partida = partidaService.findPartidaOcaById(partidaOcaId);
		if(partida==null) throw new ResourceNotFoundException("La partida no existe");

		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorOca(u.getId(), partida.getId());

		if(mensaje!=null){
			partida.addMensaje(mensaje,j);
			this.partidaService.saveOca(partida);
			
		}

		if(partida.getEstado()==TipoEstadoPartida.TERMINADA){
			redirectAttributes.addFlashAttribute("message", "La partida ya ha terminado");
			return "redirect:/partida/oca/" + partidaOcaId + "/resumen";
		}else if(partida.getEstado()==TipoEstadoPartida.CREADA){
			redirectAttributes.addFlashAttribute("message", "La partida aun no ha empezado");
			return "redirect:/partida/oca/" + partidaOcaId + "/espera";
		}

		if(j!=null){
			model.put("modo","jugador");
			model.put("jugadorAutenticado", j);
			if(partida.getColorJugadorActual()!=j.getColor()) response.addHeader("Refresh", REFRESH_SEECONDS);
		}else if(partida.getUsuariosObservadores().contains(u)){
			model.put("modo","observador");
		}
		model.put("partidaOca", partida);
		model.put("now", LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
		return VIEWS_JUGAR_OCA;
	}


	@PostMapping(value="/oca/{partidaOcaId}/jugar")
	public String tirarDadosPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId,
					 ModelMap model, HttpServletResponse response){
		PartidaOca partida = partidaService.findPartidaOcaById(partidaOcaId);
		if(partida==null) throw new ResourceNotFoundException("La partida no existe");

		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorOca(u.getId(), partida.getId());
		FichaOca ficha = j.getFichaOca();

		if(j.getColor() != partida.getColorJugadorActual()){
				return "redirect:/noAccess";
		}		
		partidaService.jugarOca(partida, ficha, j);
		return "redirect:/partida/oca/" + partidaOcaId + "/jugar";
	}

	
	//jugar PARCHIS
	@GetMapping("/parchis/{partidaParchisId}/jugar")
	public String jugarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model,HttpServletResponse response,RedirectAttributes redirectAttributes,@Param("mensaje") String mensaje){
		PartidaParchis partida = partidaService.findPartidaParchisById(partidaParchisId);
		if(partida==null) throw new ResourceNotFoundException("La partida no existe");
		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorParchis(u.getId(), partida.getId());
		if(mensaje!=null){
			partida.addMensaje(mensaje,j);
			this.partidaService.saveParchis(partida);
			
		}
		

		if(partida.getEstado()==TipoEstadoPartida.TERMINADA){
			redirectAttributes.addFlashAttribute("message", "La partida ya ha terminado");
			return "redirect:/partida/parchis/" + partidaParchisId + "/resumen";

		}else if(partida.getEstado()==TipoEstadoPartida.CREADA){
			redirectAttributes.addFlashAttribute("message", "La partida aun no ha empezado");
			return "redirect:/partida/parchis/" + partidaParchisId + "/espera";
		}

		if(j!=null){
			model.put("modo","jugador");
			model.put("jugadorAutenticado", j);
			if(partida.getColorJugadorActual()!=j.getColor()) response.addHeader("Refresh", REFRESH_SEECONDS);
		}else if(partida.getUsuariosObservadores().contains(u)){
			model.put("modo","observador");
			response.addHeader("Refresh", REFRESH_SEECONDS);
		}
		model.put("partidaParchis", partida);
		//model.put("now", new Date());	
		return VIEWS_JUGAR_PARCHIS;
	}
	
	@PostMapping(value="/parchis/{partidaParchisId}/jugar")
	public String tirarDadosPartidaParchis(@ModelAttribute("MoverFichaParchisForm") MoverFichaParchisForm mfpf, @PathVariable("partidaParchisId") int partidaParchisId,
					 ModelMap model, HttpServletResponse response,  RedirectAttributes redirectAttributes){
		

		PartidaParchis partida = partidaService.findPartidaParchisById(partidaParchisId);
		if(partida==null) throw new ResourceNotFoundException("La partida no existe");
		
		Usuario u = this.usuarioService.getLoggedUsuario();
		Jugador j = this.jugadorService.findJugadorParchis(u.getId(), partida.getId());
		model.put("partidaParchis", partida);
		model.put("now", LocalDateTime.now());
		model.put("modo","jugador");
		model.put("jugadorAutenticado", j);
		model.put("debug", "raro");
		if(j.getColor() != partida.getColorJugadorActual()){
				return "redirect:/noAccess";
		}


		//El dado tiene un valor y se ha movido ficha --> procesar movimiento de ficha
		if(mfpf.getJugadorId() != null && mfpf.getFichaId() != null){
			partidaService.jugarParchis(partida, mfpf.getFichaId(), mfpf.getJugadorId(),mfpf.getDado());	
			redirectAttributes.addFlashAttribute("debug", "fichaMovida");
			if(partida.getDado()!=null){//El dado tiene valor despues de mover --> el jugador tiene que volver a mover por que ha comido, sacado 6 o metido ficha
				int dado = partidaService.tirarDado(partida);
				List<FichaParchis> fichasQuePuedenMoverse = j.getFichasQuePuedenMoverse(dado);
				model.put("fichasQueSePuedenMover", fichasQuePuedenMoverse);
				model.put("dado", dado);
				model.put("debug", "volverATirar");
				return VIEWS_JUGAR_PARCHIS;
			}
			return "redirect:/partida/parchis/{partidaParchisId}/jugar";
		}


		//El dado no tiene valor --> El jugador tira el dado para ver que saca
		if(partida.getDado()==null){ 
			int dado = partidaService.tirarDado(partida);
			List<FichaParchis> fichasQuePuedenMoverse = j.getFichasQuePuedenMoverse(dado);
			model.put("fichasQueSePuedenMover", fichasQuePuedenMoverse);
			model.put("dado", dado);
			model.put("debug", "dadoNull");
			return VIEWS_JUGAR_PARCHIS;
		}
		return "redirect:/partida/parchis/{partidaParchisId}/jugar";
	}
	
	

	@GetMapping("/oca/{partidaOcaId}/resumen")
	public String terminadaPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model, HttpServletResponse response,  
	RedirectAttributes redirectAttributes){
		PartidaOca partida = partidaService.findPartidaOcaById(partidaOcaId);
		if(partida==null) throw new ResourceNotFoundException("La partida no existe");
		
		if(partida.getEstado()==TipoEstadoPartida.TERMINADA){
			redirectAttributes.addFlashAttribute("message", "La partida ya ha terminado");
			return "redirect:/partida/oca/" + partidaOcaId + "/resumen";

		}else if(partida.getEstado()==TipoEstadoPartida.CREADA){
			redirectAttributes.addFlashAttribute("message", "La partida aun no ha empezado");
			return "redirect:/partida/oca/" + partidaOcaId + "/espera";
		}


		model.put("partida", partida);
		return VIEWS_PARTIDAOCA_TERMINADA;
	}

	@GetMapping("/parchis/{partidaParchisId}/resumen")
	public String terminadaPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model, HttpServletResponse response, 
	RedirectAttributes redirectAttributes){
		PartidaParchis partida = partidaService.findPartidaParchisById(partidaParchisId);

	if(partida.getEstado()==TipoEstadoPartida.CREADA){
			redirectAttributes.addFlashAttribute("message", "La partida todavia no ha empezado");
			return "redirect:/parchis/"+partida.getId()+"/espera";
		}
		else if(partida.getEstado()==TipoEstadoPartida.JUGANDO){
			redirectAttributes.addFlashAttribute("message", "La partida se está jugando");
			return "redirect:/parchis/"+partida.getId()+"/jugar";
		}


		model.put("partidaOca", partida);
		return VIEWS_PARTIDAOCA_TERMINADA;
	}
}