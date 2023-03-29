package daw_programacion.tarea5ud8proyectoconpool;

import daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD.MyDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// esta clase implementa la interfaz de EmpleadoDAO
public class EmpleadoDaoBDMysql implements EmpleadoDAO {

    public static EmpleadoDaoBDMysql instancia;

    static {
        instancia = new EmpleadoDaoBDMysql();
    }

    private EmpleadoDaoBDMysql() {
    }

    // método para poder obtener la instancia
    public static EmpleadoDaoBDMysql obtenerInstancia() {
        return instancia;
    }

    public boolean existeEmpleado(int id_empleado) throws SQLException {
        Connection bd = MyDataSource.getConnection();
        String sql = "SELECT count(*) FROM empleados WHERE id_empleado=?";
        PreparedStatement pstmt = bd.prepareStatement(sql);
        pstmt.setInt(1, id_empleado);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if (rs.getInt(1) > 0) {
            System.out.println("El registro ya existe en la BBDD");
            return true;
        }else {
            System.out.println("El registro no existe en la BBDD");
            return false;

        }


    }
    public ResultSet existeIdEmpleado(int id_empleado) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id_empleado=?";
        Connection bd = MyDataSource.getConnection();
        PreparedStatement pstmt = bd.prepareStatement(sql);
        pstmt.setInt(1, id_empleado);
        return pstmt.executeQuery();

    }


    public boolean create(EmpleadoDTO empleadoACrear) {
        boolean estaInsertado = false;
        try (Connection bd = MyDataSource.getConnection()){
            if (!existeEmpleado(empleadoACrear.idEmpleado())){
                String sql = "INSERT INTO empleados (id_empleado,nombre,apellidos,fecha_nacimiento,puesto,email) VALUES(?,?,?,?,?,?)";
                PreparedStatement pstmt = bd.prepareStatement(sql);
                pstmt.setInt(1,empleadoACrear.idEmpleado());
                pstmt.setString(2,empleadoACrear.nombreEmpleado());
                pstmt.setString(3,empleadoACrear.apellidosEmpleado());
                pstmt.setString(4,empleadoACrear.fechaNacimiento());
                pstmt.setString(5,empleadoACrear.puesto());
                pstmt.setString(6, empleadoACrear.emailEmpleado());
                if (pstmt.executeUpdate() !=0){
                    estaInsertado = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estaInsertado;
    }

    @Override
    public EmpleadoDTO readById(int id_empleado) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id_empleado=?";
        try (Connection bd = MyDataSource.getConnection()){
            PreparedStatement pstmt = bd.prepareStatement(sql);
            pstmt.setInt(1,id_empleado);
            ResultSet rs = pstmt.executeQuery();
            return new EmpleadoDTO(rs.getInt(1),rs.getNString(2), rs.getNString(3),rs.getString(4) ,rs.getNString(5),rs.getNString(6));
        }
    }

    @Override
    public List<EmpleadoDTO> readAll() throws SQLException {
        ArrayList<EmpleadoDTO> ArrayListDeEmpleados;
        try(Connection conn = MyDataSource.getConnection()){
            String sql = "SELECT * FROM empleados";
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            pstmt = conn.prepareStatement(sql);
            ArrayListDeEmpleados = new ArrayList<>();
            rs = pstmt.executeQuery();
            while (rs.next()) ArrayListDeEmpleados.add(new EmpleadoDTO(rs.getInt(1), rs.getNString(2), rs.getNString(3),rs.getString(4), rs.getNString(5), rs.getNString(6) ));

        }
        return ArrayListDeEmpleados;
    }



    @Override
    public boolean delete(int id_empleado) throws SQLException, IOException {
       boolean Borrado = false;
        String slq = "DELETE FROM empleados WHERE empleados.id_empleado= (?)";
        PreparedStatement pstmt = MyDataSource.getConnection().prepareStatement(slq);
        pstmt.setInt(1,id_empleado);
        if ( pstmt.executeUpdate() != 0){
            System.out.println("Registro borrado en la base de datos");
            Borrado=true;
        }
        return Borrado;
    }
    @Override
    public boolean update(EmpleadoDTO EmpleadoAModificar, int id_empleado) throws SQLException, IOException {
      boolean estaActualizado = false;
        try (Connection bd = MyDataSource.getConnection()){
            if (existeEmpleado(EmpleadoAModificar.idEmpleado())){
                String sql = "UPDATE empleados SET id_empleado = (?) ,nombre = (?),apellidos = (?),fecha_nacimiento = (?),puesto = (?),email =(?)  WHERE empleados.id_empleado=(?)";

                PreparedStatement pstmt = bd.prepareStatement(sql);

                pstmt.setInt(1,EmpleadoAModificar.idEmpleado());
                pstmt.setString(2,EmpleadoAModificar.nombreEmpleado());
                pstmt.setString(3,EmpleadoAModificar.apellidosEmpleado());
                pstmt.setString(4,EmpleadoAModificar.fechaNacimiento());
                pstmt.setString(5,EmpleadoAModificar.puesto());
                pstmt.setString(6, EmpleadoAModificar.emailEmpleado());
                pstmt.setInt(7,id_empleado);
                if (pstmt.executeUpdate() !=0){
                    System.out.println("Se ha actualizado el empleado en la base de datos");
                    estaActualizado = true;

                }
            }else {
                System.out.println("El empleado no esta insertado en la base de datos no se puede modificar");
            }

        return estaActualizado ;
        }
    }
}
