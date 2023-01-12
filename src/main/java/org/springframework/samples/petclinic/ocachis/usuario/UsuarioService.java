package org.springframework.samples.petclinic.ocachis.usuario;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOcaRepository;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchisRepository;
import org.springframework.samples.petclinic.ocachis.solicitud.Solicitud;
import org.springframework.samples.petclinic.ocachis.solicitud.SolicitudRepository;
import org.springframework.samples.petclinic.ocachis.user.AuthoritiesService;
import org.springframework.samples.petclinic.ocachis.user.User;
import org.springframework.samples.petclinic.ocachis.user.UserService;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.DuplicateUsernameException;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.InvalidPasswordException;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.InvalidUsernameException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	private static final Integer LONGITUD_MINIMA_USERNAME = 3;
	private static final Integer LONGITUD_MINIMA_PASSWORD = 5;
	
	private UsuarioRepository usuarioRepository;
	private SolicitudRepository solicitudRepository;
	private PartidaOcaRepository partidaOcaRepository;
	private PartidaParchisRepository partidaParchisRepository;

	private UserService userService;
	
	private AuthoritiesService authoritiesService;
	

	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, UserService userService, AuthoritiesService authoritiesService,SolicitudRepository solicitudRepository, PartidaOcaRepository partidaOcaRepository, PartidaParchisRepository partidaParchisRepository) {
		this.usuarioRepository = usuarioRepository;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
		this.solicitudRepository = solicitudRepository;
		this.partidaOcaRepository = partidaOcaRepository;
		this.partidaParchisRepository = partidaParchisRepository;
	
	}

	@Transactional(rollbackFor = {DuplicateUsernameException.class, InvalidPasswordException.class, InvalidUsernameException.class})
	public void saveUsuario(@Valid Usuario usuario) throws DuplicateUsernameException, InvalidPasswordException, InvalidUsernameException {
		if(usuario.getEstadisticas()==null)usuario.setEstadisticas(new Estadisticas());
		Optional<User> user = userService.findUser(usuario.getUser().getUsername());
		if(user.isPresent()) throw new DuplicateUsernameException();
		if(usuario.getUser().getUsername().length()<LONGITUD_MINIMA_USERNAME) throw new InvalidUsernameException();
		if(usuario.getUser().getPassword().length()<LONGITUD_MINIMA_PASSWORD) throw new InvalidPasswordException();
		
		// creamos el usuario
		usuarioRepository.save(usuario);
//		//creamos el user
		userService.saveUser(usuario.getUser());
//		//creamos authorities
		authoritiesService.saveAuthorities(usuario.getUser().getUsername(), "jugador");
	}

	@Transactional
	public void resetearUsuario(Usuario u){
		u.resetEstadisticas();
		usuarioRepository.save(u);
	}

	@Transactional
	public void updateUsuario(@Valid Usuario usuario) {
		usuarioRepository.updateUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido());
		
	}

	public boolean existsUsuarioById(int usuarioId) {
		return usuarioRepository.existsById(usuarioId);
	}

    
    @Transactional(readOnly = true)
    public Collection<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }
    
	@Transactional
    public void deleteUsuarioById(int id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		Usuario u = usuario.get();
		Collection<Solicitud> solicitudes = solicitudRepository.findAllSolicitudesUsuario(id);
		if(solicitudes != null){
			for(Solicitud s: solicitudes){
				this.solicitudRepository.delete(s);
			}
		}
		
		Collection<PartidaOca> partidasOcaObservando = this.partidaOcaRepository.findPartidaUsuarioObservada(usuario.get());
		Collection<PartidaParchis> partidasParchisObservando = this.partidaParchisRepository.findPartidaUsuarioObservada(usuario.get());
		if(partidasOcaObservando != null){
			for(PartidaOca p : partidasOcaObservando){
				List<Usuario> l = p.getUsuariosObservadores();
				l.remove(usuario.get());
				p.setUsuariosObservadores(l);
			}
		}else if(partidasParchisObservando !=null){
			for (PartidaParchis p : partidasParchisObservando){
				
				List<Usuario> observadores = p.getUsuariosObservadores();
				observadores.remove(usuario.get());
				p.setUsuariosObservadores(observadores);
				
			}
		}

        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Usuario findUsuarioById(int id){
        Optional<Usuario> optUsuario = this.usuarioRepository.findById(id);
		if(optUsuario.isEmpty()) throw new ResourceNotFoundException("Usuario no encontrado");
		return optUsuario.get();
    }

	@Transactional(readOnly = true)
    public Usuario findUsuarioByUsername(String username) throws DataAccessException {
        return usuarioRepository.findByUsername(username);
    }


	public Usuario getLoggedUsuario(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(!auth.isAuthenticated()) return null;
		 String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();	
		 return this.findUsuarioByUsername(username);
	}

	public Collection<Usuario> findFiltroApodo(String apodo,int id){
		  Collection<Solicitud> solicitudes = this.solicitudRepository.findAllAmigos(id);
		  Collection<Usuario> usuarios =  this.usuarioRepository.findFiltroApodo(apodo, id);
		  for(Solicitud solicitud: solicitudes){
			if(usuarios.contains(solicitud.getUsuarioInvitado()) ){
				usuarios.remove(solicitud.getUsuarioInvitado());
			}
			else if(usuarios.contains(solicitud.getUsuarioSolicitud())){
				usuarios.remove(solicitud.getUsuarioSolicitud());
			}
		  }
		return usuarios;
	  }

	public List<Usuario> Top5ParchisFichasComidas(){
		return usuarioRepository.findTop5ByOrderByEstadisticasParchisFichasComidasDesc();
	}

	public List<Usuario> Top5OcaVecesCaidoEnMuerte(){
		return usuarioRepository.findTop5ByOrderByEstadisticasOcaVecesCaidoEnMuerteDesc();
	}

	public List<Usuario> Top5ParchisPartidasGanadas(){
		return usuarioRepository.findTop5ByOrderByEstadisticasParchisPartidasGanadasDesc();
	}

	public List<Usuario> Top5OcaPartidasGanadas(){
		return usuarioRepository.findTop5ByOrderByEstadisticasOcaPartidasGanadasDesc();
	}
}
