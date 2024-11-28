package PSP.Ejemplos;

public class Ejemplo5 {
    private static final Object lock = new Object(); // Objeto para sincronización
    private static int turn = 1; // Turno para controlar el orden de los hilos

    public static void main(String[] args) {
        // Hilo A
        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                while (turn != 1) { // Espera su turno
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Hilo-A ejecutando");
                turn = 2; // Cambia el turno al siguiente hilo
                lock.notifyAll();
            }
        });

        // Hilo B
        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                while (turn != 2) { // Espera su turno
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Hilo-B ejecutando");
                turn = 3; // Cambia el turno al siguiente hilo
                lock.notifyAll();
            }
        });

        // Hilo C
        Thread threadC = new Thread(() -> {
            synchronized (lock) {
                while (turn != 3) { // Espera su turno
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Hilo-C ejecutando");
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
