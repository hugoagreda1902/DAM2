package PSP.ejerciciosResueltos.Multihilo.pilaConcurrente;

import java.util.LinkedList;

// Clase que implementa una pila concurrente
class PilaConcurrente<T> {
    // La pila subyacente es una LinkedList
    private final LinkedList<T> pila = new LinkedList<>();

    // Método sincronizado para agregar elementos a la pila
    public synchronized void push(T elemento) {
        pila.push(elemento); // Agregar el elemento a la pila
        System.out.println(Thread.currentThread().getName() + " agregó: " + elemento);
        notifyAll(); // Notificar a los hilos en espera
    }

    // Método sincronizado para extraer elementos de la pila
    public synchronized T pop() {
        // Si la pila está vacía, el hilo espera
        while (pila.isEmpty()) {
            try {
                wait(); // Espera hasta que haya elementos en la pila
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de interrupciones
            }
        }

        // Extraer el elemento de la pila
        T elemento = pila.pop();
        System.out.println(Thread.currentThread().getName() + " extrajo: " + elemento);
        return elemento; // Devolver el elemento extraído
    }
}
