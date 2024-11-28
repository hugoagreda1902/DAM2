package ejerciciosprocessbuilder;

import java.io.*;

public class RedirigirSalida {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("ping");
        processBuilder.redirectOutput(new File("ping .txt"));
        try {
            Process process = processBuilder.start();
            process.waitFor(); // Esperar a que el proceso termine
            System.out.println("La salida ha sido redirigida a 'fecha.txt'");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
