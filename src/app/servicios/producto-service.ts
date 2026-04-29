import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../cliente';
import { AgregarCliente } from '../componente/agregar-cliente/agregar-cliente';
import { Producto } from '../componente/producto-component/productoModel';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {

  private urlBase:string='http://localhost:8080/pedidos/';
  private urlPedido:string ='http://localhost:8080/pedidos/';
  private urlCliente:string='http://localhost:8080/pedidos/agregar-cliente';
  private urlProducto:string='http://localhost:8080/pedidos/agregar-producto';


  productos!: Producto[]
  
  private HTTP = inject(HttpClient)

 

  // Eliminamos elemenos dentros del array pedidos
  eliminar(producto: Producto){
    // con el metodo indexof devolvemos el indece del arrglo
    const indice:number =this.productos.indexOf(producto);
    //metodo splice permite elimiar elementos de un arreglo, se paso como parametro la variable y el #1 para eliminar un que coincida con el indice
    this.productos.splice(indice,1)
  }


  // Enviar datos hacia en backend, con el metodo post
  agregarCliente(cliente: Cliente):Observable<Object>{
    return this.HTTP.post(this.urlCliente, cliente);
  }

  agregarProducto(producto: Producto):Observable<object>{
    return this.HTTP.post(this.urlProducto, producto)
  }

  guardar(){
    this.productos.push()

  }

  

  // Metodo GET para optener los clientes desde la base de datos
  obtenerCliente():Observable<Cliente[]>{
    return this.HTTP.get<Cliente[]>(this.urlCliente);

  }


  // Aqui recibimos los datos de que lo que se guardo en la base de datos. Y lo mostramos en el front-end en forma de lista
  // obtenerProducto():Observable<Producto[]>{
  //   return this.HTTP.get<Producto[]>(this.urlProducto);

  // }


 

  obtenerProductoPorCodigo(codigo:number):Observable<Producto>{
    return this.HTTP.get<Producto>(`http://localhost:8080/pedidos/hacer-pedido/${codigo}`)
    // return this.clienteHttp.get<Producto>(`${this.urlBase}/${codigo}`);
  }

 
  
}
