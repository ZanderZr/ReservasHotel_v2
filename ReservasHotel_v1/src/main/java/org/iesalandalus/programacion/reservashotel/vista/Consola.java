package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    private Consola() {

    }

    public static void mostrarMenu() {
        System.out.println("Men�:");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion() {
        Opcion opcion = null;
        int numOpcion;
        do {
            System.out.println("Elige una opci�n insertando el n�mero de dicha opci�n:");
            numOpcion = Entrada.entero();
        } while (numOpcion > 15 || numOpcion < 1);

        switch (numOpcion) {
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
            case 14:
                opcion = Opcion.REALIZAR_CHECKIN;
                break;
            case 15:
                opcion = Opcion.REALIZAR_CHECKOUT;
                break;
        }

        return opcion;
    }

    public static Huesped leerHuesped() throws IllegalArgumentException {
        System.out.println("Introduce los datos del nuevo huesped:");

        System.out.println("Nombre:");
        String nombre = Entrada.cadena();
        if (nombre == null) {
            throw new IllegalArgumentException("Nombre no v�lido.");
        }

        System.out.println("Telefono:");
        String telefono = Entrada.cadena();
        if (telefono == null) {
            throw new IllegalArgumentException("Tel�fono no v�lido.");
        }

        System.out.println("Correo:");
        String correo = Entrada.cadena();
        if (correo == null) {
            throw new IllegalArgumentException("Correo no v�lido.");
        }

        System.out.println("DNI:");
        String dni = Entrada.cadena();
        if (dni == null) {
            throw new IllegalArgumentException("DNI no v�lido.");
        }

        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento en formato 'dd/MM/yyyy':");
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de nacimiento no v�lida.");
        }

        return new Huesped(nombre, telefono, correo, dni, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni() {
        System.out.print("Introduce el DNI del hu�sped: ");
        String dni = Entrada.cadena();

        try {
            return new Huesped("Nombre Ficticio", "666777888", "correoficticio@gmail.com", dni, LocalDate.now().minusYears(20));
        } catch (IllegalArgumentException e) {
            System.out.println("DNI introducido no es v�lido. Por favor, int�ntalo de nuevo.");
            return getHuespedPorDni();
        }
    }

    public static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Reserva.FORMATO_FECHA_RESERVA);
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.println(mensaje);
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, formatter);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no v�lida. Por favor, introduce la fecha en el formato dd/MM/yyyy.");
            }
        }
        return fecha;
    }

    public static LocalDateTime leerFechaHora(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Reserva.FORMATO_FECHA_HORA_RESERVA);
        LocalDateTime fechaHora = null;
        boolean fechaHoraValida = false;
        while (!fechaHoraValida) {
            try {
                System.out.println(mensaje);
                String fechaHoraString = Entrada.cadena();
                fechaHora = LocalDateTime.parse(fechaHoraString, formatter);
                fechaHoraValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no v�lida. Por favor, introduce la fecha en el formato dd/MM/yyyy HH:hh.");
            }
        }
        return fechaHora;
    }

    public static Habitacion leerHabitacion() throws IllegalArgumentException {
        System.out.println("Introduce los datos de una nueva habitaci�n:");

        System.out.println("N�mero de planta (1 - 3):");
        int planta = Entrada.entero();
        if (planta < 1 || planta > 3) {
            throw new IllegalArgumentException("N�mero de planta no v�lido.");
        }

        System.out.println("N�mero de puerta (1 - 15):");
        int puerta = Entrada.entero();
        if (puerta < 1 || puerta > 15) {
            throw new IllegalArgumentException("N�mero de puerta no v�lido.");
        }

        System.out.println("Precio (40.0 - 150.0):");
        double precio = Entrada.realDoble();
        if (precio < 40.0 || precio > 150.0) {
            throw new IllegalArgumentException("Precio no v�lido.");
        }

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }


    public static Habitacion leerHabitacionPorIdentificador() throws IllegalArgumentException {
        int planta;
        int puerta;
        do {
            System.out.println("Introduce el n�mero de planta de la habitaci�n (1-3):");
            planta = Entrada.entero();
        } while (planta < 1 || planta > 3);

        do {
            System.out.println("Introduce el n�mero de puerta de la habitaci�n (1-15):");
            puerta = Entrada.entero();
        } while (puerta < 1 || puerta > 15);

        return new Habitacion(planta, puerta, 50.0, TipoHabitacion.TRIPLE);
    }


    public static TipoHabitacion leerTipoHabitacion() throws IllegalArgumentException {
        TipoHabitacion tipoHabitacion = null;
        int opcionTipoHab;
        do {
            System.out.println("Tipo de habitaci�n:\n1.- Suite\n2.- Simple\n3.- Doble\n4.- Triple");

            opcionTipoHab = Entrada.entero();

        } while (opcionTipoHab < 1 || opcionTipoHab > 4);

        switch (opcionTipoHab) {
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


    public static Regimen leerRegimen() {
        Regimen regimen = null;
        int opcionReg;
        do {
            System.out.println("Tipo de regimen:\n1.- Solo alojamiento\n2.- Alojamiento y desayuno\n3.- Media pensi�n\n4.- Pension completa");
            opcionReg = Entrada.entero();
        } while (opcionReg < 1 || opcionReg > 4);

        switch (opcionReg) {
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

    public static Reserva leerReserva() {
        int numeroPersonas = 0;
        LocalDate fechaInicioReserva;
        LocalDate fechaFinReserva;

        try {
            Huesped huesped = getHuespedPorDni();
            Habitacion habitacion = leerHabitacionPorIdentificador();
            Regimen regimen = leerRegimen();


            do {
                fechaInicioReserva = leerFecha("Introduce la fecha de inicio de la reserva:");
            } while (fechaInicioReserva.isBefore(LocalDate.now()));

            do {
                fechaFinReserva = leerFecha("Introduce la fecha de fin de la reserva:");

            } while (fechaFinReserva.isBefore(fechaInicioReserva));

            System.out.println("Numero de personas:");
            numeroPersonas = Entrada.entero();

            return new Reserva(huesped, habitacion, regimen, fechaInicioReserva, fechaFinReserva, numeroPersonas);

        } catch (IllegalArgumentException e) {
            System.out.println("Ha ocurrido un error al leer la reserva: " + e.getMessage());
            return null;
        }
    }

}
