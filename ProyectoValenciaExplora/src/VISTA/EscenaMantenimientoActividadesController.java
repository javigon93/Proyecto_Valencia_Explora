/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import DatosBDA.Actividades_DAO;
import DatosBDA.Subtipo_DAO;
import DatosBDA.Tipo_DAO;
import MODELO.Actividades;
import MODELO.Subtipo;
import MODELO.Tipo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaMantenimientoActividadesController implements Initializable {

    private Connection conexion;
    private Tipo_DAO bd_tipo;
    private Subtipo_DAO bd_subtipo;
    private Actividades_DAO bd_actividades;
    private Actividades actividad_solicitada;
    private Tipo tipo;
    private Subtipo subtipo;
    private ObservableList<Tipo> listaTipos = FXCollections.observableArrayList();
    private ObservableList<String> listaAcciones = FXCollections.observableArrayList("Añadir Actividad", "Modificar Actividad", "Eliminar Actividad");
    private ObservableList<Subtipo> listaSubtipos = FXCollections.observableArrayList();
    private Alert alerta;
    @FXML
    private Button botonATRAS;
    @FXML
    private Button botonGuardar;
    private ComboBox<String> comboAccion;
    @FXML
    private ComboBox<Subtipo> comboSubtipo;
    @FXML
    private TextField fieldNombre;
    @FXML
    private Button botonImagen;
    @FXML
    private Text textRuta;
    @FXML
    private TextField fieldURL;
    @FXML
    private TextField fieldDescripción;
    @FXML
    private ComboBox<Tipo> comboTipoActividad;
    @FXML
    private Text textSubtipoAct;
    @FXML
    private Text textNombre;
    @FXML
    private Text textDescripcion;
    @FXML
    private Text textImagen;
    @FXML
    private Text textURL;
    @FXML
    private Text textTipoAct;
    @FXML
    private ImageView imageViewSeleccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipo = new Tipo();
        subtipo = new Subtipo();
        actividad_solicitada = new Actividades();
        bd_actividades = new Actividades_DAO();
      

//        invisibilizarTodo();

    }

    @FXML
    private void alClicar(ActionEvent event) {

        if (event.getSource() == botonATRAS) {
            cargarEscenaMantenimiento();
        }

        if (event.getSource() == botonGuardar) {

            if (actividad_solicitada.getCodigoSubtipo() != 0 && actividad_solicitada.getNombre() != null && actividad_solicitada.getURL() != null) {

                try {

                    alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Confirmación");
                    alerta.setHeaderText("Confirma la entrada de una nueva Actividad?");
                    alerta.setContentText("¿Deseas añadir tu actividad " + actividad_solicitada.getNombre() + " Tipo: " + tipo.getNombre() + " Subtipo: " + subtipo.getNombre());

                    Optional<ButtonType> respuestaUsuario = alerta.showAndWait();

                    if (respuestaUsuario.get() == ButtonType.OK) {

                        actividad_solicitada.setIdActividad(bd_actividades.buscarMAX_IDatividad(conexion) + 1);
                        bd_actividades.insertarActividad(conexion, actividad_solicitada);

//                        invisibilizarTodo();

                        alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Inserción en la base de datos completa");
                        alerta.setHeaderText("Se ha insertado tu Nueva Actividad");
                        alerta.showAndWait();

                    }

                } catch (IOException e) {

                    alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error!");
                    alerta.setHeaderText("Ha ocurrido un error inesperado");
                    alerta.setContentText("Error " + e.getMessage());
                    alerta.showAndWait();

                } catch (SQLException ex) {

                    alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error!");
                    alerta.setHeaderText("Ha ocurrido un error en la Base de Datos");
                    alerta.setContentText("Error " + ex.getMessage() + " Código de error: " + ex.getErrorCode());
                    alerta.showAndWait();

                }
            } else {

                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setHeaderText("Ha ocurrido un error inesperado");
                alerta.setContentText("Faltan campos por rellenar");
                alerta.showAndWait();

            }
        }

        if (event.getSource() == botonImagen) {

            //CREAMOS EL OBJETO FILECHOOSER
            FileChooser fileChooser = new FileChooser();

            // PODEMOS INDICAR QUE SE SITUE EN UN DIRECTORIO CONCRETO  c:\tmp Â¡Â¡Cuidado si no existe en vuestro ordenador!!
            // fileChooser.setInitialDirectory( new File("c:\\tmp"));
            //ABRIMOS VENTANA FILECHOOSER Y RECUPERAMOS EL FICHERO SELECCIONADO
            File file = fileChooser.showOpenDialog(null);

            //REVISAMOS SI EL USUARIO HA SELECCIONADO UN FICHERO O NO
            if (file != null) {
                textRuta.setText(file.getAbsolutePath());
                actividad_solicitada.setURL_IMAGEN(file.getAbsolutePath());
                Image imagen_anadir= new Image(file.toURI().toString());
                imageViewSeleccion.setImage(imagen_anadir);
                centrarImagen();
            } else {

            }

        }

    }

