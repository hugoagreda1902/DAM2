package EjerciciosAunMasConcretos.UT1;

//Crea un programa que utilice ProcessBuilder para lanzar múltiples procesos que
// ejecuten el comando ping a diferentes direcciones IP. Recoge la salida de cada proceso
// y muéstrala en la consola.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiProcessPing {
    public static void main(String[] args) {
        // Lista de direcciones a las que hacer ping (compatible con Java 8)
        List<String> addresses = Arrays.asList("google.com", "github.com", "example.com");

        // Lista para guardar los procesos
        List<Process> processes = new ArrayList<>();

        for (String address : addresses) {
            try {
                // Crear un proceso que ejecute el comando "ping"
                ProcessBuilder pb = new ProcessBuilder("ping", "-c", "3", address);
                Process process = pb.start();
                processes.add(process);

                // Leer y mostrar la salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                System.out.println("Output for: " + address);
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.err.println("Error executing ping for: " + address);
                e.printStackTrace();
            }
        }

        // Esperar a que todos los procesos terminen
        for (Process process : processes) {
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                System.err.println("Process interrupted");
                e.printStackTrace();
            }
        }

        System.out.println("All processes completed.");
    }
}

