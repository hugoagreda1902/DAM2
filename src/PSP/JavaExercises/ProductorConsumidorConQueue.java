package PSP.JavaExercises;

import java.util.concurrent.*;

public class ProductorConsumidorConQueue {
    private static final BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>(5);

    public static class Productor implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    buffer.put(i);
                    System.out.println("Productor produjo: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Consumidor implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    int item = buffer.take();
                    System.out.println("Consumidor consumió: " + item);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Productor()).start();
        new Thread(new Consumidor()).start();
    }
}
