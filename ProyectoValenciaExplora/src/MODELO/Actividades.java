/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author 34679
 */
public class Actividades {
    
    
     private int idActividad;    
    private int codigoSubtipo;
    private String nombre;
    private String descripcion;
    private String URL;
    private String tipo;
    public int getIdActividad() {
        return idActividad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getCodigoSubtipo() {
        return codigoSubtipo;
    }

    public void setCodigoSubtipo(int codigoSubtipo) {
        this.codigoSubtipo = codigoSubtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
    
    
}