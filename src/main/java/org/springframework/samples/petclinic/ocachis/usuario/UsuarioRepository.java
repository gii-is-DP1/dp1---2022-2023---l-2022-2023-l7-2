package org.springframework.samples.petclinic.ocachis.usuario;


import java.util.List;
import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Usuario findById(int id);

    Collection<Usuario> findAll();
    
	@Modifying
	@Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellido = :apellido WHERE u.id = :id ")
	void updateUsuario(@Param("id") int id, @Param("nombre") String nombre, @Param("apellido") String apellido);

	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.user.username =:username")
    public Usuario findByUsername(@Param("username") String username);

	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.user.username LIKE %:apodo% AND usuario.id !=:usuarioId ")
	Collection<Usuario> findFiltroApodo(@Param("apodo") String apodo,@Param("usuarioId") int id);

	List<Usuario> findTop5ByOrderByEstadisticasParchisFichasComidasDesc();
	
	List<Usuario> findTop5ByOrderByEstadisticasOcaVecesCaidoEnMuerteDesc();
	
	List<Usuario> findTop5ByOrderByEstadisticasParchisPartidasGanadasDesc();
	
	List<Usuario> findTop5ByOrderByEstadisticasOcaPartidasGanadasDesc();
}
