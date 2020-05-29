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
import MODELO.DetallePacks;
import MODELO.Subtipo;
import MODELO.Tipo;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaModificarBorrarController implements Initializable {

    Alert alerta;
    Connection conexion;
    Actividades_DAO bd_actividades;
    Tipo_DAO bd_tipo;
    Subtipo_DAO bd_subtipo;
    Actividades actividad_seleccionada;
    Tipo tipo;
    Subtipo subtipo;
    
     Set<Actividades> actividades;
    private ObservableList<Tipo> listaTipos = FXCollections.observableArrayList();
    private ObservableList<Subtipo> listaSubtipos = FXCollections.observableArrayList();
    private ObservableList<Actividades> listaActividades;
    @FXML
    private Button botonATRAS;
    private ComboBox<Subtipo> comboSubtipo;
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
    private TextField fieldURL;
    @FXML
    private Text textURL;
    @FXML
    private TextField fieldDescripción;
    @FXML
    private Text textTipoAct;
    private ComboBox<Tipo> comboTipoActividad;
    @FXML
    private TableView<Actividades> tableActividades;
    @FXML
    private ImageView imageViewSeleccion;
    @FXML
    private TextField fieldBuscador;
    @FXML
    private TableColumn<Actividades, Integer> columnaTipo;
    @FXML
    private TableColumn<Actividades, Integer> columnaSubtipo;
    
    @FXML
    private TableColumn<Actividades, Integer> columnaNombre;
    @FXML
    private ComboBox<Subtipo> comboFiltroSubtipo;
    @FXML
    private ComboBox<Tipo> comboFiltroTipoActividad;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonEliminar;

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
        
        if (event.getSource() == botonATRAS) {
            cargarEscenaMantenimiento();
        }
        
    }
    
    
    @FXML
    private void alInsertar(ActionEvent event) {
        
        if (event.getSource()==fieldNombre) {
            String nuevoNombre= fieldNombre.getText();
            if (!actividad_seleccionada.getNombre().equalsIgnoreCase(nuevoNombre)) {
                actividad_seleccionada.setNombre(nuevoNombre);
                fieldNombre.setStyle("-fx-background-color: #ffcccc;");
            }
            
        }
        
        if (event.getSource()==fieldDescripción) {
            String nuevaDescripcion= fieldDescripción.getText();
            if (actividad_seleccionada.getDescripcion()==null || !actividad_seleccionada.getDescripcion().equalsIgnoreCase(nuevaDescripcion)) {
                actividad_seleccionada.setDescripcion(nuevaDescripcion);
                fieldDescripción.setStyle("-fx-background-color: #ffcccc;");
        }
            
           
                
            
    }
        
         if (event.getSource()== fieldURL) {
                
              String nuevaURL= fieldURL.getText();
            if (!actividad_seleccionada.getURL().equalsIgnoreCase(nuevaURL)) {
                actividad_seleccionada.setURL(nuevaURL);
                fieldURL.setStyle("-fx-background-color: #ffcccc;");
        }  
        
        
    }
    }
   
    private void cargarEscenaMantenimiento() {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("escenaEleccionMantenimientoAct.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaEleccionMantenimientoActController controlador = loader.getController();
            
            controlador.setConexion(conexion);
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
    
    @FXML
    private void alInsertarTexto(ActionEvent event) {
    }
    
    public void metodoEjecutaAlInicio() {

        //LO QUE SE CARGA ANTES DE CAMBIAR de escena a aquí de nuevo, se podrá hacer en la siguiente escena.
        cargarTipos();
        bd_actividades = new Actividades_DAO();
        actividad_seleccionada = new Actividades();
        cargarActividades();
        Image imagen_inicial = new Image("IMG/nofoto.png");
        
        imageViewSeleccion.setImage(imagen_inicial);
        centrarImagen();
        habilitarFiltrado();
       
	}
                                
                                
    
    
    private void centrarImagen() {
        
        Image img = imageViewSeleccion.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;
            
            double ratioX = imageViewSeleccion.getFitWidth() / img.getWidth();
            double ratioY = imageViewSeleccion.getFitHeight() / img.getHeight();
            
            double reducCoeff = 0;
            if (ratioX >= ratioY) {
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
    
    @FXML
    private void alSeleccionarActividad(MouseEvent event) {
        
        actividad_seleccionada = tableActividades.getSelectionModel().getSelectedItem();
        System.out.println(actividad_seleccionada.getCodigoSubtipo());
        fieldNombre.setText(actividad_seleccionada.getNombre());
        fieldDescripción.setText(actividad_seleccionada.getDescripcion());
        fieldURL.setText(actividad_seleccionada.getURL());
        imageViewSeleccion.setImage(actividad_seleccionada.getImagen());
        centrarImagen();
        blanquearFields();
        
    }
    
    private void cargarActividades() {
        
        try {
            
            actividades = bd_actividades.buscarActividades(conexion);
            
            listaActividades = FXCollections.observableArrayList(actividades);
            tableActividades.setItems(listaActividades);
            tableActividades.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            //disponemos la información adecuada en las respectivas columnas
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaSubtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));
            
        } catch (SQLException | IOException e) {
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
    
    private void cargarTipos() {

        bd_tipo = new Tipo_DAO();
        bd_subtipo = new Subtipo_DAO();
        try {

            List<Tipo> tipos = bd_tipo.buscarTipos(conexion);
            listaTipos.setAll(tipos);

            comboFiltroTipoActividad.setItems(listaTipos);

        } catch (IOException | SQLException e) {

            System.out.println(e.getMessage());

        }

    }
    
    
    private void cargarSubtipos() {

        bd_subtipo = new Subtipo_DAO();
        try {

            Set<Subtipo> subtipos = bd_subtipo.buscarSubTiposPorTipo(conexion, tipo.getCodigoTipo());

            listaSubtipos.setAll(subtipos);

            comboFiltroSubtipo.setItems(listaSubtipos);

        } catch (IOException | SQLException e) {

            System.out.println(e.getMessage());

        }

    }
    
    private void habilitarFiltrado(){
    
     
        FilteredList<Actividades> filtrado= new FilteredList<>(listaActividades, p ->true);
        fieldBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
			filtrado.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getTipo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (person.getSubtipo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                else if (person.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
                                
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Actividades> sortedData = new SortedList<>(filtrado);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableActividades.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableActividades.setItems(sortedData);
    
    
    
    
    }

    @FXML
    private void alSeleccionarFiltroSubtipo(ActionEvent event) {
        
        subtipo= comboFiltroSubtipo.getValue();
        
        try{
        
        actividades=bd_actividades.buscarActividadesPorSubtipo(conexion, subtipo.getCodigoSubtipo());
         listaActividades = FXCollections.observableArrayList(actividades);
        tableActividades.setItems(listaActividades);
         habilitarFiltrado();
        
        }
        catch(SQLException | IOException e){
          alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + e.getMessage());
            alerta.showAndWait();
           
        
        
        }
    }

    @FXML
    private void alSeleccionarFiltroTipo(ActionEvent event) {
        
         tipo = comboFiltroTipoActividad.getValue();
        
        try{
            
            actividades= bd_actividades.buscarActividadesPorTipo(conexion, tipo.getCodigoTipo());
            listaActividades = FXCollections.observableArrayList(actividades);
            tableActividades.setItems(listaActividades);
        
        cargarSubtipos();
        habilitarFiltrado();
        
        }
        
        catch(SQLException | IOException e){
          alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + e.getMessage());
            alerta.showAndWait();
        
        
        }
    }

    @FXML
    private void alPulsar(ActionEvent event) {
        
       
        
        if (event.getSource()==botonModificar) {
            
             alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Confirma los cambios");
               alerta.setContentText("Deseas modificar la actividad: " + actividad_seleccionada.getNombre()+"?" );
               
               
            Optional<ButtonType> respuestaUsuario = alerta.showAndWait();

            if (respuestaUsuario.get() == ButtonType.OK) {
 
            try {
                bd_actividades.actualizarActividad(conexion, actividad_seleccionada);
                
                
            } catch (SQLException | IOException ex) {
               alerta = new Alert(Alert.AlertType.ERROR);
               alerta.setContentText("ERROR " + ex.getMessage());
               alerta.showAndWait();
            }
            
               

            }
               
           
        }
        
    }
    
    private void blanquearFields(){
    
    fieldDescripción.setStyle(null);
    fieldURL.setStyle(null);
    
    fieldNombre.setStyle(null);
    }

    
}
