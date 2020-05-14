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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

/**
 * @author 34679
 */
public class EscenaActividadesController implements Initializable {

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
        Detallepacks_DAO dp_DAO = new Detallepacks_DAO(conexion);
        DetallePacks dp = new DetallePacks();
        dp.setIdPack(Integer.valueOf(idPack.getText()));
        Random random = new Random();
        dp.setNumLinea(random.nextInt(100));
        dp.setActividad(actividadSeleccionada);
        dp.setPrecioActividad(Double.valueOf(precioActividad.getText()));
        //dp.setPrecioActividad(Double.valueOf(500));
        dp.setNumeroPlazas(numeroPlazas.getValue());
        dp.setFechaInicio(FechaInicio.getValue());
        dp.setFechaFinal(FechaFinal.getValue());
        dp.setDuracion(LocalTime.now());
        try {
            dp_DAO.insertarDetallePack(dp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
