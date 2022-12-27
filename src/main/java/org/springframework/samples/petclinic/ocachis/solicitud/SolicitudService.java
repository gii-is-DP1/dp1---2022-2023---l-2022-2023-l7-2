package org.springframework.samples.petclinic.ocachis.solicitud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorRepository;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOcaRepository;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchisRepository;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudService {
        private SolicitudRepository solicitudRepository;
        private UsuarioRepository usuarioRepository;
        private JugadorService jugadorService;
        private PartidaOcaRepository partidaOcaRepository;
        private PartidaParchisRepository partidaParchisRepository;

        @Autowired
        public SolicitudService(SolicitudRepository solicitudRepository, UsuarioRepository usuarioRepository, JugadorService jugadorService, PartidaOcaRepository partiOcaRepository,PartidaParchisRepository partidaParchisRepository){
          this.solicitudRepository = solicitudRepository;
          this.usuarioRepository = usuarioRepository;
          this.jugadorService = jugadorService;
          this.partidaOcaRepository = partiOcaRepository;
          this.partidaParchisRepository = partidaParchisRepository;
        }
    
        @Transactional(readOnly=true)
        public Collection<Solicitud> findAllLogros(){
            return this.solicitudRepository.findAll();
        }

        @Transactional(readOnly=true)
        public Map<Usuario,Boolean> findAllAmigos(int id){
            
            Collection<Solicitud> solicitudes =  this.solicitudRepository.findAllAmigos(id);
            Map<Usuario,Boolean> amigos = new HashMap<Usuario,Boolean>();
           
            for(Solicitud solicitud : solicitudes){
                if(solicitud.getUsuarioInvitado().getId()==id){
                    Usuario usuarioSolicitud = solicitud.getUsuarioSolicitud();
                    Boolean estaJugando = this.jugadorService.estaDentroPartida(usuarioSolicitud.getId());
                    amigos.put(usuarioSolicitud, estaJugando);

                   
                }else{
                    Usuario usuarioInvitado = solicitud.getUsuarioInvitado();
                    Boolean estaJugando = this.jugadorService.estaDentroPartida(usuarioInvitado.getId());
                   amigos.put(usuarioInvitado, estaJugando);
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
        public Boolean sePuedeEspectar(Integer usuarioId, Map<String,Integer> partidaAEspectar){
            Set<Usuario> amigos = this.findAllAmigos(usuarioId).keySet();
            Boolean sePuedeEspectar = true;
            if(partidaAEspectar.get("parchis")!=null){
                Integer idPartidaParchis = partidaAEspectar.get("parchis");
                PartidaParchis partidaParchis =this.partidaParchisRepository.findByIdNoOptional(idPartidaParchis);
                Collection<Jugador> jugadores = partidaParchis.getJugadores();
                for(Jugador jugador : jugadores){
                    if(!(amigos.contains(jugador.getUsuario()))){
                        sePuedeEspectar = false;
                    }
                }    
    
            }else{
                Integer idPartidaOca = partidaAEspectar.get("oca");
                PartidaOca partidaOca = this.partidaOcaRepository.findByIdNoOptional(idPartidaOca);
                Collection<Jugador> jugadores = partidaOca.getJugadores();
                for(Jugador jugador : jugadores){
                    if(!(amigos.contains(jugador.getUsuario()))){
                        sePuedeEspectar = false;
                    }
                }    
            }

            return sePuedeEspectar;
        }
        public String obtenerUrlEspectar(Map<String,Integer> partidaAEspectar){
            String espectarUrl;
            if(partidaAEspectar.get("parchis")!=null){
                Integer idPartidaParchis = partidaAEspectar.get("parchis");
               
                espectarUrl = "/partida/parchis/"+idPartidaParchis+"/jugar";
    
            }else{
                Integer idPartidaOca = partidaAEspectar.get("oca");
                espectarUrl = "/partida/oca/" +idPartidaOca+"/jugar";
            }
            return espectarUrl;
        }



}
