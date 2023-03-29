package daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD;
// Esta clase es para no tener que crear el archivo propiterties externo sino que todo esta en esta clase.

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSource {
    private static HikariConfig  config = new HikariConfig();

    private static HikariDataSource dataSource;
    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/crudEmpleados?allowPublicKeyRetrieval=true&useSSL=false");
        config.setUsername("usuarioempleado");
        config.setPassword("1234");
        config.addDataSourceProperty("maximunPoolSize",1);
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize","250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");

        dataSource = new HikariDataSource(config);

    }
    private MyDataSource(){};
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
