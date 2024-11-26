package datos.Archivos;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class file1metodosBasicos {
    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Alumno\\Desktop\\hola.txt");
        String nombre = archivo.getName();
        String ruta = archivo.getPath();
        String rutaABS = archivo.getAbsolutePath();
        boolean existir = archivo.exists();
        boolean escribir = archivo.canWrite();
        boolean leer = archivo.canRead();
        boolean fichero = archivo.isFile();
        boolean directorio = archivo.isDirectory();
        boolean dirABS = archivo.isAbsolute();
        long modificado = archivo.lastModified();
        long largo = archivo.length();
        // boolean mkdir = archivo.mkdir();
        // boolean mkdirs = archivo.mkdirs();

        if (archivo.exists()) {
            Date fechaModificacion = new Date(modificado);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
            String fechaFormateada = formato.format(fechaModificacion);

            System.out.println("Nombre del archivo: " + nombre);
            System.out.println("Ruta del archivo: " + ruta);
            System.out.println("Ruta del absoluta archivo: " + rutaABS);
            System.out.println("El archivo se escribir: " + escribir);
            System.out.println("El archivo se puede leer: " + leer);
            System.out.println("El archivo es un fichero: " + fichero);
            System.out.println("El archivo es un directorio: " + directorio);
            System.out.println("El archivo tiene una ruta absoluta: " + dirABS);
            System.out.println("El archivo cuando ha sido modificado:" + fechaModificacion);
            System.out.println("El archivo pesa: " + largo/1000000 + "MB");







        } else {
            System.out.println("No existe el archivo");
        }
    }
}
