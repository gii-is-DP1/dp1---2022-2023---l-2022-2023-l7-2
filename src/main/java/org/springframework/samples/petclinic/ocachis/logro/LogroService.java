package org.springframework.samples.petclinic.ocachis.logro;

import java.lang.reflect.Field;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MetaNegativaException;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MultiplesMetasDefinidasException;
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
        return this.logroRepository.findById(id);
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
        }/*else{
            result = true;
            return result;
        }*/
    }



}