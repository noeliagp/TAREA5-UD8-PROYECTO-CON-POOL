package daw_programacion.tarea5ud8proyectoconpool.Controladores;

import daw_programacion.tarea5ud8proyectoconpool.AppIndex;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class AppControllerMenuPrincipal {
    @FXML
    private Label TextoCabecera;

    @FXML
    protected void onButtonCrearEmpleado() throws IOException {
        // ESCENA
        FXMLLoader cargador = new FXMLLoader(AppIndex.class.getResource("menucrear-view.fxml"));
        Parent menuCrearVista = cargador.load();


        // ESCENARIO
        Stage EscenarioMenuCrear = new Stage();
        EscenarioMenuCrear.setScene(new Scene(menuCrearVista));
        EscenarioMenuCrear.setTitle(" Menu Crear Empleado");
        EscenarioMenuCrear.show();
        // FINALIZAMOS EL ESCENARIO DE MENU PRINCIPAL Y GUARDAMOS LA REFERENCIA DE ESCENARIO DEL MENU CREAR EN EL CONTENEDOR
        AppIndex.getUltimoEscenario().close();

        AppIndex.setUltimoEscenario(EscenarioMenuCrear);
    }

    @FXML
    protected void onButtonMostrarEmpleados() throws IOException {
        // Escena
        FXMLLoader cargador = new FXMLLoader(AppIndex.class.getResource("menumostrar-view.fxml"));
        Parent menuMostrar = cargador.load();

        Stage EscenarioMenuMostrar = new Stage();
        EscenarioMenuMostrar.setScene(new Scene(menuMostrar));
        EscenarioMenuMostrar.setTitle(" Menu Mostrar Empleados");
        EscenarioMenuMostrar.show();

        // FINALIZAMOS EL ESCENARIO DE MENU PRINCIPAL Y GUARDAMOS LA REFERENCIA DE ESCENARIO DEL MENU MOSTRAR EN EL CONTENEDOR
        AppIndex.getUltimoEscenario().close();

        AppIndex.setUltimoEscenario(EscenarioMenuMostrar);
    }
    @FXML
    protected void onButtonModificarEmpleado() throws IOException {
// Escena
        FXMLLoader cargador = new FXMLLoader(AppIndex.class.getResource("menumodificar-view.fxml"));
        Parent menuModificar = cargador.load();

        Stage EscenarioMenuModificar = new Stage();
        EscenarioMenuModificar.setScene(new Scene(menuModificar));

        EscenarioMenuModificar.setTitle(" Menú Modificar Empleado");
        EscenarioMenuModificar.show();
        // FINALIZAMOS EL ESCENARIO DE MENU PRINCIPAL Y GUARDAMOS LA REFERENCIA DE ESCENARIO DEL MENU MODIFICAR EN EL CONTENEDOR
        AppIndex.getUltimoEscenario().close();

        AppIndex.setUltimoEscenario(EscenarioMenuModificar);
    }
    @FXML
    protected void onButtonBorrarEmpleado() throws IOException {
        FXMLLoader cargador = new FXMLLoader(AppIndex.class.getResource("menuborrar-view.fxml"));
        Parent menuBorrar = cargador.load();

        Stage EscenarioMenuBorrar = new Stage();
        EscenarioMenuBorrar.setScene(new Scene(menuBorrar));

        EscenarioMenuBorrar.setTitle(" Menú Borrar Empleado");
        EscenarioMenuBorrar.show();
        // FINALIZAMOS EL ESCENARIO DE MENU PRINCIPAL Y GUARDAMOS LA REFERENCIA DE ESCENARIO DEL MENU MODIFICAR EN EL CONTENEDOR
        AppIndex.getUltimoEscenario().close();

        AppIndex.setUltimoEscenario(EscenarioMenuBorrar);
    }
    @FXML
    protected void onButtonSalir() {
        System.exit(0);

    }
}