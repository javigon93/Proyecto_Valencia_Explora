/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import DatosBDA.DetallePacks_DAO;
import DatosBDA.Packs_DAO;
import MODELO.DetallePacks;
import MODELO.Packs;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaRevisarConfirmarController implements Initializable {

    private Packs pack;
    private Connection conexion;
    private DetallePacks_DAO detallepacksBD;
    private Packs_DAO packBD;
    private double precioTotal;
    private String textoResumen;
    private final DecimalFormat df = new DecimalFormat("#.00");
    private Notifications notificacion;
    private Alert alerta;
    @FXML
    private Button botonAtras;
    @FXML
    private Button botonConfirmar;
    @FXML
    private Label labelPrecioTotal;
    @FXML
    private TextArea areaResumen;
    @FXML
    private TextField fieldNombrePack;
    private ArrayList<DetallePacks> actividadesEscogidas;
    @FXML
    private TextField fieldDescripción;
    @FXML
    private Button botonDescripcion;
    @FXML
    private CheckBox checkFavorito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pack = new Packs();
        detallepacksBD = new DetallePacks_DAO();
        packBD = new Packs_DAO();
        labelPrecioTotal.setText("Total: ");

    }

   

    private void abreEscenaActividades(ActionEvent event) { //METODO DE PASO A OTRA ESCENA, PASO DE PARAMETROS Y CARGA PREVIA


     try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/escenaActividades.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            EscenaActividadesController controladorActividades = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            controladorActividades.setListaDetalleActividadesSeleccionadas(actividadesEscogidas);
            controladorActividades.setTexto(textoResumen);
            controladorActividades.setTotal(precioTotal);
            controladorActividades.metodoEjecutaAlInicio();
            Stage escenarioVentana = (Stage) botonAtras.getScene().getWindow();
            escenarioVentana.setTitle("Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearAvisoError("ERROR " + ex.getMessage());
//            alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR " + ex.getMessage());
//            alerta.showAndWait();
        } catch (NullPointerException e){
            crearAvisoError("ERROR EN EL ARCHIVO /VISTA/escenaActividades.fxml");
//            alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR EN EL ARCHIVO /VISTA/escenaActividades.fxml");
//            alerta.showAndWait();
        }

    }
    
     private void retornoInicio(ActionEvent event) { //METODO DE PASO A OTRA ESCENA, PASO DE PARAMETROS Y CARGA PREVIA


     try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/VISTA/FXMLInicial.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta
           

            
            Stage escenarioVentana = (Stage) botonAtras.getScene().getWindow();
            escenarioVentana.setTitle("Actividades");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));

        } catch (IOException ex) {
            crearAvisoError("ERROR " + ex.getMessage());
//            alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR " + ex.getMessage());
//            alerta.showAndWait();
        }
     catch (NullPointerException e){
             crearAvisoError("ERROR EN EL ARCHIVO /VISTA/FXMLInicial.fxml");
//            alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setContentText("ERROR EN EL ARCHIVO /VISTA/FXMLInicial.fxml");
//            alerta.showAndWait();
        }

    }

    @FXML
    private void alPulsarAtras(ActionEvent event) { //AL PULSAR EL BOTON ATRAS SE EJECUTA EL MÉTODO DE PASO A OTRA ESCENA
        abreEscenaActividades(event);
    }

    @FXML
    private void alPulsarConfirmar(ActionEvent event) {
        
        //si el pack tiene nombre salta un information si se ha insertado bien el pack, si no, salta un arror avisando de que no hay nombre, o bien si hay un error de SQL ( o cualquier otro)se avisa también.

        if (pack.getNombre_pack() != null) {

            try {
                packBD.insertarPack(conexion, pack);
                detallepacksBD.insertarDetallePack(conexion, actividadesEscogidas);
                alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Se ha actualizado tu Pack");
                alerta.setContentText("Gracias por Utilizar el servicio!!");
                alerta.showAndWait();
                conexion.close();
                retornoInicio(event);
            } catch (SQLException ex) {
                crearAvisoError("ERROR EN BD\n\nHa ocurrido un problema en la BD\n\n" + ex.getMessage() + "CODIGO: " + ex.getErrorCode());
//                alerta = new Alert(Alert.AlertType.ERROR);
//
//                alerta.setTitle("ERROR EN BD");
//                alerta.setHeaderText("Ha ocurrido un problema en la BD");
//                alerta.setContentText(ex.getMessage() + "CODIGO: " + ex.getErrorCode());
//                alerta.showAndWait();

            } catch (Exception e) {
                crearAvisoError("ERROR EN BD\n\nHa ocurrido un problema\n" +e.getMessage());
//                alerta = new Alert(Alert.AlertType.ERROR);
//
//                alerta.setTitle("ERROR EN BD");
//                alerta.setHeaderText("Ha ocurrido un problema");
//                alerta.setContentText(e.getMessage());
//                alerta.showAndWait();

            }

        } else {
            crearAvisoInformacionDuradero("Nombra a tu Pack antes de confirmarlo.");
//            alerta = new Alert(Alert.AlertType.ERROR);
//
//            alerta.setTitle("Inserta un Nombre");
//            alerta.setHeaderText("Inserta un Nombre");
//            alerta.setContentText("Inserta un Nombre");
//            alerta.showAndWait();
        }
    }

    @FXML
    private void alIntroducirNombre(ActionEvent event) { //al introducir nombre del pack, se hace un set del nombre en el objeto pack.
        pack.setNombre_pack(fieldNombrePack.getText());

    }


    public void metodoEjecutaAlInicio() { //Cuando se cargue previamente esta escena, si no se ha seleccionado ninguna actividad anteriormente, se pone que no se ha escogido nada
                                            // si existen, se carga la info pasada de la otra escena en un textarea y un label.

        if (actividadesEscogidas.isEmpty()) {
            areaResumen.setText("Aún no has escogido NADA");
        } else {

            areaResumen.setText(textoResumen);
             labelPrecioTotal.setText("Total: " +df.format(precioTotal)+ "€");
        }
    }

    
    @FXML
    private void alPulsarDescripcion(ActionEvent event) { //se añade la descripción y se avisa de que se ha agregado.
        if(!fieldDescripción.getText().equals(null)){
        pack.setDescripcion(fieldDescripción.getText());
        }
//        alerta = new Alert(Alert.AlertType.INFORMATION);
//        alerta.setTitle("Descripción Incluída");
//        alerta.setHeaderText("Se ha completado la actualización de la descripción");
//        alerta.setContentText("Actualizado tu pack: Tu descripción es '" + pack.getDescripcion() + "'");
//        alerta.showAndWait();
    }

    @FXML
    private void alpulsarFavorito(ActionEvent event) { //SI LE DAMOS Y ESTÁ Seleccionado, se hace un set con valor 1 (true) , si se le da y no se selecciona... (false)

        if (checkFavorito.isSelected()) {

            pack.setFavorito(1);
            crearAvisoInformacionBreve("Tu pack Es ahora tu Favorito!!");
//            alerta = new Alert(Alert.AlertType.INFORMATION);
//            alerta.setTitle("Favorito");
//            alerta.setHeaderText("Tu pack Es ahora tu Favorito!!");
//            alerta.setContentText("Actualizado tu pack");
//            alerta.showAndWait();

        }

        if (checkFavorito.isSelected() == false) {

            pack.setFavorito(0);
            crearAvisoInformacionBreve("Tu pack ya no es ahora tu Favorito");
//            alerta = new Alert(Alert.AlertType.INFORMATION);
//            alerta.setTitle("Favorito");
//            alerta.setHeaderText("Tu pack ya no es ahora tu Favorito");
//            alerta.setContentText("No es tu favorito");
//            alerta.showAndWait();

        }
    }
        private void crearAvisoError(String texto) {

        notificacion = Notifications.create()
                .text(texto)
                .title("Error")
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);

        notificacion.showError();

    }

    private void crearAvisoInformacionDuradero(String texto) {

        notificacion = Notifications.create()
                .text(texto)
                .title("Aviso")
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);

        notificacion.showInformation();

    
        
        
    }
    
    private void crearAvisoInformacionBreve(String texto) {

        notificacion = Notifications.create()
                .text(texto)
                .title("Aviso")
                .hideAfter(Duration.seconds(1))
                .position(Pos.CENTER_RIGHT);

        notificacion.showInformation();

    
        
        
    }



//GETTERS Y SETTERS de lo que se pasa de la otra escena.
     public String getTextoResumen() {
        return textoResumen;
    }

    public void setTextoResumen(String textoResumen) {
        this.textoResumen = textoResumen;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setActividadesEscogidas(ArrayList<DetallePacks> actividadesEscogidas) {
        this.actividadesEscogidas = actividadesEscogidas;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}
