package PSP.Ejemplos;

import java.util.concurrent.Semaphore;

public class Ejemplo7 {
    private static int sharedData = 0; // Recurso compartido
    private static int readCount = 0; // Número de lectores actualmente activos
    private static final Semaphore mutex = new Semaphore(1); // Exclusión mutua para readCount
    private static final Semaphore writeLock = new Semaphore(1); // Exclusión mutua para el recurso compartido

    public static void main(String[] args) {
        Runnable reader = () -> {
            try {
                mutex.acquire(); // Bloquea para modificar readCount
                readCount++;
                if (readCount == 1) {
                    writeLock.acquire(); // Primer lector bloquea escritura
                }
                mutex.release(); // Libera para que otros lectores accedan

                // Sección crítica de lectura
                System.out.println(Thread.currentThread().getName() + " leyendo: " + sharedData);
                Thread.sleep(500);

                mutex.acquire(); // Bloquea para modificar readCount
                readCount--;
                if (readCount == 0) {
                    writeLock.release(); // Último lector libera escritura
                }
                mutex.release(); // Libera para otros lectores
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writer = () -> {
            try {
                writeLock.acquire(); // Bloquea el recurso para escritura

                // Sección crítica de escritura
                sharedData++;
                System.out.println(Thread.currentThread().getName() + " escribió: " + sharedData);
                Thread.sleep(1000);

                writeLock.release(); // Libera el recurso
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Crear y ejecutar lectores y escritores
        for (int i = 0; i < 3; i++) {
            new Thread(reader, "Lector-" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writer, "Escritor-" + i).start();
        }
    }
}
