package datos.Archivos;
import java.io.FileReader;
import java.io.IOException;

// Proximo: cuantas palabras y cuantos espacios

public class file2leerCaracteres {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt");

            int i;
            int contador = 0;
            // Lee el arhcivo caracter por caracter
            while ((i = fr.read()) != -1 && contador < 2) {
                if (i == 32) {
                    contador++;
                }
                System.out.println((char) i); //Letras
                System.out.println((Integer.toBinaryString(i))); //Bit
                System.out.println((int) i);

            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
