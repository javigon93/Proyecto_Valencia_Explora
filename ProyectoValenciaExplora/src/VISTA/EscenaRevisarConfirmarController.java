/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import MODELO.Actividades;
import MODELO.DetallePacks;
import MODELO.Packs;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaRevisarConfirmarController implements Initializable {
    Packs pack;
    @FXML
    private Button botonAtras;
    @FXML
    private Button botonConfirmar;
    @FXML
    private Label labelPrecioTotal;
    @FXML
    private TextArea areaResumen;
    @FXML
    private TextField fieldNombrePack;
    private ArrayList<DetallePacks> actividadesEscogidas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       imprimirResumen();
        
        
    }    

    public void setActividadesEscogidas(ArrayList<DetallePacks> actividadesEscogidas) {
        this.actividadesEscogidas = actividadesEscogidas;
    }
    
    
    
    private void abreotraescena(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("escenaActividades.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            //RECUPERAMOS EL STAGE EN EL QUE ESTAMOS
            Stage escenarioVentana = (Stage) botonAtras.getScene().getWindow();
            escenarioVentana.setTitle("Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root)); 
           
            
        } catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + ex.getMessage());
            alerta.showAndWait();
        }
    
}

    @FXML
    private void alPulsarAtras(ActionEvent event) {
        abreotraescena(event);
    }

    @FXML
    private void alPulsarConfirmar(ActionEvent event) {
    }
    
    private void imprimirResumen(){
    
        String texto="";
        
        
        for (int i = 0; i < actividadesEscogidas.size(); i++) {
            
            areaResumen.setText(actividadesEscogidas.get(i).getPersonas() + "\n");
            
        }
        
        
    
    
    
    
    
    }

    @FXML
    private void alIntroducirNombre(ActionEvent event) {
    }
}
