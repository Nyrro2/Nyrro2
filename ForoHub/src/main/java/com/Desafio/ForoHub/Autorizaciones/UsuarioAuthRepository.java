package com.Desafio.ForoHub.Autorizaciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioAuthRepository extends JpaRepository<AuthUsuario, Long> {

    Optional<UsuarioAuthRepository>findByEmail(String email);

    Collection<? extends GrantedAuthority> getAuthorities();
}
