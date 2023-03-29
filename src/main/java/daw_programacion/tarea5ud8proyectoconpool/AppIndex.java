package daw_programacion.tarea5ud8proyectoconpool;

import daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD.MyDataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class AppIndex extends Application {
    private  static Stage UltimoEscenario;

    // MÉTODOS

    public static Stage getUltimoEscenario(){
        return UltimoEscenario;
    }

    public static void setUltimoEscenario(Stage ultimoEscenario){
        AppIndex.UltimoEscenario = ultimoEscenario;
    }
    @Override
    public void start(Stage EscenarioMenuPrincipal) throws IOException {
       // DISPOSICIONES
        FXMLLoader fxmlLoader = new FXMLLoader(AppIndex.class.getResource("menuprincipal-view.fxml"));

      // ESCENARIO
        Scene escenaMenuPrincipal = new Scene(fxmlLoader.load(),350, 350);
        escenaMenuPrincipal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        EscenarioMenuPrincipal.setTitle("Gestión de empleados!");

        EscenarioMenuPrincipal.setScene(escenaMenuPrincipal);
        EscenarioMenuPrincipal.show();
        UltimoEscenario = EscenarioMenuPrincipal;



    }


    public static void main(String[] args) throws SQLException {
        Connection bd = MyDataSource.getConnection();
        if (bd!=null) System.out.println("Conexión a la BBDD" + bd);

        launch();
    }
}