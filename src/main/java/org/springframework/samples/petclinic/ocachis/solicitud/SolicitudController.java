package org.springframework.samples.petclinic.ocachis.solicitud;

import java.util.Collection;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController {
    
    private static final String VIEWS_AMIGOS = "solicitud/amigos";
	private static final String VIEWS_PENDIENTES = "solicitud/pendientes";
    private SolicitudService solicitudService;
    private UsuarioService usuarioService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService, UsuarioService usuarioService){
        this.solicitudService = solicitudService;
        this.usuarioService = usuarioService;
    }
    @GetMapping(value="/amigos")
    public String listarAmigos(Map<String,Object> model){

        Usuario usuario = usuarioService.getLoggedUsuario();
        

       Collection<Usuario> amigos = solicitudService.findAllAmigos(usuario.getId());
		model.put("amigos", amigos);
		return VIEWS_AMIGOS;
    }
    @GetMapping(value="/pendientes")
    public String listarSolicitudesPendientes(Map<String,Object> model){
        Usuario usuario = usuarioService.getLoggedUsuario();
        Collection<Solicitud> pendientes = solicitudService.findAllSolicitudesPendientes(usuario.getId());
        model.put("pendientes", pendientes);
        return VIEWS_PENDIENTES;
    }
    /* 
    @GetMapping(value="/solicitud/pendientes/{solicitudId}/aceptar")
    public String aceptarSolicitud(Map<String,Object> model){

        listarSolicitudesPendientes(model)
    }
    */
}
