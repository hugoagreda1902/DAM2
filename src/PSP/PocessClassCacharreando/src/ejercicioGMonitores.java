/*
G) Ejercicio: crear un programa que lance 10 hilos de ejecución
donde a cada hilo se le pasará la base y la altura de un triángulo, y
cada hilo ejecutará el cálculo del área de dicho triángulo informando
de qué base y qué altura recibió y cuál es el área resultado.
 */
public class ejercicioGMonitores {

    private static final Object lock = new Object();
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
            synchronized (lock) {
                int area = (bases[id] * alturas[id]) / 2;
                System.out.println("Triángulo " + (id + 1) + " con base " + bases[id] + " y altura " + alturas[id] + " tiene área " + area);
            }
        }
    }
}
/*
Explicación:

En esta solución usamos un monitor para asegurar que el cálculo de área sea exclusivo por hilo,
y evitar problemas de acceso concurrente a la salida estándar.
 */
