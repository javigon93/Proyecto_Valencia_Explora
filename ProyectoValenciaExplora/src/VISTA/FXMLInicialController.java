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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author 34679
 */
public class FXMLInicialController implements Initializable {
    
    private Label label;
    @FXML
    private Button botonEmpezar;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void alPulsarIniciar(ActionEvent event) {
        abreotraescena(event);
        
    }
    
    
     @FXML
    private void abreotraescena(ActionEvent event) {

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
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + ex.getMessage());
            alerta.showAndWait();
        }
    
}
}