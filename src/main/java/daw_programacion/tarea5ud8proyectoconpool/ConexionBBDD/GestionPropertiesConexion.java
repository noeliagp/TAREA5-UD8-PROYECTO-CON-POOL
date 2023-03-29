package daw_programacion.tarea5ud8proyectoconpool.ConexionBBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GestionPropertiesConexion {
  public static Properties cargarArchivo(FileInputStream fis) throws IOException{
      Properties miColeccion = new Properties();
      miColeccion.load(fis);
      return miColeccion;
  }
  public static Properties getProperties(String nombreArchivo) throws IOException{
      return cargarArchivo(new FileInputStream(nombreArchivo));
  }

}
