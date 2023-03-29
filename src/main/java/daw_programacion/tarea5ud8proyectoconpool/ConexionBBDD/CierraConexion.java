package daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CierraConexion extends Thread{
    @Override
    public void run(){

        // obtener la referencia de la conexion que ya esta abierta
        Connection refConnAbierta = null;
        try {
            refConnAbierta = MyDataSource.getConnection();
            refConnAbierta.close();
            System.out.println("Se ha cerrado la conexión con la Base de Datos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
