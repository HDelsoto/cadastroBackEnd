package com.had.apirest.controller;
import java.util.List;
import com.had.apirest.model.UsuarioModel;
import com.had.apirest.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    @GetMapping(path = "/usuarios/{codigo}")
    public ResponseEntity consultar(@PathVariable("codigo") Integer codigo){
        return repository.findById(codigo)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/")
    public List<UsuarioModel> consultarTodos(){
        return repository.findAll();
        
    }    
    

    @PostMapping(path = "/usuario")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);
    }

    @DeleteMapping(path = "/usuario")
    public void delete(@RequestBody UsuarioModel usuario) {
        repository.delete(usuario);
    }

    @PutMapping(path = "/usuario")
    public UsuarioModel update(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);
    }

}
