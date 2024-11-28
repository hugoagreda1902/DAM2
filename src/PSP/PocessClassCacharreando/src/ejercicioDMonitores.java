/*
D)Crea un programa que ejecute 2 threads en paralelo y escriba una
secuencia ordenada de números de 1 a 20 sin repeticiones ni saltos de números.
 */

public class ejercicioDMonitores {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new ImprimirSecuencia(1)).start();
        new Thread(new ImprimirSecuencia(2)).start();
    }

    static class ImprimirSecuencia implements Runnable {
        private final int id;

        public ImprimirSecuencia(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                for (int i = id; i <= 20; i += 2) {
                    synchronized (lock) {
                        System.out.println(i);
                        lock.notify(); // Notificamos al otro hilo para que imprima su número
                        if (i < 20) {
                            lock.wait(); // Esperamos a que el otro hilo imprima
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
En este caso, utilizamos un monitor (lock) para garantizar que los hilos se alternen al imprimir.
El hilo con ID 1 imprime los números impares, y el hilo con ID 2 imprime los números pares.
Los hilos se sincronizan mediante wait() y notify(), donde uno espera a que el otro termine de imprimir.
 */