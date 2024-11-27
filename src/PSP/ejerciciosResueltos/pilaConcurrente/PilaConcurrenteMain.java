package PSP.ejerciciosResueltos.pilaConcurrente;

public class PilaConcurrenteMain {
    public static void main(String[] args) {
        // Crear la pila concurrente
        PilaConcurrente<Integer> pila = new PilaConcurrente<>();

        // Definir el número de hilos que se van a crear
        int numHilos = 10;

        // Crear los hilos que realizarán operaciones en la pila
        Thread[] hilos = new Thread[numHilos];

        // Crear los hilos: los hilos con índice par serán productores, los impares serán consumidores
        for (int i = 0; i < numHilos; i++) {
            if (i % 2 == 0) {
                // Crear un hilo productor
                hilos[i] = new Thread(new ProductorPila(pila), "Productor-" + i);
            } else {
                // Crear un hilo consumidor
                hilos[i] = new Thread(new ConsumidorPila(pila), "Consumidor-" + i);
            }
            // Iniciar el hilo
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join(); // Hace que el hilo principal espere a que cada hilo termine
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de interrupciones
            }
        }

        // Imprimir mensaje cuando todas las operaciones hayan finalizado
        System.out.println("Operaciones en la pila completadas.");
    }
}
