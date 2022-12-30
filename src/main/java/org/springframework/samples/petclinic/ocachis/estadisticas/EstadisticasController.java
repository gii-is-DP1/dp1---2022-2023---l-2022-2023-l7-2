package org.springframework.samples.petclinic.ocachis.estadisticas;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EstadisticasController {
    private EstadisticasService estadisticasService;
    private UsuarioService usuarioService;
    private static final String VIEWS_ESTADISTICAS_USUARIO = "estadisticas/estadisticasUsuario";

    @Autowired
    public EstadisticasController(EstadisticasService estadisticasService, UsuarioService usuarioService){
        this.estadisticasService = estadisticasService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/miestadisticas")
    public String showEstadisticasUsuario(Map<String,Object> model){
        Usuario usuario = usuarioService.getLoggedUsuario();
        Estadisticas estadisticas = this.estadisticasService.getEstadisticasUsuario(usuario.getId());
        model.put("estadisticas", estadisticas);
        model.put("apodoUsuario",usuario.getUser().getUsername());
        return VIEWS_ESTADISTICAS_USUARIO;       
    }
    
}
