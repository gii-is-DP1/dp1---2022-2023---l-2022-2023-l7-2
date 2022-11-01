package org.springframework.samples.petclinic.ocachis.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UsuarioService{
    
    private UsuarioRepository usuarioRepository;
    
    @Autowired
	public UsuarioService(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}
    
    @Transactional(readOnly = true)
    public Collection<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }
    @Transactional
    public void deleteUsuarioById(int id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario findUsuarioById(int id){
        return this.usuarioRepository.findById(id);
    }
}