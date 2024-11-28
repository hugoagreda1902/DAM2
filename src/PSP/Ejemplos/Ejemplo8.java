package PSP.Ejemplos;

public class Ejemplo8 {
    private boolean signal = true; // Controla si el semáforo está disponible

    // Adquirir permiso
    public synchronized void acquire() throws InterruptedException {
        while (!signal) {
            wait(); // Espera hasta que el semáforo esté disponible
        }
        signal = false; // Bloquea el semáforo
    }

    // Liberar permiso
    public synchronized void release() {
        signal = true; // Libera el semáforo
        notify(); // Notifica a los hilos en espera
    }

    public static void main(String[] args) {
        Ejemplo8 binarySemaphore = new Ejemplo8();

        Runnable task = () -> {
            try {
                binarySemaphore.acquire(); // Adquiere el semáforo
                System.out.println(Thread.currentThread().getName() + " accediendo a recurso exclusivo.");
                Thread.sleep(1000); // Simula trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " liberando el recurso.");
                binarySemaphore.release(); // Libera el semáforo
            }
        };

        // Crear y ejecutar hilos
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Hilo-" + i).start();
        }
    }
}
