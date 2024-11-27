package PSP.ejerciciosResueltos.pilaConcurrente;

// Clase que simula a un consumidor que extrae elementos de la pila
class ConsumidorPila implements Runnable {
    private final PilaConcurrente<Integer> pila;

    // Constructor que recibe la pila concurrente
    public ConsumidorPila(PilaConcurrente<Integer> pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        // El consumidor realiza 5 extracciones de la pila
        for (int i = 0; i < 5; i++) {
            // Extraer un elemento de la pila
            pila.pop();

            try {
                // Simular el tiempo que toma consumir el elemento
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de interrupciones
            }
        }
    }
}
