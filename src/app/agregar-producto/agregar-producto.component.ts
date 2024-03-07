import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrls: ['./agregar-producto.component.css']
})
export class AgregarProductoComponent {
  producto: Producto = new Producto();

  constructor(private productoServicio: ProductoService,
    private enrutador: Router){}

  
    onSubmit(){
      this.guardarProducto();
    }
    //si guarda el producto se redirecciona a la pagina de la lista de productos
    guardarProducto(){
      this.productoServicio.agregarProducto(this.producto).subscribe(
        {
          next: (datos) => {
            this.irListaProductos();
          }, //si bota error saldra en la consola
          error: (error: any) => {console.log(error)}
        }
      );
    }
  
    irListaProductos(){
      this.enrutador.navigate(['/productos']);
    }
}
