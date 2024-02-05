package main.java.org.iesalandalus.programacion.reservashotel.vista;

import main.java.org.iesalandalus.programacion.reservashotel.dominio.*;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private Consola(){

    }

    public static void mostrarMenu() {
        System.out.println("Menú:");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion(){
        Opcion opcion = null;
        int numOpcion;
        do {
            System.out.println("Elige una opción insertando el número de dicha opción:");
            numOpcion = Entrada.entero();
        } while (numOpcion>13 || numOpcion<1);

        switch (numOpcion){
            case 1:
                opcion = Opcion.SALIR;
                break;
            case 2:
                opcion = Opcion.INSERTAR_HUESPED;
                break;
            case 3:
                opcion = Opcion.BUSCAR_HUESPED;
                break;
            case 4:
                opcion = Opcion.BORRAR_HUESPED;
                break;
            case 5:
                opcion = Opcion.MOSTRAR_HUESPEDES;
                break;
            case 6:
                opcion = Opcion.INSERTAR_HABITACION;
                break;
            case 7:
                opcion = Opcion.BUSCAR_HABITACION;
                break;
            case 8:
                opcion = Opcion.BORRAR_HABITACION;
                break;
            case 9:
                opcion = Opcion.MOSTRAR_HABITACIONES;
                break;
            case 10:
                opcion = Opcion.INSERTAR_RESERVA;
                break;
            case 11:
                opcion = Opcion.ANULAR_RESERVA;
                break;
            case 12:
                opcion = Opcion.MOSTRAR_RESERVAS;
                break;
            case 13:
                opcion = Opcion.CONSULTAR_DISPONIBILIDAD;
                break;
        }

        return opcion;
    }

    public static Huesped leerHuesped(){
        System.out.println("Introduce los datos del nuevo huesped:");

        System.out.println("Nombre:");
        String nombre = Entrada.cadena();

        System.out.println("Telefono:");
        String telefono = Entrada.cadena();

        System.out.println("Correo:");
        String correo = Entrada.cadena();

        System.out.println("DNI:");
        String dni = Entrada.cadena();

        LocalDate fechaNacimiento = leerFecha("Introduce una fecha en formato 'dd/MM/yyyy':");

        return new Huesped(nombre, telefono, correo, dni, fechaNacimiento);
    }

    public static Huesped leerClientePorDni() {
        System.out.print("Introduce el DNI del huésped: ");
        String dni = Entrada.cadena();

        return new Huesped("Nombre Ficticio", "666777888", "correoficticio@gmail.com", dni, LocalDate.now().minusYears(20));

    }


    public static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.println(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, formatter);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Por favor, introduce la fecha en el formato dd/MM/yyyy.");
            }
        }
        return fecha;
    }


    public static Habitacion leerHabitacion(){
        System.out.println("introduzca los datos de una nueva habitacion:");

        System.out.println("Numero de planta (1 - 3):");
        int planta = Entrada.entero();

        System.out.println("Numero de puerta (1 - 15):");
        int puerta = Entrada.entero();

        System.out.println("Precio (40.0 - 150.0):");
        double precio = Entrada.realDoble();

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador(){
        System.out.println("Introduce el numero de planta de la habitacion:");
        int planta = Entrada.entero();

        System.out.println("Introduce el numero de puerta de la habitacion:");
        int puerta = Entrada.entero();

        double precio = 50.0;
        TipoHabitacion tipoHabitacion = TipoHabitacion.TRIPLE;

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }

    public static TipoHabitacion leerTipoHabitacion(){
        TipoHabitacion tipoHabitacion = null;
        int opcionTipoHab;
        do {
            System.out.println("Tipo de habitación:\n1.- Suite\n2.- Simple\n3.- Doble\n4.- Triple");
            opcionTipoHab = Entrada.entero();
        } while (opcionTipoHab<1 || opcionTipoHab>4);

        switch (opcionTipoHab){
            case 1:
                tipoHabitacion = TipoHabitacion.SUITE;
                break;
            case 2:
                tipoHabitacion = TipoHabitacion.SIMPLE;
                break;
            case 3:
                tipoHabitacion = TipoHabitacion.DOBLE;
                break;
            case 4:
                tipoHabitacion = TipoHabitacion.TRIPLE;
                break;
        }
        return tipoHabitacion;
    }

    public static Regimen leerRegimen(){
        Regimen regimen = null;
        int opcionReg;
        do{
            System.out.println("Tipo de regimen:\n1.- Solo alojamiento\n2.- Alojamiento y desayuno\n3.- Media pensión\n4.- Pension completa");
            opcionReg = Entrada.entero();
        } while (opcionReg<1 || opcionReg>4);

        switch (opcionReg){
            case 1:
                regimen = Regimen.SOLO_ALOJAMIENTO;
                break;
            case 2:
                regimen = Regimen.ALOJAMIENTO_DESAYUNO;
                break;
            case 3:
                regimen = Regimen.MEDIA_PENSION;
                break;
            case 4:
                regimen = Regimen.PENSION_COMPLETA;
                break;
        }
        return regimen;
    }

    public static Reserva leerReserva(){
        Huesped huesped = leerHuesped();
        Habitacion habitacion = leerHabitacion();
        Regimen regimen = leerRegimen();
        LocalDate fechaInicioReserva = leerFecha("Introduce la fecha de inicio de la reserva:");
        LocalDate fechaFinReserva = leerFecha("Introduce la fecha de fin de la reserva:");

        System.out.println("Numero de personas:");
        int numeroPersonas = Entrada.entero();

       return new Reserva(huesped,habitacion,regimen,fechaInicioReserva,fechaFinReserva,numeroPersonas);
    }
}
