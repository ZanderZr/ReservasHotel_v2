package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo alojamiento", 0),
    ALOJAMIENTO_DESAYUNO("Alojamiento mas desayuno", 15),
    MEDIA_PENSION("Media pensi�n", 30),
    PENSION_COMPLETA("Pensi�n completa", 50);

    private String descripcion;
    private double incrementoPrecio;

    Regimen(String descripcion, double incrementoPrecio){
        this.descripcion = descripcion;
        this.incrementoPrecio = incrementoPrecio;
    }

    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {
        return "Regimen{" +
                "descripcion='" + descripcion + '\'' +
                ", incrementoPrecio=" + incrementoPrecio +
                '}';
    }
}