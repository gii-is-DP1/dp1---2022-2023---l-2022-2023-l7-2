package org.springframework.samples.petclinic.ocachis.logro;

import java.lang.reflect.Field;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void saveLogro(Logro l){
        
         this.logroRepository.save(l);
    }

    @Transactional(readOnly=true)
    public Logro findById(int id){
        return this.logroRepository.findById(id);
    }
    
    public Boolean unaMetaDefinida(Logro l) throws IllegalAccessException{
        int cont = 0;
        Field[] campos = l.getEstadisticasACumplir().getClass().getFields();
       


        for(Field c : campos){
            if((Integer) c.get(l.getEstadisticasACumplir()) != null){
                cont++;
            }
            /*else{
                c.set(l, 0);
            }*/
            
        }
        if(cont != 1){
            return false;
        }
        return true;
        /*
        if(l.getEstadisticasACumplir().getOcaDuracionMaxima() != null){
            cont++;
        }else if(l.getEstadisticasACumplir().getOcaDuracionMedia() != null){
            cont++;
        }else if(l.getEstadisticasACumplir().getOcaDuracionMinima() != null){
            cont++;
        }else if(l.getEstadisticasACumplir().getOcaDuracionTotal() != null){
            cont++;
        }

        
        else if(l.getEstadisticasACumplir().getParchisPartidasGanadas() != null){
            cont++;
        }
        else if (l.getEstadisticasACumplir().getParchisPartidasJugadas()!=null){
            cont++;
        }
        else if(l.getEstadisticasACumplir().getParchisDuracionTotal()!=null){
            cont++;
        }
        else if(l.getEstadisticasACumplir().getParchisDuracionMinima()!=null){
            cont++;
        }
        else if(l.getEstadisticasACumplir().getParchisDuracionMaxima()!=null){
            cont++;
        }
        else if(l.getEstadisticasACumplir().getParchisDuracionMedia()!=null)
        */
    }



}