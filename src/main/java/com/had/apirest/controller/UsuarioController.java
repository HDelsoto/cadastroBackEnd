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
import org.springframework.web.bind.annotation.RequestMethod;
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

    @GetMapping(path = "/usuarios/nomex={nome}")
    public List<UsuarioModel> listaByFilter(@PathVariable(value = "nome") String nome) {
        if (nome.length()!=0)
        {
            StringBuilder stringBuilder = new StringBuilder(nome);
            stringBuilder.insert(nome.length() - 1, '%');
            stringBuilder.insert(nome.length() + 1, '%');
            return repository.findByNomeLike(stringBuilder.toString());
        }
        return repository.findByNomeLike("%");

    }
    @RequestMapping(path = "/usuarios/{nome}/{situacao}/{perfil}", method = RequestMethod.GET)
    public List<UsuarioModel> listaByFilter2(@PathVariable(value = "nome") String nome,
                                             @PathVariable(value = "situacao") String situacao,
                                             @PathVariable(value = "perfil") String perfil) {
        StringBuilder stringBuilder1 = new StringBuilder("%");
        StringBuilder stringBuilder2 = new StringBuilder("%");
        StringBuilder stringBuilder3 = new StringBuilder("%");
        if (nome.length()!=0 && !nome.equals("xyzabc"))
        {
            stringBuilder1.insert(0, "%");
            stringBuilder1.insert(1, nome);                                    
        }
        if (situacao.length()!=0 && !situacao.equals("xyzabc"))
        {
            stringBuilder2.insert(0, '%');
            stringBuilder2.insert(1, situacao);                                
        }
        if (perfil.length()!=0 && !perfil.equals("xyzabc"))
        {
            stringBuilder3.insert(0, '%');
            stringBuilder3.insert(1, perfil);                      
        }    
        return repository.findByFilter(stringBuilder1.toString(),stringBuilder2.toString(),stringBuilder3.toString());             
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
