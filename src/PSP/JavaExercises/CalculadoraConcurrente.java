package PSP.JavaExercises;

import java.util.concurrent.*;

public class CalculadoraConcurrente {
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public static int sumar(int a, int b) {
        return a + b;
    }

    public static int restar(int a, int b) {
        return a - b;
    }

    public static int multiplicar(int a, int b) {
        return a * b;
    }

    public static int dividir(int a, int b) {
        return a / b;
    }

    public static void main(String[] args) {
        Callable<Integer> tareaSuma = () -> sumar(3, 2);
        Callable<Integer> tareaResta = () -> restar(10, 4);
        Callable<Integer> tareaMultiplicar = () -> multiplicar(4, 5);
        Callable<Integer> tareaDividir = () -> dividir(10, 2);

        try {
            Future<Integer> resultadoSuma = executor.submit(tareaSuma);
            Future<Integer> resultadoResta = executor.submit(tareaResta);
            Future<Integer> resultadoMultiplicar = executor.submit(tareaMultiplicar);
            Future<Integer> resultadoDividir = executor.submit(tareaDividir);

            System.out.println("Suma: " + resultadoSuma.get());
            System.out.println("Resta: " + resultadoResta.get());
            System.out.println("Multiplicación: " + resultadoMultiplicar.get());
            System.out.println("División: " + resultadoDividir.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
