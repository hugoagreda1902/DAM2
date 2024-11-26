package PSP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class actividad1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el comando que deseas ejecutar:");
        String comando = sc.nextLine();
        try {
            Process proceso = Runtime.getRuntime().exec(comando);

            // Capturar y mostrar la salida del comando
            BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));

            System.out.println("=== Salida del comando ===");
            String linea;
            while ((linea = salida.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("=== Errores del comando ===");
            while ((linea = errores.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que el proceso finalice y verificar el código de salida
            int codigoSalida = proceso.waitFor();
            if (codigoSalida == 0) {
                System.out.println("El comando se ejecutó correctamente con código de salida: " + codigoSalida);
            } else {
                System.out.println("El comando falló con código de salida: " + codigoSalida);
            }


        } catch (Exception e) {
            System.err.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }
}

