package daw_programacion.tarea5ud8proyectoconpool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EmpleadoDAO {
    // estos métodos interaccionan con la base de datos

    public boolean create(EmpleadoDTO tarea) throws SQLException;


    public EmpleadoDTO readById(int id_empleado) throws SQLException;

    List<EmpleadoDTO> readAll() throws SQLException;

    public boolean update(EmpleadoDTO Empleado,int id_empleado) throws SQLException, IOException;

    public boolean delete(int id_empleado) throws SQLException, IOException;

}
