package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.PuntoAtencion;

public interface PuntoAtencionRepositorio extends MongoRepository<PuntoAtencion, String>{

    @Query("{}")
    Collection<PuntoAtencion> darPuntos();

    void deleteById(ObjectId id);

    @Query("{_id: ?0}")
    PuntoAtencion findById(ObjectId id);
}
