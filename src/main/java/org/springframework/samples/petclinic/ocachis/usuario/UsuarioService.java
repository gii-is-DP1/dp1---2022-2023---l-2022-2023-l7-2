package org.springframework.samples.petclinic.ocachis.usuario;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocahis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocahis.user.UserService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public void saveUsuario(@Valid Usuario usuario) {
		if(usuario.getEstadisticas()==null)usuario.setEstadisticas(new Estadisticas());
//		usuario.setIconIfDefault();
		
		// creamos el usuario
		usuarioRepository.save(usuario);
//		//creamos el user
		userService.saveUser(usuario.getUser());
//		//creamos authorities
		authoritiesService.saveAuthorities(usuario.getUser().getUsername(), "player");
		
	}
	

	@Transactional(readOnly = true)
	public Usuario findUsuarioById(int id) throws DataAccessException {
		return usuarioRepository.findById(id);
	}

}