package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reserva {

    public static final int MAX_NUMERO_MESES_RESERVA = 3;
    private static final int MAX_HORAS_POSTERIOR_CHECKOUT = 12;
    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy HH:mm";

    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio;
    private int numeroPersonas;

    public Reserva( Huesped huesped, Habitacion habitacion, Regimen regimen,
                    LocalDate fechaInicioReserva, LocalDate fechaFinReserva,
                    int numeroPersonas){
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        setPrecio();

    }

    public Reserva(Reserva reserva){
        setHuesped(reserva.huesped);
        setHabitacion(reserva.habitacion);
        setRegimen(reserva.regimen);
        setFechaInicioReserva(reserva.fechaInicioReserva);
        setFechaFinReserva(reserva.fechaFinReserva);
        setNumeroPersonas(reserva.numeroPersonas);
        setPrecio();

    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public double getPrecio() {
        return precio;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setRegimen(Regimen regimen) {
        this.regimen = regimen;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        LocalDate hoy = LocalDate.now();
        LocalDate maxFechaInicioReserva = hoy.plusMonths(MAX_NUMERO_MESES_RESERVA);

        if (fechaInicioReserva.isBefore(hoy)) {
            throw new IllegalArgumentException("La fecha de inicio de la reserva no puede ser anterior al día actual.");
        } else if (fechaInicioReserva.isAfter(maxFechaInicioReserva)) {
            throw new IllegalArgumentException("La fecha de inicio de la reserva no puede ser posterior a " + MAX_NUMERO_MESES_RESERVA + " meses a partir de ahora.");
        } else {
            this.fechaInicioReserva = fechaInicioReserva;
        }
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        if(fechaFinReserva.isBefore(fechaInicioReserva)){
            throw new IllegalArgumentException(("La fecha de fin de reserva no puede ser anterior a la fecha de reserva."));
        }
        this.fechaFinReserva = fechaFinReserva;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        if(checkIn.isBefore(fechaInicioReserva.atStartOfDay())){
            throw new IllegalArgumentException("El check-in no puede ser anterior al inicio de la reserva.");
        }
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("El check-out no puede ser anterior al check-in.");
        }
        if (checkOut.isAfter(fechaFinReserva.atTime(MAX_HORAS_POSTERIOR_CHECKOUT, 0))) {
            throw new IllegalArgumentException("El check-out debe hacerse como máximo a las " + MAX_HORAS_POSTERIOR_CHECKOUT + ":00 horas del día en que finaliza la reserva.");
        }
        this.checkOut = checkOut;
    }

    private void setPrecio() {
        if (habitacion == null || regimen == null || fechaInicioReserva == null || fechaFinReserva == null) {
            throw new IllegalArgumentException("Los datos de la reserva no están completos.");
        }

        double precioHabitacion = habitacion.getPrecio();
        double incrementoPrecio = regimen.getIncrementoPrecio();
        long numeroNoches = ChronoUnit.DAYS.between(fechaInicioReserva, fechaFinReserva);

        if (numeroNoches < 0) {
            throw new IllegalArgumentException("La fecha de inicio de la reserva debe ser anterior a la fecha de fin.");
        }

        if (numeroPersonas <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser mayor que cero.");
        }
        if(incrementoPrecio!= 0) {
            this.precio = ((incrementoPrecio * numeroPersonas) + precioHabitacion) * numeroNoches;
        } else {
            this.precio = (numeroPersonas + precioHabitacion) * numeroNoches;
        }
    }


    public void setNumeroPersonas(int numeroPersonas) {
        if (numeroPersonas > habitacion.getTipoHabitacion().getNumeroMaximoPersonas()) {
            throw new IllegalArgumentException("El número de personas supera el número máximo permitido para este tipo de habitación.");
        }
        this.numeroPersonas = numeroPersonas;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Reserva that = (Reserva) obj;
        return Objects.equals(habitacion, that.habitacion) &&
                Objects.equals(fechaInicioReserva, that.fechaInicioReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "huesped=" + huesped +
                ", habitacion=" + habitacion +
                ", regimen=" + regimen +
                ", fechaInicioReserva=" + fechaInicioReserva +
                ", fechaFinReserva=" + fechaFinReserva +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", precio=" + precio +
                ", numeroPersonas=" + numeroPersonas +
                '}';
    }
}
