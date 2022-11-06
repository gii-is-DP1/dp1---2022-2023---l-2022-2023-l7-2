package org.springframework.samples.petclinic.ocachis.usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.id =:id")
	public Usuario findById(@Param("id") int id);
}
