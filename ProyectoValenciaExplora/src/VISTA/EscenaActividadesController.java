/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;
import DatosBDA.Actividades_DAO;
import MODELO.Actividades;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaActividadesController implements Initializable {
    Connection conexion;
    Actividades_DAO bd_act;
    private ObservableList<Actividades> ListaActividades;
    @FXML
    private TableView<Actividades> tableActividades;
    @FXML
    private TableColumn<Actividades, Integer> columnaTipo;
    @FXML
    private TableColumn<Actividades, Integer> columnaNombre;
    @FXML
    private Pane paneDescripcion;
    @FXML
    private Button botonANADIR;
    private Label labelDescripcion;
    @FXML
    private TextArea areaDescripcion;
    @FXML
    private ImageView imagenActividad;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bd_act=new Actividades_DAO();
        try{
        
        
        paneDescripcion.setDisable(true);
        conectar();
        Set<Actividades> actividades= bd_act.buscarActividades(conexion);
        
        ListaActividades=FXCollections.observableArrayList(actividades);
        
            tableActividades.setItems(ListaActividades);
            tableActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        
        }
        catch(SQLException e){
            
            
            System.out.println(e.getMessage());
            
            
            
        }
    
    }
     
    
     public void conectar() throws SQLException {

        String bd = "esquema_proyecto_2.0";
        String usuario = "root";
        String password = "Gonzalez_Landete";
        String url = "jdbc:mysql://localhost:3306/" + bd + "?serverTimezone=UTC";

        conexion = DriverManager.getConnection(url, usuario, password);
         System.out.println("Conectado");
}

    @FXML
    private void alpulsarActividad(MouseEvent event) {
        Actividades actividadSeleccionada;
        
       actividadSeleccionada= tableActividades.getSelectionModel().getSelectedItem();
       Image img=new Image("/IMG/micalet.png");
        imagenActividad.setImage(img);
        paneDescripcion.setDisable(false);
        areaDescripcion.setWrapText(true);
        areaDescripcion.setText(actividadSeleccionada.getDescripcion());
        
        
    }
     
}
