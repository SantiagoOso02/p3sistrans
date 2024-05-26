package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.ToString;

@Document(collection = "operaciones_bancarias")
public class OperacionBancaria {
    
    @Field("_id")
    private String id;

    @Field("tipo")
    private TipoOperacion tipo;

    @Field("fecha")
    private LocalDate fecha;

    @Field("monto")
    private float monto;

    @Field("cuenta_origen")
    private String cuenta_origen;

    @Field("cuenta_destino")
    private String cuenta_destino;

    @Field("punto_atencion")
    private String punto_atencion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoOperacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoOperacion tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getMonto(){
        return monto;
    }

    public void setMonto(float monto){
        this.monto = monto;
    }

    public String getCuenta_origen() {
        return cuenta_origen;
    }

    public void setCuenta_origen(String cuenta_origen) {
        this.cuenta_origen = cuenta_origen;
    }

    public String getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(String cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }

    public String getPunto_atencion() {
        return punto_atencion;
    }

    public void setPunto_atencion(String punto_atencion) {
        this.punto_atencion = punto_atencion;
    }



    





}
