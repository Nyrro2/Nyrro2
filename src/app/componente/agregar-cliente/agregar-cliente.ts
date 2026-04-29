import { Component, inject } from '@angular/core';
import { Cliente } from '../../cliente';
import { ProductoService } from '../../servicios/producto-service';
import { Observable } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-cliente',
  imports: [FormsModule],
  templateUrl: './agregar-cliente.html',
  styleUrl: './agregar-cliente.css',
})
export class AgregarCliente {
  

  clientes!:Cliente[]; 

  cliente: Cliente = new Cliente();
  nombreCliente:string | null= null;
  telefonoCliente:string|null= null;

  private productoServicio = inject(ProductoService);
  private enrutador = inject(Router);

  ngOnInit(){
    // Cargar los clientes
    this.obtenerclientes();

  }

  onSubmit(){
  //  this.obtenerclientes();
  }

  private obtenerclientes():void{
    this.productoServicio.obtenerCliente().subscribe(
      {
        next: (datos)=>{
          this.clientes = datos;
        },
        error:(error)=>{
          console.error("Error al obtener los cliente", error)
        }
      }
    )

  }

  agregarCliente() {
    this.productoServicio.agregarCliente(this.cliente).subscribe({
      next: (datos)=>{
        // this.obtenerclientes();
        this.limpiarcampo();
      }, 
      error:(error:any)=>{console.log(error)}

    })
    

  }

  limpiarcampo(){
    this.nombreCliente = '';
  }

}
