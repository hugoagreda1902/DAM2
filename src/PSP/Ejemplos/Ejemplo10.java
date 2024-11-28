package PSP.Ejemplos;

import java.util.concurrent.CyclicBarrier;

public class Ejemplo10 {
    public static void main(String[] args) {
        int numThreads = 3; // Número de hilos
        CyclicBarrier barrier = new CyclicBarrier(numThreads, () -> {
            System.out.println("Todos los hilos alcanzaron la barrera. Continuando...");
        });

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " realizando trabajo inicial.");
                Thread.sleep((int) (Math.random() * 1000)); // Simula trabajo
                System.out.println(Thread.currentThread().getName() + " esperando en la barrera.");
                barrier.await(); // Espera a los demás hilos
                System.out.println(Thread.currentThread().getName() + " continuando trabajo.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Crear y ejecutar hilos
        for (int i = 0; i < numThreads; i++) {
            new Thread(task, "Hilo-" + i).start();
        }
    }
}
