package uniandes.edu.co.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import uniandes.edu.co.proyecto.repositorio.OficinaRepositorio;


@ComponentScan({"uniandes.edu.co.proyecto.repositorio"})
@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner{

	@Autowired
	private OficinaRepositorio oficinaRepositorio;


	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception{

		
	}

}
