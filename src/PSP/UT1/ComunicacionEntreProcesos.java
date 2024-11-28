package EjerciciosAunMasConcretos.UT1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Diseña un programa que:
//Lance un proceso secundario que calcule el cuadrado de un número recibido como argumento.
//Reciba el resultado del proceso y lo muestre en la consola.

public class ComunicacionEntreProcesos {
    public static void main(String[] args) {
        // Número a calcular el cuadrado
        int numero = 5;

        try {
            // Crear el proceso que ejecutará el comando para calcular el cuadrado
            ProcessBuilder pb = new ProcessBuilder("java", "CalcularCuadrado", String.valueOf(numero));
            Process proceso = pb.start();

            // Leer el resultado del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Resultado del proceso: " + line);
            }

            proceso.waitFor(); // Espera que el proceso termine

        } catch (Exception e) {
            System.err.println("Error al ejecutar el proceso");
            e.printStackTrace();
        }
    }
}

class CalcularCuadrado {
    public static void main(String[] args) {
        int numero = Integer.parseInt(args[0]);
        int resultado = numero * numero;
        System.out.println("El cuadrado de " + numero + " es: " + resultado);
    }
}

