import { Component, ElementRef, EventEmitter, inject, Input, } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Pedido } from './pedido.model';
import {  ProductoService } from '../../servicios/producto-service';
import { ActivatedRoute } from '@angular/router';
import { debounceTime, distinctUntilChanged, filter, Observable, switchMap } from 'rxjs';
import { Cliente } from '../../cliente';
import { CommonModule } from '@angular/common';
import { Producto } from '../producto-component/productoModel';



@Component({
  selector: 'app-pedidoComponent',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './pedidoComponent.html',
  styleUrl: './pedido.css',
})
export class PedidoComponent{
  
  productos: Producto[]=[]
  private productoServicio= inject(ProductoService);
  private ruta= inject(ActivatedRoute); //Para recuperar los recibido en el http

  clienteBuscado = new FormControl('',{nonNullable:true}); //buscamos cliente de forma activa

  @Input() getSumatotal!:number
  
  
  eliminarPedido(producto:Producto) {
    this.productoServicio.eliminar(producto)
  }

  
 
  buscarnombreCliente(){
    this.clienteBuscado.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      filter(teclado =>teclado!==null &&teclado!==""),
      switchMap((teclado:string)=>this.productoServicio.obtenerCliente())


    )
  }


  


}

