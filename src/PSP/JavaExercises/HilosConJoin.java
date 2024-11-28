package PSP.JavaExercises;

public class HilosConJoin {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hilo 1 terminado.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread hilo2 = new Thread(() -> {
            try {
                System.out.println("Hilo 2 esperando a Hilo 1.");
                hilo1.join();
                System.out.println("Hilo 2 terminado.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        hilo1.start();
        hilo2.start();
    }
}
