package org.springframework.samples.petclinic.ocachis.partida;

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
    public String unirsePartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model){
    	PartidaOca partidaOca = partidaService.findByIdOca(partidaOcaId);
        model.put("jugadores", partidaOca.getJugadores());
        return VIEWS_ESPERA;
    }
    
    @PostMapping("/{partidaOcaId}/ocaJoin")
    public String createEnJoinSalaOca(@PathVariable("partidaOcaId") int partidaOcaId, BindingResult result, ModelMap model) {
    	PartidaOca p = partidaService.findByIdOca(partidaOcaId);
    	if (result.hasErrors()) {
			return VIEWS_ESPERA;
		}else{
			Jugador j = new Jugador();
			this.jugadorService.save(j);
		}
    	return VIEWS_ESPERA;
    }
    
    @GetMapping("/{partidaParchisId}/parchisJoin")
    public String unirsePartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model){
    	PartidaParchis partidaParchis = partidaService.findByIdParchis(partidaParchisId);
        model.put("jugadores", partidaParchis.getJugadores());
        return VIEWS_ESPERA;
    }
    
    @PostMapping("/{partidaParchisId}/parchisJoin")
    public String createEnJoinSalaParchis(@PathVariable("partidaParchisId") int partidaParchisId, BindingResult result, ModelMap model) {
    	PartidaParchis p = partidaService.findByIdParchis(partidaParchisId);
    	if (result.hasErrors()) {
			return VIEWS_ESPERA;
		}else{
			//Crear jugador
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		org.springframework.security.core.userdetails.User loggedUser =null;
			if(auth.isAuthenticated()){
				loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			}else {
				return "redirect:/noAccess";
			}
			Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
			Jugador jugador = new Jugador();
			jugador.setUsuario(u);
			jugador.setPartidaParchis(p);
			if(p.getJugadores().size()==1){
				jugador.setColor(Color.AMARILLO);
			  } else if(p.getJugadores().size()==2){
				jugador.setColor(Color.VERDE);
			  } else if(p.getJugadores().size()==3){
				jugador.setColor(Color.AZUL);
			  }
	  		this.jugadorService.save(jugador);
		}
    	return VIEWS_ESPERA;
    }
    

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/create")
    public String crearPartida(ModelMap model){
        ProcesarPartidaForm proceso = new ProcesarPartidaForm();
        model.put("proceso", proceso);
        return CREATE_SALAS;
    }  

    @PostMapping("/create")
 	public String processCrearLogro( ProcesarPartidaForm proceso, BindingResult result, ModelMap model) throws IllegalAccessException{
   
        String tipo = proceso.getTipo();
        if (result.hasErrors()) {
			
			return CREATE_SALAS;
		}else{
			//Caso Oca
            if(tipo.equals("oca")){
				//Crear partida
                PartidaOca partidaOca = new PartidaOca();
                partidaOca.setMaxJugadores(proceso.getNumJugador());
                this.partidaService.saveOca(partidaOca);

				//Crear jugador
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        		org.springframework.security.core.userdetails.User loggedUser =null;
				if(auth.isAuthenticated()){
					loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
					}		
				else {
					return "redirect:/noAccess";
					}
				Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
				Jugador jugador = new Jugador();
				jugador.setUsuario(u);
				jugador.setPartidaOca(partidaOca);
				jugador.setColor(Color.ROJO);
		  		this.jugadorService.save(jugador);
			//Caso Parchís
            }else if(tipo.equals("parchis")){
                //Crear partida
				PartidaParchis partidaParchis = new PartidaParchis();
                partidaParchis.setMaxJugadores(proceso.getNumJugador());
                this.partidaService.saveParchis(partidaParchis);

				//Crear jugador
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        		org.springframework.security.core.userdetails.User loggedUser =null;
				if(auth.isAuthenticated()){
					loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
					}		
				else {
					return "redirect:/noAccess";
					}
				Usuario u = usuarioService.findUsuarioByUsuario(loggedUser.getUsername());
				Jugador jugador = new Jugador();
				jugador.setUsuario(u);
				jugador.setPartidaParchis(partidaParchis);
				jugador.setColor(Color.ROJO);
		  		this.jugadorService.save(jugador);
            }
           

            }
            return "redirect:/sala/";
        }

}