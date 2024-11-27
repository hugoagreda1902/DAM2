package PSP.ejerciciosResueltos.Muliproceso;

public class factorialMultihiloBasico {
    public static void main(String[] args) {
        int numero = 5; // Número del que queremos calcular el factorial
        int numHilos = 2; // Número de hilos para el cálculo
        long resultado = calcularFactorial(numero, numHilos); // Llamada al método para calcular el factorial
        System.out.println("El factorial de " + numero + " es: " + resultado); // Mostramos el resultado
    }

    public static long calcularFactorial(int numero, int numHilos) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número debe ser no negativo"); // Validamos si el número es negativo
        }
        if (numero == 0 || numero == 1) {
            return 1; // El factorial de 0 y 1 es 1
        }

        // Dividimos el rango entre los hilos
        FactorialCompartido factorialCompartido = new FactorialCompartido(); // Creamos una instancia para gestionar el resultado compartido
        int rangoPorHilo = numero / numHilos; // Calculamos el rango de trabajo para cada hilo
        Thread[] hilos = new Thread[numHilos]; // Creamos un array para almacenar los hilos

        // Asignamos tareas a cada hilo
        for (int i = 0; i < numHilos; i++) {
            int inicio = i * rangoPorHilo + 1; // Calculamos el inicio del rango
            int fin = (i == numHilos - 1) ? numero : (i + 1) * rangoPorHilo; // El último hilo trabaja con el resto de números
            hilos[i] = new Thread(new TareaFactorial(inicio, fin, factorialCompartido)); // Creamos el hilo con su tarea asignada
        }

        // Iniciamos los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperamos a que terminen todos los hilos
        for (Thread hilo : hilos) {
            try {
                hilo.join(); // Espera a que el hilo termine
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Si el hilo se interrumpe, lo manejamos
            }
        }

        // Devolvemos el resultado calculado por todos los hilos
        return factorialCompartido.getResultado();
    }

    // Clase para gestionar el resultado compartido
    static class FactorialCompartido {
        private long resultado = 1; // Valor inicial del resultado es 1

        // Método sincronizado para multiplicar y actualizar el resultado
        public synchronized void multiplicar(long valor) {
            resultado *= valor; // Multiplicamos el valor actual con el nuevo valor
        }

        // Método para obtener el resultado final
        public long getResultado() {
            return resultado;
        }
    }

    // Tarea que calcula un rango del factorial
    static class TareaFactorial implements Runnable {
        private final int inicio; // Rango de inicio
        private final int fin; // Rango de fin
        private final FactorialCompartido factorialCompartido; // Instancia para el resultado compartido

        // Constructor que recibe el rango y la instancia compartida
        public TareaFactorial(int inicio, int fin, FactorialCompartido factorialCompartido) {
            this.inicio = inicio;
            this.fin = fin;
            this.factorialCompartido = factorialCompartido;
        }

        // Método que realiza el cálculo del factorial para el rango
        @Override
        public void run() {
            long resultadoParcial = 1; // Inicializamos el resultado parcial
            for (int i = inicio; i <= fin; i++) {
                resultadoParcial *= i; // Calculamos el producto para el rango dado
            }
            // Mostramos el resultado parcial del hilo
            System.out.println("Hilo calculando rango " + inicio + " a " + fin + ", resultado parcial: " + resultadoParcial);
            factorialCompartido.multiplicar(resultadoParcial); // Actualizamos el resultado compartido
        }
    }
}
