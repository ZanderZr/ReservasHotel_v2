package main.java.org.iesalandalus.programacion.reservashotel.negocio;

import main.java.org.iesalandalus.programacion.reservashotel.dominio.Habitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {

    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    public Habitaciones(int capacidad){
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
        if (habitacion != null && buscar(habitacion) == null) {
            coleccionHabitaciones[tamano++] = habitacion;
        }
    }

    public Habitacion buscar(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
                return coleccionHabitaciones[i];
            }
        }
        return null;
    }

    public void borrar(Habitacion habitacion) {
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
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

    private boolean tamanoSuperado(int indice) {
        return tamano > indice;
    }

    private boolean capacidadSuperada(int indice) {
        return tamano > capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
    }
}
