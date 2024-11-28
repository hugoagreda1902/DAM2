/*
A)
Implementar un programa que escriba 5 hilos, mediante la clase Thread y posteriormente mediante el interfaz Runnable,
y que escriba un “hola mundo” por cada hilo que se ejecute y el número del hilo que se está imprimiendo.
Cada uno de los hilos tendrá que esperar un tiempo proporcional a su identificador antes de imprimir el mensaje.
una vez acabados los hilos el programa principal escriba el mensaje “Fin de ejecución de todos los hilos”.
*/



public class ejercicioAmonitores {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // Creamos y lanzamos los hilos
        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            new Thread(new HolaMundoRunnable(id)).start();
        }

        // Esperamos a que todos los hilos terminen
        Thread.sleep(5000);
        System.out.println("Fin de ejecución de todos los hilos");
    }

    static class HolaMundoRunnable implements Runnable {
        private final int id;

        public HolaMundoRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                // Esperamos un tiempo proporcional al identificador del hilo
                Thread.sleep(id * 1000);
                synchronized (lock) { // Usamos el monitor (lock) para sincronizar
                    System.out.println("Hola Mundo desde el hilo " + id);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
Explicación:

En lugar de usar semáforos, se utiliza un monitor implementado con un objeto (lock).
Los hilos se sincronizan mediante la palabra clave synchronized,
lo que asegura que solo un hilo imprima en la consola a la vez
*/