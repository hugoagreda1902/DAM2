package ejericiciosruntime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingDireccion {
    public static void main(String[] args) {
        String direccion = "google.com"; // Cambia esto por la dirección que quieras hacer ping
        try {
            Process proceso = Runtime.getRuntime().exec("ping -c 4 " + direccion); // En Windows usa "ping -n 4"
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
