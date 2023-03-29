package daw_programacion.tarea5ud8proyectoconpool.Controladores;

import daw_programacion.tarea5ud8proyectoconpool.AppIndex;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDTO;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDaoBDMysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AppControllerMenuCrear {
    @FXML
    private Label TextoCabecera;
    @FXML
    private VBox CuadroCrear;
    @FXML
    private TextFlow Cuadroinformacion;

    @FXML
    private Text mensaje;
    @FXML
    private TextField idEmpleadoCrear;
    @FXML
    private TextField nombreEmpleadoCrear;
    @FXML
    private TextField apellidoEmpleadoCrear;
    @FXML
    private TextField fechaNacimientoCrear;
    @FXML
    private TextField puestoEmpleadoCrear;
    @FXML
    private TextField emailEmpleadoCrear;

    @FXML
    protected void onClickButtonCREAR() throws SQLException, IOException {
        var clases = Cuadroinformacion.getStyleClass();
        // SI LOS CAMPOS NO ESTAN VACIOS HARÁN LO SIGUIENTE
        if (!(idEmpleadoCrear.getText().equals("") || nombreEmpleadoCrear.getText().equals("")) || apellidoEmpleadoCrear.getText().equals("") || fechaNacimientoCrear.getText().equals("") || puestoEmpleadoCrear.getText().equals("")){

            // creamos un objeto de tipo TareaDTO
            EmpleadoDTO empleadoACrear = new EmpleadoDTO(Integer.parseInt(idEmpleadoCrear.getText()), nombreEmpleadoCrear.getText(),apellidoEmpleadoCrear.getText(),fechaNacimientoCrear.getText(),puestoEmpleadoCrear.getText(),emailEmpleadoCrear.getText());
            // CREAMOS UNA VARIABLE BOOLEAN HACIENDO REFERENCIA AL PATRON SINGLETON
            boolean insertado = EmpleadoDaoBDMysql.obtenerInstancia().create(empleadoACrear);


            // CREARMOS LAS FILA, CREAMOS LA TAREA Y LA GUARDAMOS
            if (insertado) {

                // EL TIPO DE MENSAJE QUE SE VA A MOSTRAR CON DEBIDA CLASE QUE ES UN ALERT
                clases.remove(1);
                clases.add("alert-success");
                mostrarMensaje("El empleado con los siguientes datos: " + "\n Id de empleado: " +Integer.parseInt(idEmpleadoCrear.getText())+"\n Nombre empleado/a: " + nombreEmpleadoCrear.getText()+ "\n Apellidos del empleado/a: " + apellidoEmpleadoCrear.getText() + "\n Fecha de nacimiento: " + fechaNacimientoCrear.getText() + "\n Puesto asignado: " + puestoEmpleadoCrear.getText() + "\n Email del empleado/a: " + emailEmpleadoCrear.getText() + "\n HA SIDO CREADA CORRECTAMENTE." );
                // BORRAMOS EL TEXTO QUE HAYA DENTRO DE ESTOS CAMPOS PARA PODER INTRODUCIR LOS SIGUIENTES
                idEmpleadoCrear.clear();
                nombreEmpleadoCrear.clear();
                apellidoEmpleadoCrear.clear();
                fechaNacimientoCrear.clear();
                puestoEmpleadoCrear.clear();
                emailEmpleadoCrear.clear();
            } else {
                // EL TIPO DE MENSAJE QUE SE VA A MOSTRAR CON DEBIDA CLASE QUE ES UN ALERT
                clases.remove(1);
                clases.add("alert-danger");
                mostrarMensaje("El empleado con los siguientes datos: " + "\n Id de empleado: " +Integer.parseInt(idEmpleadoCrear.getText())+"\n Nombre empleado/a: " + nombreEmpleadoCrear.getText()+ "\n Apellidos del empleado/a: " + apellidoEmpleadoCrear.getText() + "\n Fecha de nacimiento: " + fechaNacimientoCrear.getText() + "\n Puesto asignado: " + puestoEmpleadoCrear.getText() + "\n Email del empleado/a: " + emailEmpleadoCrear.getText()   +"\n NO SE HA CREADO PORQUE YA EXISTE.");
                // BORRAMOS EL TEXTO QUE HAYA DENTRO DE ESTOS CAMPOS PARA PODER INTRODUCIR LOS SIGUIENTES
                idEmpleadoCrear.clear();
                nombreEmpleadoCrear.clear();
                apellidoEmpleadoCrear.clear();
                fechaNacimientoCrear.clear();
                puestoEmpleadoCrear.clear();
                emailEmpleadoCrear.clear();
            }
        }
        // SI ALGUNO DE LOS CAMPOS ESTA VACIO HARÁ LO SIGUIENTE
        else {
            // BORRAMOS ESA FILA DE DATOS Y MOSTRAMOS EL MENSAJE A TRAVÉS DE UN ALERT
            clases.remove(1);
            clases.add("alert-danger");
            mostrarMensaje("La tarea NO se ha creado ya que alguno campo esta vacío");
        }

    }
    @FXML
    protected void mostrarMensaje(String msj) {
        mensaje.setText(msj);
        Cuadroinformacion.setVisible(true);
    }

    @FXML
    protected void ocultar() {
        Cuadroinformacion.setVisible(false);
    }
    @FXML
    protected void onButtonMenuPrincipal() throws IOException {
        FXMLLoader cargador = new FXMLLoader(AppIndex.class.getResource("menuprincipal-view.fxml"));
        Parent menuMenuPrincipal = cargador.load();

        Stage EscenarioMenuCrear = new Stage();
        EscenarioMenuCrear.setScene(new Scene(menuMenuPrincipal, 350, 350));

        EscenarioMenuCrear.setTitle("Menu Principal");
        EscenarioMenuCrear.show();

        AppIndex.getUltimoEscenario().close();
        AppIndex.setUltimoEscenario(EscenarioMenuCrear);

    }
}

