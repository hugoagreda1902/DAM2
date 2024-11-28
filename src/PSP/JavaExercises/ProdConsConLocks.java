package PSP.JavaExercises;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;
import java.util.Queue;

public class ProdConsConLocks {
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final int LIMITE = 5;
    private static final Lock lock = new ReentrantLock();
    private static final Condition lleno = lock.newCondition();
    private static final Condition vacio = lock.newCondition();

    public static class Productor implements Runnable {
        @Override
        public void run() {
            int valor = 0;
            try {
                while (true) {
                    lock.lock();
                    while (buffer.size() == LIMITE) {
                        lleno.await();
                    }
                    buffer.add(valor);
                    System.out.println("Producido: " + valor);
                    valor++;
                    vacio.signalAll();
                    lock.unlock();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static class Consumidor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    lock.lock();
                    while (buffer.isEmpty()) {
                        vacio.await();
                    }
                    int valor = buffer.poll();
                    System.out.println("Consumido: " + valor);
                    lleno.signalAll();
                    lock.unlock();
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Productor()).start();
        new Thread(new Consumidor()).start();
    }
}
