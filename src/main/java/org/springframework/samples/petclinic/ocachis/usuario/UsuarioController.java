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
	
	@GetMapping(value="/usuarios/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("edit", false);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}
	

	@PostMapping(value = "/usuarios/nuevo")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}
		else {
			Boolean errorPresent = false;
			//verificar que el username no existe todavia
			Optional<User> user = userService.findUser(usuario.getUser().getUsername());
			if(user.isPresent()) {
				result.rejectValue("user.username", "duplicate", "is already in use");
				errorPresent=true;
				
			}
			if(usuario.getUser().getPassword().length()<3) {
				result.rejectValue("user.password", "short", "La longitud mínima de la contraseña es de caracteres");
				errorPresent=true;
			}
			
			if(errorPresent) {
				model.put("edit", false);
				return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
			}
			//creacion del usuario, user and authorities
			this.usuarioService.saveUsuario(usuario);
			
			return "redirect:/login";
		}
	}
	
	
	@GetMapping(value = "/usuarios/{usuarioId}/edit")

	public String initUpdateusuarioForm(@PathVariable("usuarioId") int usuarioId, Map<String, Object> model) {
		
		//Obtener la autenticación del usuario
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				org.springframework.security.core.userdetails.User loggedUser =null;
				
				
				//si el usuario está autenticado, obtenemos sus credenciales
				if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
				
				//si no devolvemos un error de que no hay nadie autenticado
				else return "redirect:/noAccess";
				
				//inicializamos las variables:
				//currentUser es el user que está autenticado
				//requestedUser es el user que se está intentando modificar
				//usuario es el usuario que se va a modificar
				User currentUser=null;
				User requestedUser=null;
				Usuario requestedUsuario =null;
				if(loggedUser!=null) currentUser = userService.findUser(loggedUser.getUsername()).get();
				
				//Obtener el user que se está intentando modificar si existe en la bd
				if(usuarioService.existsUsuarioById(usuarioId)) {
					requestedUsuario = this.usuarioService.findUsuarioById(usuarioId);
					requestedUser = this.usuarioService.findUsuarioById(usuarioId).getUser();
				}
				
				//Se rechaza el acceso si no es el mismo user
				if(!currentUser.equals(requestedUser) && currentUser != null && requestedUser != null) {
					return "redirect:/noAccess";
				}
		
		
		
		
		
		//else the user is loaded and the view is shown to edit it 
		model.put("usuario",requestedUsuario);
		
		//la variable edit sirve para mostrar o no el username y la contraseña, ya que estas no pueden ser actualizadas
		model.put("edit", true);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/usuarios/{usuarioId}/edit")

	public String processUpdateUsuarioForm(@Valid Usuario usuario, BindingResult result,
			@PathVariable("usuarioId") int usuarioId, Map<String, Object> model) {
		
		//Obtener la autenticación del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User loggedUser =null;
		
		
		//si el usuario está autenticado, obtenemos sus credenciales
		if(auth.isAuthenticated())	loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		//si no devolvemos un error de que no hay nadie autenticado
		else return "redirect:/noAccess";
		
		//inicializamos las variables:
		//currentUser es el user que está autenticado
		//requestedUser es el user que se está intentando modificar
		User currentUser=null;
		User requestedUser=null;
		
		if(loggedUser!=null) currentUser = userService.findUser(loggedUser.getUsername()).get();
		
		//Obtener el user que se está intentando modificar si existe en la bd
		if(usuarioService.existsUsuarioById(usuarioId)) requestedUser = this.usuarioService.findUsuarioById(usuarioId).getUser();
		
		//Se rechaza el acceso si no es el mismo user
		if(!currentUser.equals(requestedUser) && currentUser != null && requestedUser != null) {
			return "redirect:/noAccess";
		}
		
		
		if (result.hasErrors()) {
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}	
		
		//si todo funciona correctamente se actualiza el usuario		
		usuario.setId(usuarioId);
		this.usuarioService.updateUsuario(usuario);
		return "redirect:/usuario/{usuarioId}/edit";
	
	}
	
	@GetMapping(value="/editarPerfil")
	public String getProfile(Map<String, Object> model) {
		//Obtener la autenticación del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User loggedUser =null;
		
		
		//si el usuario está autenticado, obtenemos sus credenciales
		if(auth.isAuthenticated()) {
		loggedUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		Usuario usuario = usuarioService.findUsuarioByUsername(loggedUser.getUsername());
		return "redirect:/usuarios/" + usuario.getId() + "/edit";
		}else {
			return "redirect:noAccess";
		}
	}
}
