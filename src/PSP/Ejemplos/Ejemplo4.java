package PSP.Ejemplos;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Ejemplo4 {
    private static final LinkedList<Integer> buffer = new LinkedList<>(); // Buffer compartido
    private static final int CAPACITY = 5; // Tamaño máximo del buffer
    private static final Semaphore empty = new Semaphore(CAPACITY); // Espacios vacíos en el buffer
    private static final Semaphore full = new Semaphore(0); // Ítems disponibles en el buffer
    private static final Semaphore mutex = new Semaphore(1); // Exclusión mutua para modificar el buffer

    public static void main(String[] args) {
        // Hilo productor
        Thread producer = new Thread(() -> {
            int value = 0;
            while (true) {
                try {
                    empty.acquire(); // Espera espacio disponible
                    mutex.acquire(); // Bloquea el acceso al buffer
                    buffer.add(value);
                    System.out.println("Productor produjo: " + value++);
                    mutex.release(); // Libera el acceso al buffer
                    full.release(); // Incrementa ítems disponibles
                    Thread.sleep(500); // Simula producción
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hilo consumidor
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    full.acquire(); // Espera ítems disponibles
                    mutex.acquire(); // Bloquea el acceso al buffer
                    int value = buffer.removeFirst();
                    System.out.println("Consumidor consumió: " + value);
                    mutex.release(); // Libera el acceso al buffer
                    empty.release(); // Incrementa espacios vacíos
                    Thread.sleep(1000); // Simula consumo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
