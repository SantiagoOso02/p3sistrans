package uniandes.edu.co.proyecto;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import uniandes.edu.co.proyecto.repositorio.CuentasRepositorio;
import uniandes.edu.co.proyecto.repositorio.OficinaRepositorio;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepositorio;
import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.Cuentas;
import uniandes.edu.co.proyecto.modelo.Empleado;
import uniandes.edu.co.proyecto.modelo.Estado;
import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
import uniandes.edu.co.proyecto.modelo.TipoCliente;
import uniandes.edu.co.proyecto.modelo.TipoCuenta;
import uniandes.edu.co.proyecto.modelo.TipoEmpleado;
import uniandes.edu.co.proyecto.modelo.TipoPunto;


@ComponentScan({"uniandes.edu.co.proyecto.repositorio"})
@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner{

	@Autowired
	private OficinaRepositorio oficinaRepositorio;

	@Autowired
	private PuntoAtencionRepositorio puntoAtencionRepositorio;

	@Autowired
	private CuentasRepositorio cuentasRepositorio;


	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception{

		// List<Empleado> empleados=new Empleado("GERENTE_OFICINA", "8765234512");

		//RF2: Crear Oficina
		Oficina nuevaOficina = new Oficina();
		
		nuevaOficina.setDireccion("Calle 100 # 15-20");
		nuevaOficina.setNum_puntos_atencion_posibles(5);

		Empleado gerente = new Empleado(TipoEmpleado.GERENTE_OFICINA, "8765234512");

		nuevaOficina.setGerente(gerente);


		oficinaRepositorio.save(nuevaOficina);
		
		System.out.println("Oficina guardada");
		



		//RF3: Crear un punto de atención Y Parte de RF1: Crear un empleado
		PuntoAtencion nuevoPuntoAtencion = new PuntoAtencion();

		nuevoPuntoAtencion.setTipo(TipoPunto.PUNTO_ATENCION_PERSONALIZADO);
		nuevoPuntoAtencion.setLocalizacion(List.of(4.60971, -74.08175));

		Empleado empleado = new Empleado(TipoEmpleado.GERENTE_OFICINA, "8765234512");

		Empleado empleado2 = new Empleado(TipoEmpleado.GERENTE_GENERAL, "9845321457");
		
		nuevoPuntoAtencion.setEmpleados(List.of(empleado,empleado2));

		puntoAtencionRepositorio.save(nuevoPuntoAtencion);

		System.out.println("Punto de atención guardado");

		Collection<PuntoAtencion> puntos = puntoAtencionRepositorio.darPuntos();
		
		for (PuntoAtencion punto : puntos) {

			System.out.println(punto.getEmpleados().get(1).getNum_documento());
		}

		//RF3: Eliminar un punto de atención
		
			puntoAtencionRepositorio.deleteById(puntos.iterator().next().getId());
			System.out.println("Punto de atención eliminado");

			
		//RF4: Crear una cuenta
		Cuentas nuevaCuenta = new Cuentas();
		
		nuevaCuenta.set_id("1234567890");
		nuevaCuenta.setTipo_cuenta(TipoCuenta.AHORROS);
		nuevaCuenta.setSaldo(100000);
		nuevaCuenta.setEstado(Estado.ACTIVADA);

		Cliente cliente = new Cliente();
		cliente.setNombre("Juan Perez");
		cliente.setNum_documento("1234567890");
		cliente.setTipo_cliente(TipoCliente.PERSONA_NATURAL);

		nuevaCuenta.setCliente(cliente);

		cuentasRepositorio.save(nuevaCuenta);

		System.out.println("Cuenta guardada");

		


		
	}

}
