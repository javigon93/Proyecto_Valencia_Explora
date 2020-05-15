package VISTA;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLInicialController implements Initializable
{

    @FXML
    private Button botonEmpezar;

    private Connection conexion;

    @Override
    public void initialize( URL url, ResourceBundle rb )
    {
        // TODO
    }

    @FXML
    private void alPulsarIniciar( ActionEvent event )
    {
        abreotraescena( event );
    }

    private void abreotraescena( ActionEvent event )
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            // CARGAMOS OTRO FXML
            loader.setLocation( getClass().getResource( "escenaActividades.fxml" ) );
            Parent root = loader.load(); // el metodo initialize() se ejecuta
            // Obtenemos el control para pasar la conexion como parametro
            EscenaActividadesController controller = loader.< EscenaActividadesController > getController();
            controller.setConexion( conexion );
            // Una vez se establece la conexion cargamos datos
            controller.cargarDatos();

            Stage escenarioVentana = (Stage)botonEmpezar.getScene()
                .getWindow();
            escenarioVentana.setTitle( "Actividades" );
            // CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene( new Scene( root ) );
            

        }
        catch( Exception ex )
        {
            Alert alerta = new Alert( Alert.AlertType.ERROR );
            alerta.setContentText( "ERROR " + ex.getMessage() );
            alerta.showAndWait();
        }

    }

    public void setConexion( Connection conexion )
    {
        this.conexion = conexion;
    }

}
