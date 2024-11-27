package PSP.ejerciciosResueltos.Muliproceso;

import java.io.BufferedReader; // Importamos BufferedReader para leer la salida del proceso
import java.io.InputStreamReader; // Importamos InputStreamReader para leer el flujo de entrada del proceso
import java.io.IOException; // Importamos IOException para manejar posibles errores de entrada/salida
import java.util.ArrayList; // Importamos ArrayList para manejar la lista de procesos
import java.util.Arrays; // Importamos Arrays para convertir la lista de direcciones en una lista
import java.util.List; // Importamos List para manejar listas de direcciones y procesos

public class multiProcessPing {
    public static void main(String[] args) {
        // Lista de direcciones a las que hacer ping
        List<String> addresses = Arrays.asList("google.com", "github.com", "example.com");

        // Lista para guardar los procesos creados
        List<Process> processes = new ArrayList<>();

        // Iteramos sobre las direcciones para ejecutar un ping
        for (String address : addresses) {
            try {
                // Creamos un ProcessBuilder que ejecuta el comando "ping"
                ProcessBuilder pb = new ProcessBuilder("ping", "-c", "3", address);
                Process process = pb.start(); // Iniciamos el proceso de ping
                processes.add(process); // Agregamos el proceso a la lista de procesos

                // Leemos la salida del proceso usando BufferedReader
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    System.out.println("Output for: " + address); // Mostramos cuál dirección estamos haciendo ping
                    // Leemos y mostramos línea por línea la salida del ping
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line); // Mostramos cada línea de la salida
                    }
                } catch (IOException e) { // En caso de error al leer la salida
                    System.err.println("Error reading the output of the ping process for: " + address);
                    e.printStackTrace(); // Imprimimos el error
                }
            } catch (Exception e) { // En caso de error al ejecutar el ping
                System.err.println("Error executing ping for: " + address);
                e.printStackTrace(); // Imprimimos el error
            }
        }

        // Esperamos que todos los procesos terminen y verificamos el código de salida de cada uno
        for (Process process : processes) {
            try {
                int exitCode = process.waitFor(); // Esperamos a que el proceso termine y obtenemos su código de salida
                if (exitCode != 0) { // Si el código de salida es distinto de 0, hubo un error
                    System.err.println("Ping process finished with an error. Exit code: " + exitCode);
                }
            } catch (InterruptedException e) { // Si el proceso fue interrumpido
                System.err.println("Process interrupted");
                e.printStackTrace(); // Imprimimos el error
            }
        }
        System.out.println("All processes completed."); // Informamos que todos los procesos han terminado
    }
}
