package org.springframework.samples.petclinic.ocachis.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocahis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocahis.user.User;
import org.springframework.samples.petclinic.ocahis.user.UserService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	
	@GetMapping(value="/usuario/nuevo")
	public String initCreationForm(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		List<String> errors = new ArrayList<>();
		model.put("errors", errors);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/usuario/nuevo")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}
		else {
			Boolean errorPresent = false;
			
			List<String> errors = new ArrayList<>();
			//verificar que el username no existe todavia
			
			Optional<User> user = userService.findUser(usuario.getUser().getUsername());
			if(user.isPresent()) {
				errors.add("The username already exists");
				errorPresent=true;
				
			}
			if(usuario.getUser().getPassword().length()<3) {
				errors.add("The password must be at least 3 characters long");
				errorPresent=true;
			}
			
			if(errorPresent) {
				model.put("errors", errors);
				return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
			}
			//creacion del usuario, user and authorities
			this.usuarioService.saveUsuario(usuario);
			
			return "redirect:/login";
		}
	}
	
	
	@GetMapping(value = "/usuario/{usuarioId}/edit")
	public String initUpdateusuarioForm(@PathVariable("usuarioId") int usuarioId, Map<String, Object> model) {
		
		//fetch information about logged user and demanded user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		User currentUser = userService.findUser(user.getUsername()).get();
		
		Usuario usuario = this.usuarioService.findUsuarioById(usuarioId);
		User requestedUser = usuario.getUser();
		
		
		List<String> errors = new ArrayList<>();
		
		//if they are not the same user access is denied
		if(!currentUser.equals(requestedUser)) {
			return "redirect:/noAccess";
		}
		
		//else the user is loaded and the view is shown to edit it 
		model.put("usuario",usuario);
		model.put("errors", errors);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/usuario/{usuarioId}/edit")
	public String processUpdateUsuarioForm(@Valid Usuario usuario, BindingResult result,
			@PathVariable("usuarioId") int usuarioId, Map<String, Object> model) {
		if (result.hasErrors()) {
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}
		else {
			Optional<User> user = userService.findUser(usuario.getUser().getUsername());
			if(user.isPresent()) {
				List<String> errors = new ArrayList<>();
				errors.add("The username already exists");
				model.put("errors", errors);
				return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
			}
			
			usuario.setId(usuarioId);	
			this.usuarioService.saveUsuario(usuario);
			return "redirect:/usuario/{usuarioId}";
		}
	}
	
}
