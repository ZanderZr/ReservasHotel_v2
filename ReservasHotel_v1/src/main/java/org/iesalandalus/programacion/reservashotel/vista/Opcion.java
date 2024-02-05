package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("1.- Salir"),
    INSERTAR_HUESPED("2.- Insertar huesped"),
    BUSCAR_HUESPED("3.- Buscar huesped"),
    BORRAR_HUESPED("4.- Borrar huesped"),
    MOSTRAR_HUESPEDES("5.- Mostrar huespedes"),
    INSERTAR_HABITACION("6.- Insertar habitacion"),
    BUSCAR_HABITACION("7.- Buscar habitacion"),
    BORRAR_HABITACION("8.- Borrar habitacion"),
    MOSTRAR_HABITACIONES("9.- Mostrar habitaciones"),
    INSERTAR_RESERVA("10.- Insertar reserva"),
    ANULAR_RESERVA("11.- Anular reserva"),
    MOSTRAR_RESERVAS("12.- Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD("13.- Consultar disponibilidad"),
    REALIZAR_CHECKIN("14.- Realizar check-in"),
    REALIZAR_CHECKOUT("15.- Realizar check-out");

    private String mensajeAMostrar;

    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar = mensajeAMostrar;
    }

    @Override
    public String toString() {
        return mensajeAMostrar;
    }
}
