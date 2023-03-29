module com.example.tarea5ud8proyectoconpool {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    requires mysql.connector.j;
    requires java.sql;
    requires com.zaxxer.hikari;
    opens daw_programacion.tarea5ud8proyectoconpool to javafx.fxml;
    exports daw_programacion.tarea5ud8proyectoconpool;
    exports daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD;
    opens daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD to javafx.fxml;
    exports daw_programacion.tarea5ud8proyectoconpool.Controladores;
    opens daw_programacion.tarea5ud8proyectoconpool.Controladores to javafx.fxml;
}