import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Cabecero } from "./componente/cabecero/cabecero";
import { PiePagina } from "./componente/pie-pagina/pie-pagina";
import { FormularioReactivo } from "./componente/formulario-reactivo/formulario-reactivo";

import { Producto } from './componente/producto-component/productoModel';
import { ProductoService } from './servicios/producto-service';

@Component({
  selector: 'app-root',
  imports: [Cabecero, FormularioReactivo, RouterOutlet, PiePagina],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  productos:Producto[] =[]


  // Inyeccion de dependecia
  constructor(private productoServicio:ProductoService){
    // Asignacion de atributos. Para que productos[] acceda a los metodos que estan alojados en pedidosServices
    this.productos = productoServicio.productos;
  }

  // getProductoTotal(){
  //   let productoTotal:number = 0;
  //   this.productos.forEach(producto =>{
  //     productoTotal+=producto.precio
  //   });
  //   return productoTotal;
  // }






}
