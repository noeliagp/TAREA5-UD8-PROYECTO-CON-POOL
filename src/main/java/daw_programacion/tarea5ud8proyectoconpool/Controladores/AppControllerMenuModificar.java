package daw_programacion.tarea5ud8proyectoconpool.Controladores;

import daw_programacion.tarea5ud8proyectoconpool.AppIndex;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDAO;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDTO;
import daw_programacion.tarea5ud8proyectoconpool.EmpleadoDaoBDMysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppControllerMenuModificar {
    @FXML
    private Label TextoCabecera;
    @FXML
    private TextField idEmpleadoBuscar;
    @FXML
    private TextFlow Cuadroinformacion;

    @FXML
    private TextField idEmpleadoModificar;
    @FXML
    private TextField nombreEmpleadoModificar;
    @FXML
    private TextField apellidosEmpleadoModificar;
    @FXML
    private TextField fechaNacimientoModificar;
    @FXML
    private TextField puestoEmpleadoModificar;
    @FXML
    private TextField emailEmpleadoModificar;

    @FXML
    private Text mensaje;
    @FXML
    protected void onClickButtonMODIFICIDAR() throws SQLException, IOException {
        var clases = Cuadroinformacion.getStyleClass();
        if (!(idEmpleadoModificar.getText().equals("") || nombreEmpleadoModificar.getText().equals("")) || apellidosEmpleadoModificar.getText().equals("") || fechaNacimientoModificar.getText().equals("") || puestoEmpleadoModificar.getText().equals("")) {
            // creamos un objeto de tipo TareaDTO
          EmpleadoDTO empleadoAModificar = new EmpleadoDTO(Integer.parseInt(idEmpleadoModificar.getText()), nombreEmpleadoModificar.getText(), apellidosEmpleadoModificar.getText(), fechaNacimientoModificar.getText(), puestoEmpleadoModificar.getText(), emailEmpleadoModificar.getText());

            int idEmpleadoABuscar = Integer.parseInt(this.idEmpleadoModificar.getText());
            boolean insertado = EmpleadoDaoBDMysql.obtenerInstancia().existeEmpleado(idEmpleadoABuscar);
            if (insertado) {

                EmpleadoDaoBDMysql.obtenerInstancia().update(empleadoAModificar, Integer.parseInt(idEmpleadoModificar.getText()));
                // EL TIPO DE MENSAJE QUE SE VA A MOSTRAR CON DEBIDA CLASE QUE ES UN ALERT
                clases.remove(1);
                clases.add("alert-success");
                mostrarMensaje("El empleado con los siguientes datos: " + "\n Id de empleado: " + Integer.parseInt(idEmpleadoModificar.getText()) + "\n Nombre empleado/a: " + nombreEmpleadoModificar.getText() + "\n Apellidos del empleado/a: " + apellidosEmpleadoModificar.getText() + "\n Fecha de nacimiento: " + fechaNacimientoModificar.getText() + "\n Puesto asignado: " + puestoEmpleadoModificar.getText() + "\n Email del empleado/a: " + emailEmpleadoModificar.getText() + "\n HA SIDO MODIFICADA CORRECTAMENTE.");
                // BORRAMOS EL TEXTO QUE HAYA DENTRO DE ESTOS CAMPOS PARA PODER INTRODUCIR LOS SIGUIENTES
                idEmpleadoModificar.clear();
                nombreEmpleadoModificar.clear();
                apellidosEmpleadoModificar.clear();
                fechaNacimientoModificar.clear();
                puestoEmpleadoModificar.clear();
                emailEmpleadoModificar.clear();
            } else {
                // EL TIPO DE MENSAJE QUE SE VA A MOSTRAR CON DEBIDA CLASE QUE ES UN ALERT
                clases.remove(1);
                clases.add("alert-danger");
                mostrarMensaje("El empleado con los siguientes datos: " + "\n Id de empleado: " + Integer.parseInt(idEmpleadoModificar.getText()) + "\n Nombre empleado/a: " + nombreEmpleadoModificar.getText() + "\n Apellidos del empleado/a: " + apellidosEmpleadoModificar.getText() + "\n Fecha de nacimiento: " + fechaNacimientoModificar.getText() + "\n Puesto asignado: " + puestoEmpleadoModificar.getText() + "\n Email del empleado/a: " + emailEmpleadoModificar.getText() + "\n NO SE HA MODIFICADO PORQUE NO EXISTE.");
                // BORRAMOS EL TEXTO QUE HAYA DENTRO DE ESTOS CAMPOS PARA PODER INTRODUCIR LOS SIGUIENTES
                idEmpleadoModificar.clear();
                nombreEmpleadoModificar.clear();
                apellidosEmpleadoModificar.clear();
                fechaNacimientoModificar.clear();
                puestoEmpleadoModificar.clear();
                emailEmpleadoModificar.clear();
            }
            // SI ALGUNO DE LOS CAMPOS ESTA VACIO HARÁ LO SIGUIENTE
        }else {
            //MOSTRAMOS EL MENSAJE A TRAVÉS DE UN ALERT
            clases.remove(1);
            clases.add("alert-danger");
            mostrarMensaje("El empleado NO se ha modificado ya que alguno campo esta vacío");
        }
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
    @FXML
    protected void onClickButtonBUSCARPORID() throws RuntimeException, SQLException {
        var clases = Cuadroinformacion.getStyleClass();
        if (!(idEmpleadoBuscar.getText().equals(""))){
           int idEmpleadoABuscar = Integer.parseInt(this.idEmpleadoBuscar.getText());
            try {
                ResultSet rs = EmpleadoDaoBDMysql.instancia.existeIdEmpleado(idEmpleadoABuscar);
                rs.next();
                if (rs.getInt(1) > 0) {
                    System.out.println("El empleado existe en la BBDD");
                    idEmpleadoModificar.setText("" + rs.getInt(1));
                    nombreEmpleadoModificar.setText(rs.getString(2));
                    apellidosEmpleadoModificar.setText(rs.getString(3));
                    fechaNacimientoModificar.setText(rs.getString(4));
                    puestoEmpleadoModificar.setText(rs.getString(5));
                    emailEmpleadoModificar.setText(rs.getString(6));
                }else{
                    // EL TIPO DE MENSAJE QUE SE VA A MOSTRAR CON DEBIDA CLASE QUE ES UN ALERT
                    clases.remove(1);
                    clases.add("alert-danger");
                    mostrarMensaje("El id del empleado a buscar no existe");

                }

                idEmpleadoModificar.setDisable(true);
                idEmpleadoBuscar.clear();
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
}
