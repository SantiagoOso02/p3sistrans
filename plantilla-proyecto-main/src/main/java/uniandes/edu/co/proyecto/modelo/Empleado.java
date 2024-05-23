package uniandes.edu.co.proyecto.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Empleado {
    
    @Field("_id")
    @Id
    private ObjectId _id; 

    @Field("tipo_empleado")
    private TipoEmpleado tipo_empleado; 

    @Field("num_documento")
    private String num_documento; 


    public Empleado(ObjectId _id, TipoEmpleado tipo_empleado, String num_documento) {
        super();
        this._id = _id;
        this.tipo_empleado = tipo_empleado;
        this.num_documento = num_documento;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

