package com.Desafio.ForoHub.Seguridad;

import com.Desafio.ForoHub.Autorizaciones.UsuarioAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioAuthRepository usuarioAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) usuarioAuthRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no registrada por email:" + username));
    }
}
