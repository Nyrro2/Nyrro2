package com.Desafio.ForoHub.Autorizaciones;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

@Table(name= "authUsuario")
@Entity(name = "AuthUsuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AuthUsuario implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String psw; //Contraseña


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() throws UsernameNotFoundException {
        return List.of(new SimpleGrantedAuthority("Role_User"));
    }

    @Override
    public String getPassword() {
        return psw;
    }

    @Override
    public String getUsername() {
        return email;
    }


}
