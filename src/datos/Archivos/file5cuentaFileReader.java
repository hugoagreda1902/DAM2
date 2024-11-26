package datos.Archivos;
import java.io.FileReader;
import java.io.IOException;

public class file5cuentaFileReader {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt");
            int i;
            int contador = 0;
            // Lee el arhcivo caracter por caracter
            while ((i = fr.read()) != -1 ) {
                contador++;
                System.out.print(+(int) i +1);

            }
            fr.close();
            System.out.println("\n Se ha usado el filereader " + contador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
