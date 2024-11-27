package PSP.ejerciciosResueltos.Multihilo.contadorMonitores;

// Clase que representa un contador seguro para ser utilizado por múltiples hilos
class contador {

    private int valor;  // Variable para almacenar el valor del contador

    // Método sincronizado para incrementar el valor del contador
    public synchronized void incrementar() {
        valor++;  // Incrementa el valor del contador
        // Muestra en consola el hilo que ha realizado el incremento y el nuevo valor del contador
        System.out.println(Thread.currentThread().getName() + " incrementó a: " + valor);
    }

    // Método sincronizado para obtener el valor actual del contador
    public synchronized int obtenerValor() {
        return valor;  // Devuelve el valor actual del contador
    }
}
