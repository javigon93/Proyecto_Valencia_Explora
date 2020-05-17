/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import DatosBDA.Actividades_DAO;
import DatosBDA.DetallePacks_DAO;
import DatosBDA.Packs_DAO;
import MODELO.Actividades;
import MODELO.DetallePacks;
import MODELO.Packs;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaActividadesController implements Initializable {

    private Connection conexion;
    private Actividades_DAO bd_act= new Actividades_DAO();
    private Packs_DAO bd_packs=new Packs_DAO();
    private Actividades actividad_seleccionada= new Actividades();
    private DetallePacks detalleActividad= new DetallePacks();
    private Alert alerta;
    private final IntegerSpinnerValueFactory personas = new IntegerSpinnerValueFactory(0, 20, 0, 1);
    private ArrayList<DetallePacks> listaDetalleActividadesSeleccionadas = new ArrayList<>();
    private ObservableList<String> listaHoras= FXCollections.observableArrayList("00:30", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "12:00", "24:00" );
    private ObservableList<Actividades> listaActividades;
    private String texto = "";
    private DecimalFormat df = new DecimalFormat("#.00");
    private double total=0;
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
    @FXML
    private Button botonBorrar;

    @FXML
    private TextArea areaInfoActividad;
    @FXML
    private TextArea areaPrecio;
    @FXML
    private Text textPrecioTotal;
    @FXML
    private ComboBox<String> comboDuracion;
    @FXML
    private TextField fieldDuracion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          bd_act= new Actividades_DAO();
      bd_packs=new Packs_DAO();
      actividad_seleccionada= new Actividades();
      detalleActividad= new DetallePacks();

        try {
            
            panePorDefecto();
            conectar();
            
            Set<Actividades> actividades = bd_act.buscarActividades(conexion);
            comboDuracion.setItems(listaHoras);
            listaActividades = FXCollections.observableArrayList(actividades);
            tableActividades.setItems(listaActividades);
            tableActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaSubtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));
            spinnerPersonas.setValueFactory(personas);
           
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } catch(IOException ex){
        
            System.out.println(ex.getMessage());
        
        
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
        
        imagenActividad.setImage(actividad_seleccionada.getImagen());
        paneDescripcion.setDisable(false);
        areaDescripcion.setWrapText(true);
        areaDescripcion.setText(actividad_seleccionada.getDescripcion()+ "\n\nMás Información en: " + actividad_seleccionada.getURL());
        detalleActividad.setIdActividad(actividad_seleccionada.getIdActividad());
    }

    @FXML
    private void alPulsarAnadir(ActionEvent event) {

        if (detalleActividad.getPersonas() != 0 && detalleActividad.getFechaInicio() != null && detalleActividad.getFechaFin() != null && detalleActividad.getPrecio() !=0 && detalleActividad.getDuracion() != null) {

            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("Confirma tu Actividad");
            alerta.setContentText("¿Deseas añadir la actividad " + actividad_seleccionada.getNombre() + " a tu Pack?");

            Optional<ButtonType> respuestaUsuario = alerta.showAndWait();

            if (respuestaUsuario.get() == ButtonType.OK) {
               
                imprimirDetalleyPrecio();
                listaDetalleActividadesSeleccionadas.add(detalleActividad);
                detalleActividad = new DetallePacks();
                panePorDefecto();

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

        try {
            double precioActividad = Double.parseDouble(textPrecio.getText());
            detalleActividad.setPrecio(precioActividad);
        } catch (NumberFormatException e) {

            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Ha ocurrido un error");
            alerta.setContentText("Introduce un Número");
            alerta.showAndWait();

        }
    }

    @FXML
    private void alPulsarRevisar(ActionEvent event) {
        abreotraescena(event);
    }

    private void abreotraescena(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/escenaRevisarConfirmar.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaRevisarConfirmarController revisarControlador = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            revisarControlador.setActividadesEscogidas(listaDetalleActividadesSeleccionadas);
            revisarControlador.setConexion(conexion);
            revisarControlador.setPrecioTotal(total);
            revisarControlador.setTextoResumen(texto);
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

        if (detalleActividad.getFechaInicio().isBefore(pickerFin.getValue())) {

            detalleActividad.setFechaFin(pickerFin.getValue());
            fieldDuracion.setText(""+detalleActividad.calcularDiasActividad());
        } else{
        
        alerta= new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("Introduce una fecha Correcta");
        alerta.setContentText("La Fecha Fin debe de ser Posterior a la Fecha Inicio");
            
        alerta.showAndWait();
        
        
        
        }
    }

    private void panePorDefecto() {
        textPrecio.clear();
        paneDescripcion.setDisable(true);
        spinnerPersonas.setValueFactory(personas);
        pickerFin.setValue(LocalDate.now());
        pickerInicio.setValue(LocalDate.now());

        pickerInicio.setEditable(false);

        pickerFin.setEditable(false);
        fieldDuracion.clear();
    }

    @FXML
    private void alPulsarBorrar(ActionEvent event) {

        if (listaDetalleActividadesSeleccionadas.isEmpty() == false) {

            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("Confirma la Eliminación");
            alerta.setContentText("¿Deseas eliminar tu pack?");

            Optional<ButtonType> respuestaUsuario = alerta.showAndWait();

            if (respuestaUsuario.get() == ButtonType.OK) {
                areaDescripcion.clear();
                areaPrecio.clear();
                areaInfoActividad.clear();
                listaDetalleActividadesSeleccionadas.clear();
                panePorDefecto();
                texto="";
                total=0;
                textPrecioTotal.setText("Total: ");

            }

        }

    } private void imprimirDetalleyPrecio(){
        
         texto += "Actividad: " + actividad_seleccionada.getNombre() + " Número de Personas: " + detalleActividad.getPersonas() + "\nFecha Inicio: "
                        + detalleActividad.getFechaInicio() + "Fecha Final: " + detalleActividad.getFechaFin() + "\n\n";

                areaInfoActividad.setText(texto);
                areaPrecio.appendText(df.format(detalleActividad.calcularPrecioIndividual()) + "€\n\n\n");
                total+=detalleActividad.calcularPrecioIndividual();
                textPrecioTotal.setText("Total: "+df.format(total)+"€");
                
    
    }
    
    
    public void metodoEjecutaAlInicio() {

        for (int i = 0; i < listaDetalleActividadesSeleccionadas.size(); i++) {
            
            areaPrecio.appendText(df.format(listaDetalleActividadesSeleccionadas.get(i).calcularPrecioIndividual()) + "€\n\n\n");
            
            
            
        }
        
        areaInfoActividad.setText(texto);
        textPrecioTotal.setText("Total: "+df.format(total)+"€");
    }

    
    
    public ArrayList<DetallePacks> getListaDetalleActividadesSeleccionadas() {
        return listaDetalleActividadesSeleccionadas;
    }

    public void setListaDetalleActividadesSeleccionadas(ArrayList<DetallePacks> listaDetalleActividadesSeleccionadas) {
        this.listaDetalleActividadesSeleccionadas = listaDetalleActividadesSeleccionadas;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @FXML
    private void alSeleccionarDuracion(ActionEvent event) {
        
        DateTimeFormatter formatoHoras= DateTimeFormatter.ISO_TIME;
        
        
        detalleActividad.setDuracion(LocalTime.parse(comboDuracion.getSelectionModel().getSelectedItem()+":00"));
        
    }
    
    
}
