package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadisticasGlobalesRepository extends CrudRepository<EstadisticasGlobales,Integer>{
    @Query("Select ea FROM EstadisticasGlobales ea WHERE ea.id = 1")
    EstadisticasGlobales get();

    @Query("SELECT count(pp) FROM PartidaParchis pp")
    Integer getSumaParchisJugadas();

    @Query("SELECT max(u.estadisticas.parchisDuracionMaxima) FROM Usuario u")
    Integer getParchisDuracionMaxima();

    @Query("SELECT min(u.estadisticas.parchisDuracionMinima) FROM Usuario u")
    Integer getParchisDuracionMinima();

    @Query("SELECT sum(pp.duracion) FROM PartidaParchis pp")
    Integer getSumaParchisDuracionTotal();

    @Query("SELECT sum(pp.duracion)/count(pp) FROM PartidaParchis pp")
    Integer getParchisDuracionMedia();

    @Query("SELECT sum(u.estadisticas.parchisFichasComidas) FROM Usuario u")
    Integer getSumaParchisFichasComidas();

    ////////////////////////////////////////

    @Query("SELECT count(po) FROM PartidaOca po")
    Integer getSumaOcaJugadas();

    @Query("SELECT max(u.estadisticas.ocaDuracionMaxima) FROM Usuario u")
    Integer getOcaDuracionMaxima();

    @Query("SELECT min(u.estadisticas.ocaDuracionMinima) FROM Usuario u")
    Integer getOcaDuracionMinima();

    @Query("SELECT sum(po.duracion) FROM PartidaOca po")
    Integer getSumaOcaDuracionTotal();

    @Query("SELECT sum(po.duracion)/count(po) FROM PartidaOca po")
    Integer getOcaDuracionMedia();

    @Query("SELECT sum(u.estadisticas.ocaVecesCaidoEnMuerte) FROM Usuario u")
    Integer getSumaOcaVecesCaidoEnMuerte();

    EstadisticasGlobales save(EstadisticasGlobales est);
}
