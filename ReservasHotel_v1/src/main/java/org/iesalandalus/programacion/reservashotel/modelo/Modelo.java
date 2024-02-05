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
        huespedes = new Huespedes();
        habitaciones = new Habitaciones();
        reservas = new Reservas();
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
            throw new OperationNotSupportedException("El hu�sped ya est� registrado en el sistema.");
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
            throw new OperationNotSupportedException("El hu�sped a borrar no existe.");
        }
        huespedes.borrar(huesped);
    }

    public ArrayList<Huesped> getHuespedes() {
        ArrayList<Huesped> copia = new ArrayList<>();
        for (Huesped huesped : huespedes.get()) {
            copia.add(new Huesped(huesped));
        }
        return copia;
    }


    // Metodos para la gestion de Habitacion:
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitaciones.buscar(habitacion) == null) {
            habitaciones.insertar(habitacion);
        } else {
            throw new OperationNotSupportedException("La habitacion ya est� registrada en el sistema.");
        }
    }

    public Habitacion buscar(Habitacion habitacion) throws NoSuchElementException {
        Habitacion habitacionEncontrada = habitaciones.buscar(habitacion);
        if (habitacionEncontrada == null) {
            throw new NoSuchElementException("La habitaci�n buscada no existe.");
        }
        return habitacionEncontrada;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitaciones.buscar(habitacion) == null) {
            throw new OperationNotSupportedException("La habitaci�n a borrar no existe.");
        }
        habitaciones.borrar(habitacion);
    }

    public ArrayList<Habitacion> getHabitaciones() {
        ArrayList<Habitacion> copia = new ArrayList<>();
        for (Habitacion habitacion : habitaciones.get()) {
            copia.add(new Habitacion(habitacion));
        }
        return copia;
    }

    public ArrayList<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) {
        ArrayList<Habitacion> copia = new ArrayList<>();
            for (Habitacion habitacion : habitaciones.get()) {
                if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                    copia.add(new Habitacion(habitacion));  // Usar el constructor de copia
                }
            }
            return copia;  // Devolver array del tama�o correcto
        }

    // Metodos para la gestion de Reserva:
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reservas.buscar(reserva) == null) {
            reservas.insertar(reserva);
        } else {
            throw new OperationNotSupportedException("La reserva ya est� registrada en el sistema.");
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
    public ArrayList<Reserva> getReservas() {
            ArrayList<Reserva> copia = new ArrayList<>();
            for (Reserva reserva : reservas.get()) {
                copia.add(new Reserva(reserva));
            }
            return copia;
    }
    public ArrayList<Reserva> getReservas(Huesped huesped){
        return reservas.getReservas(huesped);
    }

    public ArrayList<Reserva> getReservas(TipoHabitacion tipoHabitacion){
        return reservas.getReservas(tipoHabitacion);
    }

    public ArrayList<Reserva> getReservasFuturas(Habitacion habitacion){
       return reservas.getReservasFuturas(habitacion);
    }

   public void realizarCheckin (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckin(reserva, fecha);
   }
   public void realizarCheckout (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckout(reserva, fecha);
   }
}
