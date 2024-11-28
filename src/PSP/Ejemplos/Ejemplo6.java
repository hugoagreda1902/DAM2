package PSP.Ejemplos;

import java.util.concurrent.Semaphore;

public class Ejemplo6 {
    // Semáforos para controlar los recursos
    private static final Semaphore resourceA = new Semaphore(2); // 2 permisos para el recurso A
    private static final Semaphore resourceB = new Semaphore(1); // 1 permiso para el recurso B
    private static final Semaphore resourceC = new Semaphore(3); // 3 permisos para el recurso C

    public static void main(String[] args) {
        // Crear y lanzar hilos
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                createTask("Recurso A", resourceA).run(); // Tarea para Recurso A
                createTask("Recurso B", resourceB).run(); // Tarea para Recurso B
                createTask("Recurso C", resourceC).run(); // Tarea para Recurso C
            }, "Hilo-" + i).start();
        }
    }

    // Método estático para manejar el acceso a los recursos
    private static Runnable createTask(String resourceName, Semaphore semaphore) {
        return () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " intentando usar " + resourceName);
                semaphore.acquire(); // Adquiere permiso del recurso
                System.out.println(Thread.currentThread().getName() + " usa " + resourceName);
                Thread.sleep(1000); // Simula trabajo con el recurso
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " libera " + resourceName);
                semaphore.release(); // Libera el permiso
            }
        };
    }
}
