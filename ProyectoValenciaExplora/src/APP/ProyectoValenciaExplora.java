package APP;

import DatosBDA.Packs_DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import VISTA.FXMLInicialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicacion, contiene una sola conexion que se pasa como
 * parametro a los controladores Cuando se cierra la aplicacion la conexion a la
 * base de datos tambien se cierra
 * 
 * @author juanf
 */
public class ProyectoValenciaExplora extends Application{

	private Connection conexion;

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/VISTA/FXMLInicial.fxml"));
		Parent root = loader.load();
		FXMLInicialController controller = loader.<FXMLInicialController>getController();
		controller.setConexion(conexion);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		conectarBasedeDatos();
		super.init();
	}

	private void conectarBasedeDatos() {
		String bd = "esquema_proyecto";
		String usuario = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/" + bd + "?serverTimezone=UTC";
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			System.err.println("Fallo en la conexion: " + e.getMessage());
		}
	}

	@Override
	public void stop() throws Exception {
		// Si la conexion esta activa la cerramos
		if (conexion != null) {
			conexion.close();
		}
		super.stop();
	}

}
