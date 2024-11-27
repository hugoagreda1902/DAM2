package PSP.ejerciciosResueltos.contadorMonitores;

// Clase que ejecutará el programa utilizando la clase Contador
public class contadorSeguro {
    public static void main(String[] args) {
        // Se crea una instancia de Contador
        contador contador = new contador();

        // Se define la tarea que incrementará el contador 10 veces en cada hilo
        Runnable tarea = () -> {
            for (int i = 0; i < 10; i++) {
                contador.incrementar();  // Incrementa el contador
                try {
                    Thread.sleep(50);  // Hace que el hilo espere 50 ms antes de continuar con la siguiente iteración
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Si el hilo es interrumpido, se maneja la excepción
                }
            }
        };

        // Se crean dos hilos, ambos ejecutarán la misma tarea
        Thread hilo1 = new Thread(tarea, "Hilo 1");
        Thread hilo2 = new Thread(tarea, "Hilo 2");

        // Se inician ambos hilos
        hilo1.start();
        hilo2.start();

        try {
            // Espera a que ambos hilos terminen antes de continuar
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Si el hilo principal es interrumpido, se maneja la excepción
        }

        // Muestra el valor final del contador después de que ambos hilos hayan terminado
        System.out.println("Valor final del contador: " + contador.obtenerValor());
    }
}
