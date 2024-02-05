package main.java.org.iesalandalus.programacion.reservashotel;

import main.java.org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import main.java.org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import main.java.org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import main.java.org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import main.java.org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import main.java.org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import main.java.org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import main.java.org.iesalandalus.programacion.reservashotel.vista.Consola;
import main.java.org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;


import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainApp {

    public static final int CAPACIDAD = 45;
    private Huespedes huespedes = new Huespedes(CAPACIDAD);
    private Habitaciones habitaciones = new Habitaciones(CAPACIDAD);
    private Reservas reservas = new Reservas(CAPACIDAD);

    public static void main(String[] args) {
        MainApp app = new MainApp();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            app.ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
        System.out.println("Gracias por utilizar nuestra aplicación. ¡Hasta pronto!");
    }


    private void ejecutarOpcion(Opcion opcion) {

        switch (opcion) {
            case SALIR -> {
                System.out.println("Cerrando la aplicación...");
                System.exit(0);
                break;
            }
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPEDES -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> {

                TipoHabitacion tH = Consola.leerTipoHabitacion();
                LocalDate fechaI = Consola.leerFecha("Fecha de inicio de reserva:");
                LocalDate fechaF = Consola.leerFecha("Fecha de fin de reserva:");

                consultarDisponibilidad(tH, fechaI, fechaF);
            }

        }
    }


    private void insertarHuesped() {
        Huesped nuevoHuesped = Consola.leerHuesped();
        if (huespedes.buscar(nuevoHuesped) == null) {
            try {
                huespedes.insertar(nuevoHuesped);
                System.out.println("Huésped insertado correctamente.");
            } catch (OperationNotSupportedException e) {
                System.out.println("Error al insertar el huésped: " + e.getMessage());
            }
        } else {
            System.out.println("El huésped ya está registrado en el sistema.");
        }
    }


    private void buscarHuesped() {
        Huesped huespedFicticio = Consola.leerClientePorDni();
        Huesped huespedEncontrado = huespedes.buscar(huespedFicticio);
        if (huespedEncontrado != null) {
            System.out.println("Huésped encontrado: " + huespedEncontrado);
        } else {
            System.out.println("No se encontró ningún huésped con el DNI proporcionado.");
        }
    }

    private void borrarHuesped() {
        Huesped huespedFicticio = Consola.leerClientePorDni();
        Huesped huespedBorrado = huespedes.buscar(huespedFicticio);
        if (huespedBorrado != null) {
            huespedes.borrar(huespedBorrado);
            System.out.println("Huésped borrado: " + huespedBorrado);
        } else {
            System.out.println("No se encontró ningún huésped con el DNI proporcionado.");
        }
    }


    public void mostrarHuespedes() {
        if (huespedes.getTamano() == 0) {
            System.out.println("No hay huéspedes almacenados.");
        } else {
            System.out.println("Lista de huéspedes almacenados:");
            for (Huesped huesped : huespedes.get()) {
                System.out.println(huesped.toString());
            }
        }
    }

    private void insertarHabitacion() {
        Habitacion nuevaHabitacion = Consola.leerHabitacion();

        if( habitaciones.buscar(nuevaHabitacion) == null){
            try{
                habitaciones.insertar(nuevaHabitacion);
                System.out.println("Habitacion insertada correctamente.");
            } catch (OperationNotSupportedException e) {
                System.out.println("Error al insertar la habitacion: " + e.getMessage());
            }
        } else {
            System.out.println("La habitacion ya está registrada en el sistema.");
        }
    }

    private void buscarHabitacion() {
        Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
        Habitacion habitacionEncontrada = habitaciones.buscar(habitacionFicticia);
        if(habitacionEncontrada != null){
            System.out.println("Habitación encontrada: " + habitacionEncontrada);
        } else {
            System.out.println("No se encontro ninguna habitacion con el identificador proporcionado.");
        }
    }

    private void borrarHabitacion() {
        Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
        Habitacion habitacionBorrada = habitaciones.buscar(habitacionFicticia);
        if(habitacionBorrada != null){
            habitaciones.borrar(habitacionBorrada);
            System.out.println("Habitacion borrada: " + habitacionBorrada);
        } else{
            System.out.println("No se encontro ninguna habitacion con el identificador proporcionado.");
        }
    }

    private void mostrarHabitaciones() {
        if (habitaciones.getTamano() == 0) {
            System.out.println("No hay habitaciones almacenadas.");
        } else {
            System.out.println("Lista de habitaciones almacenadas:");
            for (Habitacion habitacion : habitaciones.get()) {
                System.out.println(habitacion.toString());
            }
        }
    }

    private void insertarReserva() {
        Reserva nuevaReserva = Consola.leerReserva();
        Habitacion habitacionDeseada = nuevaReserva.getHabitacion();
        Habitacion habitacionDisponible = consultarDisponibilidad(habitacionDeseada.getTipoHabitacion(), nuevaReserva.getFechaInicioReserva(), nuevaReserva.getFechaFinReserva());

        if (habitacionDisponible != null) {
            nuevaReserva.setHabitacion(habitacionDisponible);
            if (reservas.buscar(nuevaReserva) == null) {
                try {
                    reservas.insertar(nuevaReserva);
                    System.out.println("Reserva insertada correctamente.");
                } catch (OperationNotSupportedException e) {
                    System.out.println("Error al insertar la reserva: " + e.getMessage());
                }
            } else {
                System.out.println("La reserva ya está registrada en el sistema.");
            }
        } else {
            System.out.println("No hay disponibilidad para el tipo de habitación deseada en el periodo indicado.");
        }
    }


    private void listarReservas(Huesped huesped) {
        boolean hayReservas = false;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHuesped().equals(huesped)) {
                if (!hayReservas) {
                    System.out.println("Lista de reservas del huésped:");
                    hayReservas = true;
                }
                System.out.println(reserva.toString());
            }
        }
        if (!hayReservas) {
            System.out.println("No hay reservas para el huésped indicado.");
        }
    }

    private void listarReservas(TipoHabitacion tipoHabitacion) {
        boolean hayReservas = false;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                if (!hayReservas) {
                    System.out.println("Lista de reservas para el tipo de habitación " + tipoHabitacion + ":");
                    hayReservas = true;
                }
                System.out.println(reserva.toString());
            }
        }
        if (!hayReservas) {
            System.out.println("No hay reservas para el tipo de habitación " + tipoHabitacion + ".");
        }
    }


    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular) {
        List<Reserva> reservasAnulables = new ArrayList<>();
        LocalDate hoy = LocalDate.now(); // Obtenemos la fecha actual.

        for (Reserva reserva : reservasAAnular) {
            if (reserva.getFechaInicioReserva().isAfter(hoy)) {
                // Si la fecha de inicio de la reserva aún no ha llegado, es anulable.
                reservasAnulables.add(reserva);
            }
        }

        // Convertimos la lista de reservas anulables a un array y lo devolvemos.
        return reservasAnulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        System.out.println("Anular su reserva:");
        Huesped huespedFicticio = Consola.leerClientePorDni();
        Reserva[] reservasAnulables = getReservasAnulables(reservas.getReservas(huespedFicticio));

        if (reservasAnulables.length == 0) {
            System.out.println("El huésped no tiene reservas anulables.");
        } else if (reservasAnulables.length == 1) {
            System.out.println("El huésped tiene una reserva anulable: " + reservasAnulables[0]);
            System.out.println("¿Desea anular esta reserva? (S/N):");
            String respuesta = Entrada.cadena();
            if (respuesta.equalsIgnoreCase("S")) {
                reservas.borrar(reservasAnulables[0]);
                System.out.println("La reserva ha sido anulada.");
            } else {
                System.out.println("Anulación cancelada.");
            }
        } else {
            System.out.println("El huésped tiene varias reservas anulables:");
            for (int i = 0; i < reservasAnulables.length; i++) {
                System.out.println((i + 1) + ".- " + reservasAnulables[i]);
            }
            System.out.println("Introduce el número de la reserva que deseas anular:");
            int indice = Entrada.entero() - 1;
            if (indice >= 0 && indice < reservasAnulables.length) {
                reservas.borrar(reservasAnulables[indice]);
                System.out.println("La reserva seleccionada ha sido anulada.");
            } else {
                System.out.println("Número de reserva no válido.");
            }
        }
    }


    public void mostrarReservas() {
        if (reservas.getTamano() == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            System.out.println("Lista de todas las reservas almacenadas:");
            for (Reserva reserva : reservas.get()) {
                System.out.println(reserva.toString());
            }
        }
    }


    private Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Habitacion habitacion : habitaciones.get()) {
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                boolean estaDisponible = true;
                for (Reserva reserva : reservas.get()) {
                    if (reserva.getHabitacion().equals(habitacion) &&
                            !reserva.getFechaFinReserva().isBefore(fechaInicio) &&
                            !reserva.getFechaInicioReserva().isAfter(fechaFin)) {
                        estaDisponible = false;
                        break;
                    }
                }
                if (estaDisponible) {
                    return habitacion;
                }
            }
        }
        System.out.println("Esa habitacion no está disponible.");
        return null;
    }

}


