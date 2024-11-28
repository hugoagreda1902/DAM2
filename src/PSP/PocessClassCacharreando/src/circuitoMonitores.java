import java.util.concurrent.Semaphore;
/*
 A) Realizar un programa java que simule una carrera de Fórmula 1 en
 el circuito de Mónaco:- Cada coche estará representado por un hilo, que estarán definidos
con una única clase que recibirá el nombre del piloto en su
constructor.- Los coches deben escribir por pantalla todas las vueltas de la 1
hasta 78.
Correrán los pilotos: Hamilton, Vettel, Raikkonen, Alonso, Sainz
Jr, Bottas y Vandoome.
 */
public class circuitoMonitores {

    // Número total de vueltas en la carrera
    private static final int TOTAL_VUELTAS = 78;

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
        private static final Object lock = new Object();

        public Coche(String piloto) {
            this.piloto = piloto;
        }

        @Override
        public void run() {
            for (int vuelta = 1; vuelta <= TOTAL_VUELTAS; vuelta++) {
                // Sincronización: solo un coche puede escribir la vuelta a la vez
                synchronized (lock) {
                    System.out.println(piloto + " ha completado la vuelta " + vuelta);
                }

                try {
                    // Simulamos un tiempo de carrera entre 100-300ms para cada vuelta
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

Monitores (synchronized): El synchronized garantiza que solo un coche pueda escribir la vuelta a la vez, ya que todos los coches comparten el mismo recurso, la consola.
Cada hilo (coche) imprime las vueltas de 1 a 78.
Se añade una espera aleatoria para simular un tiempo entre las vueltas de cada coche.
 */