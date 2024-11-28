package PSP.JavaExercises;

public class Contador {
    private static int contador = 0;

    public static synchronized void incrementar() {
        contador++;
        System.out.println("Contador: " + contador);
    }

    public static void main(String[] args) {
        Runnable tarea = Contador::incrementar;
        
        for (int i = 0; i < 10; i++) {
            new Thread(tarea).start();
        }
    }
}
