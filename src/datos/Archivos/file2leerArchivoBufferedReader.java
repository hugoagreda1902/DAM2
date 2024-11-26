package datos.Archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Imprima la primera línea con si es 13 que pare, igual que cuenta  los espacios
public class file2leerArchivoBufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt"));
        String linea;
        while ((linea= br.readLine())!=null) {
            System.out.println(linea);
        }
        br.close();
    }
}
