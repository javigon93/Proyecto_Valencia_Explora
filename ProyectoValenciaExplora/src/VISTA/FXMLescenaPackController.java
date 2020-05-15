package VISTA;
import DatosBDA.Actividades_DAO;
import DatosBDA.Packs_DAO;
import DatosBDA.Detallepacks_DAO;
import MODELO.Actividades;
import MODELO.DetallePacks;
import MODELO.Packs;
import VISTA.EscenaActividadesController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class FXMLescenaPackController implements Initializable {
    private Connection conexion;
    Actividades_DAO bd_act;
    Packs_DAO bd_pack;
    Detallepacks_DAO bd_dp;
    private ObservableList<Actividades> ListaActividades;
    @FXML
    private TableView<Actividades> tableActividades;
    @FXML
    private TableColumn<Actividades, Integer> columnaTipo;
    @FXML
    private TableColumn<Actividades, Integer> columnaNombre;
    private Pane paneDescripcion;
    @FXML
    private Button botonANADIR;
    private Label labelDescripcion;
    private TextArea areaDescripcion;
    private ImageView imagenActividad;
    @FXML
    private TableColumn<Actividades, Integer> columnaSubtipo;
    private Text numActEnPack;
    @FXML
    private Button botonVolver;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Set<Actividades> actividades= EscenaActividadesController.pack.getActividades();
        
        ListaActividades=FXCollections.observableArrayList(actividades);
        
            tableActividades.setItems(ListaActividades);
            tableActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaSubtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));
        
        }
    
        public void conectar() throws SQLException {

        String bd = "esquema_proyecto";
        String usuario = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/" + bd + "?serverTimezone=UTC";

        conexion = DriverManager.getConnection(url, usuario, password);
        System.out.println("Conectado");
    }
    
        public void setConexion(Connection aConexion) {
        this.conexion = aConexion;
    }
    private void alpulsarActividad(MouseEvent event) {
        Actividades actividadSeleccionada;
        
       actividadSeleccionada= tableActividades.getSelectionModel().getSelectedItem();
       Image img=new Image("/IMG/micalet.png");
        imagenActividad.setImage(img);
        paneDescripcion.setDisable(false);
        areaDescripcion.setWrapText(true);
        areaDescripcion.setText(actividadSeleccionada.getDescripcion());
        
    }

    @FXML
    private void confirmarPack(ActionEvent event) {
        alertConfirmationPack();
    }

    public String mostrarAct(){
        /*ESTE METODO LO QUE HACE ES MOSTRAR LAS ACTIVIDADES EN MODO STRING EN EL ALERT DE CONFIRMAL EL PACK*/
        String muestraDeActividades = "";
        for (Actividades Actividad : EscenaActividadesController.pack.getActividades()) {
            muestraDeActividades = muestraDeActividades+ "\n" + Actividad.getNombre() ;
        }
        return muestraDeActividades;
    }
    
    
    public boolean alertConfirmationPack(){
        /*ESTE ES EL ALERT DEL BOTON A LA HORA DE CONFIRMAR EL PACK, LE PASO LA CANTIDAD DE PACKS QUE COGE TODOS LOS PACKS
        DE LA BASE DE DATOS LOS CUENTA Y LE SUMA 1, Y ESE HE PUESTO QUE SEA EL IDPACK DEL PACK QUE ESTAMOS CREANDO
        EL NUM DE LINEA LO DECLARO AQUI, Y LO CUENTO EN ESTE METODO Y LUEGO SE LO PASO AL METODO INSERTAR DETALLEPACKS PORQUE COMO ESTA EN BUCLE ME VA SUMANDO EL NUMERO DE LINEA
        BASICAMENTE ESTE METODO FUNCIONA SI LE DOY AL BOTON ACEPTAR DEL ALERT HACE TODO LO DE INSERTAR Y TODO ESO, SI LE DOY A CANCEL NO HACE NADA
        */
        boolean respuestaAlert = false;
        bd_act = new Actividades_DAO();
        bd_pack = new Packs_DAO(conexion);
        bd_dp = new Detallepacks_DAO(conexion);
        int cantPacks = 1;
        int numLinea = 1;
        try {
            cantPacks = bd_pack.cantidadPacks() + 1;
        } catch (SQLException e) {
            
        }
        
        int numlinea = 0;
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAlerta.setTitle("Valencia Explora");
        dialogoAlerta.setHeaderText("Usted esta a punto de confirmar este pack.");
        dialogoAlerta.setContentText("Actividades del pack: "+mostrarAct());

        Optional<ButtonType> respuestaUsuario = dialogoAlerta.showAndWait();

        if (respuestaUsuario.isPresent()) {
            if (respuestaUsuario.get() == ButtonType.OK) {
                respuestaAlert = true;
                try {
                    bd_pack.insertarPackBD(conexion);
                    for (Actividades actividad : EscenaActividadesController.pack.getActividades()) {
                    /*LA LINEA JUSTO DEBAJO LE ESTABLECE AL DETALLEPACK DENTRO DE ACTIVIDADES SU NUMLINEA, COMO NO HABIA OTRA MANERA DE
                        HACERLO, LO HE HECHO ASI
                        */
                    actividad.getDp().setNumLinea(numlinea++);
                    /*EN EL METODO INSERTARDETALLEPACKS COMO YA HE COMENTADO, EL cantPacksEQUIVALE AL IDPACK CON EL QUE ESTAMOS TRABAJANDO
                    Y EL numlLinea++ AL NUMERO DE LINEA DE CADA ACTIVIDAD
                    */
                    bd_dp.insertarDetallePack(actividad.getDp(), cantPacks, numLinea++);
                    }
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                
                
                
            } else if (respuestaUsuario.get() == ButtonType.CANCEL) {
                respuestaAlert = false;
            } 
        } else {
           respuestaAlert = false;
        }
    return respuestaAlert;
    } 

    @FXML
    private void volverAct(ActionEvent event) {
        /*BOTON PARA VOLVER A ESCENA ACTIVIDADES*/
       abreotraescena(event);
    }

    private void abreotraescena(ActionEvent event) {
/*ESTO ES MI METODO PARA VOLVER A ESCENA ACTIVIDADES, PERO NO ME FUNCIONA AQUI TIENE QUE CAMBIAR EL CODIGO QUE HAY POR EL QUE TIENE JAVI*/
        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("EscenaActividades.fxml"));
            //Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonVolver.getScene().getWindow();
            escenarioVentana.setTitle("Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            //escenarioVentana.setScene(new Scene(root));

        } catch (Exception ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + ex.getMessage());
            alerta.showAndWait();
        }

    }
    
}
