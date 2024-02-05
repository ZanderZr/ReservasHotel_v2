package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import javax.naming.OperationNotSupportedException;
import java.util.NoSuchElementException;

public class Habitaciones {

    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    public Habitaciones(int capacidad) {
        this.capacidad = capacidad;
        this.coleccionHabitaciones = new Habitacion[capacidad];
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copiaProfunda = new Habitacion[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaProfunda[i] = new Habitacion(coleccionHabitaciones[i]);
        }
        return copiaProfunda;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede insertar una habitación nula.");
        }
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("La habitación ya existe y no se admiten repetidos.");
        }
        if (tamanoSuperado()) {
            throw new OperationNotSupportedException("No se pueden insertar más habitaciones, se ha alcanzado la capacidad máxima.");
        }
        if (capacidadSuperada()) {
            throw new IllegalStateException("Se ha superado la capacidad máxima, esto no debería ocurrir.");
        }
        coleccionHabitaciones[tamano++] = habitacion;
    }

    public Habitacion buscar(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
                return coleccionHabitaciones[i];
            }
        }
        return null;
    }


    public void borrar(Habitacion habitacion) throws NoSuchElementException {
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        } else {
            throw new NoSuchElementException("La habitación proporcionada no se encuentra en la colección.");
        }
    }

    private int buscarIndice(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
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
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
    }
}
