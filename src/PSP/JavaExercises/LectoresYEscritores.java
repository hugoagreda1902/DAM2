package PSP.JavaExercises;

import java.util.concurrent.Semaphore;

public class LectoresYEscritores {
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore lectura = new Semaphore(1);
    private static int lectores = 0;

    public static class Lector extends Thread {
        @Override
        public void run() {
            try {
                lectura.acquire();
                synchronized (this) {
                    lectores++;
                    if (lectores == 1) {
                        mutex.acquire();
                    }
                }
                lectura.release();

                System.out.println("Lector " + Thread.currentThread().getName() + " está leyendo.");
                Thread.sleep(2000);

                synchronized (this) {
                    lectores--;
                    if (lectores == 0) {
                        mutex.release();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Escritor extends Thread {
        @Override
        public void run() {
            try {
                mutex.acquire();
                System.out.println("Escritor " + Thread.currentThread().getName() + " está escribiendo.");
                Thread.sleep(2000);
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            new Lector().start();
        }
        for (int i = 1; i <= 2; i++) {
            new Escritor().start();
        }
    }
}
