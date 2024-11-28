package PSP.Ejemplos;

import java.util.concurrent.Semaphore;

public class Ejemplo9 {
    private static final int[][] matrix = new int[3][3]; // Matriz compartida
    private static final Semaphore mutex = new Semaphore(1); // Exclusión mutua para la matriz

    public static void main(String[] args) {
        // Crear un hilo para cada fila
        for (int i = 0; i < matrix.length; i++) {
            int row = i; // `row` debe ser efectivamente final para usarlo dentro de la lambda
            Runnable task = () -> {
                try {
                    for (int col = 0; col < matrix[row].length; col++) {
                        mutex.acquire(); // Bloquea el acceso a la matriz
                        matrix[row][col] = (row + 1) * (col + 1); // Procesa la celda
                        System.out.println(Thread.currentThread().getName() + " procesando matriz[" + row + "][" + col + "]");
                        mutex.release(); // Libera el acceso
                        Thread.sleep(500); // Simula procesamiento
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            new Thread(task, "Hilo-Fila-" + row).start(); // Lanza un hilo para la fila
        }
    }
}
