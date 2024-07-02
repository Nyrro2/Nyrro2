package com.aluracursos.literatura;

import com.aluracursos.literatura.Servicios.Autor;
import com.aluracursos.literatura.Servicios.Datos;
import com.aluracursos.literatura.Servicios.DatosAutor;
import com.aluracursos.literatura.Servicios.DatosLibro;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name= "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;
    @ElementCollection(fetch =  FetchType.EAGER)
    private  List<String>idioma;

    private Integer contadorDescarga;
    private String nombre;


    @ManyToOne
    private Autor autor;


    public Libro(){}

    public Libro (DatosLibro datosLibro, Autor autor){
        this.titulo = datosLibro.titulo();
        this.contadorDescarga = datosLibro.contadorDescarga();
        this.nombre = datosLibro.autor().stream().map(DatosAutor::nombre).collect(Collectors.toList()).toString();
        this.idioma = datosLibro.idioma();
        this.autor = autor;

    }

    public void setContadorDescarga(Integer contadorDescarga) {
        this.contadorDescarga = contadorDescarga;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }


    public Libro(long id) {
        Id = id;
    }

    public Integer getContadorDescarga() {
        return contadorDescarga;
    }


    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
        if (autor != null && !autor.getLibro().contains(this)){
            autor.getLibro().add(this);
        }
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    @Override
    public String toString() {
        return "++++++++++++++++++++++++++++++++++++++++++++++" + "\n"+
                "Titulo = " + titulo + "\n"+
                "nombre = " + nombre + "\n"+
                "Idioma = " + idioma + "\n"+
                "Contador de Descarga = " + contadorDescarga + "\n"+
                "+++++++++++++++++++++++++++++++++++++++++++++++";

    }

}
