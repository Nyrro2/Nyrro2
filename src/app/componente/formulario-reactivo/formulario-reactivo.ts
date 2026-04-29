import { Component, inject, Input } from '@angular/core';
 '@angular/forms';
import { ProductoService } from '../../servicios/producto-service';
import { Cliente } from '../../cliente';
import { Producto } from '../producto-component/productoModel';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { debounceTime, distinctUntilChanged, filter, switchMap } from 'rxjs';
import { FormArray, FormBuilder, FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PedidoComponent } from "../pedidoComponent/pedidoComponent";


@Component({
  selector: 'app-formulario-reactivo',
  imports: [FormsModule, ReactiveFormsModule, CommonModule, RouterOutlet],
  templateUrl: './formulario-reactivo.html',
  styleUrl: './formulario-reactivo.css',
})
export class FormularioReactivo {
  codigo!: number
  descripcion!:string
  cantidad!:number;
  undMed!:string;
  precio!:number;
  total!:number;
  clienteBuscado = new FormControl('', { nonNullable: true }); //buscamos cliente de forma activa
  resultado: Producto[] = [];
  clientes: Cliente[] = [];

  producto:Producto = {
    codigo:0,
    descripcion: '',
    undMed:'',
    precio: 0,

  }  

  productos: Producto[]=[]
  

  @Input() productoTotal!:number

  codigoBuscado = new FormControl();
  filtrado = this.codigo;
  
  private productoServicio = inject(ProductoService);

  
  private ruta = inject(ActivatedRoute); //Para recuperar los recibido en el http


  // form!: FormGroup
  // constructor(private fb: FormBuilder){
  //   this.form = this.fb.group({
  //     cliente: ['', Validators.required],
  //     lineas: this.fb.array([

  //     ])
    
  //   })
  // }

  // get lineas():FormArray{
  //   return this.form.get('lineas')as FormArray;
  // }

  // crearLinea():FormGroup{
  //   return this.fb.group({
  //     codigo:['', [Validators.required, Validators.maxLength(10)]],
  //     descripcion:['', [Validators.required, Validators.maxLength(10)]],
  //     undmed:['', [Validators.required, Validators.maxLength(6)]],
  //     precio: ['', [Validators.required, Validators.maxLength(6)]],
  //     cantidad: ['', [Validators.required, Validators.maxLength(2)]],
  //     subTotal: ['',[Validators.required, Validators.maxLength(3)]]})
  //   }

  // agregarLinea():void{
    // this.lineas.push(this.crearLinea())}

  // guardar():void{
  //   if(this.form.invalid){
  //     this.form.markAllAsTouched();
  //     return
  //   }
  //   console.log('Payload:', this.form.value)
  // }
  
  nuevaFila: Producto={codigo:0, descripcion:'', undMed:'', precio:0, }

  listaProductos: Producto[]=[];

  verificarYAgregar(){
    if(this.nuevaFila.codigo && this.nuevaFila.descripcion&& this.nuevaFila.precio && this.cantidad >1){
      this.listaProductos.push({...this.nuevaFila})
      this.nuevaFila={codigo:0, descripcion:' ', undMed:' ', precio:0, }
    }

  }
  
  agregarValor(){
    
    if(this.cantidad >= 1) {
      this.productoServicio.guardar()
    } else {
      console.log('Favor introducir valores');
    }
  }

  ngOnInit() {
    // this.buscarPorId()
    this.codigo = this.ruta.snapshot.params['codigo'];
    this.codigoBuscado.valueChanges
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        filter((term) => term !== null && term !== ''),
        switchMap((term:number) => this.productoServicio.obtenerProductoPorCodigo(term)),
      )
      .subscribe({
        next: (resultado: Producto) => {
          this.producto = resultado;
        },
        error: (err: any) => console.error(err),
      });
  }

  private buscarPorId() {
    this.codigo = this.ruta.snapshot.params['codigo'];
    this.codigoBuscado.valueChanges
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        filter((term) => term !== null && term !== ''),
        switchMap((term: number) => this.productoServicio.obtenerProductoPorCodigo(term)),
      )
      .subscribe({
        next: (resultado: Producto) => {
          this.producto = resultado;
        },
        error: (err: any) => console.error(err),
      });
  }

  buscarnombreCliente(){
    this.clienteBuscado.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      filter((teclado) => teclado !== null && teclado !== ''),
      switchMap((teclado: string) => this.productoServicio.obtenerCliente()),
    );
  }

  private obtenerProducto(): void {
    this.productoServicio.obtenerProductoPorCodigo(this.codigo).subscribe({
      next: (datos) => {
        this.producto = datos;

        if(this.codigo != null){
          this.descripcion = this.producto.descripcion;
          this.precio = this.producto.precio;
        }
      },
      error: (error) => {
        console.error('Error al obtener los cliente', error);
      },
    });
  }

  subTotal() {
    if (this.cantidad > 0) {
      let total: number = this.cantidad * this.producto.precio;
      return total;
    }
    return this.total;
  }

  getProductoTotal(){
    let productoTotal:number = 0;
    this.productos.forEach(producto =>{
      productoTotal+= producto.precio
    });
    return productoTotal;
  }


}
