package com.Desafio.ForoHub.Seguridad;


import com.Desafio.ForoHub.Autorizaciones.UsuarioAuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltradoSecurity extends OncePerRequestFilter {

    @Autowired
    private tokenService tokenService;

    @Autowired
    UsuarioAuthRepository usuarioAuthRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");//


        if(authHeader != null){

            System.out.println("Validamos que el token no es null");
            var token = authHeader.replace("Bearer", "");
            var subject = tokenService.getSubject(token);
            System.out.println(tokenService.getSubject(token));

            if (subject != null){
                // token valido
                var usuario = usuarioAuthRepository.findByEmail(subject).orElse(null);

                if (usuario != null){
                var authe = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authe);

            } else {
                    System.out.println("Email no registrado" + subject);
                }

        }
        filterChain.doFilter(request,response);


    }
}}
