package PSP.JavaExercises;

import java.util.concurrent.Semaphore;

public class FilosofoComiendo {
    private static final Semaphore[] tenedores = new Semaphore[5];

    static {
        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Semaphore(1);
        }
    }

    public static class Filosofo extends Thread {
        private int id;

        public Filosofo(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    pensar();
                    comer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void pensar() throws InterruptedException {
            System.out.println("Filósofo " + id + " está pensando.");
            Thread.sleep(1000);
        }

        private void comer() throws InterruptedException {
            tenedores[id].acquire();
            tenedores[(id + 1) % 5].acquire();

            System.out.println("Filósofo " + id + " está comiendo.");
            Thread.sleep(1000);

            tenedores[id].release();
            tenedores[(id + 1) % 5].release();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Filosofo(i).start();
        }
    }
}
