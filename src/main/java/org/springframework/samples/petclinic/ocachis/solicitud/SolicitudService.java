package org.springframework.samples.petclinic.ocachis.solicitud;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.jugador.JugadorService;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.partida.PartidaService;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudService {
        private SolicitudRepository solicitudRepository;
        private JugadorService jugadorService;
        private PartidaService partidaService;
        private UsuarioService usuarioService;

        @Autowired
        public SolicitudService(SolicitudRepository solicitudRepository, JugadorService jugadorService, PartidaService partidaService, UsuarioService usuarioService){
          this.solicitudRepository = solicitudRepository;
          this.jugadorService = jugadorService;
          this.partidaService = partidaService;
          this.usuarioService = usuarioService;
        }
    
        @Transactional
        public Solicitud save(Solicitud s){
            return this.solicitudRepository.save(s);
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
        public Collection<Solicitud> findAllSolicitudesPendientes(Integer usuarioId){
            return this.solicitudRepository.findAllSolicitudesPendientes(usuarioId);
        }
        
        @Transactional
        public void aceptarSolicitud(int id){
            Optional<Solicitud> optSolicitud = this.solicitudRepository.findById(id);
            if(optSolicitud.isEmpty()) throw new ResourceNotFoundException("Solicitud no encontrada");
            Solicitud solicitud = optSolicitud.get();
            solicitud.setTipoEstado(TipoEstadoSolicitud.ACEPTADA);
            save(solicitud);
        }

        @Transactional
        public void rechazarSolicitud(int id){
            Optional<Solicitud> optSolicitud = this.solicitudRepository.findById(id);
            if(optSolicitud.isEmpty()) throw new ResourceNotFoundException("Solicitud no encontrada");
            Solicitud solicitud = optSolicitud.get();
            solicitud.setTipoEstado(TipoEstadoSolicitud.RECHAZADA);
            save(solicitud);
        }

        @Transactional
        public void agregarUsuario(int usuarioSolicitudId, int usuarioInvitadoId ){
            Solicitud solicitud = new Solicitud();
            solicitud.setTipoEstado(TipoEstadoSolicitud.ENVIADA);
            Usuario usuarioSolicitud = this.usuarioService.findUsuarioById(usuarioSolicitudId);
            Usuario usuarioInvitado = this.usuarioService.findUsuarioById(usuarioInvitadoId);
            if(usuarioSolicitud== null || usuarioInvitado==null) throw new ResourceNotFoundException("El usuario no existe");
            solicitud.setUsuarioSolicitud(usuarioSolicitud);
            solicitud.setUsuarioInvitado(usuarioInvitado);
            save(solicitud);
        }

        @Transactional(readOnly = true)
        public Boolean sePuedeEspectar(Integer usuarioId, Map<String,Integer> partidaAEspectar){
            Set<Usuario> amigos = this.findAllAmigos(usuarioId).keySet();
            Boolean sePuedeEspectar = true;
            if(partidaAEspectar.get("parchis")!=null){
                Integer idPartidaParchis = partidaAEspectar.get("parchis");
                PartidaParchis partidaParchis =this.partidaService.findPartidaParchisById(idPartidaParchis);
                Collection<Jugador> jugadores = partidaParchis.getJugadores();
                for(Jugador jugador : jugadores){
                    if(!(amigos.contains(jugador.getUsuario()))){
                        sePuedeEspectar = false;
                    }
                }    
            }else{
                Integer idPartidaOca = partidaAEspectar.get("oca");
                PartidaOca partidaOca = this.partidaService.findPartidaOcaById(idPartidaOca);
                Collection<Jugador> jugadores = partidaOca.getJugadores();
                for(Jugador jugador : jugadores){
                    if(!(amigos.contains(jugador.getUsuario()))){
                        sePuedeEspectar = false;
                    }
                }    
            }
            return sePuedeEspectar;
        }

        @Transactional(readOnly = true)
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

        @Transactional(readOnly = true)
        public Boolean sonAmigos(Integer amigoId, Integer usuarioId){
            Collection<Solicitud> solicitudes =  this.solicitudRepository.findAllAmigos(usuarioId);
            Boolean sonAmigos = false;
            for(Solicitud solicitud : solicitudes){
                if(solicitud.getUsuarioInvitado().getId()==amigoId || solicitud.getUsuarioSolicitud().getId()==amigoId){
                    sonAmigos = true;
                }
            }
            return sonAmigos;
        }
}
