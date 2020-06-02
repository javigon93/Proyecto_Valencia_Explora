/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 34679
 */
public class EscenaAyudaMantenimientoController implements Initializable {
    private int modo=0;
    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelDescrGen;
    @FXML
    private Label labelDescElementos;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private Label labelDescrFuncion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(modo);
        
        
    }    
    
    private void cargarAyudaInsertar(){
    
        labelTitulo.setText("Ayuda en ventana 'Insertar actividad'");
        labelDescrFuncion.setText("Esta ventana tiene como única funcion proporcionarte las herramientas para añadir una nueva actividad al registro\n"
                + "\nSimplemente rellena los campos descritos a continuación:");
        labelDescrGen.setText("Existen varios a rellenar, los cuales se incluyen:"
                + "\n\nTipo: Aquí se mostrará un desplegable con todos los tipos de actividad disponibles."
                + "\nSubtipo: Aquí se mostrará, DESPUÉS de seleccionar un tipo, sus subtipos asociados disponibles."
                + "\n\nNombre: Aquí introduce el nombre de la actividad.\n"
                + "\nDescripción: Inserta aquí la descripción que desear otorgar a la Actividad"
                + "\nURL: En este apartado deberás insertar la URL de la actividad. La aplicación posee un sistema reconocimiento de URL correctas, por lo que deberás insertar mediante copia y pega las URL"
                );
        labelDescElementos.setText("Esta funcionalidad posee elementos interesantes como:\n\n"
                + "Boton Imagen: Selecciona este botón para poder seleccionar una imagen, a través de la apertura de una ventana sencilla de usar. Tras la seleccion de la imagen se mostrará su URL para facilitar su reconocimiento"
                + "\nBoton Guardar: Selecciona este botón para poder guardar los cambios, se te preguntará acerca si estás seguro de hacerlo");
        Image imagen= new Image("/IMG/camera.png");
        image1.setImage(imagen);
        imagen= new Image("/IMG/anadir.png");
        image2.setImage(imagen);
    
    
    }
    
     private void cargarAyudaModificarBorrar(){
       
        labelTitulo.setText("Ayuda en ventana 'Modificar/Borrar'");
        labelDescrFuncion.setText("Esta ventana tiene como única funcion proporcionarte las herramientas para poder borrar o modificar actividades ya registradas\n"
                + "\nSimplemente rellena los campos descritos a continuación (RECUERDA: selecciona previamente una actividad):");
        labelDescrGen.setText("Existen varios a rellenar, los cuales se incluyen:"
                + "\n\nTipo: Aquí se mostrará un desplegable con todos los tipos de actividad disponibles."
                + "\n\nSubtipo: Aquí se mostrará, DESPUÉS de seleccionar un tipo, sus subtipos asociados disponibles."
                + "\n\nNombre: Aquí introduce el nombre de la actividad.\n"
                + "\n\nDescripción: Inserta aquí la descripción que desear otorgar a la Actividad."
                + "\n\nURL: En este apartado deberás insertar la URL de la actividad. La aplicación posee un sistema reconocimiento de URL correctas, por lo que deberás insertar mediante copia y pega las URL."
                );
        labelDescElementos.setText("Esta funcionalidad posee elementos interesantes como:\n\n"
                + "Boton Imagen: Selecciona este botón para poder seleccionar una imagen, a través de la apertura de una ventana sencilla de usar."
                + "\nBoton Borrar: Selecciona este botón para poder borrar la actividad, se te preguntará acerca si estás seguro de hacerlo."
                + "\nBoton Modificar: Selecciona este botón para poder Modificar la actividad seleccionada, se te preguntará acerca de ello."
               );
        Image imagen= new Image("/IMG/camera.png");
        image1.setImage(imagen);
        imagen= new Image("/IMG/eliminar.png");
        image2.setImage(imagen);
        imagen=new Image("/IMG/menos.png");
        image3.setImage(imagen);
    
}  private void cargarAyudaCreaciónPack(){
       
        labelTitulo.setText("Ayuda en ventana 'Eleccion de tu Pack'");
        labelDescrFuncion.setText("Este es el núcleo de tus deseos, ¡donde puedes seleccionar las actividades de tu pack a tu gusto!\n"
                + "\nSimplemente selecciona la actividad que prefieras, rellena los campos mosstrados y añade la actividad a tu pack. :");
        labelDescrGen.setText("Existen varios campos a rellenar tras seleccionar la actividad, los cuales se incluyen:"
                + "\n\nNºde Personas: Selecciona el número de personas que participarán en la actividad."
                + "\n\nFecha Inicio: Selecciona fecha/hora de tu actividad."
                + "\n\nFecha Fin: Selecciona la fecha/hora de finalización de tu actividad.\n"
                + "\n\nPrecio Unitario: Inserta aquí el precio de la actividad."
                );
        labelDescElementos.setText("Esta funcionalidad posee elementos interesantes como:\n\n"
                + "Boton Borrar: Botón creado para resetear tu pack si no estás satisfecho."
                + "\nBoton Más Información: Botón que te redirige a la web de Oficial de la Actividad."
                + "\nBoton Añadir: Donde se te pregunta si deseas añadir la actividad. Si seleccionas OK, la verás registrada."
                + "\nBoton Revisar/Confirmar: Donde te redirigimos a un resumen de tu pack, donde podrás personalizarlo."
               );
       
    
}
     
    public void metodoEjecutaAlInicio() {
          labelTitulo.setStyle(" -fx-font-weight:bold;");
        switch (modo) {
            case 1:
                cargarAyudaInsertar();
                break;
            case 2:
                cargarAyudaModificarBorrar();
                break;
            case 3:
                cargarAyudaCreaciónPack();
                break;
            default:
                break;
        }
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
     
}