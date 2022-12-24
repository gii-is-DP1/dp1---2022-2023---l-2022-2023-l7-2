package org.springframework.samples.petclinic.ocachis.solicitud;

import java.util.Collection;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String listarAmigos(@Param("apodoFiltro") String apodoFiltro,Map<String,Object> model){
        Usuario usuario = usuarioService.getLoggedUsuario();
        if(apodoFiltro!=null){
        
        Collection<Usuario> usuariosFiltrado = this.usuarioService.findFiltroApodo(apodoFiltro,usuario.getId());
        model.put("filtrados", usuariosFiltrado);
        }
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
    
    @GetMapping(value="/pendientes/{solicitudId}/aceptar")
    public String aceptarSolicitud(@PathVariable("solicitudId") int solicitudId, Map<String,Object> model){
        
        this.solicitudService.aceptarSolicitud(solicitudId);
    
        return  "redirect:/solicitud/pendientes";
    }

    @GetMapping(value="/pendientes/{solicitudId}/rechazar")
    public String rechazarSolicitud(@PathVariable("solicitudId") int solicitudId, Map<String,Object> model){
        
        this.solicitudService.rechazarSolicitud(solicitudId);
    
        return  "redirect:/solicitud/pendientes";
    }
    @GetMapping(value="/amigos/{usuarioId}/agregar")
    public String agregarUsuario(@PathVariable("usuarioId") int usuarioInvitadoId, Map<String,Object> model){
        Usuario usuarioSolicitud = usuarioService.getLoggedUsuario();
        this.solicitudService.agregarUsuario(usuarioSolicitud.getId(), usuarioInvitadoId);
        return  "redirect:/solicitud/amigos";
    }
    
    

    
}
