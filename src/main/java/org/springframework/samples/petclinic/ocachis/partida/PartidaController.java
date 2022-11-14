package org.springframework.samples.petclinic.ocachis.partida;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sala")
public class PartidaController {
    
    private PartidaService partidaService;
	private UsuarioService usuarioService;
	private JugadorService jugadorService; 

    @Autowired
	public PartidaController(PartidaService partidaService, UsuarioService usuarioService, JugadorService jugadorService) {
		this.partidaService=partidaService;
		this.usuarioService = usuarioService;
		this.jugadorService = jugadorService;
	}

    private static final String VIEWS_SALAS = "partidas/salaList";
	private static final String VIEWS_ESPERA = "partidas/espera";
    private static final String CREATE_SALAS = "partidas/createPartidaForm";

    @GetMapping("/")
    public ModelAndView showSalaList() {
        ModelAndView mav = new ModelAndView(VIEWS_SALAS);
        mav.addObject("partidaOca", partidaService.findAllOca());
		mav.addObject("partidaParchis", partidaService.findAllParchis());
        return mav;
    }

	@GetMapping("/{partidaOcaId}/ocaJoin")
    public ModelAndView showSalaList1(@PathVariable("partidaOcaId") int partidaOcaId) {
        ModelAndView mav = new ModelAndView(VIEWS_ESPERA);
        mav.addObject("jugadores", partidaService.findByIdOca(partidaOcaId).getJugadores());
        return mav;
    }

