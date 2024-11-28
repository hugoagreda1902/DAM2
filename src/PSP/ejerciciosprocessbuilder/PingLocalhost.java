package ejerciciosprocessbuilder;

import java.io.*;

public class PingLocalhost {
    public static void main(String[] args) {
        // Configuramos el comando para hacer ping a localhost
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "localhost");

        try {
            // Ejecutamos el comando
            Process process = processBuilder.start();

            // Capturamos la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Mostramos cada línea de la salida
            }

            // Esperamos a que el proceso termine y obtenemos el código de salida
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Error al ejecutar el comando.");
            } else {
                System.out.println("Ping a localhost ejecutado con éxito.");
            }
        } catch (IOException | InterruptedException e) {
            // Capturamos cualquier error
            e.printStackTrace();
        }
    }
}
