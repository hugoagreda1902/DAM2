/*
D)Crea un programa que ejecute 2 threads en paralelo y escriba
una secuencia ordenada de números de 1 a 20 sin repeticiones ni saltos de números.
 */
import java.util.concurrent.Semaphore;

public class ejercicioDSemaforos {

    private static final Semaphore semaforo1 = new Semaphore(1);
    private static final Semaphore semaforo2 = new Semaphore(0);

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
                    if (id == 1) {
                        semaforo1.acquire(); // Hilo 1 espera para imprimir
                    }
                    System.out.println(i);
                    if (id == 1) {
                        semaforo2.release(); // Permite que el hilo 2 imprima
                    } else {
                        semaforo1.release(); // Permite que el hilo 1 imprima
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
Explicación:

Usamos dos semáforos, semaforo1 y semaforo2, para controlar qué hilo
puede imprimir en cada momento. El hilo con ID 1 imprimirá
los números impares y el hilo con ID 2 imprimirá los números pares.
Cuando un hilo termina de imprimir, libera al otro hilo para que imprima su número.
 */
