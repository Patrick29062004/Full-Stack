package gm.inventarios.modelo;

import com.jayway.jsonpath.internal.function.numeric.Max;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //Entidad
//@AllArgsConstructor // construtr vacio
//@NoArgsConstructor//constructor con argumentos
//@ToString
@Table(name="productos")
@Data //metods get y set
public class Producto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Autoincrementable
    @Column ( name = "idProducto")
    Integer idProducto;
    @Column ( name = "description")
    String description;
    @Column ( name = "precio")
    Double precio;
    @Column ( name = "existencia")

    Integer existencia;

}
