/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author 34679
 */
public class Packs {
    
    private int idPack;
    private int idUsuario;
    private String nombre_pack;
    private String descripcion;
    private LocalDate fecha_pack;
    private boolean favorito;
    private ArrayList<Actividades> actividades_pack;
    public Packs() {
       
       idPack= idPack+1;
        idUsuario=1;
       fecha_pack= LocalDate.now();
       favorito=false;
        
    }

    public ArrayList<Actividades> getActividades_pack() {
        return actividades_pack;
    }

    public void setActividades_pack(ArrayList<Actividades> actividades_pack) {
        this.actividades_pack = actividades_pack;
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

    public String getNombre_pack() {
        return nombre_pack;
    }

    public void setNombre_pack(String nombre_pack) {
        this.nombre_pack = nombre_pack;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_pack() {
        return fecha_pack;
    }

    public void setFecha_pack(LocalDate fecha_pack) {
        this.fecha_pack = fecha_pack;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    
    public void introducirActividad(Actividades actividad){
    
    actividades_pack.add(actividad);
    
    
    }
    
    
}
