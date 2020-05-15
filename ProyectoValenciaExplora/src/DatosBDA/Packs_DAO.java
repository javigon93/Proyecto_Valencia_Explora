/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import VISTA.EscenaActividadesController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.LocalDate.now;

/**
 *
 * @author Alejandro Tinoco
 */
public class Packs_DAO {
        private Connection conexion;

    public Packs_DAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    /*este es el metodo para insertar un pack en la base de datos*/
    /*tal y como hizo juan con el insertar detallepacks le paso la clase Connectio como parametro*/
        public void insertarPackBD(Connection conexion) throws SQLException {
        int numPacks = cantidadPacks() + 1;
        LocalDate date = now();
        String fecha = String.valueOf(date);
        String sql = "INSERT INTO "
                + "packs (idPack,idUsuario,nombre,descripcion,fechaPack,favorito)"
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        
        ps.setInt(1, cantidadPacks() + 1);
        ps.setInt(2, 5);
        /*he puesto que el nombre sea Pack nºpack y coja el numero del pack como no tenemos forma de ponerle nombre esta ha sido mi solución*/
        ps.setString(3, "Pack nº: "+ numPacks);
        ps.setString(4, EscenaActividadesController.pack.getDescripcion());
        ps.setString(5, fecha);
        ps.setInt(6, 0);
        
        

        int i = ps.executeUpdate();
        
    
    }
        /*este es el metodo del count que raquel ha dicho que no es adecuado aun asi, me funciona*/
        public int cantidadPacks() throws SQLException {
            int cantPacks=0;
        if (conexion != null) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String consulta = "SELECT count(*) AS total FROM Packs";
                ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = ps.executeQuery();
                if(rs.next()){
                cantPacks = rs.getInt("total");
                }
                
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        }
        return cantPacks;
    }

    public Packs_DAO() {
    }
}
