package datos;

import java.io.FileReader;
import java.io.IOException;

public class AtletaOlimpico {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Alumno\\Desktop\\TiemposOlimpicos.txt");
            int c;
            boolean saltoDeLinea = true;
            int contadorLineas = 0;
            boolean esEspacio = true;
            int contadorPalabras = 0;

            while ((c = fileReader.read()) != -1) {
                if (c == 10) {
                    saltoDeLinea = true;
                } else {
                    if (saltoDeLinea) {
                        contadorPalabras++;
                    }
                    saltoDeLinea = false;
                }
                if (c == 32) {
                    esEspacio = true;
                } else {
                    if (esEspacio) {
                        contadorLineas++;
                    }
                    esEspacio = false;
                }
            }
            System.out.println(contadorLineas);
            System.out.println(contadorPalabras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}