package com.Desafio.ForoHub.Tema;

import java.time.LocalDate;

public record DatosReplyTema(
        Long id,
        String titulo,
        String publicacion,
        String username,
        String curso,
        LocalDate fechaPublicacion,
        Boolean status


) {
}
