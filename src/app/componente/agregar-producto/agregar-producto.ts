import { Component, inject } from '@angular/core';
import { ProductoService } from '../../servicios/producto-service';
import { Producto } from '../producto-component/productoModel';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-agregar-producto',
  imports: [FormsModule],
  templateUrl: './agregar-producto.html',
  styleUrl: './agregar-producto.css',
})
export class AgregarProducto {

  producto:Producto ={
    codigo: 0,
    descripcion: '',
    undMed:'',
    precio: 0,

  }
  productos!:Producto[]

  

  private productoServicio = inject(ProductoService);

  //  Se comento Para luego poder añadir Alerta de cuando se agrege algo.
  // private obtenerProductos():void{
  //   this.productoServicio.obtenerProducto().subscribe(
  //     {
  //       next: (datos)=>{
  //         this.productos = datos
  //       },
  //       error:(error) =>{
  //         console.error("Error al obtener los productos" , error)
  //       }
  //     }
  //     )
      
  // }


  guardarProducto(productoForm: NgForm){
    const {value, valid} = productoForm
    if(valid){
      this.productoServicio.agregarProducto(value);
      productoForm.resetForm();
  }
  }

  
  agregarProducto() {
    this.productoServicio.agregarProducto(this.producto).subscribe({
      next: (datos)=>{
        // this.obtenerProductos();
        this.limpiarCampo();
        let resultado = console.log('El producto agregado fue: ' + datos)
        

      }, 
      error:(error:any)=>{console.log(error)}
      

    });
    

  }

  limpiarCampo(){
    this.producto.codigo = 0;
    this.producto.descripcion = '';
    this.producto.precio = 0;
    this.producto.undMed = ''
  }

  

}
