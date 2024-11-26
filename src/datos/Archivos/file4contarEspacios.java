package datos.Archivos;
import java.io.FileReader;
import java.io.IOException;

// Cuantas palabras y cuantos espacios

public class file4contarEspacios {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt");
            int i;
            int contador = 0;
            while ((i = fr.read()) != -1) {
                if (i == 32) {
                    contador++;
                }
            }
            System.out.println(contador+1);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
