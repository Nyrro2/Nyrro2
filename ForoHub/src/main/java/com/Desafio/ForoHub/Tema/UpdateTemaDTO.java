package com.Desafio.ForoHub.Tema;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateTemaDTO(

        @NotNull
        Long id,
        String titulo,
        String username,
        String curso,
        String publicacion,
        LocalDate fechaCreacion

) {
}
