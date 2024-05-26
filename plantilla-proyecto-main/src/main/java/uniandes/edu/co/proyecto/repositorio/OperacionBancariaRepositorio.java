package uniandes.edu.co.proyecto.repositorio;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.OperacionBancaria;

public interface OperacionBancariaRepositorio extends MongoRepository<OperacionBancaria, String>{

    @Query("{fecha:{$gte:?0, $lte:?1}}")
    void darOperacionesEntreFechas(Date fechaInicio, Date fechaFinal);

}
