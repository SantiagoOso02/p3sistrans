package uniandes.edu.co.proyecto.modelo;

import org.bson.types.ObjectId;

// import org.springframework.data.mongodb.core.mapping.Document;
// import org.springframework.data.mongodb.core.mapping.Field;

import lombok.ToString;

@ToString

public class Cliente {

   
  

    private String nombre;
    
    private String num_documento;

    
    private TipoCliente tipo_cliente;

   



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }


    public TipoCliente getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(TipoCliente tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }





   

    

   
    
}
