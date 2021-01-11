package com.had.apirest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UsuarioController {
    @GetMapping(path = "/usuarios/{codigo}")
    public ResponseEntity consultar(@PathVariable("codigo") String codigo){
        return null;
    }
}
