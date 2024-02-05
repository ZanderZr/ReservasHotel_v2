package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Modelo {
    public static final int CAPACIDAD = 45;
    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    public Modelo() {
    }
    public void pruebas() throws OperationNotSupportedException {
        Huesped huesped1 = new Huesped("jojo", "666777666", "j0@gmail.com", "76660251D", LocalDate.now().minusYears(20));
        Huesped huesped2 = new Huesped("jaja", "666777667", "ja@gmail.com", "76660252X", LocalDate.now().minusYears(20));
        huespedes.insertar(huesped1);
        huespedes.insertar(huesped2);

        Habitacion habitacion1 = new Habitacion(1,1,50,TipoHabitacion.SIMPLE);
        Habitacion habitacion2 = new Habitacion(2,2,50,TipoHabitacion.DOBLE);
        habitaciones.insertar(habitacion1);
        habitaciones.insertar(habitacion2);

        Reserva reserva1 = new Reserva(huesped1, habitacion1, Regimen.PENSION_COMPLETA, LocalDate.now(), LocalDate.now().plusDays(1), 1);
        Reserva reserva2 = new Reserva(huesped1, habitacion2, Regimen.MEDIA_PENSION, LocalDate.now(), LocalDate.now().plusDays(1), 1);
        reservas.insertar(reserva1);
        reservas.insertar(reserva2);
    }
    public void comenzar() throws OperationNotSupportedException {
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
        pruebas();
    }

    public void terminar() {
        System.out.println("Info: El modelo ha terminado.");
    }

    // Metodos para la gestion de Huesped:
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huespedes.buscar(huesped) == null) {
            huespedes.insertar(huesped);
        } else {
            throw new OperationNotSupportedException("El huésped ya está registrado en el sistema.");
        }
    }

    public Huesped buscar(Huesped huesped) throws NoSuchElementException {
        Huesped huespedEncontrado = huespedes.buscar(huesped);
        if (huespedEncontrado == null) {
            throw new NoSuchElementException("El huesped buscado no existe.");
        }
        return huespedEncontrado;
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huespedes.buscar(huesped) == null) {
            throw new OperationNotSupportedException("El huésped a borrar no existe.");
        }
        huespedes.borrar(huesped);
    }

    public Huesped[] getHuespedes() {
        try {
            Huesped[] original = huespedes.get();
            Huesped[] copia = new Huesped[original.length];
            for (int i = 0; i < original.length; i++) {
                copia[i] = new Huesped(original[i]);
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener los huéspedes: " + e.getMessage());
            return null;
        }
    }

    // Metodos para la gestion de Habitacion:
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitaciones.buscar(habitacion) == null) {
            habitaciones.insertar(habitacion);
        } else {
            throw new OperationNotSupportedException("La habitacion ya está registrada en el sistema.");
        }
    }

    public Habitacion buscar(Habitacion habitacion) throws NoSuchElementException {
        Habitacion habitacionEncontrada = habitaciones.buscar(habitacion);
        if (habitacionEncontrada == null) {
            throw new NoSuchElementException("La habitación buscada no existe.");
        }
        return habitacionEncontrada;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitaciones.buscar(habitacion) == null) {
            throw new OperationNotSupportedException("La habitación a borrar no existe.");
        }
        habitaciones.borrar(habitacion);
    }

    public Habitacion[] getHabitaciones() {
        try {
            Habitacion[] original = habitaciones.get();
            Habitacion[] copia = new Habitacion[original.length];
            for (int i = 0; i < original.length; i++) {
                copia[i] = new Habitacion(original[i]);
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las habitaciones: " + e.getMessage());
            return null;
        }
    }

    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion) {
        try {
            Habitacion[] original = habitaciones.get();
            Habitacion[] copia = new Habitacion[original.length];
            int indice = 0;
            for (Habitacion habitacion : original) {
                if (habitacion.getTipoHabitacion() == tipoHabitacion) {
                    copia[indice++] = new Habitacion(habitacion);  // Usar el constructor de copia
                }
            }
            return copia;  // Devolver array del tamaño correcto
        } catch (Exception e) {
            System.out.println("Error al obtener las habitaciones: " + e.getMessage());
            return null;
        }
    }

    // Metodos para la gestion de Reserva:
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reservas.buscar(reserva) == null) {
            reservas.insertar(reserva);
        } else {
            throw new OperationNotSupportedException("La reserva ya está registrada en el sistema.");
        }
    }
    public Reserva buscar(Reserva reserva) throws NoSuchElementException {
        Reserva reservaEncontrada = reservas.buscar(reserva);
        if (reservaEncontrada == null) {
            throw new NoSuchElementException("La reserva buscada no existe.");
        }
        return reservaEncontrada;
    }
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reservas.buscar(reserva) == null) {
            throw new OperationNotSupportedException("La reserva a borrar no existe.");
        }
        reservas.borrar(reserva);
    }
    public Reserva[] getReservas() {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            for (int i = 0; i < original.length; i++) {
                copia[i] = new Reserva(original[i]);
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }
    public Reserva[] getReservas(Huesped huesped){
        return reservas.getReservas(huesped);
    }
    /*
    public Reserva[] getReservas(Huesped huesped) {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            int indice = 0;
            for (Reserva reserva : original) {
                if (reserva.getHuesped().equals(huesped)) {
                    copia[indice++] = new Reserva(reserva);
                }
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }
     */
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        return reservas.getReservas(tipoHabitacion);
    }
    /*
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            int indice = 0;
            for (Reserva reserva : original) {
                if (reserva.getHabitacion().getTipoHabitacion() == tipoHabitacion) {
                    copia[indice++] = new Reserva(reserva);
                }
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }
     */
    public Reserva[] getReservasFuturas(Habitacion habitacion){
       return reservas.getReservasFuturas(habitacion);
    }
   /* public Reserva[] getReservasFuturas(Habitacion habitacion) {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            int indice = 0;
            for (Reserva reserva : original) {
                if (reserva.getHabitacion() == habitacion) {
                    if(reserva.getFechaInicioReserva().isAfter(LocalDate.now())){
                    copia[indice++] = new Reserva(reserva);
                    }
                }
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }
    */

   public void realizarCheckin (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckin(reserva, fecha);
   }
   public void realizarCheckout (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckout(reserva, fecha);
   }
}
