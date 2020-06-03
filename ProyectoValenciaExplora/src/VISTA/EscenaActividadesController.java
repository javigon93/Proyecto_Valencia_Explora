/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import DatosBDA.Actividades_DAO;
import DatosBDA.Packs_DAO;
import DatosBDA.Subtipo_DAO;
import DatosBDA.Tipo_DAO;
import MODELO.Actividades;
import MODELO.DetallePacks;
import MODELO.Subtipo;
import MODELO.Tipo;


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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaActividadesController implements Initializable {

    private Connection conexion;
    private Actividades_DAO bd_act = new Actividades_DAO();
    private Packs_DAO bd_packs = new Packs_DAO();
    private Actividades actividad_seleccionada = new Actividades();
    private DetallePacks detalleActividad = new DetallePacks();
    private Tipo tipo;
    private Subtipo subtipo;
    private Tipo_DAO bd_tipo;
    private Subtipo_DAO bd_subtipo;
    private Alert alerta;
    private final IntegerSpinnerValueFactory personas = new IntegerSpinnerValueFactory(0, 20, 0, 1);
    private ArrayList<DetallePacks> listaDetalleActividadesSeleccionadas = new ArrayList<>();
    private final ObservableList<String> listaHoras = FXCollections.observableArrayList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
    private ObservableList<Actividades> listaActividades;
    private String texto = "";
    private final DecimalFormat formatoDosDecimales = new DecimalFormat("#.00"); //FORMATO DOS DECIMALES
    private double total = 0;
    private Set<Actividades> actividades;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int codTipo_actividad_seleccionada;
    
    private Notifications notificacion;
    private ObservableList<Tipo> listaTipos = FXCollections.observableArrayList();
    private ObservableList<Subtipo> listaSubtipos = FXCollections.observableArrayList();
    DateTimeFormatter formatoHoras = DateTimeFormatter.ISO_LOCAL_TIME;
    DateTimeFormatter formatoDias = DateTimeFormatter.ISO_LOCAL_DATE;
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
    private Button botonBorrar;

    @FXML
    private TextArea areaInfoActividad;
    @FXML
    private TextArea areaPrecio;
    @FXML
    private Text textPrecioTotal;

    @FXML
    private Text textDias;
    @FXML
    private Button botonSalir;
    @FXML
    private Button boton;
    @FXML
    private Button botonAyuda;
    @FXML
    private ComboBox<Subtipo> comboFiltroSubtipo;
    @FXML
    private Text textSubtipoAct;
    @FXML
    private Text textTipoAct;
    @FXML
    private ComboBox<Tipo> comboFiltroTipoActividad;
    @FXML
    private TextField fieldBuscador;
    @FXML
    private DatePicker dateFin;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private ComboBox<String> timeInicio;
    @FXML
    private ComboBox<String> timeFin;
    @FXML
    private ImageView imagenCalendario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bd_act = new Actividades_DAO();
        bd_packs = new Packs_DAO();
        actividad_seleccionada = new Actividades();
        detalleActividad = new DetallePacks();
        Image imagenInicio = new Image("/Icono/iconoAplicacion.png");
        imagenActividad.setImage(imagenInicio);
        botonAyuda.setStyle("-fx-background-color: transparent;");

        //inicializamos las características por defecto de la zona central, conectamos con BD...
        try {

            panePorDefecto(); //método de inhabilitación de elementos en la zona central de la escena.
            conectar();

            timeInicio.setItems(listaHoras);//SE AÑADEN LAS HORAS AL COMBO
            timeFin.setItems(listaHoras);//LO MISMO

            //ESTABLECEMOS LAS ACTIVIDADES en la table view, en base a lo obtenido del SELECT
            actividades = bd_act.buscarActividades(conexion);
            listaActividades = FXCollections.observableArrayList(actividades);
            tableActividades.setItems(listaActividades);
            tableActividades.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            //disponemos la información adecuada en las respectivas columnas
            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            columnaSubtipo.setCellValueFactory(new PropertyValueFactory<>("subtipo"));

            spinnerPersonas.setValueFactory(personas);//incluimos los valores del Spinner
            cargarTipos();
            habilitarFiltrado();
        } catch (SQLException | IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public void conectar() throws SQLException { //Típico método de conexión a BD, esta conexión se trasladará a diferentes métodos y escenas

        String bd = "esquema_proyecto_2.0";
        String usuario = "root";
        String password = "Gonzalez_Landete";
        String url = "jdbc:mysql://localhost:3306/" + bd + "?serverTimezone=UTC";

        conexion = DriverManager.getConnection(url, usuario, password);

        if (conexion != null) {

            System.out.println("Conectado");
        }
    }

    @FXML
    private void alPulsar(ActionEvent event) {

        if (event.getSource() == botonSalir) {

            abreEscenaInicio(event);

        } else if (event.getSource() == botonANADIR) {

            //si todo está rellenado, avisa de la introducción de una nueva actividad, al confirmar, se añade a una coleccion y se imprime información en las areas de la derecha a través de un método
            // cuando se añade a la colección, los elementos visuales centrales se vuelven a deshabilitar
            if (detalleActividad.getPersonas() != 0 && detalleActividad.getFechaInicio() != null && detalleActividad.getFechaFin() != null && detalleActividad.getPrecio() != 0 && detalleActividad.getDuracion() != null) {
                
            
                
                
                alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Confirma tu Actividad");
                alerta.setContentText("¿Deseas añadir la actividad " + actividad_seleccionada.getNombre() + "Con precio " + formatoDosDecimales.format(detalleActividad.calcularPrecioIndividual(codTipo_actividad_seleccionada)) + "€ a tu Pack?");

                Optional<ButtonType> respuestaUsuario = alerta.showAndWait();

                if (respuestaUsuario.get() == ButtonType.OK) {

                    imprimirDetalleyPrecio();
                    listaDetalleActividadesSeleccionadas.add(detalleActividad);
                    panePorDefecto();
                    detalleActividad = new DetallePacks();

                }

            } else { //si algo falta, salta un alert de error.
                
                crearError("Introduce todos los datos antes de continuar!");
            
            


            }
        } else if (event.getSource() == botonRevisarConfirmar) {
            abreEscenaRevisarConfirmar(event);

        } else if (event.getSource() == botonBorrar) { //salta un alert confirmation, si se da OK, se resetean todos los valores del objeto detallepacks y se borra la colección
            
            if (listaDetalleActividadesSeleccionadas.isEmpty() == false) {
               notificacion=Notifications.create();
               
            notificacion.showConfirm();
            
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
                    panePorDefecto(); //también se deshabilita los elementos centrales
                    texto = "";
                    total = 0;
                    textPrecioTotal.setText("Total: ");

                }

            }

        } else if (event.getSource() == botonAyuda) {
            cargarEscenaAyuda();
        }

    }

    @FXML
    private void alpulsarActividad(MouseEvent event) {

        actividad_seleccionada = tableActividades.getSelectionModel().getSelectedItem();
        if (actividad_seleccionada != null) {

            try {
                imagenActividad.setImage(actividad_seleccionada.getImagen());
                centrarImagen();
                codTipo_actividad_seleccionada = bd_act.buscarTipoActividad(conexion, actividad_seleccionada.getIdActividad());
                //al clicar en una actividad, los elementos visuales centrales se habilitan
                paneDescripcion.setDisable(false);
                dateFin.setDisable(true);
                timeInicio.setDisable(true);
                timeFin.setDisable(true);

                areaDescripcion.setWrapText(true);
                //se añade la descripción y URL en el area central.
                areaDescripcion.setText(actividad_seleccionada.getDescripcion() + "\n\nMás Información en: " + actividad_seleccionada.getURL());
                detalleActividad.setIdActividad(actividad_seleccionada.getIdActividad()); ///se añade la id de actividad al objeto detalleActividad aquí,
                //a travvés del valor que ofrece la actividad seleccionda

            } catch (SQLException ex) {
                
                crearError("ERROR " + ex.getMessage() + " Codigo de error: " + ex.getErrorCode());

            } catch (IOException ex) {
                
                crearError("ERROR " + ex.getMessage());

            }

        }
    }

    @FXML
    private void alPulsarPersonas(MouseEvent event) { //se obtiene el valor y se hace un set del objeto detallePack
        int personas_actividad = spinnerPersonas.getValue();
        detalleActividad.setPersonas(personas_actividad);

    }

    @FXML
    private void alIntroducirPrecio(ActionEvent event) { //al introducir un valor, este se trasnsforma a un double. Ese valor se traslada a un set del objeto detallepack

        try {
            double precioActividad = Double.parseDouble(textPrecio.getText());
            detalleActividad.setPrecio(precioActividad);
        } catch (NumberFormatException e) { //si se introducen valores NO NUMÉRICOS, salta error.
            
            crearError("Introduce un Número");


        }
    }

    private void abreEscenaRevisarConfirmar(ActionEvent event) { //este método pasa a otra escena, y al mismo tiempo traslada los algunos parámetros a esa otra escena (conexión, colección detallePacks, precio total, y el texto de resumen.
        //Tambiñen se ejecuta un método de carga previa de algunos valores en algunos elementos de la otra escena (metodoEjecutarAlInicio)
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

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());

        } catch (NullPointerException ex) {
            crearError("ERROR " + ex.getMessage());

        }

    }

    private void abreEscenaInicio(ActionEvent event) { //este método pasa a otra escena, y al mismo tiempo traslada los algunos parámetros a esa otra escena (conexión, colección detallePacks, precio total, y el texto de resumen.
        //Tambiñen se ejecuta un método de carga previa de algunos valores en algunos elementos de la otra escena (metodoEjecutarAlInicio)
        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/FXMLInicial.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) botonRevisarConfirmar.getScene().getWindow();
            escenarioVentana.setTitle("Revisar Y Confirmar");

            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());

        } catch (NullPointerException ex) {
            
            crearError("ERROR " + ex.getMessage());

        }

    }


    private void panePorDefecto() { //este es el método que desabilita elementos visuales y resetea algunos.
        textPrecio.clear();
        paneDescripcion.setDisable(true);
        spinnerPersonas.setValueFactory(personas);

        dateInicio.setValue(LocalDate.now());
        dateFin.setValue(LocalDate.now().plusDays(1));
        dateInicio.setEditable(false);

        dateFin.setEditable(false);
        textDias.setText("0");
    }

    private void imprimirDetalleyPrecio() { //para mostrar info de lo que tenemos guardado en la coleccion de detalle. Aprovechamos los objetos  (detallepacks y actividades) que se están utilziando

        texto += "Actividad: " + actividad_seleccionada.getNombre() + " Número de Personas: " + detalleActividad.getPersonas() + "\nFecha Inicio: "
                + detalleActividad.getFechaInicio() + "Fecha Final: " + detalleActividad.getFechaFin() + "\n\n";

        areaInfoActividad.setText(texto);
        areaPrecio.appendText(formatoDosDecimales.format(detalleActividad.calcularPrecioIndividual(codTipo_actividad_seleccionada)) + "€\n\n\n"); //se formatea el precio individual, obtenido de un método de la clase detallepacks
        total += detalleActividad.calcularPrecioIndividual(codTipo_actividad_seleccionada); //este se suma al dato del precio total
        textPrecioTotal.setText("Total: " + formatoDosDecimales.format(total) + "€"); //también se formatea

    }

    public void metodoEjecutaAlInicio() {

        //LO QUE SE CARGA ANTES DE CAMBIAR de escena a aquí de nuevo, se podrá hacer en la siguiente escena.
        for (int i = 0; i < listaDetalleActividadesSeleccionadas.size(); i++) {
            //datos de lo que habíamos guardado en la colección como el precio individual de cada uno...
            areaPrecio.appendText(formatoDosDecimales.format(listaDetalleActividadesSeleccionadas.get(i).calcularPrecioIndividual(codTipo_actividad_seleccionada)) + "€\n\n\n");

        }
        //ademas se añade el texto de la info de cada actividad
        areaInfoActividad.setText(texto);
        textPrecioTotal.setText("Total: " + formatoDosDecimales.format(total) + "€");
    }


    private void centrarImagen() {

        Image img = imagenActividad.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imagenActividad.getFitWidth() / img.getWidth();
            double ratioY = imagenActividad.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imagenActividad.setX((imagenActividad.getFitWidth() - w) / 2);
            imagenActividad.setY((imagenActividad.getFitHeight() - h) / 2);

        }

    }

    //GETTERS Y SETTERS DE PASO DE PARAMETROS ENTRE ESCENAS
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
    private void cargaPagina(ActionEvent event) {

        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/escenaPaginaWeb.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaPaginaWebController controlador = loader.getController();
            controlador.setUrl(actividad_seleccionada.getURL());
            controlador.abrirWeb();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.setTitle("Más Infomación");
            stage.alwaysOnTopProperty();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            crearError("ERROR " + ex.getMessage());
//            alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR " + ex.getMessage());
//            alerta.showAndWait();
        }

    }

    private void cargarEscenaAyuda() {

        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/escenaAyudaMantenimiento.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaAyudaMantenimientoController controlador = loader.getController();
            controlador.setModo(3);
            controlador.metodoEjecutaAlInicio();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Ayuda");
            stage.alwaysOnTopProperty();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException ex) {
            crearError("ERROR " + ex.getMessage());

        } catch (NullPointerException e) {
        crearError("ERROR " + e.getMessage());


        }

    }

    @FXML
    private void alSeleccionarFiltroSubtipo(ActionEvent event) {

        subtipo = comboFiltroSubtipo.getValue();

        try {

            actividades = bd_act.buscarActividadesPorSubtipo(conexion, subtipo.getCodigoSubtipo());
            listaActividades = FXCollections.observableArrayList(actividades);
            tableActividades.setItems(listaActividades);
            habilitarFiltrado();

        } catch (SQLException | IOException e) {
              crearError("ERROR " + e.getMessage());


        }
    }

    @FXML
    private void alSeleccionarFiltroTipo(ActionEvent event) {

        tipo = comboFiltroTipoActividad.getValue();

        try {

            actividades = bd_act.buscarActividadesPorTipo(conexion, tipo.getCodigoTipo());
            listaActividades = FXCollections.observableArrayList(actividades);
            tableActividades.setItems(listaActividades);

            cargarSubtipos();
            habilitarFiltrado();

        } catch (SQLException | IOException e) {
              crearError("ERROR " + e.getMessage());


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
            
            crearError("Error!: " + e.getMessage());

        }

    }

    private void cargarSubtipos() {

        bd_subtipo = new Subtipo_DAO();
        try {

            Set<Subtipo> subtipos = bd_subtipo.buscarSubTiposPorTipo(conexion, tipo.getCodigoTipo());

            listaSubtipos.setAll(subtipos);

            comboFiltroSubtipo.setItems(listaSubtipos);

        } catch (IOException | SQLException e) {
            crearError("Error!: " + e.getMessage());
            

        }

    }

    private void habilitarFiltrado() {

        FilteredList<Actividades> filtrado = new FilteredList<>(listaActividades, p -> true);
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
                } else if (person.getNombre().toLowerCase().contains(lowerCaseFilter)) {
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
    private void alSeleccionarTiempo(ActionEvent event) { //metodo de gestion de fechas. Aglutina todos los botones involucrados.

        if (event.getSource() == dateInicio) {
            //si la fecha es igual o o superior a la actula, la recoge. EN EL CASO DE SER LA AC
            LocalDate fecha_seleccionada = dateInicio.getValue();

            if (fecha_seleccionada.isAfter(LocalDate.now()) || fecha_seleccionada.isEqual(LocalDate.now())) {
                fechaInicio = fecha_seleccionada;
                timeInicio.setDisable(false);
                if (codTipo_actividad_seleccionada != 3) {

                    System.out.println(fecha_seleccionada);
                    fechaFin = fecha_seleccionada;
                }
            } else {
                crearError("Introduce una fecha Correcta\n\nLa Fecha Inicio debe de ser Actual o Posterior");

                dateInicio.setValue(LocalDate.now());

            }

        } else if (event.getSource() == dateFin) {
            LocalDate fecha_seleccionada = dateFin.getValue();

            if (fecha_seleccionada.isAfter(fechaInicio)) {
                fechaFin = fecha_seleccionada;
                timeFin.setDisable(false);

            } else {
                crearError("Introduce una fecha Correcta\n\nLa Fecha Fin debe de ser Posterior a la Fecha Inicio");

                dateFin.setValue(dateInicio.getValue().plusDays(1));

            }
        } else if (event.getSource() == timeInicio) {

            horaInicio = LocalTime.parse(timeInicio.getValue(), formatoHoras);
            LocalDateTime fecha_completa_inicio = LocalDateTime.of(fechaInicio, horaInicio);
            detalleActividad.setFechaInicio(fecha_completa_inicio);

            if (codTipo_actividad_seleccionada == 3) {

                dateFin.setDisable(false);

            } else {

                horaFin = horaInicio;
                LocalDateTime fecha_completa_fin = LocalDateTime.of(fechaFin, horaFin);
                detalleActividad.setFechaFin(fecha_completa_fin);
            }

        } else if (event.getSource() == timeFin) {

            horaFin = LocalTime.parse(timeFin.getValue(), formatoHoras);
            LocalDateTime fecha_completa_fin = LocalDateTime.of(fechaFin, horaFin);
            detalleActividad.setFechaFin(fecha_completa_fin);
            textDias.setText("" + detalleActividad.calcularDiasActividad());
        }

    }
    
    private void crearError(String texto){  //metodo que genera Notifications de Error personalizados.
    
        
                    notificacion=Notifications.create()
                    .text(texto)
                    .title("Error")
                    
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.CENTER);
                    
                    notificacion.showError();
    
    
    }

}
