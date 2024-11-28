package PSP.JavaExercises;

public class OperacionAritmeticaConcurrente {
    private static int valor = 0;

    public static synchronized void sumar(int cantidad) {
        valor += cantidad;
        System.out.println("Sumado: " + cantidad + ", valor actual: " + valor);
    }

    public static synchronized void restar(int cantidad) {
        valor -= cantidad;
        System.out.println("Restado: " + cantidad + ", valor actual: " + valor);
    }

    public static void main(String[] args) {
        Runnable tareaSuma = () -> sumar(10);
        Runnable tareaResta = () -> restar(5);

        Thread hilo1 = new Thread(tareaSuma);
        Thread hilo2 = new Thread(tareaResta);

        hilo1.start();
        hilo2.start();
    }
}
