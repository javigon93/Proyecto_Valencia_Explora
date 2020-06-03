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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaMantenimientoController implements Initializable {

    Alert alerta;
    Connection conexion;
    Notifications notificacion;
    @FXML
    private Button botonGestionAct;
    @FXML
    private Button botonGestionUsu;
    @FXML
    private Button botonGestionPacks;
    private Button botonGestionTipSub;
    @FXML
    private Button botonATRAS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conectar();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void conectar() throws SQLException { //Típico método de conexión a BD, esta conexión se trasladará a diferentes métodos y escenas

        String bd = "esquema_proyecto_2.0";
        String usuario = "root";
        String password = "Gonzalez_Landete";
        String url = "jdbc:mysql://localhost:3306/" + bd + "?serverTimezone=UTC";

        conexion = DriverManager.getConnection(url, usuario, password);

        if (conexion != null) {

            System.out.println("Conectado");
        }
    }

    @FXML
    private void alClicar(ActionEvent event) {

        if (event.getSource() == botonATRAS) {

            abreEscenaInicial(event);

        }

        if (event.getSource() == botonGestionAct) {
            abreEscenaMantenimientoActividades(event);
        }

        if (event.getSource() == botonGestionPacks) {

        }
        if (event.getSource() == botonGestionUsu) {

        }
        if (event.getSource() == botonGestionTipSub) {

        }

    }

    private void abreEscenaInicial(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("FXMLInicial.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonATRAS.getScene().getWindow();
            escenarioVentana.setTitle("Inicio");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());
//            Alert alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR " + ex.getMessage());
//            alerta.showAndWait();
        }

    }

    private void abreEscenaMantenimientoActividades(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/escenaEleccionMantenimientoAct.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaEleccionMantenimientoActController controlador = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            controlador.setConexion(conexion);

            Stage escenarioVentana = (Stage) botonGestionAct.getScene().getWindow();
            escenarioVentana.setTitle("Gestión Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());
//             alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR " + ex.getMessage());
//            alerta.showAndWait();
        } catch (NullPointerException e) {
            
            crearError("ERROR EN EL ARCHIVO /VISTA/escenaEleccionMantenimientoAct.fxml");
//            alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR EN EL ARCHIVO /VISTA/escenaEleccionMantenimientoAct.fxml");
//            alerta.showAndWait();
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
