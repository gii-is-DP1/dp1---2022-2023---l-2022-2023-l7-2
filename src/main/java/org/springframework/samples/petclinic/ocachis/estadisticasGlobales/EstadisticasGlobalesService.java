package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.estadisticas.Estadisticas;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.samples.petclinic.ocachis.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadisticasGlobalesService {

    private EstadisticasGlobalesRepository repository;
    private UsuarioService usuarioService;

    @Autowired
    public EstadisticasGlobalesService(EstadisticasGlobalesRepository repository, UsuarioService usuarioService){
        this.repository = repository;
        this.usuarioService = usuarioService;
    }


    @Transactional
    public EstadisticasGlobales getEstadisticasGlobales(){
        EstadisticasGlobales eg = repository.get();
        
        if(eg==null){
            eg = new EstadisticasGlobales();
            eg.setId(1);
            Estadisticas e = new Estadisticas();
            e.setParchisPartidasJugadas(repository.getSumaParchisJugadas());
            e.setParchisDuracionMaxima(repository.getParchisDuracionMaxima());
            e.setParchisDuracionMedia(repository.getParchisDuracionMedia());
            e.setParchisDuracionMinima(repository.getParchisDuracionMinima());
            e.setParchisDuracionTotal(repository.getSumaParchisDuracionTotal());
            e.setParchisFichasComidas(repository.getSumaParchisFichasComidas());

            e.setOcaPartidasJugadas(repository.getSumaOcaJugadas());
            e.setOcaDuracionMaxima(repository.getOcaDuracionMaxima());
            e.setOcaDuracionMedia(repository.getOcaDuracionMedia());
            e.setOcaDuracionMinima(repository.getOcaDuracionMinima());
            e.setOcaDuracionTotal(repository.getSumaOcaDuracionTotal());
            e.setOcaVecesCaidoEnMuerte(repository.getSumaOcaVecesCaidoEnMuerte());

            eg.setEstadisticasGlobales(e);
            eg.setOcaRankingJugadores(usuarioService.Top5OcaPartidasGanadas());
            eg.setOcaVecesCaidoEnMuerte(usuarioService.Top5OcaVecesCaidoEnMuerte());
            eg.setParchisRankingJugadores(usuarioService.Top5ParchisPartidasGanadas());
            eg.setParchisFichasComidas(usuarioService.Top5ParchisFichasComidas());
            eg = this.save(eg); 
        }
        return eg;
    }
    
    @Transactional
    public EstadisticasGlobales save(EstadisticasGlobales est) {
        return this.repository.save(est);
    }

    @Transactional
    public void updateEstadisticasGlobalesPartidaParchis(PartidaParchis partida){
        EstadisticasGlobales estadisticasGlobales = this.getEstadisticasGlobales();

        Integer totalFichasComidas = partida.getJugadores().stream().mapToInt(j->j.getFichasComidas()).sum();
        estadisticasGlobales.getEstadisticasGlobales().updateEstadisticasParchis(partida.getDuracion(), false, totalFichasComidas);

        List<Usuario> rankingPartidasGanadas= usuarioService.Top5ParchisPartidasGanadas();
        List<Usuario> rankingFichasComidas = usuarioService.Top5ParchisFichasComidas();
        
        estadisticasGlobales.setParchisFichasComidas(rankingFichasComidas);
        estadisticasGlobales.setParchisRankingJugadores(rankingPartidasGanadas);
        this.repository.save(estadisticasGlobales);
    }

    @Transactional
    public void updateEstadisticasGlobalesPartidaOca(PartidaOca partida){
        EstadisticasGlobales estadisticasGlobales = this.getEstadisticasGlobales();
        
        Integer totalVecesCaidoEnMuerte = partida.getJugadores().stream().mapToInt(j->j.getVecesCaidoEnMuerte()).sum();
      
        Integer duracion = partida.getDuracion();
        estadisticasGlobales.getEstadisticasGlobales().updateEstadisticasOca(duracion, false, totalVecesCaidoEnMuerte);
        
        List<Usuario> rankingCaidoEnMuerte = usuarioService.Top5OcaVecesCaidoEnMuerte();
    
        List<Usuario> rankingPartidasGanadas = usuarioService.Top5OcaPartidasGanadas();
        
        estadisticasGlobales.setOcaRankingJugadores(rankingPartidasGanadas);
        estadisticasGlobales.setOcaVecesCaidoEnMuerte(rankingCaidoEnMuerte);
        this.repository.save(estadisticasGlobales);
    }
}
