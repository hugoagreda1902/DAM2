package EjerciciosAunMasConcretos.UT1;

import java.io.*;
import java.util.*;

//Crea un programa que lance varios procesos para calcular la media de un conjunto de números.
// Cada proceso calcula la media de un subconjunto de números y luego el programa principal
// recoge los resultados y calcula la media global.

public class MediaConProcesos {
    public static void main(String[] args) {
        int[] numeros = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int numProcesos = 2;
        List<Process> procesos = new ArrayList<>();

        try {
            // Dividir el trabajo entre los procesos
            int cantidadPorProceso = numeros.length / numProcesos;
            for (int i = 0; i < numProcesos; i++) {
                int inicio = i * cantidadPorProceso;
                int fin = (i == numProcesos - 1) ? numeros.length : (i + 1) * cantidadPorProceso;

                // Crear un proceso para calcular la media de su rango
                ProcessBuilder pb = new ProcessBuilder("java", "CalcularMediaProceso", Arrays.toString(Arrays.copyOfRange(numeros, inicio, fin)));
                procesos.add(pb.start());
            }

            // Recoger los resultados de cada proceso
            double sumaTotal = 0;
            int cantidadTotal = 0;
            for (Process proceso : procesos) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String line = reader.readLine();
                String[] partes = line.split(",");
                sumaTotal += Double.parseDouble(partes[0]);
                cantidadTotal += Integer.parseInt(partes[1]);
                proceso.waitFor();
            }

            double mediaGlobal = sumaTotal / cantidadTotal;
            System.out.println("La media global es: " + mediaGlobal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CalcularMediaProceso {
    public static void main(String[] args) {
        String[] numerosString = args[0].replaceAll("[\\[\\]]", "").split(",");
        int[] numeros = Arrays.stream(numerosString).mapToInt(Integer::parseInt).toArray();

        // Calcular la media del subconjunto
        double suma = 0;
        for (int numero : numeros) {
            suma += numero;
        }
        double media = suma / numeros.length;
        System.out.println(media + "," + numeros.length); // Retornar la suma y la cantidad de elementos
    }
}

