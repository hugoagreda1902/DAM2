/*
 A) Realizar un programa java que simule una carrera de Fórmula 1 en
 el circuito de Mónaco:- Cada coche estará representado por un hilo, que estarán definidos
con una única clase que recibirá el nombre del piloto en su
constructor.- Los coches deben escribir por pantalla todas las vueltas de la 1
hasta 78.
Correrán los pilotos: Hamilton, Vettel, Raikkonen, Alonso, Sainz
Jr, Bottas y Vandoome.
 */

import java.util.concurrent.Semaphore;

public class circuitoSemaforos {

    private static final int TOTAL_VUELTAS = 78;
    private static final Semaphore semaphore = new Semaphore(1); // Solo 1 hilo puede escribir a la vez

    public static void main(String[] args) {
        // Crear los hilos de los pilotos
        Thread hamilton = new Thread(new Coche("Hamilton"));
        Thread vettel = new Thread(new Coche("Vettel"));
        Thread raikkonen = new Thread(new Coche("Raikkonen"));
        Thread alonso = new Thread(new Coche("Alonso"));
        Thread sainz = new Thread(new Coche("Sainz Jr."));
        Thread bottas = new Thread(new Coche("Bottas"));
        Thread vandoorne = new Thread(new Coche("Vandoorne"));

        // Iniciar los hilos
        hamilton.start();
        vettel.start();
        raikkonen.start();
        alonso.start();
        sainz.start();
        bottas.start();
        vandoorne.start();
    }

    static class Coche implements Runnable {
        private final String piloto;

        public Coche(String piloto) {
            this.piloto = piloto;
        }

        @Override
        public void run() {
            for (int vuelta = 1; vuelta <= TOTAL_VUELTAS; vuelta++) {
                try {
                    semaphore.acquire(); // El coche debe esperar su turno
                    System.out.println(piloto + " ha completado la vuelta " + vuelta);
                    semaphore.release(); // Libera el semáforo para que el siguiente coche escriba

                    // Simula el tiempo entre vueltas
                    Thread.sleep((int) (Math.random() * 200) + 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/*
Explicación:

Semáforos (Semaphore): Usamos un semáforo con una sola "permiso" (valor inicial de
1) para asegurarnos de que solo un coche escriba a la vez. Cada coche pide el permiso antes de escribir y lo libera después de hacerlo.
Al igual que en la solución con monitores, cada coche imprime las vueltas desde 1 hasta 78.
 */
