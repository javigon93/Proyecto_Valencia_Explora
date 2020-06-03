/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;


import MODELO.Tipo;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author 34679
 */
public class Tipo_DAO {
    
    public List<Tipo> buscarTipos(Connection conexion) throws SQLException, IOException{
//BUSQUEDA TIPOS DE LA BD EN FORMATO STRING
        List<Tipo> listaActividades = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT codigoTipo, nombre "
                    + "FROM tipo";
                    
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tipo tipo = new Tipo();
              
                
                //SE SETTEAN LOS VALORES OFRECIDOS POR LA BD
              
               tipo.setCodigoTipo(rs.getInt("codigoTipo"));
               tipo.setNombre(rs.getString("nombre")); //Y SE AÑADEN A LA COLECCIÓN DE TIPOS QUE SE INSERTARÁN EN LA TABLEVIEW
               listaActividades.add(tipo);
               
            }
            
        

        }
        
        
        return listaActividades;
}
}
