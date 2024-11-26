package PSP;

import java.util.Random;

class HiloAleatorio implements Runnable {
    private Random random; // Generador de números aleatorios

    public HiloAleatorio() {
        this.random = new Random();
    }

    @Override
    public void run() {
        int numero = random.nextInt(100) + 1; // Genera un número aleatorio entre 1 y 100
        System.out.println(numero);
    }
}

// Clase principal para ejecutar el programa
public class numeroAleatorio {
    public static void main(String[] args) {
        // Crea 100 hilos
        for (int i = 0; i < 100; i++) {
            HiloAleatorio aleatorio = new HiloAleatorio();
            Thread thread = new Thread(aleatorio); // Crea un nuevo thread
            thread.start(); // Inicia el hilo
        }
    }
}