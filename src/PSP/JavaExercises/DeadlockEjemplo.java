package PSP.JavaExercises;

public class DeadlockEjemplo {
    private static final Object recurso1 = new Object();
    private static final Object recurso2 = new Object();

    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> {
            synchronized (recurso1) {
                System.out.println("Hilo 1 bloquea recurso 1.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (recurso2) {
                    System.out.println("Hilo 1 bloquea recurso 2.");
                }
            }
        });

        Thread hilo2 = new Thread(() -> {
            synchronized (recurso2) {
                System.out.println("Hilo 2 bloquea recurso 2.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (recurso1) {
                    System.out.println("Hilo 2 bloquea recurso 1.");
                }
            }
        });

        hilo1.start();
        hilo2.start();
    }
}
