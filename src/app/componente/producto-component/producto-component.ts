import { Component, inject } from '@angular/core';
import { Producto } from './productoModel';
import {ProductoService } from '../../servicios/producto-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-producto-component',
  imports: [CommonModule],
  templateUrl: './producto-component.html',
  styleUrl: './producto-component.css',
})
export class ProductoComponent {

  productos: Producto[]=[];
  
  constructor(private productoServicio: ProductoService){
    this.productos = this.productoServicio.productos
  }
  eliminarPedido(producto:Producto) {
    this.productoServicio.eliminar(producto)
  }
 
}
