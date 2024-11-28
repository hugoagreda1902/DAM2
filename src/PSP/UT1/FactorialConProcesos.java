package EjerciciosAunMasConcretos.UT1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Desarrolla un programa que lance varios procesos para calcular el factorial de
// un número grande. Divide el cálculo entre los procesos y combina los resultados
// en el programa principal.

public class FactorialConProcesos {
    public static void main(String[] args) {
        int numero = 10; // Número grande para el cual calcular el factorial
        int numProcesos = 2; // Número de procesos a dividir el cálculo
        int rangoPorProceso = numero / numProcesos;

        // Lista para almacenar los procesos
        Process[] procesos = new Process[numProcesos];

        for (int i = 0; i < numProcesos; i++) {
            // Dividir el rango del cálculo entre los procesos
            int inicio = i * rangoPorProceso + 1;
            int fin = (i == numProcesos - 1) ? numero : (i + 1) * rangoPorProceso;

            try {
                // Ejecutar el comando para calcular el factorial
                ProcessBuilder pb = new ProcessBuilder("java", "FactorialProceso", String.valueOf(inicio), String.valueOf(fin));
                procesos[i] = pb.start();

                // Leer la salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(procesos[i].getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Proceso " + (i + 1) + ": " + line);
                }
            } catch (Exception e) {
                System.err.println("Error ejecutando el proceso de cálculo");
                e.printStackTrace();
            }
        }

        // Esperar que todos los procesos terminen
        for (Process p : procesos) {
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cálculo completo.");
    }
}

class FactorialProceso {
    public static void main(String[] args) {
        int inicio = Integer.parseInt(args[0]);
        int fin = Integer.parseInt(args[1]);
        long resultado = 1;

        // Calcular el factorial en el rango dado
        for (int i = inicio; i <= fin; i++) {
            resultado *= i;
        }

        System.out.println("Factorial parcial desde " + inicio + " hasta " + fin + ": " + resultado);
    }
}
