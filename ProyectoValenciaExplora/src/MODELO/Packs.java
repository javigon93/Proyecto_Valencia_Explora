package MODELO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Modelo Packs
 * 
 * @author jfa
 */

public class Packs {
    private int idPack = 0;
    private int idUsuario;
    private String nombre;
    private String descripcion;
    private LocalDate fechaPack;
    private String favorito;
    private Set<Actividades> actividades = new HashSet<>();
    
    public Packs() {
    }

    public Set<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(Actividades act) {
        actividades.add(act);
    }

    @Override
    public String toString() {
        return "Pack: " + "nº: " + idPack + ", nombre: " + nombre;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public LocalDate getFechaPack() {
        return fechaPack;
    }

    public void setFechaPack(LocalDate fechaPack) {
        this.fechaPack = fechaPack;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }
    
    

    
}

