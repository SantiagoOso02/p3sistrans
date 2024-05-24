package uniandes.edu.co.proyecto.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Empleado {
    
   
    @Field("num_documento")
    private String num_documento; 
    
    @Field("tipo_empleado")
    private TipoEmpleado tipo_empleado; 

    


    public Empleado( TipoEmpleado tipo_empleado, String num_documento) {
        super();
        
        this.tipo_empleado = tipo_empleado;
        this.num_documento = num_documento;
    }

  

    public TipoEmpleado getTipo_empleado() {
        return tipo_empleado;
    }

    public void setTipo_empleado(TipoEmpleado tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }




}

