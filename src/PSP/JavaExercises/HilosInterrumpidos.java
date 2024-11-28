package PSP.JavaExercises;

public class HilosInterrumpidos {
    public static void main(String[] args) {
        Thread hilo = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Hilo trabajando...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido!");
            }
        });

        hilo.start();

        try {
            Thread.sleep(5000); // El hilo principal espera 5 segundos
            hilo.interrupt(); // Interrumpimos el hilo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
