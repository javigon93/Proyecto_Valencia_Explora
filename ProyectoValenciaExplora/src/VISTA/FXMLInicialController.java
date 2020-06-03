/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author 34679
 */
public class FXMLInicialController implements Initializable {

    private Alert alerta;
    private Label label;
    private Notifications notificacion;
    @FXML
    private Button botonEmpezar;
    @FXML
    private Button botonAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void alPulsarIniciar(ActionEvent event) { //al pulsar el botoón cambiamos a la primera escena importante

        abreEscenaActividades(event);

    }

    private void abreEscenaActividades(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("escenaActividades.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonEmpezar.getScene().getWindow();
            escenarioVentana.setTitle("Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearAvisoError("ERROR " + ex.getMessage());

        } catch (NullPointerException e) {
            crearAvisoError("ERROR EN EL ARCHIVO escenaActividades.fxml");

        }

    }

    private void abreEscenaAdmin(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("EscenaMantenimiento.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonAdmin.getScene().getWindow();
            escenarioVentana.setTitle("Administrador");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearAvisoError("ERROR " + ex.getMessage());

        } catch (NullPointerException e) {
            crearAvisoError("ERROR EN EL ARCHIVO EscenaMantenimiento.fxml");

        }

    }

    @FXML
    private void alPulsarAdmin(ActionEvent event) {

        abreEscenaAdmin(event);
    }

    private void crearAvisoError(String texto) {

        notificacion = Notifications.create()
                .text(texto)
                .title("Error")
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);

        notificacion.showError();

    }
}
