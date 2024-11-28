package ejericiciosruntime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EspecificacionesPC {
    public static void main(String[] args) {
        try {
            // Comando para obtener información del sistema
            String comandoSO = System.getProperty("os.name").toLowerCase().contains("win") ? "systeminfo" : "uname -a";
            Process proceso = Runtime.getRuntime().exec(comandoSO);

            // Leer la salida del comando
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
