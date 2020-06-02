/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author 34679
 */
public class DetallePacks {

    private int idPack;
    private int numeroLinea = 0;
    private int idActividad;
    private double precio;
    private int personas;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalTime duracion;

    public DetallePacks() {

        duracion=LocalTime.parse("00:00:00");
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }


    @Override

    public String toString() {
        //toString que se implementará posteriormente
        String respuesta;
        respuesta = "Precio de la Actividad: " + precio + "\\ Personas: " + personas + "\\Fecha de la Actividad: " + fechaInicio + "Duración: " + duracion;

        return respuesta;
    }

    public long calcularDiasActividad() { //nos dice como cuantos dias hay entre ambas fechas, útil para mostrar el valor en la apliacación y calcular

        long diasActividad = ChronoUnit.DAYS.between(fechaInicio, fechaFin);

        return diasActividad;
    }

    public double calcularPrecioIndividual(int codigoTipo) {

         double precioFinal;
        if (codigoTipo==3) {
            
        
        //precio final de cada ACTIVIDAD DE MANERA INDIVIDUAL, ya que se introduce el valor por individuo y dia, estos dos ultimos datos tambien hay que tenerlos en cuenta
       

        precioFinal = (double) (precio * personas) * calcularDiasActividad();
        }
        
        else{
        
        precioFinal=(double) (precio*personas);
        
        }
        return precioFinal;

    }

}
