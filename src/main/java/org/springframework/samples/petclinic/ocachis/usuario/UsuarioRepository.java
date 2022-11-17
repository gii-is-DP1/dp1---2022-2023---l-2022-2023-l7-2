package org.springframework.samples.petclinic.ocachis.usuario;


import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Usuario findById(int id);

    Collection<Usuario> findAll();
    
	@Modifying
	@Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellido = :apellido WHERE u.id = :id ")
	void updateUsuario(@Param("id") int id, @Param("nombre") String nombre, @Param("apellido") String apellido);

	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.user.username =:username")
    public Usuario findByUsername(@Param("username") String username);
}