	@PostMapping("/{partidaOcaId}/ocaJoin")
	public String createEnJoinSalaOca(@PathVariable("partidaOcaId") int partidaOcaId, BindingResult result, ModelMap model) {
		PartidaOca p = this.partidaService.findByIdOca(partidaOcaId);
		if(p.getJugadores().size()==4){
			result.rejectValue("partidaOca", "full", "La partida está llena!");

		} else if(p.getEstado() !=TipoEstadoPartida.CREADA){
			result.rejectValue("partidaOca", "unavailable", "No te puedes unir a esta partida.");
		} else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    org.springframework.security.core.userdetails.User loggedUser =null;	
		//si el usuario está autenticado, obtenemos sus credenciales
		if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();	
		//si no devolvemos un error de que no hay nadie autenticado
		else return "redirect:/noAccess";
		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		Jugador jugador = new Jugador();
		jugador.setUsuario(u);
		jugador.setPartidaOca(p);
		if(p.getJugadores().size()==0){
			jugador.setColor(Color.ROJO);
		  } else if(p.getJugadores().size()==1){
			jugador.setColor(Color.AMARILLO);
		  } else if(p.getJugadores().size()==2){
			jugador.setColor(Color.VERDE);
		  } else if(p.getJugadores().size()==3){
			jugador.setColor(Color.AZUL);
		  }
        this.jugadorService.save(jugador);
		return VIEWS_ESPERA;
		}
    	return VIEWS_SALAS;
		
	}

	@GetMapping("/{partidaParchisId}/parchisJoin")
    public ModelAndView showSalaList2(@PathVariable("partidaParchisId") int partidaParchisId) {
        ModelAndView mav = new ModelAndView(VIEWS_ESPERA);
        mav.addObject("jugadores", partidaService.findByIdOca(partidaParchisId).getJugadores());
        return mav;
    }

    @PostMapping(value = "/{partidaParchisId}/parchisJoin")
	public String createEnJoinSalaParchis(@PathVariable("partidaParchisId") int partidaParchisId, BindingResult result, ModelMap model) {
		PartidaParchis p = this.partidaService.findByIdParchis(partidaParchisId);
		if(p.getJugadores().size()==4){
			result.rejectValue("partidaParchis", "full", "La partida está llena!");

		} else if(p.getEstado() !=TipoEstadoPartida.CREADA){
			result.rejectValue("partidaParchis", "unavailable", "No te puedes unir a esta partida.");
		} else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	org.springframework.security.core.userdetails.User loggedUser =null;	
		//si el usuario está autenticado, obtenemos sus credenciales
			if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();	
		//si no devolvemos un error de que no hay nadie autenticado
			else return "redirect:/noAccess";
			Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
			Jugador jugador = new Jugador();
			jugador.setUsuario(u);
			jugador.setPartidaParchis(p);
			if(p.getJugadores().size()==0){
				jugador.setColor(Color.ROJO);
			  } else if(p.getJugadores().size()==1){
				jugador.setColor(Color.AMARILLO);
			  } else if(p.getJugadores().size()==2){
				jugador.setColor(Color.VERDE);
			  } else if(p.getJugadores().size()==3){
				jugador.setColor(Color.AZUL);
			  }
    	    this.jugadorService.save(jugador);
			return VIEWS_ESPERA;
		}
        return VIEWS_SALAS;
	}	

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	/*
	@GetMapping(value = "/create")
	public ModelAndView showPartidaCreate(){

		ModelAndView mav = new ModelAndView(CREATE_SALAS);
		return mav;
	}
	*/
	
    @GetMapping("/create")
    public String initCrearLogro(ModelMap model){
		ProcesarPartidaForm proceso = new ProcesarPartidaForm();
		model.put("procesarPartidaForm",proceso);
        
        return CREATE_SALAS;
    }  

	
	
	@PostMapping("/create")
	public String create(BindingResult result, ModelMap model, ProcesarPartidaForm proceso){
		//String tipo = request.getParameter("tipo");
		String tipo = proceso.getTipo();
		if(tipo=="parchis"){
			PartidaParchis p = new PartidaParchis();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       		org.springframework.security.core.userdetails.User loggedUser =null;	
			if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();	
			else return "redirect:/noAccess";
			Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
			Jugador jugador = new Jugador();
			jugador.setUsuario(u);
			jugador.setPartidaParchis(p);
			jugador.setColor(Color.ROJO);
        	this.jugadorService.save(jugador);

			this.partidaService.saveParchis(p);
		} else if(tipo=="oca"){
			PartidaOca p = new PartidaOca();
			//Crear un jugador que sera el usuario que la crea
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	org.springframework.security.core.userdetails.User loggedUser =null;	
			//si el usuario está autenticado, obtenemos sus credenciales
			if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();	
			//si no devolvemos un error de que no hay nadie autenticado
			else return "redirect:/noAccess";
			Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
			Jugador jugador = new Jugador();
			jugador.setUsuario(u);
			jugador.setPartidaOca(p);
			jugador.setColor(Color.ROJO);
		  	this.jugadorService.save(jugador);

			this.partidaService.saveOca(p);

		} else{

		}
		
		return "welcome";
	}

	/*
	@GetMapping(value = "/parchisCreate")
	public ModelAndView showPartidaCreate1(){
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
	
	@PostMapping(value = "/parchisCreate")
	public String processCreationFormParchis(BindingResult result, ModelMap model) {
		PartidaParchis p = new PartidaParchis();

		//Crear un jugador que sera el usuario que la crea
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User loggedUser =null;	
		//si el usuario está autenticado, obtenemos sus credenciales
		if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();	
		//si no devolvemos un error de que no hay nadie autenticado
		else return "redirect:/noAccess";
		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		Jugador jugador = new Jugador();
		jugador.setUsuario(u);
		jugador.setPartidaParchis(p);
		if(p.getJugadores().size()==0){
			jugador.setColor(Color.ROJO);
		  } else if(p.getJugadores().size()==1){
			jugador.setColor(Color.AMARILLO);
		  } else if(p.getJugadores().size()==2){
			jugador.setColor(Color.VERDE);
		  } else if(p.getJugadores().size()==3){
			jugador.setColor(Color.AZUL);
		  }
        this.jugadorService.save(jugador);


		this.partidaService.saveParchis(p);
		return "welcome";
	}

	

	@PostMapping(value = "/ocaCreate")
	public String processCreationFormOca( BindingResult result, ModelMap model) {
		PartidaOca p = new PartidaOca();

		
		//Crear un jugador que sera el usuario que la crea
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User loggedUser =null;	
		//si el usuario está autenticado, obtenemos sus credenciales
		if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();	
		//si no devolvemos un error de que no hay nadie autenticado
		else return "redirect:/noAccess";
		Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
		Jugador jugador = new Jugador();
		jugador.setUsuario(u);
		jugador.setPartidaOca(p);
		if(p.getJugadores().size()==0){
			jugador.setColor(Color.ROJO);
		  } else if(p.getJugadores().size()==1){
			jugador.setColor(Color.AMARILLO);
		  } else if(p.getJugadores().size()==2){
			jugador.setColor(Color.VERDE);
		  } else if(p.getJugadores().size()==3){
			jugador.setColor(Color.AZUL);
		  }
        this.jugadorService.save(jugador);


		this.partidaService.saveOca(p);
		return VIEWS_ESPERA;
	}
	*/
}