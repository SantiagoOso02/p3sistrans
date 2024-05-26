package uniandes.edu.co.proyecto.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.TipoEmpleado;

public interface OficinaRepositorio extends MongoRepository<Oficina, String>{

    @Query("{_id:?0}")
    @Update("{$push:{empleado:{num_documento:?0, tipo_empleado: ?1}}}")
    void añadirEmpleado(ObjectId id_oficina, String num_documento, TipoEmpleado tipoEmpleado);
    
}
