package com.Desafio.ForoHub.Tema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosRegistroTema(
        String titulo,
        @NotBlank // anotacion para que no llegue nulo


        @NotBlank
        String curso,

        @NotBlank
        String username,


        @NotBlank
        String usuario,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String publicacion,

        @NotBlank
        LocalDate fechaPublicacion,

        @NotBlank
        LocalDate fechaDeCreacion,


        @NotBlank
        Boolean activo



        ) {
}