package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Cuentas;
import uniandes.edu.co.proyecto.modelo.Estado;
import uniandes.edu.co.proyecto.modelo.TipoCliente;
import uniandes.edu.co.proyecto.modelo.TipoCuenta;

public interface CuentasRepositorio extends MongoRepository<Cuentas, String>{

    @Query("{_id: ?0}")
    @Update("{$push:{cliente:{nombre:?1, num_documento:?2,tipo_cliente:?3}}}")
    void añadirCliente(String id_cuenta, String nombre, String num_documento, TipoCliente tipo_cliente);

    @Query("{_id: ?0}")
    @Update("{$push:{operacion_bancaria:{_id: ?1}")
    void añadirOperacionBancaria(String idCuenta, String idOperacion);

    @Query("{_id: ?0}")
    @Update("{$set:{estado: ?1}")
    void cambiarEstadoCuenta(String id_cuenta, Estado nuevoEstado);

    @Query("{_id: ?0}")
    @Update("{$set:{saldo: ?1}")
    void cambiarSaldo(String id_cuenta, int nuevoSaldo);

    @Aggregation(pipeline={"{$match:{tipo_cuenta:?0}},{$match:{saldo:{$gte:?1, $lte:?2}}}, {$match:{fecha_ultima_transaccion:?3}}, {$match:{cliente.nombre:?4}}"})
    List<Cuentas> filtrarCuentas(TipoCuenta tipoCuenta, Integer saldoMin, Integer saldoMax, LocalDate fechaUltimoMovimiento, String nombreCliente);

}