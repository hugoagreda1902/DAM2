/*
E) Ejercicio: crear un programa que lance 10 hilos de ejecución donde cada hilo
accederá a una variable compartida de la clase contador
y cada hilo incrementará en uno de manera concurrente en exclusión mutua.
*/
public class ejercicioEMonitores {

    private static final Object lock = new Object(); // Monitor para exclusión mutua
    private static int contador = 0;

    public static void main(String[] args) {
        // Lanzamos 10 hilos que incrementarán el contador
        for (int i = 0; i < 10; i++) {
            new Thread(new Incrementador()).start();
        }
    }

    static class Incrementador implements Runnable {
        @Override
        public void run() {
            synchronized (lock) { // Sincronización con el monitor
                contador++; // Incrementamos el contador
                System.out.println("Contador: " + contador);
            }
        }
    }
}
/*
Explicación:

Aquí usamos un monitor (en este caso, el objeto lock) para sincronizar el
acceso al contador y aseguramos que solo un hilo pueda acceder
a la variable compartida a la vez, garantizando la exclusión mutua.
 */