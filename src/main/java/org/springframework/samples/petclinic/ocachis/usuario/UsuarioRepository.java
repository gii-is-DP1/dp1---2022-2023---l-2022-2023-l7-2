package org.springframework.samples.petclinic.ocachis.usuario;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.id =:id")
	public Usuario findById(@Param("id") int id);

	@Modifying
	@Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellido = :apellido WHERE u.id = :id ")
	public void updateUsuario(@Param("id") int id, @Param("nombre") String nombre, @Param("apellido") String apellido);
}
