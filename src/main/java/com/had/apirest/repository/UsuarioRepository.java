package com.had.apirest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.had.apirest.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository <UsuarioModel, Integer> {
    
}
