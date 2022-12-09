package org.springframework.samples.petclinic.ocachis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.logro.Logro;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MultiplesMetasDefinidasException;
import org.springframework.samples.petclinic.ocachis.user.User;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.DuplicateUsernameException;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.InvalidPasswordException;
import org.springframework.samples.petclinic.ocachis.usuario.exceptions.InvalidUsernameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsuarioServiceTests {
	
	 @Autowired
		protected UsuarioService usuarioService;

	 @Test
	 void shouldFindUsuarioWithCorrectId() {
		 Usuario usuario1 = this.usuarioService.findUsuarioById(1);
			assertThat(usuario1.getNombre()).isEqualTo("nombreUsuario");
			assertThat(usuario1.getApellido()).isEqualTo("apellidoUsuario");
	 }
	
	 
	 @Test
	 void shouldSaveUsuario() {
		 	Integer numeroInicialDeUsuarios = usuarioService.findAll().size();
		 	User user = new User();
			user.setUsername("usuarioSinUsernameRepetido");
			user.setPassword("contraseñaValida");
			user.setEnabled(true);
			Usuario usuario = new Usuario();
			usuario.setUser(user);
			usuario.setNombre("nombreValido");
			usuario.setApellido("apellidoValido");
			try {
				usuarioService.saveUsuario(usuario);
			}catch (Exception e) {
				e.printStackTrace();
			}
			assertThat(usuario.getId()).isNotEqualTo(0);
			Integer numeroFinalDeUsuarios = usuarioService.findAll().size();
			assertThat(numeroFinalDeUsuarios).isEqualTo(numeroInicialDeUsuarios + 1);
	 }
	 
		@Test
		@Transactional
		void shouldUpdateUsuario() throws DuplicateUsernameException{
			Usuario usuario = this.usuarioService.findUsuarioById(1);
			String oldApellido = usuario.getApellido();
			String newApellido = oldApellido + "X";
			usuario.setApellido(newApellido);
			this.usuarioService.updateUsuario(usuario);
			usuario = this.usuarioService.findUsuarioById(1);
			assertThat(usuario.getApellido()).isEqualTo(newApellido);
		}
		
		
		@Test
		void shoudThrowDuplicatedUsername() {
			User user = new User();
			user.setUsername("usuario");
			user.setPassword("12345");
			user.setEnabled(true);
			Usuario usuario = new Usuario();
			usuario.setUser(user);
			usuario.setNombre("nombreUsuario");
			usuario.setApellido("apellidoUsuario");
			assertThrows(DuplicateUsernameException.class, () -> usuarioService.saveUsuario(usuario),"Permite establecer usuario duplicados");
		}
		
		@Test
		void shoudThrowInvaidUsername() {
			User user = new User();
			user.setUsername("us");
			user.setPassword("12345");
			user.setEnabled(true);
			Usuario usuario = new Usuario();
			usuario.setUser(user);
			usuario.setNombre("nombreUsuario");
			usuario.setApellido("apellidoUsuario");
			assertThrows(InvalidUsernameException.class, () -> usuarioService.saveUsuario(usuario),"Permite establecer usernames cortos");
		}
		
		@Test
		void shoudThrowInvalidPassword() {
			User user = new User();
			user.setUsername("usario1234");
			user.setPassword("1234");
			user.setEnabled(true);
			Usuario usuario = new Usuario();
			usuario.setUser(user);
			usuario.setNombre("nombreUsuario");
			usuario.setApellido("apellidoUsuario");
			assertThrows(InvalidPasswordException.class, () -> usuarioService.saveUsuario(usuario),"Permite establecer contraseñas cortas");
		}
}
