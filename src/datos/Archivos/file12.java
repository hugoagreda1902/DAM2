package datos.Archivos;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class file12 {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("C:/Users/Alumno/Downloads/cuadradosPrintWriter.txt"));

            for (int i = 1; i <= 10; i++) {
                writer.printf("%-6d\n%-6d%n", i, i * i);
            }
            writer.close();
            System.out.println("Números y sus cuadrados escritos en cuadradosPrintWriter.txt.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}