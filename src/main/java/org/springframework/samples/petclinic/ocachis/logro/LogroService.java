package org.springframework.samples.petclinic.ocachis.logro;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogroService {
    
    private LogroRepository logroRepository;
    
    @Autowired
    public LogroService(LogroRepository logroRepository){
        this.logroRepository = logroRepository;
    }
    
    public Collection<Logro> findAllLogros(){
        return this.logroRepository.findAll();
    }
    public void saveLogro(Logro l){
         this.logroRepository.save(l);
    }
    public Logro findById(int id){
        return this.logroRepository.findById(id);
    }

}