//    @FXML
//    private void alSeleccionarAccion(ActionEvent event) {
//        String accion = comboAccion.getValue();
//
//        if (comboAccion.getSelectionModel().getSelectedIndex() == 0) {
//            mostrarElementosAñadir();
//
//        }
//
//    }

    @FXML
    private void alSeleccionarSubtipo(ActionEvent event) {

        subtipo = comboSubtipo.getValue();
        actividad_solicitada.setCodigoSubtipo(subtipo.getCodigoSubtipo());

    }

    @FXML
    private void alSeleccionarTipo(ActionEvent event) {

        tipo = comboTipoActividad.getValue();
        cargarSubtipos();

    }

    @FXML
    private void alInsertar(ActionEvent event) {

        if (event.getSource() == fieldNombre) {

            String nombre_actividad = fieldNombre.getText();
            actividad_solicitada.setNombre(nombre_actividad);

        }

        if (event.getSource() == fieldDescripción) {

            String descripcion = fieldDescripción.getText();
            actividad_solicitada.setDescripcion(descripcion);

        }

        if (event.getSource() == fieldURL) {

            String URL_actividad = fieldURL.getText();
            actividad_solicitada.setURL(URL_actividad);
        }

    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void metodoEjecutaAlInicio() {

        //LO QUE SE CARGA ANTES DE CAMBIAR de escena a aquí de nuevo, se podrá hacer en la siguiente escena.
        cargarTipos();
        Image imagen_inicial= new  Image("Icono/iconoAplicacion.png");
        imageViewSeleccion.setImage(imagen_inicial);
        centrarImagen();
    }

    private void cargarTipos() {

        bd_tipo = new Tipo_DAO();
        bd_subtipo = new Subtipo_DAO();
        try {

            List<Tipo> tipos = bd_tipo.buscarTipos(conexion);

            listaTipos.setAll(tipos);

            comboTipoActividad.setItems(listaTipos);

        } catch (IOException | SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private void cargarSubtipos() {

        bd_subtipo = new Subtipo_DAO();
        try {

            Set<Subtipo> subtipos = bd_subtipo.buscarSubTiposPorTipo(conexion, tipo.getCodigoTipo());

            listaSubtipos.setAll(subtipos);

            comboSubtipo.setItems(listaSubtipos);

        } catch (IOException | SQLException e) {

            System.out.println(e.getMessage());

        }

    }

//    private void mostrarElementosAñadir() {
//
//        botonGuardar.setVisible(true);
//        botonImagen.setVisible(true);
//        comboSubtipo.setVisible(true);
//        comboTipoActividad.setVisible(true);
//        fieldDescripción.setVisible(true);
//        fieldNombre.setVisible(true);
//        fieldURL.setVisible(true);
//
//        textDescripcion.setVisible(true);
//        textImagen.setVisible(true);
//        textNombre.setVisible(true);
//        textRuta.setVisible(true);
//        textSubtipoAct.setVisible(true);
//        textTipoAct.setVisible(true);
//        textURL.setVisible(true);
//
//    }

    private void mostrarElementosModificar() {

    }

    private void mostrarElementosEliminar() {

    }

//    private void invisibilizarTodo() {
//
//        botonGuardar.setVisible(false);
//        botonImagen.setVisible(false);
//        comboSubtipo.setVisible(false);
//        comboTipoActividad.setVisible(false);
//        fieldDescripción.setVisible(false);
//        fieldNombre.setVisible(false);
//        fieldURL.setVisible(false);
//
//        textDescripcion.setVisible(false);
//        textImagen.setVisible(false);
//        textNombre.setVisible(false);
//        textRuta.setVisible(false);
//        textSubtipoAct.setVisible(false);
//        textTipoAct.setVisible(false);
//        textURL.setVisible(false);
//
//        
//
//    }

    private void cargarEscenaMantenimiento() {

        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("escenaEleccionMantenimientoAct.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonATRAS.getScene().getWindow();
            escenarioVentana.setTitle("Mantenimiento");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + ex.getMessage());
            alerta.showAndWait();
        }

    }
    
    private void centrarImagen(){
    
    Image img = imageViewSeleccion.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageViewSeleccion.getFitWidth() / img.getWidth();
            double ratioY = imageViewSeleccion.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageViewSeleccion.setX((imageViewSeleccion.getFitWidth() - w) / 2);
            imageViewSeleccion.setY((imageViewSeleccion.getFitHeight() - h) / 2);

        }
    
    
    
    
    
    }


}
