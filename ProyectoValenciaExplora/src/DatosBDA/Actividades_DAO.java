/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosBDA;

import MODELO.Actividades;
import MODELO.Packs;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
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

    PreparedStatement ps;

    public Set<Actividades> buscarActividades(Connection conexion) throws SQLException, IOException {
//BUSQUEDA ACTIVIDADES DE LA BD CON INNER JOIN PARA OBETENER LOS VALORES DE TIPO Y SUBTIPO EN FORMATO STRING
        Set<Actividades> listaActividades = new HashSet<>();

        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT a.nombre,t.nombre AS 'tipo', a.descripcion,a.codigoSubtipo, s.nombre AS 'subtipo', a.idActividad, a.url, a.Imagen "
                    + "FROM tipo t "
                    + "INNER JOIN subtipo s INNER JOIN actividades a "
                    + "ON t.codigotipo=s.codigotipo AND s.codigoSubtipo= a.codigosubtipo";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {

                Blob blob = rs.getBlob("a.Imagen");
                byte[] data = blob.getBytes(1, (int) blob.length());
                BufferedImage B_img = ImageIO.read(new ByteArrayInputStream(data));

                Image image = SwingFXUtils.toFXImage(B_img, null);

                //SE SETTEAN LOS VALORES OFRECIDOS POR LA BD
                Actividades actividad = new Actividades();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setTipo(rs.getString("tipo"));
                actividad.setDescripcion(rs.getString("a.descripcion"));
                actividad.setSubtipo(rs.getString("subtipo"));
                actividad.setNombre(rs.getString("a.nombre"));
                actividad.setURL(rs.getString("a.url"));
                actividad.setImagen(image);
                actividad.setCodigoSubtipo(rs.getInt("a.codigoSubtipo"));
                listaActividades.add(actividad); //Y SE AÑADEN A LA COLECCIÓN DE ACTIVIDADES QUE SE INSERTARÁN EN LA TABLEVIEW

            }

        }

        return listaActividades;
    }

    public void insertarActividad(Connection conexion, Actividades actividad) throws SQLException, IOException {

        int x = 0;
        String consulta;
        File archivo = new File(actividad.getURL_IMAGEN());
        FileInputStream input = new FileInputStream(archivo);

        consulta = "INSERT INTO actividades VALUES (?,?,?,?,?,?)";

        ps = conexion.prepareStatement(consulta);

        //PARAMETROS
        ps.setInt(1, actividad.getIdActividad());              // primer parametro
        ps.setInt(2, actividad.getCodigoSubtipo());  // segundo parÃ¡metro
        ps.setString(3, actividad.getNombre());  	 // tercer parÃ¡metro 
        ps.setString(4, actividad.getDescripcion());
        ps.setString(5, actividad.getURL());
        ps.setBlob(6, input);

        // cuarto parÃ¡metro
        x += ps.executeUpdate();

        System.out.println(x + " filas insertadas");

    }

    public void actualizarActividad(Connection conexion, Actividades actividad) throws SQLException, IOException {

        int x = 0;
        String consulta;
        FileInputStream input = null;
        System.out.println(actividad.getCodigoSubtipo());
        if (actividad.getURL_IMAGEN() == null) {

            consulta = "UPDATE actividades SET codigoSubtipo=?, nombre=?, descripcion=?, url=? WHERE idActividad=?";
            ps = conexion.prepareStatement(consulta);
            ps.setInt(1, actividad.getCodigoSubtipo());
            ps.setString(2, actividad.getNombre());
            ps.setString(3, actividad.getDescripcion());
            ps.setString(4, actividad.getURL());
            ps.setInt(5, actividad.getIdActividad());

        } else {
            File archivo = new File(actividad.getURL_IMAGEN());
            input = new FileInputStream(archivo);
            consulta = "UPDATE actividades SET codigoSubtipo=?, nombre=?, descripcion=?, url=?, Imagen=? WHERE idActividad=?";
            ps = conexion.prepareStatement(consulta);
            ps.setInt(1, actividad.getCodigoSubtipo());              // primer parametro
            ps.setString(2, actividad.getNombre());  // segundo parÃ¡metro
            ps.setString(3, actividad.getDescripcion());  	 // tercer parÃ¡metro 
            ps.setString(4, actividad.getURL());
            ps.setBlob(5, input);
            ps.setInt(6, actividad.getIdActividad());
        }

        //PARAMETROS
        // cuarto parÃ¡metro
        x += ps.executeUpdate();

        System.out.println(x + " filas modificadas");

    }

    public int buscarMAX_IDatividad(Connection conexion) throws SQLException {

        //METODO IMPORTANTE!, OFRECE ACTUALIZADA LA ID DE PACK MÁS ACTUAL, PARA EVITAR FALLOS DE PK EN BD
        int idPack = 0;

        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT MAX(idActividad) FROM actividades";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            while (rs.next()) { //avanza a la fila siguiente

                idPack = rs.getInt("MAX(idActividad)");
                System.out.println(idPack);
            }
        }

        return idPack;

    }

    public Set<Actividades> buscarActividadesPorTipo(Connection conexion, int codigoTipo) throws SQLException, IOException {
//BUSQUEDA ACTIVIDADES DE LA BD CON INNER JOIN PARA OBETENER LOS VALORES DE TIPO Y SUBTIPO EN FORMATO STRING
        Set<Actividades> listaActividades = new HashSet<>();

        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT a.nombre,t.nombre AS 'tipo', a.descripcion, s.nombre AS 'subtipo',a.codigoSubtipo, a.idActividad, a.url, a.Imagen FROM tipo t INNER JOIN subtipo s INNER JOIN actividades a ON t.codigotipo=s.codigotipo AND s.codigoSubtipo= a.codigosubtipo WHERE t.codigoTipo =?";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, codigoTipo);
            rs = ps.executeQuery();

            while (rs.next()) {

                Blob blob = rs.getBlob("a.Imagen");
                byte[] data = blob.getBytes(1, (int) blob.length());
                BufferedImage B_img = ImageIO.read(new ByteArrayInputStream(data));

                Image image = SwingFXUtils.toFXImage(B_img, null);

                //SE SETTEAN LOS VALORES OFRECIDOS POR LA BD
               Actividades actividad = new Actividades();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setTipo(rs.getString("tipo"));
                actividad.setDescripcion(rs.getString("a.descripcion"));
                actividad.setSubtipo(rs.getString("subtipo"));
                actividad.setNombre(rs.getString("a.nombre"));
                actividad.setURL(rs.getString("a.url"));
                actividad.setImagen(image);
                actividad.setCodigoSubtipo(rs.getInt("a.codigoSubtipo"));
                listaActividades.add(actividad); //Y SE AÑADEN A LA COLECCIÓN DE ACTIVIDADES QUE SE INSERTARÁN EN LA TABLEVIEW

            }

        }

        return listaActividades;
    }

    public Set<Actividades> buscarActividadesPorSubtipo(Connection conexion, int codigoSubtipo) throws SQLException, IOException {
//BUSQUEDA ACTIVIDADES DE LA BD CON INNER JOIN PARA OBETENER LOS VALORES DE TIPO Y SUBTIPO EN FORMATO STRING
        Set<Actividades> listaActividades = new HashSet<>();

        ResultSet rs;
        String consulta;

        if (conexion != null) {

            consulta = "SELECT a.nombre,t.nombre AS 'tipo', a.descripcion, s.nombre AS 'subtipo',a.codigoSubtipo, a.idActividad, a.url, a.Imagen FROM tipo t INNER JOIN subtipo s INNER JOIN actividades a ON t.codigotipo=s.codigotipo AND s.codigoSubtipo= a.codigosubtipo WHERE s.codigoSubtipo =?";
            ps = conexion.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, codigoSubtipo);
            rs = ps.executeQuery();

            while (rs.next()) {

                Blob blob = rs.getBlob("a.Imagen");
                byte[] data = blob.getBytes(1, (int) blob.length());
                BufferedImage B_img = ImageIO.read(new ByteArrayInputStream(data));

                Image image = SwingFXUtils.toFXImage(B_img, null);

               Actividades actividad = new Actividades();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setTipo(rs.getString("tipo"));
                actividad.setDescripcion(rs.getString("a.descripcion"));
                actividad.setSubtipo(rs.getString("subtipo"));
                actividad.setNombre(rs.getString("a.nombre"));
                actividad.setURL(rs.getString("a.url"));
                actividad.setImagen(image);
                actividad.setCodigoSubtipo(rs.getInt("a.codigoSubtipo"));
                listaActividades.add(actividad); //Y SE AÑADEN A LA COLECCIÓN DE ACTIVIDADES QUE SE INSERTARÁN EN LA TABLEVIEW
            }

        }

        return listaActividades;
    }

    public void borrarActividad(Connection conexion, Actividades actividad) throws SQLException, IOException {

        int x = 0;
        String consulta;
        

        consulta = "DELETE FROM actividades WHERE idActividad=?";

        ps = conexion.prepareStatement(consulta);

        //PARAMETROS
        ps.setInt(1, actividad.getIdActividad());              // primer parametro
        

        // cuarto parÃ¡metro
        x += ps.executeUpdate();

        System.out.println(x + " filas eliminadas");

    }

}
