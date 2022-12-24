package org.springframework.samples.petclinic.ocachis.solicitud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudService {
        private SolicitudRepository solicitudRepository;
        private UsuarioRepository usuarioRepository;

        @Autowired
        public SolicitudService(SolicitudRepository solicitudRepository, UsuarioRepository usuarioRepository){
          this.solicitudRepository = solicitudRepository;
          this.usuarioRepository = usuarioRepository;
        }
    
        @Transactional(readOnly=true)
        public Collection<Solicitud> findAllLogros(){
            return this.solicitudRepository.findAll();
        }

        @Transactional(readOnly=true)
        public Collection<Usuario> findAllAmigos(int id){
            Collection<Solicitud> solicitudes =  this.solicitudRepository.findAllAmigos(id);
            Collection<Usuario> amigos = new ArrayList<>();
            for(Solicitud solicitud : solicitudes){
                if(solicitud.getUsuarioInvitado().getId()==id){
                    amigos.add(solicitud.getUsuarioSolicitud());
                }else{
                    amigos.add(solicitud.getUsuarioInvitado());
                }
            }
            return amigos;
        }

        @Transactional(readOnly=true)
        public Collection<Solicitud> findAllSolicitudesPendientes(int id){
            return this.solicitudRepository.findAllSolicitudesPendientes(id);
        }
        
        @Transactional
        public void aceptarSolicitud(int id){
            Solicitud solicitud = this.solicitudRepository.findById(id);
            solicitud.setTipoEstado(TipoEstadoSolicitud.ACEPTADA);
            this.solicitudRepository.save(solicitud);
        }

        @Transactional
        public void rechazarSolicitud(int id){
            Solicitud solicitud = this.solicitudRepository.findById(id);
            solicitud.setTipoEstado(TipoEstadoSolicitud.RECHAZADA);
            this.solicitudRepository.save(solicitud);
        }

        @Transactional
        public void agregarUsuario(int usuarioSolicitudId, int usuarioInvitadoId ){
            Solicitud solicitud = new Solicitud();
            solicitud.setTipoEstado(TipoEstadoSolicitud.ENVIADA);
            Usuario usuarioSolicitud = this.usuarioRepository.findById(usuarioSolicitudId);
            Usuario usuarioInvitado = this.usuarioRepository.findById(usuarioInvitadoId);
            solicitud.setUsuarioSolicitud(usuarioSolicitud);
            solicitud.setUsuarioInvitado(usuarioInvitado);
            this.solicitudRepository.save(solicitud);

        }



}
