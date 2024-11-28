package PSP.JavaExercises;

import java.util.concurrent.Semaphore;

public class SemaforoLimitarHilos {
    private static final Semaphore semaforo = new Semaphore(3);

    public static void acceder() {
        try {
            semaforo.acquire();
            System.out.println(Thread.currentThread().getName() + " accediendo a la sección crítica.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(SemaforoLimitarHilos::acceder).start();
        }
    }
}
