package PSP.ejerciciosResueltos.Multihilo;

// Importamos las clases necesarias para trabajar con una cola concurrente y un contador atómico.
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ColaConcurrente {
    public static void main(String[] args) {
        // Creamos una cola concurrente que los productores y consumidores compartirán.
        Queue<Integer> cola = new ConcurrentLinkedQueue<>();

        // Creamos un contador atómico que lleva el registro de los elementos producidos.
        AtomicInteger producidos = new AtomicInteger(0);

        // Límite de elementos a producir (cuando se alcanza, la producción se detiene).
        int limiteProduccion = 50;

        // Definimos el número de hilos productores
        int numProductores = 3;
        Thread[] productores = new Thread[numProductores];
        for (int i = 0; i < numProductores; i++) {
            // Creamos y lanzamos los hilos productores
            productores[i] = new Thread(new Productor(cola, producidos, limiteProduccion),
                    "Productor-" + i);
            productores[i].start();
        }

        // Definimos el número de hilos consumidores
        int numConsumidores = 2;
        Thread[] consumidores = new Thread[numConsumidores];
        for (int i = 0; i < numConsumidores; i++) {
            // Creamos y lanzamos los hilos consumidores
            consumidores[i] = new Thread(new Consumidor(cola, producidos, limiteProduccion),
                    "Consumidor-" + i);
            consumidores[i].start();
        }

        // Esperamos a que los hilos productores y consumidores terminen su ejecución
        try {
            for (Thread productor : productores) {
                productor.join(); // Esperamos a que todos los hilos productores terminen
            }
            for (Thread consumidor : consumidores) {
                consumidor.join(); // Esperamos a que todos los hilos consumidores terminen
            }
        } catch (InterruptedException e) {
            // Si el hilo principal es interrumpido, manejamos la excepción
            Thread.currentThread().interrupt();
        }

        // Imprimimos un mensaje indicando que la producción y el consumo han terminado.
        System.out.println("Producción y consumo completados.");
    }

    // Clase Productor implementa Runnable, lo que permite ser ejecutada en un hilo.
    static class Productor implements Runnable {
        private final Queue<Integer> cola;
        private final AtomicInteger producidos;
        private final int limiteProduccion;

        // Constructor que recibe la cola, el contador y el límite de producción.
        public Productor(Queue<Integer> cola, AtomicInteger producidos, int limiteProduccion) {
            this.cola = cola;
            this.producidos = producidos;
            this.limiteProduccion = limiteProduccion;
        }

        @Override
        public void run() {
            try {
                // Mientras no se alcance el límite de producción
                while (producidos.get() < limiteProduccion) {
                    // Generamos un número aleatorio entre 1 y 100 para simular la producción de un item
                    int item = (int) (Math.random() * 100 + 1);

                    // Añadimos el item a la cola
                    cola.add(item);

                    // Incrementamos el contador de elementos producidos de manera atómica
                    int totalProducidos = producidos.incrementAndGet();

                    // Imprimimos el nombre del hilo productor y el item producido
                    System.out.println(Thread.currentThread().getName() + " produjo: " + item);

                    // Si el número total de elementos producidos alcanza el límite, terminamos el ciclo
                    if (totalProducidos >= limiteProduccion) break;

                    // Simulamos el tiempo que tarda en producir un item (50ms)
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, manejamos la excepción
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase Consumidor implementa Runnable, lo que permite ser ejecutada en un hilo.
    static class Consumidor implements Runnable {
        private final Queue<Integer> cola;
        private final AtomicInteger producidos;
        private final int limiteProduccion;

        // Constructor que recibe la cola, el contador y el límite de producción.
        public Consumidor(Queue<Integer> cola, AtomicInteger producidos, int limiteProduccion) {
            this.cola = cola;
            this.producidos = producidos;
            this.limiteProduccion = limiteProduccion;
        }

        @Override
        public void run() {
            try {
                // Mientras que los productos no hayan alcanzado el límite y la cola no esté vacía
                while (true) {
                    // Intentamos obtener un item de la cola
                    Integer item = cola.poll();

                    // Si el item es no nulo, lo consumimos
                    if (item != null) {
                        System.out.println(Thread.currentThread().getName() + " consumió: " + item);
                    }

                    // Si ya se ha alcanzado el límite de producción y la cola está vacía, terminamos el ciclo
                    if (producidos.get() >= limiteProduccion && cola.isEmpty()) break;

                    // Simulamos el tiempo de consumo (100ms)
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, manejamos la excepción
                Thread.currentThread().interrupt();
            }
        }
    }
}
