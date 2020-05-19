/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import MODELO.Packs;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 34679
 */
public class Packs_DAO {

    private PreparedStatement ps;

    public Packs_DAO() {

    }
//METODO DE INSERCIOÓN DE PACK
    public void insertarPack(Connection conexion, Packs pack) throws Exception {

        int x = 0;
        String consulta;
        System.out.println(pack.getFecha_pack());
        Date fecha_Pack_sql = java.sql.Date.valueOf(pack.getFecha_pack()); //NO DEJA INTRODUCIR LOCALDATE, se pasa a DATE hasta solucionar

        consulta = "INSERT INTO packs VALUES (?,?,?,?,?,?)";

        ps = conexion.prepareStatement(consulta);

        //PARAMETROS
        ps.setInt(1, pack.getIdPack());              // primer parametro
        ps.setInt(2, pack.getIdUsuario());  // segundo parÃ¡metro
        ps.setString(3, pack.getNombre_pack());  	 // tercer parÃ¡metro 
        ps.setString(4, pack.getDescripcion());
        ps.setDate(5, fecha_Pack_sql);
        ps.setInt(6, pack.getFavorito());

        // cuarto parÃ¡metro
        x += ps.executeUpdate();

        System.out.println(x + " filas insertadas");

    }

    public int buscariDReciente(Connection conexion) throws SQLException { 
        
        //METODO IMPORTANTE!, OFRECE ACTUALIZADA LA ID DE PACK MÁS ACTUAL, PARA EVITAR FALLOS DE PK EN BD
        int idPack = 0;
        
        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT MAX(idPack) FROM packs";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            while (rs.next()){ //avanza a la fila siguiente
 

            idPack = rs.getInt("MAX(idPack)");
            System.out.println(idPack);
            }
        }

        return idPack;

    }
}
