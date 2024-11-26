package datos.Archivos;
import java.io.FileWriter;
import java.io.IOException;

public class file3sustituirTexto {
    public static void main(String[] args) {
        try {
            FileWriter archivo = new FileWriter("C:\\Users\\Alumno\\Desktop\\prueba.txt");
            archivo.write("Nombre del archivo");
            archivo.close();
            System.out.println("Archivo modificado correctamente");;
        }catch (IOException e) {
            System.out.println("Error");
        }
    }
}
