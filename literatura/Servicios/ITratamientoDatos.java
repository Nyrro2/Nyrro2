package com.aluracursos.literatura.Servicios;

public interface ITratamientoDatos {
//    Este arreglo se hace para recibir datos genericos
    <T> T obtenerDatos(String json, Class<T> clase);
}
