package org.springframework.samples.petclinic.ocachis.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.user.User;
import org.springframework.samples.petclinic.ocachis.user.UserService;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
	
	private static final String VIEWS_USUARIO_CREATE_OR_UPDATE_FORM = "usuarios/createOrUpdateUsuarioForm";
 
	private final UsuarioService usuarioService;
	private final UserService userService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, UserService userService, AuthoritiesService authoritiesService) {
		this.usuarioService = usuarioService;
		this.userService = userService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	/**Método que verifica que un usuarioId es el mismo que el usuarioId de la persona autenticada
	   */
	private Boolean esElMismoUserQueElAutenticado(Integer usuarioId) {
		Usuario usuarioAutenticado = getAutenticadedUsuario();
		User userOcachisAutenticado = usuarioAutenticado.getUser();
		User userOcachisSolicitado = this.usuarioService.findUsuarioById(usuarioId).getUser();
		if(userOcachisSolicitado != null &&	userOcachisAutenticado.equals(userOcachisSolicitado)) 
			return true;
		return false;
	}
	
	private Usuario getAutenticadedUsuario(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(!auth.isAuthenticated()) return null;
		 String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();	
		 return this.usuarioService.findUsuarioByUsername(username);
	}
	
	@GetMapping(value="/usuarios/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}
	

	@PostMapping(value = "/usuarios/nuevo")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result, Map<String, Object> model) {
			try {
				usuarioService.saveUsuario(usuario);
			}catch (InvalidUsernameException e) {
				result.rejectValue("user.username", "short", "La longitud mínima del username es de 3 caracteres");
			}catch(InvalidPasswordException e) {
				result.rejectValue("user.password", "short", "La longitud mínima de la contraseña es de 5 caracteres");
			}catch(DuplicateUsernameException e) {
				result.rejectValue("user.username", "duplicate", "Este usuario ya existe");
			}
			
			if (result.hasErrors()) {
				return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
			}
			return "redirect:/login";
	}
	
	@GetMapping(value = "/usuarios/{usuarioId}/edit")
	public String initUpdateusuarioForm(@PathVariable("usuarioId") int usuarioId, Map<String, Object> model) {
		if(!esElMismoUserQueElAutenticado(usuarioId)) return "redirect:/noAccess";		
		Usuario usuarioSolicitado = this.usuarioService.findUsuarioById(usuarioId);
		model.put("usuario",usuarioSolicitado);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/usuarios/{usuarioId}/edit")
	public String processUpdateUsuarioForm(@Valid Usuario usuario, BindingResult result,
			@PathVariable("usuarioId") int usuarioId, Map<String, Object> model) {			
		if(!esElMismoUserQueElAutenticado(usuarioId)) return "redirect:/noAccess";
		usuario.setId(usuarioId);
		
		if (result.hasErrors()) {
			model.put("usuario", usuario);
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}
		this.usuarioService.updateUsuario(usuario);
		model.put("message", "Usuario actualizado correctamente");
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}	
	
	
	@GetMapping(value="/usuarios/profile")
	public String editProfile(Map<String, Object> model) {
		Usuario usuarioEditado = getAutenticadedUsuario();
		if(usuarioEditado!=null) return "redirect:/usuarios/"+usuarioEditado.getId()+"/edit";
		else return "redirect:/noAccess";
	}
}
