package PSP.JavaExercises;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomico {
    private static final AtomicInteger contador = new AtomicInteger(0);

    public static void incrementar() {
        System.out.println("Contador: " + contador.incrementAndGet());
    }

    public static void main(String[] args) {
        Runnable tarea = ContadorAtomico::incrementar;

        for (int i = 0; i < 5; i++) {
            new Thread(tarea).start();
        }
    }
}
