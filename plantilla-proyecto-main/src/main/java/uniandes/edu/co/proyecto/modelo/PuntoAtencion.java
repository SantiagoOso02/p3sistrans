package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "puntosAtencion")
public class PuntoAtencion {

    @Id
    private String id;

    @Field("tipo")
    private TipoPunto tipo;
    
    @Field("localizacion")
    private List<Double> localizacion; // [latitud, longitud]

    @Field("empleados")
    private List<Empleado> empleados;

    // Constructores, getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoPunto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPunto tipo) {
        this.tipo = tipo;
    }

    public List<Double> getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(List<Double> localizacion) {
        this.localizacion = localizacion;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
