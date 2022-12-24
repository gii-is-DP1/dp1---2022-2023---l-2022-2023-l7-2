package org.springframework.samples.petclinic.ocachis.solicitud;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface SolicitudRepository extends CrudRepository<Solicitud,Integer>{
    Collection<Solicitud> findAll();
   
     Solicitud findById(int id);

     Solicitud save(Solicitud s);
    
    @Query("SELECT solicitud FROM Solicitud solicitud WHERE (solicitud.usuarioInvitado.id=:usuarioId OR solicitud.usuarioSolicitud.id=:usuarioId) AND solicitud.tipoEstado=1")
    Collection<Solicitud> findAllAmigos(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT solicitud FROM Solicitud solicitud WHERE (solicitud.usuarioInvitado.id=:usuarioId OR solicitud.usuarioSolicitud.id=:usuarioId) AND solicitud.tipoEstado=0")
    Collection<Solicitud> findAllSolicitudesPendientes(@Param("usuarioId") Integer usuarioId);

   

   
}
