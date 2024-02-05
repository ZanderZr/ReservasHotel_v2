package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Habitaciones {
    private ArrayList<Habitacion> coleccionHabitaciones;

    public Habitaciones() {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    // He unido la copia profunda y el get en uno
    public ArrayList<Habitacion> get() {
        ArrayList<Habitacion> copia = new ArrayList<>();
        for (Habitacion habitacion : coleccionHabitaciones) {
            copia.add(new Habitacion(habitacion));
        }
        return copia;
    }

    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede insertar una habitación nula.");
        }
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("La habitación ya existe y no se admiten repetidos.");
        }
        coleccionHabitaciones.add(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion) {
        int indice = coleccionHabitaciones.indexOf(habitacion);
        if (indice != -1) {
            return coleccionHabitaciones.get(indice);
        }
        return null;
    }

    public void borrar(Habitacion habitacion) throws NoSuchElementException {
        if (!coleccionHabitaciones.remove(habitacion)) {
            throw new NoSuchElementException("La habitación proporcionada no se encuentra en la colección.");
        }
    }

}
