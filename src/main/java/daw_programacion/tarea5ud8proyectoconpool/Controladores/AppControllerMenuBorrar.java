package daw_programacion.tarea5ud8proyectoconpool.Controladores;

import daw_programacion.tarea5ud8proyectoconpool.AppIndex;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDTO;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDaoBDMysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppControllerMenuBorrar implements Initializable {
    @FXML
    private TextFlow Cuadroinformacion;

    @FXML
    private Text mensaje;
    @FXML
    private VBox EmpleadosMostrar;
    @FXML
    private TextField idEmpleadoBorrar;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<EmpleadoDTO> EmpleadoDAOlist = null;
        try {
            EmpleadoDAOlist = EmpleadoDaoBDMysql.obtenerInstancia().readAll();

            EmpleadoDAOlist.forEach(lamda -> {
                try {
                    crearFilaTarea(lamda.idEmpleado(), lamda.nombreEmpleado(), lamda.apellidosEmpleado(), lamda.fechaNacimiento(), lamda.puesto(), lamda.emailEmpleado());

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

    private void crearFilaTarea(int idEmpleado, String nombreEmpleado, String apellidosEmpleado, String fechaNacimiento,
                                String puesto, String emailEmpleado) throws IOException, SQLException {
        // GUARDAMOS EN UNA VARIABLE EL TITULO Y LA DESCRIPCION AUN NO ES NECESARIO
        var idEmpleadoAñadir = new Text("" + idEmpleado);
        // ANCHO QUE OCUPA EL TITULO
        //   idEmpleadoAñadir.setWrappingWidth(10);
        var nombreEmpleadoAñadir = new Text(nombreEmpleado);
        var apellidosEmpleadoAñadir = new Text(apellidosEmpleado);
        var fechaNacimientoEmpleadoAñadir = new Text(fechaNacimiento.toString());
        var puestoEmpleadoAñadir = new Text(puesto);
        var emailEmpleadoAñadir = new Text(emailEmpleado);


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
        FilaNueva.setSpacing(120);
        // EN EN VBOX AGREGAMOS EL HBOX QUE HEMOS CREADO ARRIBA QUE ES FILANUEVA
        EmpleadosMostrar.getChildren().add(FilaNueva);


    }

    private void borrarFilaEmpleado(){
        EmpleadosMostrar.getChildren().clear();
        List<EmpleadoDTO> EmpleadoDAOlist = null;
        try {
            EmpleadoDAOlist = EmpleadoDaoBDMysql.obtenerInstancia().readAll();

            EmpleadoDAOlist.forEach(lamda -> {
                try {
                    crearFilaTarea(lamda.idEmpleado(), lamda.nombreEmpleado(), lamda.apellidosEmpleado(), lamda.fechaNacimiento(), lamda.puesto(), lamda.emailEmpleado());

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

    @FXML
    protected void mostrarMensaje(String msj) {
        mensaje.setText(msj);
        Cuadroinformacion.setVisible(true);
    }

    @FXML
    protected void ocultar() {
        Cuadroinformacion.setVisible(false);
    }


    public void onClickButtonBORRARPORID() throws  IOException ,SQLException {
        if (!(idEmpleadoBorrar.getText().equals(""))) {
            int idEmpleadoABorrar = Integer.parseInt(this.idEmpleadoBorrar.getText());
            try {
                boolean estainsertado = false;
                if (estainsertado = true) {
                    EmpleadoDaoBDMysql.obtenerInstancia().delete(idEmpleadoABorrar);
                    borrarFilaEmpleado();
                    mostrarMensaje("Se ha borrado con existo el empleado con id : " + Integer.parseInt(String.valueOf(this.idEmpleadoBorrar.getText())));


                    idEmpleadoBorrar.clear();
                }else {
                    estainsertado = false;
                    mostrarMensaje("No existe el id de empleado: " + Integer.parseInt(String.valueOf(this.idEmpleadoBorrar.getText())));
                    idEmpleadoBorrar.clear();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

