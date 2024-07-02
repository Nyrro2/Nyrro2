package com.aluracursos.literatura.Principal;

import com.aluracursos.literatura.Libro;
import com.aluracursos.literatura.Servicios.*;
import com.aluracursos.literatura.repositorio.AutorRepository;
import com.aluracursos.literatura.repositorio.Librorepository;

import java.util.*;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConexionApi conexionApi = new ConexionApi();
    private static final String URL = "http://gutendex.com/books/?search=";

    private TratamientoDatos tratamiento = new TratamientoDatos();
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private List<Libro> libro;
    private List<Autor> autor = new ArrayList<>();
    private Datos datos;
    private AutorRepository autorRepositorio;
    private Librorepository libroRepositorio;



    public Principal(Librorepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void opcion() {
        var seleccion = -1;
        while (seleccion != 0) {
            var opciones = """
                    +++++++++++++++++++++++++++++++++++++++++++++++++++
                                        
                    Seleccione la opcion deseada:
                    1- Buscar libro por titulo
                    2- Listados todos los libros guardados
                    3- Listados autor guardado
                    4- Listado Libro por idiomas  
                    5- Buscar Autor en determinado año           
                    ++++++++++++++++++++++++++++++++++++++++++++++++++++
                    """;

            System.out.println(opciones);
            seleccion = teclado.nextInt();
            teclado.nextLine();

            switch (seleccion) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    listadoLibro();
                    break;
                case 3:
                    autoresGuardado();
                    break;
                case 4:
                    libroPorIdioma();
                    break;
                case 5:
                    autorAño();
                case 0:
                    System.out.println("""
                            ***********************************************
                            
                            Cerrando Aplicacion
                           
                            ***********************************************
                            """);
                    break;
                default:
                    System.out.println("Opcion invalida");

            }

        }
    }

    private void autorAño(){
        System.out.println("Favor ingresar el año del autor que deseas buscar");
        entradaNumero();
        var años = teclado.nextInt();
        autor: autorRepositorio.añosAutor(años);
        if (autor == null){
            System.out.println("No hay registro de autor para este año");
        }else {
            autor.forEach(System.out::println);
        }

    }

    private void entradaNumero() {
        while (!teclado.hasNextInt()){
            System.out.println("Aqui solo se permiten numero");
            teclado.next();
        }

    }

    private void libroPorIdioma(){
        System.out.println(""" 
                +++++++++++++++++++++++++++++++++++++++
                
                Favor seleccione un numero correspondiente a los que desea:
                1 - Español
                2 - Ingles
                3 - Italiano
                4 - Portugues
                ++++++++++++++++++++++++++++++++++++++
                """);
        entradaNumero();
        var eleccion = teclado.nextInt();
        switch (eleccion){
            case 1:
                libroIdioma("es");
                break;
            case 2:
                libroIdioma("en");
                break;
            case 3:
                libroIdioma("it");
                break;
            case 4:
                libroIdioma("pt");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    private void libroIdioma(String idioma) {
        try{
            libro = libroRepositorio.findByIdioma(idioma);
            if (libro == null){
                System.out.println("No hay Libros registrados en ese idioma");
            } else {
                libro.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Error en la busqueda");
        }
    }

    private void autoresGuardado() {
        if (autor == null){
            System.out.println("No hay autor guardado");
        }else {
            autor = autorRepositorio.findAll();
            autor.forEach(System.out::println);
        }
    }

    private void listadoLibro() {
        if (libro == null ){
            System.out.println(" No hay libro guardado");
        }else {
            libro = libroRepositorio.findAll();
            libro.forEach(System.out::println);
        }
    }


    private void buscarPorTitulo() {
        System.out.println(" Que libro quieres buscar? ");
        var busqueda = teclado.nextLine();
        var json = conexionApi.buscarDatos(URL + busqueda.replace(" ", "+"));
        Datos delivery = tratamiento.obtenerDatos(json, Datos.class);


        Optional<DatosLibro> respuesta = delivery.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(busqueda.toUpperCase()))
                .findFirst();

        if (respuesta.isPresent()) {
            DatosLibro datosLibro = respuesta.get();
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor autor = autorRepositorio.findByNombre(datosAutor.nombre());

            if (autor == null) {
                autor = new Autor(datosAutor);
                autorRepositorio.save(autor);
            }

            Libro libro = libroRepositorio.findByTitulo(datosLibro.titulo());

            if (libro == null) {
                libro = new Libro(datosLibro, autor);
                libroRepositorio.save(libro);
                System.out.println(libro);

            } else {
                System.out.println("Libro ya guardado");
            }
        } else {
            System.out.println("Libro no esta en pagina Gutendex");
        }
    }


}