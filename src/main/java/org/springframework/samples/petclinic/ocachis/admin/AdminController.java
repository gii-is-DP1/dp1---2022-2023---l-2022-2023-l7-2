package org.springframework.samples.petclinic.ocachis.admin;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collection;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
  
    @GetMapping(value="/listUsuarios")
    public String listarUsuarios(Map<String,Object> model){
		Collection<Usuario> results = this.usuarioService.findAll();
		model.put("selections", results);
		return "admin/listUsuarios";
	}

    @GetMapping(value="/listPartidas/oca/{numPagina}")
    public String listarPartidasOca(@PathVariable("numPagina") Integer numPagina, Map<String,Object> model){
        Pageable pageable = PageRequest.of(numPagina,5);
		Page<PartidaOca> results = this.partidaService.findAllPageableOca(pageable);
        Integer numPaginas = results.getTotalPages();
        model.put("numTotalPaginas",numPaginas);
		model.put("oca", results);
		return "admin/listPartidas";
	}

    @GetMapping(value="/listPartidas/parchis/{numPagina}")
    public String listarPartidasParchis(@PathVariable("numPagina") Integer numPagina,Map<String,Object> model){
        Pageable pageable = PageRequest.of(numPagina,5);
        Page<PartidaParchis> results = this.partidaService.findAllPageableParchis(pageable);
        Integer numPaginas = results.getTotalPages();
        model.put("numTotalPaginas",numPaginas);
		model.put("parchis", results);
		return "admin/listPartidas";
	}

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

    @GetMapping("/listUsuarios/{usuarioId}/reset")
    public String resetearUsuario(@PathVariable("usuarioId") int usuarioId, ModelMap model){
        Usuario u = this.usuarioService.findUsuarioById(usuarioId);
        if(u != null){
            usuarioService.resetearUsuario(u);
            model.put("message","Se han reseteado las estadisticas del usuario con éxito");
        }else{
            model.put("message","El usuario no existe");
        }
        
        return "redirect:/admin/listUsuarios";
    }
    

    @GetMapping("/listPartidas/partidaOca/{partidaOcaId}/delete")
    public String borrarPartidaOca(@PathVariable("partidaOcaId") int partidaOcaId, ModelMap model){
        PartidaOca p = this.partidaService.findPartidaOcaById(partidaOcaId);
        if(p != null){
            this.partidaService.borrarPartidaOca(partidaOcaId);
            model.put("message","Se ha borrado la partida con éxito");
        }else{
            model.put("message","La partida no existe");
        }
        
        return "redirect:/admin/listPartidas/oca/0";
    }

       
    @GetMapping("/listPartidas/partidaParchis/{partidaParchisId}/delete")
    public String borrarPartidaParchis(@PathVariable("partidaParchisId") int partidaParchisId, ModelMap model){
        PartidaParchis u = this.partidaService.findPartidaParchisById(partidaParchisId);
        if(u != null){
            partidaService.borrarPartidaParchis(partidaParchisId);
            model.put("message","Se ha borrado la partida con éxito");
        }else{
            model.put("message","La partida no existe");
        }
        
        return "redirect:/admin/listPartidas/parchis/0";
    }
}