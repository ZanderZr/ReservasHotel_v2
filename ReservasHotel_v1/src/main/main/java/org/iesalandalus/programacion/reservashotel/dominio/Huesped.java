package main.java.org.iesalandalus.programacion.reservashotel.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {

    private static final String ER_TELEFONO = "^\\d{9}$";
    private static final String ER_CORREO = "^([a-zA-Z0-9._%-]+)@([a-zA-Z0-9.-]+).([a-zA-Z]{2,6})$";
    private static final String ER_DNI = "(\\d{8})([A-Z])";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

    public void setNombre(String nombre) {
        if (nombre == null || !nombre.matches("^([A-Z][a-z]*)(\\s[A-Z][a-z]*)*$")) {
            nombre = formateaNombre(nombre);
        }
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("Teléfono no válido");
        }
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        if (correo == null || !correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("Correo no válido");
        }
        this.correo = correo;
    }

    public void setDni(String dni) {
        if (dni == null || !comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("DNI no válido");
        }
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDni() {
        return dni;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Huesped(String nombre, String telefono, String correo,
                   String dni, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setTelefono(telefono);
        setCorreo(correo);
        setDni(dni);
        this.fechaNacimiento = fechaNacimiento;
    }



    public Huesped(Huesped huesped){
        setNombre(huesped.nombre);
        setTelefono(huesped.telefono);
        setCorreo(huesped.correo);
        setDni(huesped.dni);
        this.fechaNacimiento = fechaNacimiento;
    }

    private static String formateaNombre(String nombre) {
        String[] palabras = nombre.trim().split("\\s+");
        StringBuilder nombreFormateado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                nombreFormateado.append(Character.toUpperCase(palabra.charAt(0)));
                nombreFormateado.append(palabra.substring(1).toLowerCase());
                nombreFormateado.append(" ");
            }
        }

        return nombreFormateado.toString().trim();
    }

    private boolean comprobarLetraDni(String dni) {
        Pattern pattern = Pattern.compile(ER_DNI);
        Matcher matcher = pattern.matcher(dni);

        if (matcher.matches()) {
            String numero = matcher.group(1);
            String letra = matcher.group(2);
            return true;
        } else {
            return false;
        }
    }

    public String getIniciales() {
        String[] palabras = nombre.split("\\s+");
        StringBuilder iniciales = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }

        return iniciales.toString().toUpperCase();
    }

    @Override
    public String toString() {
        return "Huesped{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Huesped huesped = (Huesped) obj;
        return dni.equals(huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

}
