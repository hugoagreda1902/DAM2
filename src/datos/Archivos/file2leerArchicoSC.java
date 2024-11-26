package datos.Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class file2leerArchicoSC {
    public static void main(String[] args) {
        try {
            File archivo = new File("C:\\Users\\Alumno\\Desktop\\prueba.txt");
            Scanner leer = new Scanner(archivo);
            while (leer.hasNextLine()) {
                String linea = leer.nextLine();
                System.out.println(linea);
            }
            leer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        }
    }
}
