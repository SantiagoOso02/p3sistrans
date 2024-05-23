package uniandes.edu.co.proyecto.modelo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oficinas")
public class Oficina {

    @Field("_id")
    @Id
    private ObjectId _id; 

    @Field("direccion")
    private String direccion; 

    @Field("num_puntos_atecion_posibles")
    private int num_puntos_atecion_posibles; 

    @Field("gerente")
    private Empleado gerente;
    

    @Field("punto_atencion")
    @DBRef
    private PuntoAtencion puntoAtencion;

    public Oficina(ObjectId _id, String direccion, int num_puntos_atecion_posibles) {
        super();
        this._id = _id;
        this.direccion = direccion;
        this.num_puntos_atecion_posibles = num_puntos_atecion_posibles;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNum_puntos_atecion_posibles() {
        return num_puntos_atecion_posibles;
    }

    public void setNum_puntos_atecion_posibles(int num_puntos_atecion_posibles) {
        this.num_puntos_atecion_posibles = num_puntos_atecion_posibles;
    }

    public Empleado getGerente() {
        return gerente;
    }

    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
    }

    public PuntoAtencion getPuntoAtencion() {
        return puntoAtencion;
    }

    public void setPuntoAtencion(PuntoAtencion puntoAtencion) {
        this.puntoAtencion = puntoAtencion;
    }

    
}
