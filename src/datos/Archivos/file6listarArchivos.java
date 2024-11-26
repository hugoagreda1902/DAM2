package datos.Archivos;
import java.io.File;

// Que cambie el nombre de los .jpg de una carpeta
public class file6listarArchivos {

    public static void main(String[] args) {
        File carpeta = new File("C:/Users/Alumno/Desktop/prueba1");

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        System.out.println(archivo.getName());
                    }
                }
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta.");
        }
    }

    private static boolean esImagen(File archivo) {
        return archivo.getName().endsWith(".jpg") || archivo.getName().endsWith(".jpeg");
    }
}
