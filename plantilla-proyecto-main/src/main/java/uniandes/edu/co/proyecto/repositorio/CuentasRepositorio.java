package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Cuentas;
import uniandes.edu.co.proyecto.modelo.TipoCliente;

public interface CuentasRepositorio extends MongoRepository<Cuentas, String>{

    @Query("{_id: ?0}")
    @Update("{$push:{cliente:{nombre:?1, num_documento:?2,tipo_cliente:?3}}}")
    void añadirCliente(String id_cuenta, String nombre, String num_documento, TipoCliente tipo_cliente);

    @Query("{_id: ?0}")
        @Update("{$set:{estado: ?1}")
        void cambiarEstadoCuenta(String id_cuenta, String estado_nuevo);

}