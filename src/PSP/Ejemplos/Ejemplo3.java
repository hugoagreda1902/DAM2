package PSP.Ejemplos;

public class Ejemplo3 {
    private static final Object lock = new Object(); // Objeto para sincronización
    private static boolean turn = true; // true: turno del hilo que imprime números pares

    public static void main(String[] args) {
        // Hilo para imprimir números pares
        Thread evenThread = new Thread(() -> {
            for (int i = 0; i <= 10; i += 2) {
                synchronized (lock) {
                    while (!turn) { // Espera su turno
                        try {
                            lock.wait(); // Libera el bloqueo y espera
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Par: " + i);
                    turn = false; // Cambia el turno
                    lock.notify(); // Notifica al otro hilo
                }
            }
        });

        // Hilo para imprimir números impares
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i < 10; i += 2) {
                synchronized (lock) {
                    while (turn) { // Espera su turno
                        try {
                            lock.wait(); // Libera el bloqueo y espera
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Impar: " + i);
                    turn = true; // Cambia el turno
                    lock.notify(); // Notifica al otro hilo
                }
            }
        });

        evenThread.start();
        oddThread.start();
    }
}
