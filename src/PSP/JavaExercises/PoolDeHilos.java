package PSP.JavaExercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolDeHilos {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Runnable tarea = () -> {
            System.out.println("Hilo " + Thread.currentThread().getName() + " ejecutando tarea.");
            try {
                Thread.sleep(2000); // Simula trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            pool.execute(tarea);
        }

        pool.shutdown(); // Cierra el pool tras terminar las tareas
    }
}
