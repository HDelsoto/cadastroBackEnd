package com.had.apirest.controller;

import java.util.List;
import java.util.Optional;

import com.had.apirest.model.UsuarioModel;
import com.had.apirest.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    
    @GetMapping(path = "/usuarios/{codigo}")
    public Optional<UsuarioModel> listaProdutoUnico(@PathVariable(value = "codigo") String id) {
        return repository.findById(Integer.parseInt(id));
    }

    
    @GetMapping(path = "/usuarios/cpf={codigo}")
    public List<UsuarioModel> listaCpf(@PathVariable(value = "codigo") String cpf) {
        return repository.findByCpfIs(cpf);

    }

    
    @GetMapping(path = "/usuarios")
    public List<UsuarioModel> consultarTodos() {
        return repository.findAll();
    }

    
    @PostMapping(path = "/usuarios")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);

    }

    
    @DeleteMapping("/usuarios/{id}")
    public void delete(@PathVariable("id") int id ) {
        repository.deleteById(id);
    }
    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioModel> update(@PathVariable("id") int id, @RequestBody UsuarioModel usuario ) {
        Optional<UsuarioModel> usuarioData = repository.findById(id);
		if (usuarioData.isPresent()) {
			UsuarioModel _usuario = usuarioData.get();
             _usuario.setId(usuario.getId());
             _usuario.setCpf(usuario.getCpf());
             _usuario.setEmail(usuario.getEmail());
             _usuario.setFuncao(usuario.getFuncao());
             _usuario.setNome(usuario.getNome());
             _usuario.setPerfil(usuario.getPerfil());
             _usuario.setSituacao(usuario.getSituacao());
             _usuario.setTelefone(usuario.getTelefone());
             return new ResponseEntity<>(repository.save(_usuario), HttpStatus.OK);
		} else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}
        
    }

}
