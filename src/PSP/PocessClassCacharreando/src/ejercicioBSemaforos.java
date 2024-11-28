/*
Realiza un programa en Java que ejecute tres hilos de forma concurrente.
Uno de ellos debe sumar los números pares de 1 al 100; otro los impares,
y otro los que terminen en 2 o en 3.
Posible salida:
Inicio
Suma: 249500 del pares FIN!
Suma: 250000 del impares FIN!
Suma: 99500 del termina2_3 FIN!
*/


import java.util.concurrent.Semaphore;

public class ejercicioBSemaforos {
    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        System.out.println("Inicio");

        Thread t1 = new Thread(new Sumador(1, 100, 2)); // Pares
        Thread t2 = new Thread(new Sumador(1, 100, 1)); // Impares
        Thread t3 = new Thread(new SumadorTermina2o3(1, 100));

        t1.start();
        t2.start();
        t3.start();
    }

    static class Sumador implements Runnable {
        private int start, end, step;

        public Sumador(int start, int end, int step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }

        @Override
        public void run() {
            int suma = 0;
            for (int i = start; i <= end; i += step) {
                suma += i;
            }
            try {
                semaphore.acquire();
                System.out.println("Suma: " + suma + " del " + (step == 2 ? "pares" : "impares") + " FIN!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    static class SumadorTermina2o3 implements Runnable {
        private int start, end;

        public SumadorTermina2o3(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int suma = 0;
            for (int i = start; i <= end; i++) {
                if (i % 10 == 2 || i % 10 == 3) {
                    suma += i;
                }
            }
            try {
                semaphore.acquire();
                System.out.println("Suma: " + suma + " del termina2_3 FIN!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}

/*
Explicación:

Usamos semáforos para asegurar que solo un hilo imprime en consola a la vez.
Cada hilo calcula la suma de los números que le corresponde (pares, impares, y los que terminan en 2 o 3).
 */