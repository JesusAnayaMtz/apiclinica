package com.volt.api.controller;

import com.volt.api.infra.security.TokenService;
import com.volt.api.model.DatosAutenticacionUsuario;
import com.volt.api.model.DatosJWTToken;
import com.volt.api.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager; //esta inicia el proceso de autenticacion

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication autenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(autenticationToken);  //creamos un usuario auntenticado
        var JwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());  //aqui obtenemos el principal viene siendo el usuaruo que ya fue autenticado y se catea a un objeto del tipo usuario
        return ResponseEntity.ok(new DatosJWTToken(JwtToken));  //creamos un dto de token para retornarlo y no interactuar directo con la entidad
    }
}
