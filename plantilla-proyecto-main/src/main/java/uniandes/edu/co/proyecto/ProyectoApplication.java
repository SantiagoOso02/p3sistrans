package uniandes.edu.co.proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
import uniandes.edu.co.proyecto.modelo.OperacionBancaria;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
import uniandes.edu.co.proyecto.modelo.TipoCliente;
import uniandes.edu.co.proyecto.modelo.TipoCuenta;
import uniandes.edu.co.proyecto.modelo.TipoEmpleado;
import uniandes.edu.co.proyecto.modelo.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.TipoPunto;

@ComponentScan({"uniandes.edu.co.proyecto.repositorio"})
@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

    @Autowired
    private OficinaRepositorio oficinaRepositorio;

    @Autowired
    private static PuntoAtencionRepositorio puntoAtencionRepositorio;

    @Autowired
    private static CuentasRepositorio cuentasRepositorio;

    @Autowired
    private static OperacionBancariaRepositorio operacionBancariaRepositorio;

    public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

    @Override
	public void run(String... strings) throws Exception{
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Crear Oficina");
            System.out.println("3. Crear Punto de Atención");
            System.out.println("4. Borrar Punto de Atención");
            System.out.println("5. Crear Cuenta");
            System.out.println("6. Cambiar Estado de Cuenta a Cerrada o Desactivada");
            System.out.println("7. Registrar Operación sobre Cuenta");
            System.out.println("8. Consultar las Cuentas en Bancandes");
            System.out.println("9. Generar Extracto Bancario para una Cuenta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    crearUsuario(scanner);
                    break;
                case 2:
                    crearOficina(scanner);
                    break;
                case 3:
                    crearPuntoAtencion(scanner);
                    break;
                case 4:
                    borrarPuntoAtencion(scanner);
                    break;
                case 5:
                    crearCuenta(scanner);
                    break;
                case 6:
                    cambiarEstadoCuenta(scanner);
                    break;
                case 7:
                    operacionSobreCuenta(scanner);
                    break;
                case 8:
                    consultarCuentas(scanner);
                    break;
                case 9:
                    generarExtractoBancario(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (option != 0);

        scanner.close();
    }

    private static void crearUsuario(Scanner scanner) {
        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Cliente");
        System.out.println("2. Empleado");
        int tipoUsuario = scanner.nextInt();
        scanner.nextLine();

        if (tipoUsuario == 1) {
            crearCliente(scanner);
        } else if (tipoUsuario == 2) {
            crearEmpleado(scanner);
        } else {
            System.out.println("Opción no válida. Volviendo al menú principal.");
        }
    }

    private static Cliente crearCliente(Scanner scanner) {
        System.out.println("Ingrese el número de cuenta al que desea agregar el cliente:");
        System.out.print("Número cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.println("Ingrese los datos del cliente:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Número de documento: ");
        String numDocumento = scanner.nextLine();
        TipoCliente tipoCliente = null;
        while (tipoCliente == null) {
            System.out.print("Tipo de cliente (1. PERSONA_NATURAL, 2. PERSONA_JURIDICA): ");
            int tipoClienteInt = scanner.nextInt();
            scanner.nextLine();

            if (tipoClienteInt == 1) {
                tipoCliente = TipoCliente.PERSONA_NATURAL;
            } else if (tipoClienteInt == 2) {
                tipoCliente = TipoCliente.PERSONA_JURIDICA;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        cuentasRepositorio.añadirCliente(numeroCuenta, nombre, numDocumento, tipoCliente);
        System.out.println("Cliente creado y embebido en cuenta.");
        return new Cliente(nombre, numDocumento, tipoCliente);
    }

    private static Empleado crearEmpleado(Scanner scanner) {
        System.out.println("Ingrese el número de punto de atención al que desea agregar el empleado:");
        System.out.print("Punto de atención: ");
        String puntoAtencion = scanner.nextLine();
        System.out.println("Ingrese los datos del empleado:");
        System.out.print("Número de documento: ");
        String numDocumento = scanner.nextLine();
        TipoEmpleado tipoEmpleado = null;
        while (tipoEmpleado == null) {
            System.out.print("Tipo de empleado (1. GERENTE_GENERAL, 2. GERENTE_OFICINA, 3. CAJERO): ");
            int tipoEmpleadoInt = scanner.nextInt();
            scanner.nextLine();

            switch (tipoEmpleadoInt) {
                case 1:
                    tipoEmpleado = TipoEmpleado.GERENTE_GENERAL;
                    break;
                case 2:
                    tipoEmpleado = TipoEmpleado.GERENTE_OFICINA;
                    break;
                case 3:
                    tipoEmpleado = TipoEmpleado.CAJERO;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        puntoAtencionRepositorio.añadirEmpleado(puntoAtencion, numDocumento, tipoEmpleado);
        System.out.println("Empleado creado y embebido en punto de atención.");
        return new Empleado(tipoEmpleado, numDocumento);
    }

    private void crearOficina(Scanner scanner) {
        System.out.println("Ingrese los datos de la oficina:");
        System.out.print("id: ");
        String id = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Número de puntos de atención posibles: ");
        int numPuntosAtencion = scanner.nextInt();
        scanner.nextLine();
        Empleado gerente = crearEmpleado(scanner);
        List<PuntoAtencion> puntosAtencion = new ArrayList<>();
        Oficina oficina = new Oficina();
        oficina.set_id(id);
        oficina.setDireccion(direccion);
        oficina.setNum_puntos_atencion_posibles(numPuntosAtencion);
        oficina.setGerente(gerente);
        oficina.setPuntoAtencion(puntosAtencion);

        oficinaRepositorio.save(oficina);
        System.out.println("Oficina creada y guardada en MongoDB.");
    }

    private static PuntoAtencion crearPuntoAtencion(Scanner scanner) {
        System.out.println("Ingrese los datos del punto de atención:");
        System.out.print("id: ");
        String id = scanner.nextLine();
        TipoPunto tipoPunto = null;
        while (tipoPunto == null) {
            System.out.print("Tipo de punto de atención (1. PUNTO_ATENCION_PERSONALIZADO, 2. CAJERO_AUTOMATICO, 3. PORTAL_WEB, 4. APP_MOVIL): ");
            int tipoInt = scanner.nextInt();
            scanner.nextLine();

            switch (tipoInt) {
                case 1:
                    tipoPunto = TipoPunto.PUNTO_ATENCION_PERSONALIZADO;
                    break;
                case 2:
                    tipoPunto = TipoPunto.CAJERO_AUTOMATICO;
                    break;
                case 3:
                    tipoPunto = TipoPunto.PORTAL_WEB;
                    break;
                case 4:
                    tipoPunto = TipoPunto.APP_MOVIL;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        System.out.print("Ingrese la latitud del punto de atención: ");
        double latitud = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese la longitud del punto de atención: ");
        double longitud = scanner.nextDouble();
        scanner.nextLine();

        List<Double> localizacion = Arrays.asList(latitud, longitud);

        List<Empleado> empleados = new ArrayList<>();

        PuntoAtencion puntoAtencion = new PuntoAtencion();
        puntoAtencion.setId(id);
        puntoAtencion.setTipo(tipoPunto);
        puntoAtencion.setLocalizacion(localizacion);
        puntoAtencion.setEmpleados(empleados);

        puntoAtencionRepositorio.save(puntoAtencion);
        System.out.println("Punto de atención creado y guardado en MongoDB.");
        return puntoAtencion;
    }

    private static void borrarPuntoAtencion(Scanner scanner) {
        System.out.print("Ingrese el ID del punto de atención que desea borrar: ");
        String id = scanner.nextLine();

        puntoAtencionRepositorio.deleteById(id);
        System.out.println("Punto de atención con ID " + id + " borrado correctamente.");
    }

    private static void crearCuenta(Scanner scanner) {
        System.out.println("Ingrese los datos de la cuenta:");

        System.out.print("id: ");
        String id = scanner.nextLine();

        TipoCuenta tipoCuenta = null;
        while (tipoCuenta == null) {
            System.out.print("Tipo de cuenta (1. AHORROS, 2. CORRIENTE, 3. AFC): ");
            int tipoCuentaInt = scanner.nextInt();
            scanner.nextLine();

            switch (tipoCuentaInt) {
                case 1:
                    tipoCuenta = TipoCuenta.AHORROS;
                    break;
                case 2:
                    tipoCuenta = TipoCuenta.CORRIENTE;
                    break;
                case 3:
                    tipoCuenta = TipoCuenta.AFC;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        Estado estado = null;
        while (estado == null) {
            System.out.print("Estado de la cuenta (1. ACTIVADA, 2. CERRADA, 3. DESACTIVADA): ");
            int estadoInt = scanner.nextInt();
            scanner.nextLine(); 

            switch (estadoInt) {
                case 1:
                    estado = Estado.ACTIVADA;
                    break;
                case 2:
                    estado = Estado.CERRADA;
                    break;
                case 3:
                    estado = Estado.DESACTIVADA;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        System.out.print("Saldo inicial: ");
        int saldo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Fecha de última transacción (YYYY-MM-DD): ");
        String fechaString = scanner.nextLine();
        LocalDate fechaUltimaTransaccion = LocalDate.parse(fechaString);

        Cliente cliente = crearCliente(scanner);

        List<OperacionBancaria> operacionesBancarias = new ArrayList<>();

        Cuentas cuenta = new Cuentas();
        cuenta.set_id(id);
        cuenta.setTipo_cuenta(tipoCuenta);
        cuenta.setSaldo(saldo);
        cuenta.setOperacionBancaria(operacionesBancarias);
        cuenta.setFecha_ultima_transaccion(fechaUltimaTransaccion);
        cuenta.setEstado(estado);
        cuenta.setCliente(cliente);

        cuentasRepositorio.save(cuenta);

        System.out.println("Cuenta creada y guardada en MongoDB.");
    }

    private static void cambiarEstadoCuenta(Scanner scanner) {
        System.out.print("Ingrese el ID de la cuenta que desea cambiar de estado: ");
        String idCuenta = scanner.nextLine();

        Optional<Cuentas> cuenta = cuentasRepositorio.findById(idCuenta);

        if (cuenta == null) {
            System.out.println("No se encontró ninguna cuenta con el ID proporcionado.");
            return;
        }

        Estado estadoActual = cuenta.get().getEstado();

        if (estadoActual.equals(Estado.ACTIVADA)) {
            System.out.println("Seleccione el nuevo estado:");
            System.out.println("1. CERRADA");
            System.out.println("2. DESACTIVADA");
            int nuevoEstadoInt = scanner.nextInt();
            scanner.nextLine();

            Estado nuevoEstado = null;
            switch (nuevoEstadoInt) {
                case 1:
                    int saldoActual = cuenta.get().getSaldo();
                    if (saldoActual == 0) {
                        nuevoEstado = Estado.CERRADA;
                    } else {
                        System.out.println("No se puede cerrar la cuenta porque el saldo no es cero.");
                        return;
                    }
                    break;
                case 2:
                    nuevoEstado = Estado.DESACTIVADA;
                    break;
                default:
                    System.out.println("Opción no válida. Volviendo al menú principal.");
                    return;
            }

            cuentasRepositorio.cambiarEstadoCuenta(idCuenta, nuevoEstado);
            System.out.println("Estado de cuenta cambiado exitosamente.");

        } else {
            System.out.println("La cuenta no se encuentra en estado activo. No se puede cambiar el estado.");
        }
    }

    private static void operacionSobreCuenta(Scanner scanner) {
        System.out.println("Seleccione el tipo de operación:");
        System.out.println("1. Consignar o Retirar dinero");
        System.out.println("2. Transferir dinero entre cuentas del mismo banco");
        int tipoOperacion = scanner.nextInt();
        scanner.nextLine();

        switch (tipoOperacion) {
            case 1:
                consignarRetirarDinero(scanner);
                break;
            case 2:
                transferirDinero(scanner);
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
                break;
        }
    }

    private static void consignarRetirarDinero(Scanner scanner) {
        System.out.print("Ingrese el ID de la operación: ");
        String id= scanner.nextLine();
        System.out.print("Ingrese el ID de la cuenta: ");
        String idCuenta = scanner.nextLine();

        Optional<Cuentas> cuenta = cuentasRepositorio.findById(idCuenta);

        if (cuenta == null) {
            System.out.println("No se encontró ninguna cuenta con el ID proporcionado.");
            return;
        }

        System.out.print("Ingrese el ID del punto de atención: ");
        String idPuntoAtencion = scanner.nextLine();

        Optional<PuntoAtencion> puntoAtencion = puntoAtencionRepositorio.findById(idPuntoAtencion);

        if (puntoAtencion == null) {
            System.out.println("No se encontró ningun punto de atención con el ID proporcionado.");
            return;
        }

        System.out.println("Estado actual de la cuenta:");
        System.out.println("ID: " + cuenta.get().get_id());
        System.out.println("Saldo: " + cuenta.get().getSaldo());
        System.out.println("=============================");

        System.out.print("Seleccione el tipo de operación (1. Consignación, 2. Retiro): ");
        int tipoOperacion = scanner.nextInt();
        scanner.nextLine();

        if (tipoOperacion == 1) {
            System.out.print("Ingrese el monto a consignar: ");
            int monto = scanner.nextInt();
            scanner.nextLine();

            LocalDate fechaOperacion = LocalDate.now();
            TipoOperacion tipoOperacionStr = TipoOperacion.CONSIGNAR;

            int saldoActual = cuenta.get().getSaldo();
            int nuevoSaldo = saldoActual + monto;

        
            OperacionBancaria operacionBancaria = new OperacionBancaria();
            operacionBancaria.setTipo(tipoOperacionStr);
            operacionBancaria.setPunto_atencion(idPuntoAtencion);
            operacionBancaria.setMonto(monto);
            operacionBancaria.setId(id);
            operacionBancaria.setFecha(fechaOperacion);
            operacionBancaria.setCuenta_origen(idCuenta);

            operacionBancariaRepositorio.save(operacionBancaria);

            cuentasRepositorio.cambiarSaldo(idCuenta, nuevoSaldo);
            cuentasRepositorio.añadirOperacionBancaria(idCuenta, id);

            System.out.println("Consignación realizada correctamente.");
            System.out.println("Nuevo saldo: " + nuevoSaldo);

        } else if (tipoOperacion == 2) {
            System.out.print("Ingrese el monto a retirar: ");
            int monto = scanner.nextInt();
            scanner.nextLine();

            LocalDate fechaOperacion = LocalDate.now();
            TipoOperacion tipoOperacionStr = TipoOperacion.RETIRAR;

            int saldoActual = cuenta.get().getSaldo();
            if (monto > saldoActual) {
                System.out.println("Fondos insuficientes para realizar el retiro.");
                return;
            }

            int nuevoSaldo = saldoActual - monto;

            OperacionBancaria operacionBancaria = new OperacionBancaria();
            operacionBancaria.setTipo(tipoOperacionStr);
            operacionBancaria.setPunto_atencion(idPuntoAtencion);
            operacionBancaria.setMonto(monto);
            operacionBancaria.setId(id);
            operacionBancaria.setFecha(fechaOperacion);
            operacionBancaria.setCuenta_origen(idCuenta);

            operacionBancariaRepositorio.save(operacionBancaria);

            cuentasRepositorio.cambiarSaldo(idCuenta, nuevoSaldo);
            cuentasRepositorio.añadirOperacionBancaria(idCuenta, id);

            System.out.println("Retiro realizado correctamente.");
            System.out.println("Nuevo saldo: " + nuevoSaldo);

        } else {
            System.out.println("Opción no válida. Volviendo al menú principal.");
        }
    }

    private static void transferirDinero(Scanner scanner) {
        System.out.print("Ingrese el ID de la operación bancaria: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el ID de la cuenta origen: ");
        String idCuentaOrigen = scanner.nextLine();

        Optional<Cuentas> cuentaOrigen = cuentasRepositorio.findById(idCuentaOrigen);

        if (cuentaOrigen == null) {
            System.out.println("No se encontró ninguna cuenta origen con el ID proporcionado.");
            return;
        }

        System.out.print("Ingrese el ID del punto de atención: ");
        String idPuntoAtencion = scanner.nextLine();

        System.out.print("Ingrese el ID de la cuenta destino: ");
        String idCuentaDestino = scanner.nextLine();

        Optional<Cuentas> cuentaDestino = cuentasRepositorio.findById(idCuentaDestino);

        if (cuentaDestino == null) {
            System.out.println("No se encontró ninguna cuenta destino con el ID proporcionado.");
            return;
        }

        System.out.print("Ingrese el monto a transferir: ");
        int monto = scanner.nextInt();
        scanner.nextLine();

        LocalDate fechaOperacion = LocalDate.now();
        TipoOperacion tipoOperacionStr = TipoOperacion.TRANSFERIR;

        int saldoOrigenActual = cuentaOrigen.get().getSaldo();
        if (monto > saldoOrigenActual) {
            System.out.println("Fondos insuficientes en la cuenta origen para realizar la transferencia.");
            return;
        }

        int nuevoSaldoOrigen = saldoOrigenActual - monto;

        OperacionBancaria operacionBancaria = new OperacionBancaria();
        operacionBancaria.setTipo(tipoOperacionStr);
        operacionBancaria.setPunto_atencion(idPuntoAtencion);
        operacionBancaria.setMonto(monto);
        operacionBancaria.setId(id);
        operacionBancaria.setFecha(fechaOperacion);
        operacionBancaria.setCuenta_origen(idCuentaOrigen);
        operacionBancaria.setCuenta_destino(idCuentaDestino);

        operacionBancariaRepositorio.save(operacionBancaria);

        int saldoDestinoActual = cuentaDestino.get().getSaldo();
        int nuevoSaldoDestino = saldoDestinoActual + monto;

        cuentasRepositorio.cambiarSaldo(idCuentaOrigen, nuevoSaldoOrigen);
        cuentasRepositorio.añadirOperacionBancaria(idCuentaOrigen, id);

        cuentasRepositorio.cambiarSaldo(idCuentaDestino, nuevoSaldoDestino);
        cuentasRepositorio.añadirOperacionBancaria(idCuentaDestino, id);

        System.out.println("Transferencia realizada correctamente.");
        System.out.println("Nuevo saldo de cuenta origen: " + nuevoSaldoOrigen);
        System.out.println("Nuevo saldo de cuenta destino: " + nuevoSaldoDestino);
    }

    private static void consultarCuentas(Scanner scanner) {
        System.out.println("Ingrese los criterios de búsqueda (deje en blanco para omitir):");

        System.out.print("Tipo de cuenta (AHORROS, CORRIENTE, AFC): ");
        String tipoCuentaStr = scanner.nextLine();
        TipoCuenta tipoCuenta = tipoCuentaStr.isEmpty() ? null : TipoCuenta.valueOf(tipoCuentaStr);

        System.out.print("Saldo mínimo: ");
        String saldoMinStr = scanner.nextLine();
        Integer saldoMin = saldoMinStr.isEmpty() ? null : Integer.parseInt(saldoMinStr);

        System.out.print("Saldo máximo: ");
        String saldoMaxStr = scanner.nextLine();
        Integer saldoMax = saldoMaxStr.isEmpty() ? null : Integer.parseInt(saldoMaxStr);

        System.out.print("Fecha del último movimiento (YYYY-MM-DD): ");
        String fechaUltimoMovimientoStr = scanner.nextLine();
        LocalDate fechaUltimoMovimiento = fechaUltimoMovimientoStr.isEmpty() ? null : LocalDate.parse(fechaUltimoMovimientoStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();

        List<Cuentas> cuentas = cuentasRepositorio.filtrarCuentas(tipoCuenta, saldoMin, saldoMax, fechaUltimoMovimiento, nombreCliente);

        System.out.println("Cuentas encontradas:");
        for (Cuentas cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    private static void generarExtractoBancario(Scanner scanner) {
        System.out.println("Ingrese el número de la cuenta:");
        String numeroCuenta = scanner.nextLine();

        System.out.println("Ingrese el mes para el extracto (YYYY-MM):");
        String mesStr = scanner.nextLine();
        LocalDate mesInicio = LocalDate.parse(mesStr + "-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate mesFin = mesInicio.plusMonths(1);

        Optional<Cuentas> cuenta = cuentasRepositorio.findById(numeroCuenta);
        if (cuenta == null) {
            System.out.println("La cuenta no existe.");
            return;
        }

        List<OperacionBancaria> operaciones = cuenta.get().getOperacionBancaria();
        int saldoInicial = cuenta.get().getSaldo();
        for (OperacionBancaria operacion : operaciones) {
            LocalDate fechaOperacion = operacion.getFecha();
            if (fechaOperacion.isBefore(mesInicio)) {
                float monto = operacion.getMonto();
                TipoOperacion tipoOperacion = operacion.getTipo();
                if (tipoOperacion.equals("CONSIGNACION")) {
                    saldoInicial -= monto;
                } else if (tipoOperacion.equals("RETIRO")) {
                    saldoInicial += monto;
                }else if (tipoOperacion.equals("TRANSFERENCIA")) {
                    String cuentaOrigen = operacion.getCuenta_origen();
                    if (numeroCuenta.equals(cuentaOrigen)) {
                        saldoInicial += monto;
                    } else {
                        saldoInicial -= monto;
                    }
                }
            }
        }

        List<OperacionBancaria> operacionesMes = new ArrayList<>();
        for (OperacionBancaria operacion : operaciones) {
            LocalDate fechaOperacion = operacion.getFecha();
            if (!fechaOperacion.isBefore(mesInicio) && fechaOperacion.isBefore(mesFin)) {
                operacionesMes.add(operacion);
            }
        }

        int saldoFinal = saldoInicial;
        for (OperacionBancaria operacion : operacionesMes) {
            float monto = operacion.getMonto();
            TipoOperacion tipoOperacion = operacion.getTipo();
            if (tipoOperacion.equals("CONSIGNACION")) {
                saldoFinal += monto;
            } else if (tipoOperacion.equals("RETIRO")) {
                saldoFinal -= monto;
            } else if (tipoOperacion.equals("TRANSFERENCIA")) {
                String cuentaOrigen = operacion.getCuenta_origen();
                if (numeroCuenta.equals(cuentaOrigen)) {
                    saldoFinal -= monto;
                } else {
                    saldoFinal += monto;
                }
            }
        }

        System.out.println("===== EXTRACTO BANCARIO =====");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Mes: " + mesStr);
        System.out.println("Saldo inicial: " + saldoInicial);
        System.out.println("Operaciones del mes:");
        for (OperacionBancaria operacion : operacionesMes) {
            System.out.println(operacion);
        }
        System.out.println("Saldo final: " + saldoFinal);
    }

}