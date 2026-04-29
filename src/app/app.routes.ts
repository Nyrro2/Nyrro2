import { Routes } from '@angular/router';
import { Tablero } from './componente/tablero/tablero';
import { EditarProducto } from './componente/editar-producto/editar-producto';
import { PedidoComponent} from './componente/pedidoComponent/pedidoComponent';
import { AgregarCliente } from './componente/agregar-cliente/agregar-cliente';
import { AgregarProducto } from './componente/agregar-producto/agregar-producto';
import { FormularioReactivo } from './componente/formulario-reactivo/formulario-reactivo';

export const routes: Routes = [
    {path:'', component:PedidoComponent},
    {path:'pedidos/hacer-pedido', component:FormularioReactivo},
    {path:'pedidos/editar-producto', component:EditarProducto},
    {path:'pedidos/agregar-cliente', component:AgregarCliente},
    {path:'pedidos/agregar-producto', component:AgregarProducto}

];
