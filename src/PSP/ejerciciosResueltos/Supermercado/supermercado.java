package PSP.ejerciciosResueltos.Supermercado;

import java.util.concurrent.BlockingQueue; // Interface que define la cola de clientes con operaciones de bloqueo
import java.util.concurrent.LinkedBlockingQueue; // Implementación de BlockingQueue que usa una lista enlazada para almacenar los elementos

class supermercado {
    // Cola de clientes que esperan para pagar, usa un BlockingQueue para controlar el acceso concurrente
    private final BlockingQueue<String> colaClientes = new LinkedBlockingQueue<>();

    // Método para que un cliente entre en la cola
    public void entrarCliente(String cliente) {
        try {
            // Añade al cliente a la cola. Si la cola está llena, espera hasta que haya espacio
            colaClientes.put(cliente);
            System.out.println(cliente + " está en la cola para pagar.");
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, se maneja la excepción
            Thread.currentThread().interrupt();
        }
    }

    // Método para que la cajera atienda a un cliente, extrae de la cola
    public String atenderCliente() {
        try {
            // Extrae un cliente de la cola, bloqueando si no hay clientes
            return colaClientes.take();
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, se maneja la excepción
            Thread.currentThread().interrupt();
            return null;
        }
    }
}