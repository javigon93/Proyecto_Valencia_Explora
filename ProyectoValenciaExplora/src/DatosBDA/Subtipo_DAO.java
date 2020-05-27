/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import MODELO.Subtipo;
import MODELO.Tipo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 34679
 */
public class Subtipo_DAO {
    
     private PreparedStatement ps;
    
     public Set<Subtipo> buscarSubTiposPorTipo(Connection conexion, int tipo) throws SQLException, IOException{
//BUSQUEDA ACTIVIDADES DE LA BD CON INNER JOIN PARA OBETENER LOS VALORES DE TIPO Y SUBTIPO EN FORMATO STRING
        Set<Subtipo> listaSubtipos = new HashSet<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT codigoSubtipo, nombre  FROM subtipo WHERE codigoTipo=?";
                    
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, tipo);
            rs = ps.executeQuery();

            while (rs.next()) {
                Subtipo subtipo = new Subtipo();
              
                
                //SE SETTEAN LOS VALORES OFRECIDOS POR LA BD
              
               subtipo.setCodigoSubtipo(rs.getInt("codigoSubtipo"));
               
               subtipo.setNombre(rs.getString("nombre")); //Y SE AÑADEN A LA COLECCIÓN DE ACTIVIDADES QUE SE INSERTARÁN EN LA TABLEVIEW
               listaSubtipos.add(subtipo);
               
            }
            
        

        }
        
    return listaSubtipos;
    
     }
    
    
}


