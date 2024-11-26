package datos.Archivos;
import java.io.FileReader;
import java.io.IOException;

public class file2leerArchivoFileReader {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt");
            int i;

            // Lee el archivo carácter por carácter
            while ((i = fr.read()) != -1) {
                System.out.print((char) i); // Convierte el entero a char y lo imprime
            }
            fr.close(); // Cierra el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
