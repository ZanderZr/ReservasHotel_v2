package main.java.org.iesalandalus.programacion.reservashotel.negocio;

import main.java.org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import main.java.org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import main.java.org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import main.java.org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;

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
        if (reserva != null && buscar(reserva) == null) {
            coleccionReservas[tamano++] = reserva;
        }
    }

    public Reserva buscar(Reserva reserva) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return coleccionReservas[i];
            }
        }
        return null;
    }

    public void borrar(Reserva reserva) {
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private int buscarIndice(Reserva reserva) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice) {
        return tamano > indice;
    }

    private boolean capacidadSuperada(int indice) {
        return tamano > capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
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
        return Arrays.copyOf(reservasFuturas, contador);
    }


}
