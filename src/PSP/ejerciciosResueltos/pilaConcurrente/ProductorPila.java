package PSP.ejerciciosResueltos.pilaConcurrente;


import PSP.ejerciciosResueltos.pilaConcurrente.PilaConcurrente;

// Clase que simula a un productor que agrega elementos a la pila
class ProductorPila implements Runnable {
    private final PilaConcurrente<Integer> pila;

    // Constructor que recibe la pila concurrente
    public ProductorPila(PilaConcurrente<Integer> pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        // El productor realiza 5 inserciones en la pila
        for (int i = 0; i < 5; i++) {
            // Generar un número aleatorio entre 0 y 100
            pila.push((int) (Math.random() * 100));

            try {
                // Simular el tiempo que toma producir el elemento
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de interrupciones
            }
        }
    }
}

