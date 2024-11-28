package ejericiciosruntime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingLocalhost {
    public static void main(String[] args) {
        System.out.println("Haciendo ping a localhost...");
        realizarPing("localhost");
    }

    private static void realizarPing(String direccion) {
        try {
            // Comando ping: usa el formato adecuado para Windows o Linux/Mac
            String comando = System.getProperty("os.name").toLowerCase().contains("win") ?
                    "ping -n 4 " + direccion : "ping -c 4 " + direccion;

            Process proceso = Runtime.getRuntime().exec(comando);
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
