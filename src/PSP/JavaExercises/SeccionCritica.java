package PSP.JavaExercises;

import java.util.concurrent.Semaphore;

public class SeccionCritica {
    private static final Semaphore semaforo = new Semaphore(1); // Un solo permiso
    private static int contador = 0;

    public static void acceder() {
        try {
            semaforo.acquire(); // El hilo adquiere el permiso
            contador++;
            System.out.println("Hilo " + Thread.currentThread().getName() + " está dentro. Contador: " + contador);
            Thread.sleep(1000); // Simula trabajo dentro de la sección crítica
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release(); // Libera el permiso
        }
    }

    public static void main(String[] args) {
        Runnable tarea = SeccionCritica::acceder;

        Thread t1 = new Thread(tarea);
        Thread t2 = new Thread(tarea);
        Thread t3 = new Thread(tarea);

        t1.start();
        t2.start();
        t3.start();
    }
}
