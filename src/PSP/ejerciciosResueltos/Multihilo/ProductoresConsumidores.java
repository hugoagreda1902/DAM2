package PSP.ejerciciosResueltos.Multihilo;

// Importamos las clases necesarias para usar una cola bloqueante.
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductoresConsumidores {
    public static void main(String[] args) {
        // Creamos un búfer compartido con capacidad para 5 elementos.
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5); // Búfer con capacidad de 5

        // Definimos el número de hilos productores y consumidores
        int numProductores = 2;
        int numConsumidores = 3;

        // Creamos y ejecutamos los hilos productores
        for (int i = 0; i < numProductores; i++) {
            // Cada productor ejecuta el código dentro de la clase Productor
            new Thread(new Productor(buffer), "Productor-" + i).start();
        }

        // Creamos y ejecutamos los hilos consumidores
        for (int i = 0; i < numConsumidores; i++) {
            // Cada consumidor ejecuta el código dentro de la clase Consumidor
            new Thread(new Consumidor(buffer), "Consumidor-" + i).start();
        }
    }

    // Clase Productor implementa Runnable, lo que le permite ser ejecutada en un hilo.
    static class Productor implements Runnable {
        private final BlockingQueue<Integer> buffer;

        // Constructor que recibe el búfer compartido como parámetro.
        public Productor(BlockingQueue<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Generamos un número aleatorio para simular un item producido
                    int item = (int) (Math.random() * 100);

                    // Intentamos poner el item en el búfer. Si el búfer está lleno, el hilo se bloquea.
                    buffer.put(item);
                    System.out.println(Thread.currentThread().getName() + " produjo: " + item);

                    // Simulamos el tiempo que tarda en producir un item
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, manejamos la excepción
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase Consumidor implementa Runnable, lo que le permite ser ejecutada en un hilo.
    static class Consumidor implements Runnable {
        private final BlockingQueue<Integer> buffer;

        // Constructor que recibe el búfer compartido como parámetro.
        public Consumidor(BlockingQueue<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Intentamos tomar un item del búfer. Si el búfer está vacío, el hilo se bloquea.
                    int item = buffer.take();
                    System.out.println(Thread.currentThread().getName() + " consumió: " + item);

                    // Simulamos el tiempo que tarda en consumir un item
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, manejamos la excepción
                Thread.currentThread().interrupt();
            }
        }
    }
}

