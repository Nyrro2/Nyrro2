package com.aluracursos.literatura.repositorio;

import com.aluracursos.literatura.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Librorepository extends JpaRepository<Libro,Long> {

    Libro findByTitulo(String titulo);

    List<Libro>findByIdioma(String idioma);



}
