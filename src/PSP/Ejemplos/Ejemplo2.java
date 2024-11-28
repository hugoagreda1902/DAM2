package PSP.Ejemplos;

import java.util.concurrent.Semaphore;

public class Ejemplo2 {
    // Semáforo con 3 permisos, permite que hasta 3 hilos trabajen simultáneamente
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        // Tarea que será ejecutada por varios hilos
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " esperando permiso...");
                semaphore.acquire(); // Intenta adquirir un permiso
                System.out.println(Thread.currentThread().getName() + " tiene permiso y está trabajando.");
                Thread.sleep(2000); // Simula trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " libera permiso.");
                semaphore.release(); // Libera el permiso para otros hilos
            }
        };

        // Crea y lanza 6 hilos
        for (int i = 0; i < 6; i++) {
            new Thread(task, "Hilo-" + i).start();
        }
    }
}
