package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oficinas")
public class Oficina {

    @Field("_id")
    @Id
    private String _id; 

    @Field("direccion")
    private String direccion; 

    @Field("num_puntos_atencion_posibles")
    private int num_puntos_atecion_posibles; 

    @Field("gerente")
    private Empleado gerente;
    

    @Field("punto_atencion")
    @DBRef
    private List<PuntoAtencion> puntoAtencion;

   

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNum_puntos_atencion_posibles() {
        return num_puntos_atecion_posibles;
    }

    public void setNum_puntos_atencion_posibles(int num_puntos_atecion_posibles) {
        this.num_puntos_atecion_posibles = num_puntos_atecion_posibles;
    }

    public Empleado getGerente() {
        return gerente;
    }

    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
    }

    public List<PuntoAtencion> getPuntoAtencion() {
        return puntoAtencion;
    }

    public void setPuntoAtencion(List<PuntoAtencion> puntoAtencion) {
        this.puntoAtencion = puntoAtencion;
    }

    
}
