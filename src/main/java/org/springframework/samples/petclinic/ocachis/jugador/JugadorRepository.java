package org.springframework.samples.petclinic.ocachis.jugador;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer>{
    Collection<Jugador> findAll();
    Jugador findById(int id);
    Jugador save(Jugador j);
    void delete(Jugador j);

    @Query("SELECT j from Jugador j WHERE j.usuario.id = :usuarioId")
    Collection<Jugador> findAllJugadoresForUsuario(@Param("usuarioId") Integer usuarioId);
    
    @Query("SELECT j from Jugador j WHERE j.usuario.id=:usuarioId AND j.partidaParchis.id=:parchisId")
    Jugador findJugadorParchis(@Param("usuarioId") Integer usuarioId, @Param("parchisId") Integer parchisId);

    @Query("SELECT j from Jugador j WHERE j.usuario.id=:usuarioId AND j.partidaOca.id=:ocaId")
    Jugador findJugadorOca(@Param("usuarioId") Integer usuarioId, @Param("ocaId") Integer OcaId);
}
