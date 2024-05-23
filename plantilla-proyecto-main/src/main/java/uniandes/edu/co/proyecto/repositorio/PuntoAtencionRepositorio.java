package uniandes.edu.co.proyecto.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.PuntoAtencion;

public interface PuntoAtencionRepositorio extends MongoRepository<PuntoAtencion, ObjectId>{
    
    
}
