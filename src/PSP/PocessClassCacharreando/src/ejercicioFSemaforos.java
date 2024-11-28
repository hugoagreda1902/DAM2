/*
F)Modifica el ejercicio D) para que los clientes compartan una variable
global cajaRegistradora que incrementarán con un valor aleatorio de compra.
La cajera una vez termine su código deberá mostrar el total de ese día.
 */
import java.util.concurrent.Semaphore;
import java.util.Random;

public class ejercicioFSemaforos {

    private static final Semaphore semaforoClientes = new Semaphore(0);
    private static final Semaphore semaforoCajera = new Semaphore(1);
    private static int cajaRegistradora = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(new Cliente(i + 1)).start();
        }

        Thread cajera = new Thread(new Cajera());
        cajera.start();
    }

    static class Cliente implements Runnable {
        private final int id;

        public Cliente(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Random rand = new Random();
                int compra = rand.nextInt(100) + 1;
                System.out.println("Cliente " + id + " comprando por " + compra);
                semaforoClientes.release(); // Señalamos que el cliente está listo
                synchronized (ejercicioFSemaforos.class) {
                    cajaRegistradora += compra; // Incrementamos la caja registradora
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Cajera implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    semaforoClientes.acquire(); // Esperamos a que un cliente esté listo
                    semaforoCajera.acquire(); // Solo una cajera puede atender
                    System.out.println("Caja total: " + cajaRegistradora);
                    semaforoCajera.release(); // Permitimos que otro cliente sea atendido
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*Explicación:

El semáforo semaforoClientes asegura que los clientes no sean atendidos antes de estar listos para pagar.
La cajera sólo puede atender a un cliente a la vez, lo que se sincroniza con el semáforo semaforoCajera.

Se usa la sincronización mediante synchronized en el acceso a la variable cajaRegistradora para
evitar condiciones de carrera.
 */
