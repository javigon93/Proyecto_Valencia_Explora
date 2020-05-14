package MODELO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Modelo detalle Packs
 * 
 * @author jfa
 */
public class DetallePacks
{

    private Integer idPack;
    private Integer numLinea;
    private Actividades actividad;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private Double precioActividad;
    private Integer numeroPlazas;
    private LocalTime duracion;

    public Integer getIdPack()
    {
        return idPack;
    }

    public void setIdPack( Integer idPack )
    {
        this.idPack = idPack;
    }

    public Integer getNumLinea()
    {
        return numLinea;
    }

    public void setNumLinea( Integer numLinea )
    {
        this.numLinea = numLinea;
    }

    public Actividades getActividad()
    {
        return actividad;
    }

    public void setActividad( Actividades actividad )
    {
        this.actividad = actividad;
    }

    public LocalDate getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio( LocalDate fechaInicio )
    {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal()
    {
        return fechaFinal;
    }

    public void setFechaFinal( LocalDate fechaFinal )
    {
        this.fechaFinal = fechaFinal;
    }

    public Double getPrecioActividad()
    {
        return precioActividad;
    }

    public void setPrecioActividad( Double precio )
    {
        this.precioActividad = precio;
    }

    public Integer getNumeroPlazas()
    {
        return numeroPlazas;
    }

    public void setNumeroPlazas( Integer numPlazas )
    {
        this.numeroPlazas = numPlazas;
    }

    public LocalTime getDuracion()
    {
        return duracion;
    }

    public void setDuracion( LocalTime duracion )
    {
        this.duracion = duracion;
    }

}
