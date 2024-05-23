package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "puntos_atencion")
public class PuntoAtencion {
    
    @Field("_id")
    @Id
    private ObjectId _id;

    @Field("tipo")
    private TipoPunto tipo_punto;

    @Field("localizacion")
    private List<Integer> localización;

    @Field("empleados")
    private List<Empleado> empleados;


    public PuntoAtencion(ObjectId _id, TipoPunto tipo_punto, List<Integer> localización) {
        super();
        this._id = _id;
        this.tipo_punto = tipo_punto;
        this.localización = localización;
        
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public TipoPunto getTipo_punto() {
        return tipo_punto;
    }

    public void setTipo_punto(TipoPunto tipo_punto) {
        this.tipo_punto = tipo_punto;
    }

    public List<Integer> getLocalización() {
        return localización;
    }

    public void setLocalización(List<Integer> localización) {
        this.localización = localización;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }



    
}
