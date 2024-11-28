/*
G) Ejercicio: crear un programa que lance 10 hilos de ejecución
donde a cada hilo se le pasará la base y la altura de un triángulo, y
cada hilo ejecutará el cálculo del área de dicho triángulo informando
de qué base y qué altura recibió y cuál es el área resultado.
 */

import java.util.concurrent.Semaphore;

public class ejercicioGsemaforos {

    private static final Semaphore semaforo = new Semaphore(1); // Exclusión mutua
    private static int[] bases = {3, 5, 8, 7, 6, 4, 2, 10, 9, 11};
    private static int[] alturas = {4, 3, 6, 8, 2, 7, 3, 5, 6, 4};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Triangulo(i)).start();
        }
    }

    static class Triangulo implements Runnable {
        private final int id;

        public Triangulo(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                int area = (bases[id] * alturas[id]) / 2;
                semaforo.acquire(); // Exclusión mutua
                System.out.println("Triángulo " + (id + 1) + " con base " + bases[id] + " y altura " + alturas[id] + " tiene área " + area);
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

Usamos un semáforo para asegurar que solo un hilo puede acceder al cálculo de área y mostrar el resultado
 a la vez, evitando la concurrencia en el acceso a la consola.
 */