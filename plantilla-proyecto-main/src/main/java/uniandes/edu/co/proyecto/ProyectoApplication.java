package uniandes.edu.co.proyecto;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import uniandes.edu.co.proyecto.repositorio.CuentasRepositorio;
import uniandes.edu.co.proyecto.repositorio.OficinaRepositorio;
import uniandes.edu.co.proyecto.repositorio.OperacionBancariaRepositorio;
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
public class ProyectoApplication implements CommandLineRunner {

    @Autowired
    private OficinaRepositorio oficinaRepositorio;

    @Autowired
    private PuntoAtencionRepositorio puntoAtencionRepositorio;

    @Autowired
    private CuentasRepositorio cuentasRepositorio;

    @Autowired
    private OperacionBancariaRepositorio operacionBancariaRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // RF2: Crear Oficina
        System.out.println("Ingrese la dirección de la nueva oficina:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese el número de puntos de atención posibles:");
        int numPuntosAtencion = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        Oficina nuevaOficina = new Oficina();
        nuevaOficina.setDireccion(direccion);
        nuevaOficina.setNum_puntos_atencion_posibles(numPuntosAtencion);

        System.out.println("Ingrese el número de documento del gerente de la oficina:");
        String numDocumentoGerente = scanner.nextLine();
        Empleado gerente = new Empleado(TipoEmpleado.GERENTE_OFICINA, numDocumentoGerente);

        nuevaOficina.setGerente(gerente);

        oficinaRepositorio.save(nuevaOficina);
        System.out.println("Oficina guardada");

        // RF3: Crear un punto de atención y parte de RF1: Crear un empleado
        PuntoAtencion nuevoPuntoAtencion = new PuntoAtencion();

        nuevoPuntoAtencion.setTipo(TipoPunto.PUNTO_ATENCION_PERSONALIZADO);
        nuevoPuntoAtencion.setLocalizacion(List.of(4.60971, -74.08175));

        System.out.println("Ingrese el número de documento del primer empleado del punto de atención:");
        String numDocumentoEmpleado1 = scanner.nextLine();
        Empleado empleado = new Empleado(TipoEmpleado.GERENTE_OFICINA, numDocumentoEmpleado1);

        System.out.println("Ingrese el número de documento del segundo empleado del punto de atención:");
        String numDocumentoEmpleado2 = scanner.nextLine();
        Empleado empleado2 = new Empleado(TipoEmpleado.GERENTE_GENERAL, numDocumentoEmpleado2);

        nuevoPuntoAtencion.setEmpleados(List.of(empleado, empleado2));

        puntoAtencionRepositorio.save(nuevoPuntoAtencion);
        System.out.println("Punto de atención guardado");

        Collection<PuntoAtencion> puntos = puntoAtencionRepositorio.darPuntos();

        for (PuntoAtencion punto : puntos) {
            System.out.println(punto.getEmpleados().get(1).getNum_documento());
        }

        // RF3: Eliminar un punto de atención
        puntoAtencionRepositorio.deleteById(new ObjectId(puntos.iterator().next().getId()));
        System.out.println("Punto de atención eliminado");

        // RF4: Crear una cuenta
        System.out.println("Ingrese el ID de la nueva cuenta:");
        String cuentaId = scanner.nextLine();

        System.out.println("Ingrese el saldo inicial de la cuenta:");
        double saldo = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline

        Cuentas nuevaCuenta = new Cuentas();
        nuevaCuenta.set_id(cuentaId);
        nuevaCuenta.setTipo_cuenta(TipoCuenta.AHORROS);
        nuevaCuenta.setSaldo(saldo);
        nuevaCuenta.setEstado(Estado.ACTIVADA);

        System.out.println("Ingrese el nombre del cliente:");
        String nombreCliente = scanner.nextLine();

        System.out.println("Ingrese el número de documento del cliente:");
        String numDocumentoCliente = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNombre(nombreCliente);
        cliente.setNum_documento(numDocumentoCliente);
        cliente.setTipo_cliente(TipoCliente.PERSONA_NATURAL);

        nuevaCuenta.setCliente(cliente);

        cuentasRepositorio.save(nuevaCuenta);
        System.out.println("Cuenta guardada");

        // Añadir cliente a una cuenta existente
        System.out.println("Ingrese el ID de la cuenta para añadir un cliente:");
        String idCuenta = scanner.nextLine();
        
        System.out.println("Ingrese el nombre del nuevo cliente:");
        String nombreNuevoCliente = scanner.nextLine();
        
        System.out.println("Ingrese el número de documento del nuevo cliente:");
        String numDocumentoNuevoCliente = scanner.nextLine();
        
        System.out.println("Ingrese el tipo de cliente (PERSONA_NATURAL, PERSONA_JURIDICA):");
        TipoCliente tipoCliente = TipoCliente.valueOf(scanner.nextLine());

        cuentasRepositorio.añadirCliente(idCuenta, nombreNuevoCliente, numDocumentoNuevoCliente, tipoCliente);
        System.out.println("Cliente añadido a la cuenta");

        // Cambiar el estado de una cuenta
        System.out.println("Ingrese el ID de la cuenta para cambiar el estado:");
        String idCuentaEstado = scanner.nextLine();
        
        System.out.println("Ingrese el nuevo estado de la cuenta (ACTIVADA, DESACTIVADA, etc.):");
        String nuevoEstado = scanner.nextLine();

        cuentasRepositorio.cambiarEstadoCuenta(idCuentaEstado, nuevoEstado);
        System.out.println("Estado de la cuenta cambiado");

        // Añadir empleado a una oficina existente
        System.out.println("Ingrese el ID de la oficina para añadir un empleado:");
        String idOficina = scanner.nextLine();
        
        System.out.println("Ingrese el número de documento del nuevo empleado:");
        String numDocumentoNuevoEmpleado = scanner.nextLine();
        
        System.out.println("Ingrese el tipo de empleado (GERENTE_OFICINA, GERENTE_GENERAL, etc.):");
        TipoEmpleado tipoEmpleado = TipoEmpleado.valueOf(scanner.nextLine());

        oficinaRepositorio.añadirEmpleado(new ObjectId(idOficina), numDocumentoNuevoEmpleado, tipoEmpleado);
        System.out.println("Empleado añadido a la oficina");

        // Cambiar el estado de una operación bancaria
        System.out.println("Ingrese el ID de la operación bancaria para cambiar el estado:");
        String idOperacion = scanner.nextLine();

        System.out.println("Ingrese el nuevo estado de la operación bancaria:");
        String nuevoEstadoOperacion = scanner.nextLine();

        operacionBancariaRepositorio.cambiarEstadoCuenta(idOperacion, nuevoEstadoOperacion);
        System.out.println("Estado de la operación bancaria cambiado");

        // Añadir empleado a un punto de atención existente
        System.out.println("Ingrese el ID del punto de atención para añadir un empleado:");
        String idPuntoAtencion = scanner.nextLine();
        
        System.out.println("Ingrese el número de documento del nuevo empleado:");
        String numDocumentoNuevoEmpleadoPunto = scanner.nextLine();
        
        System.out.println("Ingrese el tipo de empleado (GERENTE_OFICINA, GERENTE_GENERAL, etc.):");
        TipoEmpleado tipoEmpleadoPunto = TipoEmpleado.valueOf(scanner.nextLine());

        puntoAtencionRepositorio.añadirEmpleado(new ObjectId(idPuntoAtencion), numDocumentoNuevoEmpleadoPunto, tipoEmpleadoPunto);
        System.out.println("Empleado añadido al punto de atención");
    }
}
