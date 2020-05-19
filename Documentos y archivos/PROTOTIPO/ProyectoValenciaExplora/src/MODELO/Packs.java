/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.time.LocalDate;


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
    private int favorito;
   
    public Packs() {
       //CADA PACK TENDRÁ EL ID EN 0, ya que en la BD el valor el AUTOINCREMENTAL, el valor se sumará automáticamente, EL IDUSUARIO es 1 porque no tenemos aun control de  usuarios.
       idPack= 0;
       idUsuario=1;
       fecha_pack= LocalDate.now(); //obviamente la fecha pack es la actual porque se crea el mismo dia.
       favorito=0; //por defecto no es favorito.
        
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

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

   
    
    
    
}
