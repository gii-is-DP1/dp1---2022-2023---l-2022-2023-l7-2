package org.springframework.samples.petclinic.ocachis.logro;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.exceptions.ResourceNotFoundException;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MetaNegativaException;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MultiplesMetasDefinidasException;
import org.springframework.samples.petclinic.ocachis.partida.PartidaOca;
import org.springframework.samples.petclinic.ocachis.partida.PartidaParchis;
import org.springframework.samples.petclinic.ocachis.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogroService {
    
    private LogroRepository logroRepository;
    
    @Autowired
    public LogroService(LogroRepository logroRepository){
        this.logroRepository = logroRepository;
    }

    @Transactional(readOnly=true)
    public Collection<Logro> findAllLogros(){
        return this.logroRepository.findAll();
    }

    @Transactional(rollbackFor = {MultiplesMetasDefinidasException.class, MetaNegativaException.class})
    public void saveLogro(Logro l)throws IllegalAccessException, MultiplesMetasDefinidasException, MetaNegativaException{
        validarLogro(l);
        this.logroRepository.save(l);
    }

    @Transactional(readOnly=true)
    public Logro findById(int id){
        Optional<Logro> optLogro = this.logroRepository.findById(id);
        if(optLogro.isEmpty()) throw new ResourceNotFoundException("Logro no encontrado");
        return optLogro.get();
    }
    
    public void validarLogro(Logro l) throws IllegalAccessException, MultiplesMetasDefinidasException, MetaNegativaException{
        //Boolean result = false;
        int cont = 0;
        Field[] campos = l.getEstadisticasACumplir().getClass().getDeclaredFields();
        for(Field c : campos){
            if((Integer) c.get(l.getEstadisticasACumplir()) == null){
                c.set(l.getEstadisticasACumplir(), 0);
            }else if((Integer) c.get(l.getEstadisticasACumplir()) != 0){
                if((Integer) c.get(l.getEstadisticasACumplir()) < 0){
                    throw new MetaNegativaException();
                }else{
                    cont++;
                } 
            }
        }
        if(cont != 1){
            throw new MultiplesMetasDefinidasException();
        }
    }

    public void actualizarLogrosOca(PartidaOca partida) throws IllegalAccessException{
        Collection<Jugador> jugadores = partida.getJugadores();
        for(Jugador j: jugadores){
            Usuario usuario = j.getUsuario();
            Collection<Logro> logrosCompletados = logroRepository.findAllLogrosForUsuario(usuario.getId());
            Collection<Logro> logrosACompletar = logroRepository.findLogrosNoCompletados(logrosCompletados);
            for(Logro l: logrosACompletar){
                if(usuario.cumpleLogro(l)){
                    usuario.addLogro(l);
                }
            }
        }        
    }
    
    

    @Transactional
    public void actualizarLogrosParchis(PartidaParchis partida) throws IllegalAccessException{
        Collection<Jugador> jugadores = partida.getJugadores();
        for(Jugador j: jugadores){
            Usuario usuario = j.getUsuario();
            Collection<Logro> logrosCompletados = logroRepository.findAllLogrosForUsuario(usuario.getId());
            Collection<Logro> logrosACompletar = logroRepository.findLogrosNoCompletados(logrosCompletados);
            for(Logro l: logrosACompletar){
                if(usuario.cumpleLogro(l)){
                    usuario.addLogro(l);
                }
            }
        }        
    }
}