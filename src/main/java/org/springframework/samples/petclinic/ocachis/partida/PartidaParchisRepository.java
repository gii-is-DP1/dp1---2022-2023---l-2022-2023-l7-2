package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaParchisRepository extends CrudRepository<PartidaParchis, Integer> {

    Collection<PartidaParchis> findAll();

    @Query("SELECT parchis from PartidaParchis parchis where parchis.estado=0 OR parchis.estado=1")
    Page<PartidaParchis> findEsperaParchis(Pageable pageable);
   

    @Query("SELECT parchis from PartidaParchis parchis where parchis.codigoPartida=:codigo")
    Optional<PartidaParchis> findByCodigo(@Param("codigo") int codigo);

    @Query("SELECT parchis FROM PartidaParchis parchis")
    Page<PartidaParchis> findAllPageable(Pageable p);    

    @Query("SELECT parchis FROM PartidaParchis parchis WHERE :usuario MEMBER OF parchis.usuariosObservadores")
    Collection<PartidaParchis> findPartidaUsuarioObservada(@Param("usuario") Usuario usuario);
}