/*
F)Modifica el ejercicio D) para que los clientes compartan una variable
global cajaRegistradora que incrementarán con un valor aleatorio de compra.
La cajera una vez termine su código deberá mostrar el total de ese día.
 */
import java.util.Random;

public class ejercicioFMonitores {

    private static final Object lockClientes = new Object();
    private static final Object lockCajera = new Object();
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
                synchronized (lockClientes) {
                    cajaRegistradora += compra; // Incrementamos la caja registradora
                    lockClientes.notify(); // Notificamos a la cajera
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
                    synchronized (lockClientes) {
                        lockClientes.wait(); // Esperamos a que un cliente termine de comprar
                    }
                    synchronized (lockCajera) {
                        System.out.println("Caja total: " + cajaRegistradora);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
Explicación:

En este caso, usamos monitores. Los clientes notifican a la cajera usando lockClientes.notify()
cuando han terminado de comprar.
La cajera espera con lockClientes.wait() hasta que un cliente le notifique que está listo para pagar.
 */
