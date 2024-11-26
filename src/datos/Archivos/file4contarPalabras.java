package datos.Archivos;
import java.io.FileReader;
import java.io.IOException;

public class file4contarPalabras {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Alumno\\Desktop\\prueba.txt");
            int c;
            boolean esEspacio = true;
            int contadorPalabras = 0;

            while ((c = fileReader.read()) != -1) {
                if (Character.isWhitespace(c)) {
                    esEspacio = true;
                } else {
                    if (esEspacio) {
                        contadorPalabras++;
                    }
                    esEspacio = false;
                }
            }

            fileReader.close();
            System.out.println("Número de palabras: " + contadorPalabras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
