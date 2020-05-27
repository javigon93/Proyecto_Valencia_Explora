/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaModificarBorrarController implements Initializable {
    Connection conexion;
    @FXML
    private Button botonATRAS;
    @FXML
    private Button botonGuardar;
    @FXML
    private ComboBox<?> comboAccion;
    @FXML
    private ComboBox<?> comboSubtipo;
    @FXML
    private Text textSubtipoAct;
    @FXML
    private TextField fieldNombre;
    @FXML
    private Text textNombre;
    @FXML
    private Text textDescripcion;
    @FXML
    private Button botonImagen;
    @FXML
    private Text textImagen;
    @FXML
    private Text textRuta;
    @FXML
    private TextField fieldURL;
    @FXML
    private Text textURL;
    @FXML
    private TextField fieldDescripción;
    @FXML
    private Text textTipoAct;
    @FXML
    private ComboBox<?> comboTipoActividad;
    @FXML
    private TableView<?> tableActividades;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        //Hace falta un ejecutar al inicio
    }    

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    @FXML
    private void alClicar(ActionEvent event) {
    }

    @FXML
    private void alSeleccionarAccion(ActionEvent event) {
    }

    @FXML
    private void alSeleccionarSubtipo(ActionEvent event) {
    }

    @FXML
    private void alInsertar(ActionEvent event) {
    }

    @FXML
    private void alSeleccionarTipo(ActionEvent event) {
    }
    
}
