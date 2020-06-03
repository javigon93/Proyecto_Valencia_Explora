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
    private void alClicar(ActionEvent event) { //método de carga de diferentes métodos dependeiendo del boton

        if (event.getSource() == botonATRAS) {

            abreEscenaInicial(event);

        }

        if (event.getSource() == botonGestionAct) {
            abreEscenaMantenimientoActividades(event);
        }

        if (event.getSource() == botonGestionPacks) {
            //no disponible por coronavirus
        }
        if (event.getSource() == botonGestionUsu) {
            //no disponible por coronavirus
        }
        if (event.getSource() == botonGestionTipSub) {
            //no disponible por coronavirus
        }

    }

    private void abreEscenaInicial(ActionEvent event) { //carga de escena inicial

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

        }

    }

    private void abreEscenaMantenimientoActividades(ActionEvent event) { //carga escena de añadir actividades

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

        } catch (NullPointerException e) {

            crearError("ERROR EN EL ARCHIVO /VISTA/escenaEleccionMantenimientoAct.fxml");

        }

    }

    private void crearError(String texto) { //método creacion notification error

        notificacion = Notifications.create()
                .text(texto)
                .title("Error")
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);

        notificacion.showError();

    }

}
