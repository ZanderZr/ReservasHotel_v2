package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Reservas {

    private int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    public Reservas(int capacidad){
        this.capacidad = capacidad;
        this.coleccionReservas = new Reserva[capacidad];
    }

    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas() {
        Reserva[] copiaProfunda = new Reserva[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaProfunda[i] = new Reserva(coleccionReservas[i]);
        }
        return copiaProfunda;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public int getTamano() {
        return tamano;
    }
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede insertar una reserva nula.");
        }
        if (buscar(reserva) != null) {
            throw new OperationNotSupportedException("La reserva ya está registrada y no se admiten repetidos.");
        }
        if (tamanoSuperado()) {
            throw new OperationNotSupportedException("No se pueden insertar más reservas, se ha alcanzado la capacidad máxima.");
        }
        if (capacidadSuperada()) {
            throw new IllegalStateException("Se ha superado la capacidad máxima, esto no debería ocurrir.");
        }
        coleccionReservas[tamano++] = reserva;
    }

    public Reserva buscar(Reserva reserva) throws NoSuchElementException {
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return coleccionReservas[i];
            }
        }
        return null;
    }

    public void borrar(Reserva reserva) throws NoSuchElementException {
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        } else {
            throw new NoSuchElementException("La reserva a borrar no se encuentra en la colección.");
        }
    }

    private int buscarIndice(Reserva reserva) throws NoSuchElementException {
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano > capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        for (int i = indice; i < tamano - 1; i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
        }
    }

    public Reserva[] getReservas(Huesped huesped) {
        Reserva[] reservasHuesped = new Reserva[tamano];
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHuesped().equals(huesped)) {
                reservasHuesped[contador++] = new Reserva(coleccionReservas[i]);
            }
        }
        if (contador == 0) {
            throw new NoSuchElementException("No se encontraron reservas para el huésped proporcionado.");
        }
        return Arrays.copyOf(reservasHuesped, contador);
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        Reserva[] reservasTipoHabitacion = new Reserva[tamano];
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion[contador++] = new Reserva(coleccionReservas[i]);
            }
        }
        if (contador == 0) {
            throw new NoSuchElementException("No se encontraron reservas para el tipo de habitación proporcionado.");
        }
        return Arrays.copyOf(reservasTipoHabitacion, contador);
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        Reserva[] reservasFuturas = new Reserva[tamano];
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHabitacion().equals(habitacion) && coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturas[contador++] = new Reserva(coleccionReservas[i]);
            }
        }
        if (contador == 0) {
            throw new NoSuchElementException("No se encontraron reservas futuras para la habitación proporcionada.");
        }
        return Arrays.copyOf(reservasFuturas, contador);
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fechaHora) {
        try {
            Reserva reservaEncontrada = buscar(reserva);
            if (reservaEncontrada != null) {
                if (reservaEncontrada.getCheckIn() == null) {
                    reservaEncontrada.setCheckIn(fechaHora);
                    System.out.println("Checkin realizado con éxito: " + fechaHora.format(DateTimeFormatter.ofPattern(Reserva.FORMATO_FECHA_HORA_RESERVA)));
                } else {
                    System.out.println("Ya se ha realizado el check-in para esta reserva.");
                }
            } else {
                throw new NoSuchElementException("La reserva no se encuentra en la colección.");
            }
        } catch (Exception e) {
            System.out.println("Error al realizar el check-in: " + e.getMessage());
        }
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fechaHora) {
        try {
            Reserva reservaEncontrada = buscar(reserva);
            if (reservaEncontrada != null) {
                if (reservaEncontrada.getCheckIn() != null && reservaEncontrada.getCheckOut() == null) {
                    reservaEncontrada.setCheckOut(fechaHora);
                    System.out.println("Checkout realizado con éxito: " + fechaHora.format(DateTimeFormatter.ofPattern(Reserva.FORMATO_FECHA_HORA_RESERVA)));
                } else {
                    System.out.println("No se puede realizar el checkout sin haber hecho el checkin.");
                }
            } else {
                throw new NoSuchElementException("La reserva no se encuentra en la colección.");
            }
        } catch (Exception e) {
            System.out.println("Error al realizar el check-out: " + e.getMessage());
        }
    }

}
