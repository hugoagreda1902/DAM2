/*
C)Crea un programa que simule un supermercado: Se crearán 20 clientes y 1 cajera del tipo Thread.
Los clientes simplemente mostrarán el mensaje “Voy a comprar” y “Voy a pagar”.
Cuando los clientes terminen de comprar la cajera deberá atenderlos y
mostrará el mensaje “Cobrando  a los clientes”.
Se deberá ejecutar siempre en el orden correcto.
 */

import java.util.concurrent.Semaphore;

public class ejercicioCSemaforos {

    private static final Semaphore semaforoClientes = new Semaphore(0); // Controla cuando los clientes pueden ser atendidos
    private static final Semaphore semaforoCajera = new Semaphore(1); // Asegura que solo la cajera acceda a su sección

    public static void main(String[] args) throws InterruptedException {
        // Creamos 20 clientes y 1 cajera
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
                System.out.println("Cliente " + id + " va a comprar.");
                Thread.sleep(1000); // Simulamos el tiempo de compra
                semaforoClientes.release(); // Señalamos que el cliente está listo para pagar
                System.out.println("Cliente " + id + " va a pagar.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Cajera implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    semaforoClientes.acquire(); // Esperamos a que un cliente esté listo para pagar
                    semaforoCajera.acquire(); // Solo una cajera puede atender
                    System.out.println("Cajera atendiendo al cliente " + (i + 1));
                    Thread.sleep(500); // Simulamos el tiempo de cobro
                    semaforoCajera.release(); // Liberamos la cajera para que otro cliente sea atendido
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



/*
Explicación:

Usamos semáforos para sincronizar el acceso a la "cajera". El semáforo semaforoClientes se
incrementa cuando un cliente termina de comprar y está listo para pagar, y la cajera solo puede atender
 el semáforo está disponible.
El semáforo semaforoCajera garantiza que solo un cliente sea atendido por la cajera a la vez.
 */
