package com.Desafio.ForoHub.Controller;


import com.Desafio.ForoHub.Autorizaciones.AuthUsuario;
import com.Desafio.ForoHub.Autorizaciones.DatosAuthUsuario;
import com.Desafio.ForoHub.Seguridad.datosJWToken;
import com.Desafio.ForoHub.Seguridad.tokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private tokenService tokenService;


    @PostMapping
    public ResponseEntity autenticationUsurio(@RequestBody @Valid DatosAuthUsuario datosAuthUsuario){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                datosAuthUsuario.email(),
                datosAuthUsuario.psw());

        var usuarioAuth = authenticationManager.authenticate(authentication);
        var JWTtoken = tokenService.generarToken((AuthUsuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new datosJWToken(JWTtoken));

    }

}

