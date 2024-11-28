package EjerciciosAunMasConcretos.UT1;

import java.io.*;

//Diseña un programa que lance un proceso secundario para sumar dos números.
// El proceso secundario recibirá los números como argumentos,
// realizará la suma y devolverá el resultado al programa principal, que lo imprimirá en la consola.

public class SumaEntreProcesos {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 10;

        try {
            // Lanzar el proceso para sumar los dos números
            ProcessBuilder pb = new ProcessBuilder("java", "SumarProceso", String.valueOf(num1), String.valueOf(num2));
            Process proceso = pb.start();

            // Leer el resultado del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String resultado = reader.readLine();
            System.out.println("Resultado de la suma: " + resultado);

            proceso.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SumarProceso {
    public static void main(String[] args) {
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        int suma = num1 + num2;
        System.out.println("La suma de " + num1 + " y " + num2 + " es: " + suma);
    }
}

