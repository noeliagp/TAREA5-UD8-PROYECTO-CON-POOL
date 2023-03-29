package daw_programacion.tarea5ud8proyectoconpool;

import com.mysql.cj.util.StringInspector;

import java.time.LocalDate;
import java.util.Date;

public record EmpleadoDTO(int idEmpleado, String nombreEmpleado, String apellidosEmpleado, String fechaNacimiento,
                          String puesto,String emailEmpleado) {
}
