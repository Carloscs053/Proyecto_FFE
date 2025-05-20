package modelos;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Fichaje implements Comparable<Fichaje>{

    private int id;
    private String nombreTrabajador;
    private int codigoTrabajador;
    private String actividad;
    private Date fecha;
    private Time hora;

    public Fichaje(int id, String nombreTrabajador, int codigoTrabajador, String actividad, Date fecha, Time hora) {
        this.id = id;
        this.nombreTrabajador = nombreTrabajador;
        this.codigoTrabajador = codigoTrabajador;
        this.actividad = actividad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public int getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(int codigoTrabajador) {
        this.codigoTrabajador = codigoTrabajador;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return getNombreTrabajador() + " - " + getCodigoTrabajador() + " - "
                + getActividad() + " - " + getFecha() + " - " + getHora();
    }

    @Override
    public int compareTo(Fichaje o) {
        return o.getFecha().compareTo(this.getFecha());
    }
}
