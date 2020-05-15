/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;
import MODELO.Actividades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 34679
 */
public class Actividades_DAO {
    
   
    

    

    public Set<Actividades> buscarActividades(Connection conexion) throws SQLException {

        Set<Actividades> listaActividades = new HashSet<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT a.nombre,t.nombre AS 'tipo', a.descripcion, s.nombre AS 'subtipo', a.idActividad FROM tipo t INNER JOIN subtipo s INNER JOIN actividades a ON t.codigotipo=s.codigotipo AND s.codigoSubtipo= a.codigosubtipo";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {

                Actividades actividad = new Actividades();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setTipo(rs.getString("tipo"));
                actividad.setDescripcion(rs.getString("a.descripcion"));
                actividad.setSubtipo(rs.getString("subtipo"));
                actividad.setNombre(rs.getString("a.nombre"));
                listaActividades.add(actividad);

            }
            
        

        }
        
        
        return listaActividades;
}
}