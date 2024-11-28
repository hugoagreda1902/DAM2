package EjerciciosAunMasConcretos.UT1;

import java.io.*;
import java.util.*;

//Diseña un programa que lance varios procesos para realizar cálculos matemáticos en paralelo.
// Cada proceso debe calcular la potencia de un número elevado a diferentes exponentes.
// Recoge los resultados y muestra el total en la consola.

public class CalculosMatematicos {
    public static void main(String[] args) {
        int base = 2; // Base para las potencias
        int numProcesos = 5; // Número de procesos
        List<Process> procesos = new ArrayList<>();

        try {
            // Crear procesos para calcular potencias
            for (int i = 1; i <= numProcesos; i++) {
                ProcessBuilder pb = new ProcessBuilder("java", "PotenciaProceso", String.valueOf(base), String.valueOf(i));
                procesos.add(pb.start());
            }

            // Leer los resultados
            for (Process proceso : procesos) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String result = reader.readLine();
                System.out.println("Resultado: " + result);
                proceso.waitFor();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class PotenciaProceso {
    public static void main(String[] args) {
        int base = Integer.parseInt(args[0]);
        int exponente = Integer.parseInt(args[1]);
        long resultado = (long) Math.pow(base, exponente);
        System.out.println("La potencia de " + base + " elevado a " + exponente + " es: " + resultado);
    }
}

