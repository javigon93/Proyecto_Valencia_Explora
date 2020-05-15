/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import DatosBDA.Actividades_DAO;
import DatosBDA.Detallepacks_DAO;
import MODELO.Actividades;
import MODELO.DetallePacks;
import MODELO.Packs;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 34679
 */
public class EscenaActividadesController implements Initializable {
    public static Packs pack = new Packs();
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
    @FXML
    private TextArea areaDescripcion;
    @FXML
    private ImageView imagenActividad;
    @FXML
    private Spinner<Integer> numeroPlazas;
    // Value factory.
    final int initialValue = 1;
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10,
            initialValue);

    @FXML
    private DatePicker FechaInicio;
    @FXML
    private TextField idUsuario;
    @FXML
    private TextField precioActividad;

    @FXML
    private TextField idPack;
    @FXML
    private DatePicker FechaFinal;
    @FXML
    private TextField DiasTotales;
    private Connection conexion;
    private Actividades_DAO bd_act;
    private Actividades actividadSeleccionada;
    @FXML
    private TextField PrecioActividad;
    @FXML
    private Button botonVerPack;
    @FXML
    private Text numActEnPack;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final int initialValue = 1;
        // Value factory.
       /// SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10,
          //      initialValue);
        numeroPlazas.setValueFactory(valueFactory);
        idPack.setText(String.valueOf(1));
        idUsuario.setText(String.valueOf(1));
        paneDescripcion.setDisable(true);
    }

    @FXML
    private void alpulsarActividad(MouseEvent event) {
        actividadSeleccionada = tableActividades.getSelectionModel().getSelectedItem();
        Image img = new Image("/IMG/micalet.png");
        imagenActividad.setImage(img);
        paneDescripcion.setDisable(false);
        areaDescripcion.setWrapText(true);
        areaDescripcion.setText(actividadSeleccionada.getDescripcion());
    }

    @FXML
    private void AñadirActividad(ActionEvent event) {
       
       alertConfirmacionAnyadirActividad();
    }

    public void setConexion(Connection aConexion) {
        this.conexion = aConexion;
    }

    public void cargarDatos() throws SQLException {
        bd_act = new Actividades_DAO();
        Set<Actividades> actividades = bd_act.buscarActividades(conexion);

        ListaActividades = FXCollections.observableArrayList(actividades);

        tableActividades.setItems(ListaActividades);
        tableActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    @FXML
    private void CalcularDias(ActionEvent event) {
        int dias = 0;
        LocalDate fechaInicio = FechaInicio.getValue();
        LocalDate fechaFinal = FechaFinal.getValue();
        if (fechaInicio != null && fechaFinal != null) {
            dias = Period.between(fechaInicio, fechaFinal).getDays();
        }
        DiasTotales.setText(String.valueOf(dias));
    }
    public void anyadirAct() {
        Actividades act = tableActividades.getSelectionModel().getSelectedItem();
        act.setDp(crearDetallePacks());
        pack.setActividades(act);

    }
    public void alertConfirmacionAnyadirActividad() {
        /*
        AQUI BASICAMENTE ES EL ALERT ANTES DE ACEPTAR UNA ACTIVIDAD
        SI LE DAS A ACEPTAR
        ANYADE LA ACTIVIDAD EL SET DE ACTIVIDADES, Y TE SUMA EL NUMERITO QUE TE PONE ARRIBA DE LA APLICACION CON EL Nº DE ACTIVIDADES EN TU PACK
        */
        boolean respConfirmacion = false;
        int numAct = 0;
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAlerta.setTitle("Explora Valencia");
        dialogoAlerta.setHeaderText("Usted esta a punto de añadir una actividad a su pack");
        dialogoAlerta.setContentText("¿Está seguro de que quiere proceder con la acción?");

        Optional<ButtonType> respuestaUsuario = dialogoAlerta.showAndWait();

        if (respuestaUsuario.isPresent()) {
            if (respuestaUsuario.get() == ButtonType.OK) {
                
                respConfirmacion = true;
                anyadirAct();
                numAct = Integer.parseInt(numActEnPack.getText()) + 1;
                numActEnPack.setText(String.valueOf(numAct));
        
            } else if (respuestaUsuario.get() == ButtonType.CANCEL) {
                respConfirmacion = false;
            } else {
                respConfirmacion = false;
            }
        }
    }

    @FXML
    private void verVentanaPack(ActionEvent event) {
        abreotraescena(event);
    }

    private void abreotraescena(ActionEvent event) {

        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLescenaPack.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            FXMLescenaPackController controller = loader.< FXMLescenaPackController > getController();
            controller.setConexion( conexion ); 
            Stage escenarioVentana = (Stage) botonVerPack.getScene().getWindow();
            escenarioVentana.setTitle("Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));
        }
        catch( Exception ex )
        {
            Alert alerta = new Alert( Alert.AlertType.ERROR );
            alerta.setContentText( "ERROR " + ex.getMessage() );
            alerta.showAndWait();
        }

    }
    
    private DetallePacks crearDetallePacks(){
       Detallepacks_DAO dp_DAO = new Detallepacks_DAO(conexion);
       DetallePacks dp = new DetallePacks();
       dp.setIdPack(Integer.valueOf(idPack.getText()));
       dp.setActividad(actividadSeleccionada);
       dp.setPrecioActividad(Double.valueOf(precioActividad.getText()));
       dp.setNumeroPlazas(numeroPlazas.getValue());
       dp.setFechaInicio(FechaInicio.getValue());
       dp.setFechaFinal(FechaFinal.getValue());
       dp.setDuracion(LocalTime.now());
    return dp;}
    
}
