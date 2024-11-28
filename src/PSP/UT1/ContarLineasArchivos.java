package EjerciciosAunMasConcretos.UT1;

import java.io.*;
import java.util.*;

//Crea un programa que lance múltiples procesos para contar las líneas de un
// archivo grande. Divide el archivo en partes iguales y cada proceso
// cuenta las líneas de su parte asignada. El programa principal recoge
// los resultados parciales y muestra el total de líneas.

public class ContarLineasArchivos {
    public static void main(String[] args) {
        String archivo = "archivo.txt"; // Archivo de ejemplo
        int numProcesos = 4; // Número de procesos para dividir la carga

        try {
            // Obtener el tamaño del archivo
            File file = new File(archivo);
            long fileSize = file.length();

            // Dividir el archivo en partes
            long chunkSize = fileSize / numProcesos;

            // Iniciar los procesos
            List<Process> procesos = new ArrayList<>();
            for (int i = 0; i < numProcesos; i++) {
                long inicio = i * chunkSize;
                long fin = (i == numProcesos - 1) ? fileSize : (i + 1) * chunkSize;

                ProcessBuilder pb = new ProcessBuilder("java", "ContarLineasProceso", archivo, String.valueOf(inicio), String.valueOf(fin));
                procesos.add(pb.start());
            }

            // Leer los resultados de cada proceso
            int totalLineas = 0;
            for (Process proceso : procesos) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    totalLineas += Integer.parseInt(line); // Sumar las líneas contadas
                }
                proceso.waitFor();
            }

            System.out.println("Total de líneas en el archivo: " + totalLineas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ContarLineasProceso {
    public static void main(String[] args) {
        String archivo = args[0];
        long inicio = Long.parseLong(args[1]);
        long fin = Long.parseLong(args[2]);

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            long currentPosition = 0;
            int contadorLineas = 0;

            // Saltar hasta la posición de inicio
            while (currentPosition < inicio && (linea = reader.readLine()) != null) {
                currentPosition += linea.length() + 1;
            }

            // Contar las líneas dentro del rango
            while (currentPosition <= fin && (linea = reader.readLine()) != null) {
                contadorLineas++;
                currentPosition += linea.length() + 1;
            }

            System.out.println(contadorLineas); // Imprimir el conteo de líneas

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

