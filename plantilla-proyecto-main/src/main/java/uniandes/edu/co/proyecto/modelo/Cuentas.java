package uniandes.edu.co.proyecto.modelo;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cuentas")
public class Cuentas {

    @Id
    private String numero_cuenta;
    
    @Field("tipo_cuenta")
    private TipoCuenta tipo_cuenta;

    @Field("estado")
    private Estado estado; 

    @Field("saldo")
    private float saldo; 

    @Field("fecha_ultima_transaccion")
    private LocalDate fecha_ultima_transaccion; 


    @Field("cliente")
    private List<Cliente> cliente;

    @Field("operaciones_bancarias")
    @DBRef
    private OperacionBancaria operacionBancaria;

    public Cuentas(String numero_cuenta, TipoCuenta tipo_cuenta, Estado estado, float saldo, LocalDate fecha_ultima_transaccion) {
        super();
        this.numero_cuenta = numero_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.fecha_ultima_transaccion= fecha_ultima_transaccion;
      

    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
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

    public float getSaldo(float saldo) {
        return saldo;
    }   

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFecha_ultima_transaccion() {
        return fecha_ultima_transaccion;
    }

    public void setFecha_ultima_transaccion(LocalDate fecha_ultima_transaccion) {
        this.fecha_ultima_transaccion = fecha_ultima_transaccion;
    }


    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public OperacionBancaria getOperacionBancaria() {
        return operacionBancaria;
    }

    public void setOperacionBancaria(OperacionBancaria operacionBancaria) {
        this.operacionBancaria = operacionBancaria;
    }
    

   
   
    
    


    
}
