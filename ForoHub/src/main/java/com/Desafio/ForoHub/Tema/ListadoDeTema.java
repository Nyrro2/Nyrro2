package com.Desafio.ForoHub.Tema;

public record ListadoDeTema(
        Long id,
        String username,
        String email
) {
    public ListadoDeTema(Tema usuario){
        this(usuario.getId(), usuario.getUsername(), usuario.getEmail() );

    }

}
