package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Huespedes {

    private ArrayList<Huesped> coleccionHuespedes;

    public Huespedes() {
        this.coleccionHuespedes = new ArrayList<>();
    }

    public ArrayList<Huesped> get(){
        ArrayList<Huesped> copia= new ArrayList<>();
        for (Huesped huesped : coleccionHuespedes){
            copia.add(new Huesped(huesped));
        }
        return copia;
    }
    public int getTamano(){
        return coleccionHuespedes.size();
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede insertar un hu�sped nulo.");
        }
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("El hu�sped ya est� registrado y no se admiten repetidos.");
        }

        coleccionHuespedes.add(huesped);
    }

    public Huesped buscar(Huesped huesped) {
        int indice = coleccionHuespedes.indexOf(huesped);
            if (indice != -1) {
                return coleccionHuespedes.get(indice);
            }
        return null;
    }

    public void borrar(Huesped huesped) throws NoSuchElementException {
        if (!coleccionHuespedes.remove(huesped)) {
            throw new NoSuchElementException("El hu�sped a borrar no se encuentra en la colecci�n.");
        }
    }
}
