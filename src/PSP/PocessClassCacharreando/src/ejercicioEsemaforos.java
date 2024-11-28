/*
E) Ejercicio: crear un programa que lance 10 hilos de ejecución donde cada hilo
accederá a una variable compartida de la clase contador
y cada hilo incrementará en uno de manera concurrente en exclusión mutua.
*/
import java.util.concurrent.Semaphore;

public class ejercicioEsemaforos {

    private static final Semaphore semaforo = new Semaphore(1); // Semáforo para exclusión mutua
    private static int contador = 0;

    public static void main(String[] args) {
        // Lanzamos 10 hilos que incrementarán el contador
        for (int i = 0; i < 10; i++) {
            new Thread(new Incrementador()).start();
        }
    }

    static class Incrementador implements Runnable {
        @Override
        public void run() {
            try {
                // Bloqueo mutuo con semáforo
                semaforo.acquire();
                contador++; // Incrementamos el contador
                System.out.println("Contador: " + contador);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release(); // Liberamos el semáforo
            }
        }
    }
}
/*
Explicación:

Usamos un semáforo para garantizar que solo un hilo pueda incrementar el contador a la vez,
asegurando la exclusión mutua.
El semáforo se adquiere al principio de cada hilo antes de acceder al contador y se libera
después de la operación de incremento.

 */
