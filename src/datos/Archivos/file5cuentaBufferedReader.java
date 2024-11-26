package datos.Archivos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class file5cuentaBufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt"));
        int contador = 0;
        String linea;
        while ((linea= br.readLine())!=null) {
            contador++;
            System.out.println(linea);
        }
        System.out.println("Se ha usado el bufered reader " + contador + " veces");
        br.close();
    }
}