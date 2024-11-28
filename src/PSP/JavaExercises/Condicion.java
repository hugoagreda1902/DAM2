package PSP.JavaExercises;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condicion {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int contador = 0;

    public static void incrementar() {
        try {
            lock.lock();
            contador++;
            System.out.println("Contador: " + contador);
            if (contador == 5) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void esperar() {
        try {
            lock.lock();
            while (contador < 5) {
                condition.await();
            }
            System.out.println("Condición cumplida. El contador es 5.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(Condicion::incrementar);
        Thread hilo2 = new Thread(Condicion::incrementar);
        Thread hilo3 = new Thread(Condicion::esperar);
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
