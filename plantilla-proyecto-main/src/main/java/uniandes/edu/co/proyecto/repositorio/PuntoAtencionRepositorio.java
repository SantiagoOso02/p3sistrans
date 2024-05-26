package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
import uniandes.edu.co.proyecto.modelo.TipoEmpleado;

public interface PuntoAtencionRepositorio extends MongoRepository<PuntoAtencion, String>{

    @Query("{_id:?0}")
    @Update("{$push:{empleados:{num_documento:?0, tipo_empleado: ?1}}}")
    void añadirEmpleado(ObjectId id_oficina, String num_documento, TipoEmpleado tipoEmpleado);

    @Query("{}")
    Collection<PuntoAtencion> darPuntos();

    void deleteById(ObjectId id);

    @Query("{_id: ?0}")
    PuntoAtencion findById(ObjectId id);
}
