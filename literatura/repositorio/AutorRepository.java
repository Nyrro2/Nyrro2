package com.aluracursos.literatura.repositorio;
import com.aluracursos.literatura.Servicios.Datos;
import com.aluracursos.literatura.Servicios.DatosAutor;
import com.aluracursos.literatura.Servicios.DatosLibro;
import com.aluracursos.literatura.Servicios.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Autor findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :año AND a.muerte >= :año")
    List<Autor> añosAutor(int año);


}

