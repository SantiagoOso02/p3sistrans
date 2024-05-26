package uniandes.edu.co.proyecto.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.OperacionBancaria;

public interface OperacionBancariaRepositorio extends MongoRepository<OperacionBancaria, ObjectId>{

    @Query("{_id: ?0}")
        @Update("{$set:{estado: ?1}")
        void cambiarEstadoCuenta(String id_cuenta, String estado_nuevo);

}
