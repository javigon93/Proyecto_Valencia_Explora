/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import MODELO.Actividades;
import java.sql.Connection;

import MODELO.DetallePacks;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
/**
 *
 * @author 34679
 */
public class DetallePacks_DAO {
  
   private PreparedStatement ps;
    public DetallePacks_DAO() {
        
        
        
        
        
    }
   
    public void insertarDetallePack(Connection  conexion, ArrayList<DetallePacks> lista_detalle) throws Exception{
             //inserción DETALLEPACKS
        Packs_DAO bd_pack= new Packs_DAO();
        
            int ID=bd_pack.buscariDReciente(conexion);
            int linea=0;  
       
        int x=0;
        String consulta;
        
        for (int i = 0; i < lista_detalle.size(); i++) {
          
          linea++; //para cambiar de línea cada vez que se introduce una actividad de la coleecion, evitamos duplicaciones
          lista_detalle.get(i).setNumeroLinea(linea);
          lista_detalle.get(i).setIdPack(ID);
          Date fechaInicio= java.sql.Date.valueOf(lista_detalle.get(i).getFechaInicio());
          Date fechaFin= java.sql.Date.valueOf(lista_detalle.get(i).getFechaFin());
            System.out.println(fechaFin);
            System.out.println(fechaInicio);
          Time duracion_sql= java.sql.Time.valueOf(lista_detalle.get(i).getDuracion());
            System.out.println(duracion_sql);
          consulta = "INSERT INTO detallepacks VALUES (?,?,?,?,?,?,?,?)";

        ps = conexion.prepareStatement(consulta);

        //PARAMETROS
        ps.setInt(1, lista_detalle.get(i).getIdPack());              
        ps.setInt(2, lista_detalle.get(i).getNumeroLinea());  
        ps.setInt(3,lista_detalle.get(i).getIdActividad());  	 
        ps.setDouble(4, lista_detalle.get(i).getPrecio());
        ps.setInt(5, lista_detalle.get(i).getPersonas());
        ps.setDate(6, fechaInicio);
        ps.setDate(7, fechaFin);
        ps.setTime(8, duracion_sql);
        
      

         x += ps.executeUpdate();
        }
        System.out.println(x + " filas insertadas");
        conexion.close();

    
    
    
    
    
    }
    
    
    public void eliminarRegistroDetalle(Connection conexion, int id) throws SQLException, IOException {

        int x = 0;
        String consulta;
        

        consulta = "DELETE FROM detallepacks WHERE idActividad=?";

        ps = conexion.prepareStatement(consulta);

        //PARAMETROS
        ps.setInt(1, id);              // primer parametro
        

        // cuarto parÃ¡metro
        x += ps.executeUpdate();

        System.out.println(x + " filas eliminadas");

    }
    
}
