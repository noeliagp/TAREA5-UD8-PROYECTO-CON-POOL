package daw_programacion.tarea5ud8proyectoconpool.Controladores;

import daw_programacion.tarea5ud8proyectoconpool.AppIndex;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDTO;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDaoBDMysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppControllerMenuMostrar implements Initializable {
    @FXML
    private Label TextoCabecera;

    @FXML
    private VBox MostrarEmpleados;

    @FXML
    protected void onButtonMenuPrincipal() throws IOException {
        FXMLLoader cargador = new FXMLLoader(AppIndex.class.getResource("menuprincipal-view.fxml"));
        Parent menuMenuPrincipal = cargador.load();

        Stage EscenarioMenuCrear = new Stage();
        EscenarioMenuCrear.setScene(new Scene(menuMenuPrincipal,350, 350));
        EscenarioMenuCrear.setTitle("Menu Principal");
        EscenarioMenuCrear.show();

        AppIndex.getUltimoEscenario().close();
        AppIndex.setUltimoEscenario(EscenarioMenuCrear);

    }

    private void crearFilaTarea(int idEmpleado, String nombreEmpleado, String apellidosEmpleado, String fechaNacimiento,
                                String puesto, String emailEmpleado) throws IOException, SQLException {
        // GUARDAMOS EN UNA VARIABLE EL TITULO Y LA DESCRIPCION AUN NO ES NECESARIO
        var idEmpleadoAñadir = new Text("" + idEmpleado);
        // ANCHO QUE OCUPA EL TITULO
        idEmpleadoAñadir.setWrappingWidth(60);
        var nombreEmpleadoAñadir = new Text(nombreEmpleado);
        nombreEmpleadoAñadir.setWrappingWidth(70);
        var apellidosEmpleadoAñadir = new Text(apellidosEmpleado);
        apellidosEmpleadoAñadir.setWrappingWidth(60);
        var fechaNacimientoEmpleadoAñadir = new Text(fechaNacimiento);
        fechaNacimientoEmpleadoAñadir.setWrappingWidth(70);
        var puestoEmpleadoAñadir = new Text(puesto);
        puestoEmpleadoAñadir.setWrappingWidth(80);
        var emailEmpleadoAñadir = new Text(emailEmpleado);
        emailEmpleadoAñadir.setWrappingWidth(150);


        // CREAMOS UN NUEVO HBox QUE SERÁ LA FILANUEVA POR CADA EMPLEADO NUEVA

        HBox FilaNueva = new HBox();

        // AGREGAMOS LOS HIJOS QUE SERÁ TODOS LOS CAMPOS DE LOS DATOS DEL EMPLEADO
        FilaNueva.getChildren().add(idEmpleadoAñadir);

        FilaNueva.getChildren().add(nombreEmpleadoAñadir);
        FilaNueva.getChildren().add(apellidosEmpleadoAñadir);
        FilaNueva.getChildren().add(fechaNacimientoEmpleadoAñadir);
        FilaNueva.getChildren().add(puestoEmpleadoAñadir);
        FilaNueva.getChildren().add(emailEmpleadoAñadir);

        // LOS ESPACIOS ENTRE LOS CAMPOS
        FilaNueva.setSpacing(100);
        // EN EN VBOX AGREGAMOS EL HBOX QUE HEMOS CREADO ARRIBA QUE ES FILANUEVA
        MostrarEmpleados.getChildren().add(FilaNueva);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<EmpleadoDTO> EmpleadoDAOlist = null;
        try {
            EmpleadoDAOlist = EmpleadoDaoBDMysql.obtenerInstancia().readAll();

            EmpleadoDAOlist.forEach(lamda -> {
                try {
                    crearFilaTarea(lamda.idEmpleado(), lamda.nombreEmpleado(),lamda.apellidosEmpleado(),lamda.fechaNacimiento(), lamda.puesto(), lamda.emailEmpleado());

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}