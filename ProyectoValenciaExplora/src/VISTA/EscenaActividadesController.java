/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import DatosBDA.Actividades_DAO;
import MODELO.Actividades;
import MODELO.DetallePacks;
import MODELO.Packs;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaActividadesController implements Initializable {

    private Connection conexion;
    private Actividades_DAO bd_act;
    private Actividades actividad_seleccionada;
    private DetallePacks detalleActividad;
    private Alert alerta;
   
    private ArrayList<DetallePacks> listaDetalleActividadesSeleccionadas=new ArrayList<>();
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
    @FXML
    private TableColumn<Actividades, Integer> columnaSubtipo;
    @FXML
    private Spinner<Integer> spinnerPersonas;
    @FXML
    private TextField textPrecio;
    @FXML
    private Button botonRevisarConfirmar;
    @FXML
    private DatePicker pickerInicio;
    @FXML
    private DatePicker pickerFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bd_act = new Actividades_DAO();
        actividad_seleccionada = new Actividades();
        detalleActividad = new DetallePacks();
        IntegerSpinnerValueFactory personas = new IntegerSpinnerValueFactory(1, 20, 1, 1);
        pickerInicio.setValue(LocalDate.now());
        pickerInicio.setEditable(false);
        pickerFin.setValue(LocalDate.now());
        pickerFin.setEditable(false);

        try {

            paneDescripcion.setDisable(true);
            conectar();
            Set<Actividades> actividades = bd_act.buscarActividades(conexion);

            ListaActividades = FXCollections.observableArrayList(actividades);

            tableActividades.setItems(ListaActividades);
            tableActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaSubtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));
            spinnerPersonas.setValueFactory(personas);
        } catch (SQLException e) {

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

        actividad_seleccionada = tableActividades.getSelectionModel().getSelectedItem();
        System.out.println(actividad_seleccionada.getIdActividad() + " " + actividad_seleccionada.getNombre() + " " + actividad_seleccionada.getTipo() + " " + actividad_seleccionada.getURL());
        Image img = new Image("/IMG/micalet.png");
        imagenActividad.setImage(img);
        paneDescripcion.setDisable(false);
        areaDescripcion.setWrapText(true);
        areaDescripcion.setText(actividad_seleccionada.getDescripcion());
        detalleActividad.setIdActividad(actividad_seleccionada.getIdActividad());
    }

    @FXML
    private void alPulsarAnadir(ActionEvent event) {

        if (detalleActividad.getPersonas() != 0 && detalleActividad.getFechaInicio() != null && detalleActividad.getFechaFin() != null) {

            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("Confirma tu Actividad");
            alerta.setContentText("¿Deseas añadir la actividad " + actividad_seleccionada.getNombre() + " a tu Pack?");

            Optional<ButtonType> respuestaUsuario = alerta.showAndWait();

            if (respuestaUsuario.get() == ButtonType.OK) {
                System.out.println("" +detalleActividad.getDuracion()+ detalleActividad.getFechaFin()+ detalleActividad.getFechaInicio()+" " + detalleActividad.getIdActividad()+"");
                listaDetalleActividadesSeleccionadas.add(detalleActividad);
            

            }

        } else {

            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Error!");
            alerta.setContentText("Introduce todos los datos antes de continuar");

            alerta.showAndWait();

        }

    }

    @FXML
    private void alPulsarPersonas(MouseEvent event) {
        int personas_actividad = spinnerPersonas.getValue();
        detalleActividad.setPersonas(personas_actividad);

    }

    @FXML
    private void alIntroducirPrecio(ActionEvent event) {
        double precioActividad = Double.parseDouble(textPrecio.getText());
        detalleActividad.setPrecio(precioActividad);
    }

    @FXML
    private void alPulsarRevisar(ActionEvent event) {
        abreotraescena(event);
    }

    private void abreotraescena(ActionEvent event) {

        try{
         FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/escenaRevisarConfirmar.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
             EscenaRevisarConfirmarController revisarControlador = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            revisarControlador.setActividadesEscogidas(listaDetalleActividadesSeleccionadas);
            revisarControlador.metodoEjecutaAlInicio();
            Stage escenarioVentana = (Stage) botonRevisarConfirmar.getScene().getWindow();
            escenarioVentana.setTitle("Revisar Y Confirmar");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root)); 
           
            
        } catch (Exception ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + ex.getMessage());
            alerta.showAndWait();
        }

    }

    @FXML
    private void alSeleccionarFechaInicio(ActionEvent event) {
        detalleActividad.setFechaInicio(pickerInicio.getValue());

    }

    @FXML
    private void alSeleccionarFechaFin(ActionEvent event) {

        detalleActividad.setFechaFin(pickerFin.getValue());
    }
}
