package com.Desafio.ForoHub.Seguridad;

import com.Desafio.ForoHub.Autorizaciones.AuthUsuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class tokenService {


    @Value("${api.security.secret}") // extraer valor desde una variable en aplicaction property
    private String apiSecret;


    public String generarToken(AuthUsuario authUsuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Foro hub")
                    .withSubject(authUsuario.getEmail()) // Para generar usuario dinamico
                    .withClaim("id", authUsuario.getId()) // Retornar el usuario
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }
    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Foro hub")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
            // Invalid signature/claims
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verificador invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));


    }
}
