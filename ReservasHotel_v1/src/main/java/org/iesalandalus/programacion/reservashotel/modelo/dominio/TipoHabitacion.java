package org.iesalandalus.programacion.reservashotel.modelo.dominio;
public enum TipoHabitacion {
    SUITE("Suite", 4),
    SIMPLE("Habitaci�n simple", 1),
    DOBLE("Habitaci�n doble", 2),
    TRIPLE("Habitaci�n triple", 3);

    private final String descripcion;
    private final int numeroMaximoPersonas;
    private String cadenaAMostrar;

    TipoHabitacion(String descripcion, int numeroMaximoPersonas) {
        this.descripcion = descripcion;
        this.numeroMaximoPersonas = numeroMaximoPersonas;
        this.cadenaAMostrar = descripcion + " - M�ximo " + numeroMaximoPersonas + " personas";
    }

    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
