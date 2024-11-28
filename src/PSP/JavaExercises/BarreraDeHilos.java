
import java.util.concurrent.CyclicBarrier;

public class BarreraDeHilos {
    public static void main(String[] args) {
        CyclicBarrier barrera = new CyclicBarrier(3, () -> System.out.println("Todos los hilos llegaron a la barrera!"));

        Runnable tarea = () -> {
            System.out.println("Hilo " + Thread.currentThread().getName() + " haciendo trabajo.");
            try {
                Thread.sleep(2000); // Simula trabajo
                System.out.println("Hilo " + Thread.currentThread().getName() + " esperando en la barrera.");
                barrera.await(); // Espera en la barrera
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(tarea).start();
        new Thread(tarea).start();
        new Thread(tarea).start();
    }
}
