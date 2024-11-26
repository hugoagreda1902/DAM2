package PSP;

import java.util.concurrent.TimeUnit;

// Clase que representa un coche de F1
class CocheF1 extends Thread {
    private String piloto; // Nombre del piloto

    public CocheF1(String piloto) {
        this.piloto = piloto; // Inicializa el nombre del piloto
    }

    @Override
    public void run() {
        // Simula las vueltas de la carrera
        for (int vuelta = 1; vuelta <= 78; vuelta++) {
            System.out.println(piloto + " va por la vuelta " + vuelta);
            try {
                TimeUnit.MILLISECONDS.sleep(25); // Espera 25 ms entre vueltas
            } catch (InterruptedException e) {
                e.printStackTrace(); //
            }
        }
    }
}

public class formula1 {
    public static void main(String[] args) {
        // Lista de pilotos
        String[] pilotos = {"Hamilton", "Vettel", "Raikkonen", "Alonso", "Sainz Jr", "Bottas", "Vandoome"};

        CocheF1[] coches = new CocheF1[pilotos.length];
        for (int i = 0; i < coches.length; i++) {
            coches[i] = new CocheF1(pilotos[i]);
            coches[i].start();
        }
        for (CocheF1 coche : coches) {
            try {
                coche.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}