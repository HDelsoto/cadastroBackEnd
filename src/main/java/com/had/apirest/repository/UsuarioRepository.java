package com.had.apirest.repository;
import org.springframework.data.repository.CrudRepository;
import com.had.apirest.model.UsuarioModel;

public interface UsuarioRepository extends CrudRepository <UsuarioModel, Integer> {
    
}
