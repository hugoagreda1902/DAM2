package datos.Archivos;
import java.io.FileWriter;
import java.io.IOException;

public class file3escribirTexto {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Alumno\\Desktop\\prueba.txt", true);
            // Escribe datos en memoria (RAM)
            writer.write("Esto se escribe en el archivo");
            // Cerrar el recurso y liberar memoria
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}