package org.springframework.samples.petclinic.ocachis.admin;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PartidaService partidaService;
    private final UsuarioService usuarioService;

    @Autowired
    public AdminController(UsuarioService usuarioService, PartidaService partidaService){
        this.partidaService = partidaService;
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
    @Transactional(readOnly = true)
    @GetMapping(value="/listUsuarios")
    public String listarUsuarios(Map<String,Object> model){
		Collection<Usuario> results = this.usuarioService.findAll();
		model.put("selections", results);
		return "admin/listUsuarios";
	}
    @Transactional(readOnly = true)
    @GetMapping(value="/listPartidas")
    public String listarPartidas(Map<String,Object> model){
		Collection<PartidaOca> results1 = this.partidaService.findAllOca();
		model.put("oca", results1);
        Collection<PartidaParchis> results2 = this.partidaService.findAllParchis();
		model.put("parchis", results2);
		return "admin/listPartidas";
	}
    @Transactional
    @GetMapping("/listUsuarios/{usuarioId}/delete")
    public String borrarUsuario(@PathVariable("usuarioId") int usuarioId, ModelMap model){
        Usuario u = this.usuarioService.findUsuarioById(usuarioId);
        if(u != null){
            usuarioService.deleteUsuarioById(usuarioId);
            model.put("message","Se ha borrado el usuario con éxito");
        }else{
            model.put("message","El usuario no existe");
        }
        
        return "redirect:/admin/listUsuarios";
    }
    
    @Transactional
    @GetMapping("/listPartidas/partidaOca/{partidaOcaId}/delete")
    public String borrarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model){
        PartidaOca p = this.partidaService.findPartidaOcaById(partidaOcaId);
        if(p != null){
            this.partidaService.borrarPartidaOca(partidaOcaId);
            model.put("message","Se ha borrado la partida con éxito");
        }else{
            model.put("message","La partida no existe");
        }
        
        return "redirect:/admin/listPartidas";
    }

        @Transactional
    @GetMapping("/listPartidas/partidaParchis/{partidaParchisId}/delete")
    public String borrarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model){
        PartidaParchis u = this.partidaService.findPartidaParchisById(partidaParchisId);
        if(u != null){
            partidaService.borrarPartidaParchis(partidaParchisId);
            model.put("message","Se ha borrado la partida con éxito");
        }else{
            model.put("message","La partida no existe");
        }
        
        return "redirect:/admin/listPartidas";
    }
}