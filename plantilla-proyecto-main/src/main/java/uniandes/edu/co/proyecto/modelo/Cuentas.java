package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cuentas")
public class Cuentas {

    @Field("_id")
    @Id
    private String _id;
    
    @Field("tipo_cuenta")
    private TipoCuenta tipo_cuenta;

    @Field("estado")
    private Estado estado; 

    @Field("saldo")
    private int saldo; 

    @Field("fecha_ultima_transaccion")
    private LocalDate fecha_ultima_transaccion; 


    @Field("cliente")
    private Cliente cliente;

    @Field("operaciones_bancarias")
    @DBRef
    private List<OperacionBancaria> operacionBancaria;

   

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public TipoCuenta getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(TipoCuenta tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getSaldo() {
        return saldo;
    }   

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFecha_ultima_transaccion() {
        return fecha_ultima_transaccion;
    }

    public void setFecha_ultima_transaccion(LocalDate fecha_ultima_transaccion) {
        this.fecha_ultima_transaccion = fecha_ultima_transaccion;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OperacionBancaria> getOperacionBancaria() {
        return operacionBancaria;
    }

    public void setOperacionBancaria(List<OperacionBancaria> operacionBancaria) {
        this.operacionBancaria = operacionBancaria;
    }


   
   
    
    


    
}
