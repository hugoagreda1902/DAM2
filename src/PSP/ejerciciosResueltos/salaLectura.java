package PSP.ejerciciosResueltos;

// Importamos la clase Semaphore, que permite gestionar el acceso controlado a un recurso compartido.
import java.util.concurrent.Semaphore;

public class salaLectura {

    // Capacidad máxima de la sala de lectura (número de lectores permitidos simultáneamente).
    private static final int CAPACIDAD_MAXIMA = 3;

    // Creamos un semáforo con una cantidad de permisos igual a la capacidad máxima de la sala.
    private static final Semaphore semaforo = new Semaphore(CAPACIDAD_MAXIMA);

    // Clase interna que representa a un lector.
    static class Lector extends Thread {
        private final int id; // Identificador único del lector.

        // Constructor de la clase Lector, que recibe un identificador.
        public Lector(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                // El lector solicita acceso a la sala adquiriendo un permiso del semáforo.
                semaforo.acquire();
                System.out.println("Lector " + id + " ha entrado a la sala.");

                // Simulamos que el lector está leyendo con una pausa aleatoria de hasta 3 segundos.
                Thread.sleep((long) (Math.random() * 3000));

                System.out.println("Lector " + id + " ha terminado de leer.");
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, establecemos el estado de interrupción.
                Thread.currentThread().interrupt();
            } finally {
                // Liberamos el permiso del semáforo, permitiendo que otro lector entre a la sala.
                semaforo.release();
            }
        }
    }

    public static void main(String[] args) {
        // Creamos y arrancamos 10 lectores, cada uno en un hilo independiente.
        for (int i = 1; i <= 10; i++) {
            new Lector(i).start();
        }
    }
}
