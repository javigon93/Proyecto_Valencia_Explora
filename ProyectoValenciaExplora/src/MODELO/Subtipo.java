/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import javafx.scene.image.Image;

/**
 *
 * @author 34679
 */
public class Subtipo {
    
    private int codigoSubtipo;
    private int codigoTipo;
    private String nombre;
    private Image logo;

    public int getCodigoSubtipo() {
        return codigoSubtipo;
    }

    public void setCodigoSubtipo(int codigoSubtipo) {
        this.codigoSubtipo = codigoSubtipo;
    }

    public int getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(int codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
    
    @Override
    public String toString(){
    
    String respuesta;
    respuesta= nombre;
    
    return respuesta;
    
    
    
    }
    
    
}
