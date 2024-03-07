package gm.inventarios.controlador;

import excepcion.RecursoNoEncontradoExcepcion;
import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //Back-end Sprin boot
//http://localhost:8080/inventario-app
    @RequestMapping("inventario-app") //Angular Front-end
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {
    private  static final Logger logger =
            LoggerFactory.getLogger(ProductoControlador.class);
    @Autowired
    private ProductoServicio productoServicio;

    //http://localhost:8080/inventario-app/productos
    @GetMapping("/productos")

    public List<Producto> obtenerProductos(){
        List<Producto> productos=this.productoServicio.ListaProductos();
        logger.info("Productos Obtenidos:");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar: "+producto);
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity <Producto> obtenerProductoPorId( @PathVariable int id){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto!= null)
            return ResponseEntity.ok(producto);
        else
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: "+ id);
    }
    @PutMapping ("/productos/{id}")
    public ResponseEntity <Producto>actualizarProducto(
            @PathVariable int id,
            @RequestBody Producto productoRecibido){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if (producto==null)
            throw  new RecursoNoEncontradoExcepcion("No se encontro el id: "+id);
        producto.setDescription(productoRecibido.getDescription());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }
    @DeleteMapping ("/productos/{id}")
    public ResponseEntity <Map<String,Boolean>>
        eliminarProducto(@PathVariable int id){
        Producto producto =productoServicio.buscarProductoPorId(id);
        if (producto==null)
            throw  new RecursoNoEncontradoExcepcion("No se encontro el id: "+id);
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }
}
