/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;
import MODELO.Actividades;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author 34679
 */
public class Actividades_DAO {
    
   
    

    

    public Set<Actividades> buscarActividades(Connection conexion) throws SQLException, IOException{
//BUSQUEDA ACTIVIDADES DE LA BD CON INNER JOIN PARA OBETENER LOS VALORES DE TIPO Y SUBTIPO EN FORMATO STRING
        Set<Actividades> listaActividades = new HashSet<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT a.nombre,t.nombre AS 'tipo', a.descripcion, s.nombre AS 'subtipo', a.idActividad, a.url, a.Imagen "
                    + "FROM tipo t "
                    + "INNER JOIN subtipo s INNER JOIN actividades a "
                    + "ON t.codigotipo=s.codigotipo AND s.codigoSubtipo= a.codigosubtipo";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {

                Blob blob = rs.getBlob("a.Imagen");
                byte[] data = blob.getBytes(1, (int)blob.length());
                BufferedImage B_img = ImageIO.read(new ByteArrayInputStream(data));
              
                Image image= SwingFXUtils.toFXImage(B_img, null);
                
                //SE SETTEAN LOS VALORES OFRECIDOS POR LA BD
                Actividades actividad = new Actividades();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setTipo(rs.getString("tipo"));
                actividad.setDescripcion(rs.getString("a.descripcion"));
                actividad.setSubtipo(rs.getString("subtipo"));
                actividad.setNombre(rs.getString("a.nombre"));
                actividad.setURL(rs.getString("a.url"));
                actividad.setImagen(image);
                listaActividades.add(actividad); //Y SE AÑADEN A LA COLECCIÓN DE ACTIVIDADES QUE SE INSERTARÁN EN LA TABLEVIEW

            }
            
        

        }
        
        
        return listaActividades;
}
}