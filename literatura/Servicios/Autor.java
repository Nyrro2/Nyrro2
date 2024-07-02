package com.aluracursos.literatura.Servicios;

import com.aluracursos.literatura.Libro;
import jakarta.persistence.*;
import com.aluracursos.literatura.Servicios.Datos;
import com.aluracursos.literatura.Servicios.DatosAutor;
import com.aluracursos.literatura.Servicios.DatosLibro;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String nombre;

    private String nacimiento;
    private String muerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Libro> libro = new HashSet<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.nacimiento();
        this.muerte = datosAutor.muerte();

    }



    @Override
    public String toString() {
        return "-----------------------------------------"+ "\n" +
                "Autor: " + nombre + "\n" +
                "Año de Nacimiento: " + nacimiento + "\n" +
                "Año de Muerte: " + muerte +"\n" +
                "Libros: " + (libro != null ? libro.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(",")): "N/A ")+ "\n" +
                "----------------------------------";

    }



    public Long getId() {
        return Id;
    }


    public void setId(Long id) {
        Id = id;
    }


    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getMuerte() {
        return muerte;
    }

    public void setMuerte(String muerte) {
        this.muerte = muerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Libro> getLibro() {
        return libro;
    }

    public void setLibro(Set<Libro> libro) {
        this.libro = libro;
         for (Libro libros : libro){
            libros.setAutor(this);
        }
    }

}
