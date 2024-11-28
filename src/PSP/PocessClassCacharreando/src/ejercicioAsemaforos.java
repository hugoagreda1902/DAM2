/*
Implementar un programa que escriba 5 hilos, mediante la clase Thread y posteriormente mediante el interfaz Runnable,
y que escriba un “hola mundo” por cada hilo que se ejecute y el número del hilo que se está imprimiendo.
Cada uno de los hilos tendrá que esperar un tiempo proporcional a su identificador antes de imprimir el mensaje.
una vez acabados los hilos el programa principal escriba el mensaje “Fin de ejecución de todos los hilos”.
 */

import java.util.concurrent.Semaphore;

public class ejercicioAsemaforos {

    // Creamos un semáforo para sincronizar los hilos
    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        // Creamos y lanzamos los hilos
        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            new Thread(new HolaMundoRunnable(id)).start();
        }

        // Esperamos a que todos los hilos terminen
        Thread.sleep(5000);
        System.out.println("Fin de ejecución de todos los hilos");
    }

    static class HolaMundoRunnable implements Runnable {
        private final int id;

        public HolaMundoRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                // Esperamos un tiempo proporcional al identificador del hilo
                Thread.sleep(id * 1000);
                semaphore.acquire(); // Adquirimos el semáforo
                System.out.println("Hola Mundo desde el hilo " + id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); // Liberamos el semáforo
            }
        }
    }
}


/*
Explicación:

Cada hilo espera un tiempo proporcional a su ID utilizando Thread.sleep(id * 1000).
Se utiliza un semaforo para asegurar que solo un hilo acceda a la salida estándar
en un momento dado. Esto garantiza que los mensajes no se intercalen.
El semáforo se adquiere al comienzo del bloque crítico
(semaphore.acquire()), y se libera al final (semaphore.release()).
 */