/*
C)Crea un programa que simule un supermercado: Se crearán 20 clientes y 1 cajera del tipo Thread.
Los clientes simplemente mostrarán el mensaje “Voy a comprar” y “Voy a pagar”.
Cuando los clientes terminen de comprar la cajera deberá atenderlos y
mostrará el mensaje “Cobrando  a los clientes”.
Se deberá ejecutar siempre en el orden correcto.
 */
public class ejercicioCMonitores {

    private static final Object lockClientes = new Object(); // Para sincronizar acceso a clientes
    private static final Object lockCajera = new Object(); // Para sincronizar acceso a la cajera

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
                synchronized (lockClientes) {
                    lockClientes.notify(); // Señalamos que el cliente está listo para pagar
                }
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
                    synchronized (lockClientes) {
                        lockClientes.wait(); // Esperamos a que un cliente esté listo para pagar
                    }
                    synchronized (lockCajera) {
                        System.out.println("Cajera atendiendo al cliente " + (i + 1));
                        Thread.sleep(500); // Simulamos el tiempo de cobro
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

Se usa synchronized para proteger las secciones críticas.
El monitor lockClientes es utilizado por los clientes para notificar a la cajera cuando están listos para pagar.
El monitor lockCajera asegura que solo un cliente sea atendido a la vez.
 */