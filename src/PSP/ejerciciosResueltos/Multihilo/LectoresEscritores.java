package PSP.ejerciciosResueltos.Multihilo;

// Importa las clases necesarias para trabajar con bloqueos de lectura/escritura
import java.util.concurrent.locks.Lock; // Interfaz que proporciona operaciones de bloqueo y desbloqueo
import java.util.concurrent.locks.ReadWriteLock; // Interfaz que define los bloqueos de lectura/escritura
import java.util.concurrent.locks.ReentrantReadWriteLock; // Implementación de ReadWriteLock que ofrece un semáforo de lectura/escritura

public class LectoresEscritores {
    public static void main(String[] args) {
        // Crear un recurso compartido que será accedido por los lectores y escritores
        RecursoCompartido recurso = new RecursoCompartido();

        // Número de hilos lectores y escritores
        int numLectores = 5;
        int numEscritores = 2;

        // Crear y ejecutar los hilos de lectores
        for (int i = 0; i < numLectores; i++) {
            // Se crean los hilos de lectores, cada uno con una tarea de lectura
            new Thread(new Lector(recurso), "Lector-" + i).start();
        }

        // Crear y ejecutar los hilos de escritores
        for (int i = 0; i < numEscritores; i++) {
            // Se crean los hilos de escritores, cada uno con una tarea de escritura
            new Thread(new Escritor(recurso), "Escritor-" + i).start();
        }
    }

    // Clase que representa el recurso compartido que será utilizado por los lectores y escritores
    static class RecursoCompartido {
        // Lock de lectura/escritura que permite bloquear las operaciones de lectura y escritura de forma independiente
        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

        // Variable que almacena el dato compartido que será leído/escrito
        private String dato = "Dato inicial";

        // Método de lectura del recurso compartido
        public void leer() {
            // Obtiene el bloqueo de lectura
            Lock readLock = rwLock.readLock();
            readLock.lock(); // Bloquea el acceso a la lectura
            try {
                // Simula la lectura del dato
                System.out.println(Thread.currentThread().getName() + " leyendo: " + dato);
                Thread.sleep(100); // Simula el tiempo de lectura
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de la excepción si el hilo es interrumpido
            } finally {
                // Libera el bloqueo de lectura
                readLock.unlock();
            }
        }

        // Método de escritura del recurso compartido
        public void escribir(String nuevoDato) {
            // Obtiene el bloqueo de escritura
            Lock writeLock = rwLock.writeLock();
            writeLock.lock(); // Bloquea el acceso a la escritura
            try {
                // Simula la escritura del nuevo dato
                System.out.println(Thread.currentThread().getName() + " escribiendo: " + nuevoDato);
                Thread.sleep(200); // Simula el tiempo de escritura
                dato = nuevoDato; // Actualiza el dato
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de la excepción si el hilo es interrumpido
            } finally {
                // Libera el bloqueo de escritura
                writeLock.unlock();
            }
        }
    }

    // Clase que representa un lector que lee el recurso compartido
    static class Lector implements Runnable {
        private final RecursoCompartido recurso;

        // Constructor que recibe el recurso compartido
        public Lector(RecursoCompartido recurso) {
            this.recurso = recurso;
        }

        @Override
        public void run() {
            // El lector entra en un bucle infinito leyendo el recurso
            while (true) {
                recurso.leer(); // Realiza la lectura del recurso
            }
        }
    }

    // Clase que representa un escritor que escribe en el recurso compartido
    static class Escritor implements Runnable {
        private final RecursoCompartido recurso;

        // Constructor que recibe el recurso compartido
        public Escritor(RecursoCompartido recurso) {
            this.recurso = recurso;
        }

        @Override
        public void run() {
            // El escritor entra en un bucle infinito escribiendo el recurso
            while (true) {
                recurso.escribir("Nuevo dato " + Math.random()); // Realiza la escritura del recurso
            }
        }
    }
}

