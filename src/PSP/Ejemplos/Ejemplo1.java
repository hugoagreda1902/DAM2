package PSP.Ejemplos;

import java.util.concurrent.Semaphore;

public class Ejemplo1 {
    private static int counter = 0; // Recurso compartido
    private static final Semaphore semaphore = new Semaphore(1); // Semáforo con un único permiso

    public static void main(String[] args) {
        // Tarea que será ejecutada por varios hilos
        Runnable task = () -> {
            try {
                semaphore.acquire(); // Adquiere el permiso (bloquea si no está disponible)
                System.out.println(Thread.currentThread().getName() + " está incrementando el contador.");
                counter++; // Modifica el recurso compartido
                Thread.sleep(1000); // Simula una operación que toma tiempo
                System.out.println("Contador: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); // Libera el permiso para otros hilos
            }
        };

        // Crea y lanza 5 hilos
        for (int i = 0; i < 5; i++) {
            new Thread(task, "Hilo-" + i).start();
        }
    }
}
