package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer>{
   

    @Query("SELECT j from Jugador j WHERE j.usuario.id = :usuarioId")
    Collection<Jugador> findAllJugadoresForUsuario(@Param("usuarioId") Integer usuarioId);
    
    @Query("SELECT j from Jugador j WHERE j.usuario.id=:usuarioId AND j.partidaParchis.id=:parchisId")
    Jugador findJugadorParchis(@Param("usuarioId") Integer usuarioId, @Param("parchisId") Integer parchisId);

    @Query("SELECT j from Jugador j WHERE j.usuario.id=:usuarioId AND j.partidaOca.id=:ocaId")
    Jugador findJugadorOca(@Param("usuarioId") Integer usuarioId, @Param("ocaId") Integer OcaId);

    @Query("SELECT COUNT(j) FROM Jugador j INNER JOIN j.partidaParchis pp WHERE j.usuario.id = :id AND (pp.estado = 1 OR pp.estado = 0)")
    Integer contarJugadoresParchisJugando(@Param ("id") int id);

    @Query("SELECT COUNT(j) FROM Jugador j INNER JOIN j.partidaOca po WHERE j.usuario.id = :id AND (po.estado = 1 OR po.estado = 0)")
    Integer contarJugadoresOcaJugando(@Param ("id") int id);
}
