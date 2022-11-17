package org.springframework.samples.petclinic.ocachis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.ocachis.user.User;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsuarioServiceTests {
	 @Autowired
		protected UsuarioService usuarioService;

		@Test
		@Transactional
		void shouldUpdateUsuario() {
			Usuario usuario = this.usuarioService.findUsuarioById(1);
			String oldApellido = usuario.getApellido();
			String newApellido = oldApellido + "X";

			usuario.setApellido(newApellido);
			this.usuarioService.saveUsuario(usuario);

			// retrieving new name from database
			usuario = this.usuarioService.findUsuarioById(1);
			assertThat(usuario.getApellido()).isEqualTo(newApellido);
		}


}
