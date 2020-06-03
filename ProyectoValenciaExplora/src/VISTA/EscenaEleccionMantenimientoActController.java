/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaEleccionMantenimientoActController implements Initializable {

    private Notifications notificacion;
    Connection conexion;
    @FXML
    private Button botonATRAS;
    @FXML
    private Button botonAnadirAct;
    @FXML
    private Button botonModEliActividad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void alClicar(ActionEvent event) { //métodos para el paso escena ependiendo del botón que se pulse

        if (event.getSource() == botonATRAS) {

            abreEscenaMantenimiento(event);

        }

        if (event.getSource() == botonAnadirAct) {
            abreEscenaAnadirAct(event);
        }

        if (event.getSource() == botonModEliActividad) {

            abreEscenaModificarEliminarActividades(event);

        }

    }

    private void abreEscenaMantenimiento(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("EscenaMantenimiento.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonATRAS.getScene().getWindow();
            escenarioVentana.setTitle("Mantenimiento");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());

        }

    }

    private void abreEscenaAnadirAct(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/escenaMantenimientoActividades.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaMantenimientoActividadesController controlador = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            controlador.setConexion(conexion);
            controlador.metodoEjecutaAlInicio();

            Stage escenarioVentana = (Stage) botonAnadirAct.getScene().getWindow();
            escenarioVentana.setTitle("Añadir Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());

        } catch (NullPointerException e) {
            crearError("ERROR EN EL ARCHIVO /VISTA/escenaModificarBorrar.fxml");

        }

    }

    private void abreEscenaModificarEliminarActividades(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/escenaModificarBorrar.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaModificarBorrarController controlador = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            controlador.setConexion(conexion);
            controlador.metodoEjecutaAlInicio();
            //controlador.metodoEjecutaAlInicio();

            Stage escenarioVentana = (Stage) botonAnadirAct.getScene().getWindow();
            escenarioVentana.setTitle("Añadir Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {

            crearError("ERROR " + ex.getMessage());

        } catch (NullPointerException e) {
            crearError("ERROR EN EL ARCHIVO /VISTA/escenaModificarBorrar.fxml");

        }

    }

    private void crearError(String texto) {

        notificacion = Notifications.create()
                .text(texto)
                .title("Error")
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);

        notificacion.showError();

    }
}
