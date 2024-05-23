package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.Cuentas;

public interface CuentasRepositorio extends MongoRepository<Cuentas, String>{

    
    
}
