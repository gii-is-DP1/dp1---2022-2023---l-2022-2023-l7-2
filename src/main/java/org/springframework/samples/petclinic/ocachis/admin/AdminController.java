package org.springframework.samples.petclinic.ocachis.admin;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //private final PartidaService partidaService;
    private final UsuarioService usuarioService;

    @Autowired
    public AdminController(UsuarioService usuarioService){
        //this.partidaService = partidaService;
        this.usuarioService = usuarioService;
    }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
    
    
    /* 
    @GetMapping("/partidaEnCurso")
    public ModelAndView partidaEnCurso(){
        ModelAndView mav = new ModelAndView("admin/listPartidas");
        mav.addObject(this.partidaService.findPartidasEnJuego());
        return mav;
    }

     @GetMapping("/partidaTerminada")
    public ModelAndView partidaTerminadas(){
        ModelAndView mav = new ModelAndView("admin/listPartidas");
        mav.addObject(this.partidaService.findPartidasJugadas());
        return mav;
    }

    @GetMapping("/partidas")
    public ModelAndView partidas(){
        ModelAndView mav = new ModelAndView("admin/listPartidas");
        mav.addObject(this.partidaService.findAll());
        return mav;
    }
    */
    
    @GetMapping(value="/listUsuarios")
    public String listarUsuarios(Map<String,Object> model){
		Collection<Usuario> results = this.usuarioService.findAll();
		model.put("selections", results);
		return "admin/listUsuarios";
	}
    

}