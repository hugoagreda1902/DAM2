package PSP.ejerciciosResueltos.Multihilo.Supermercado;

public class simulacionSupermercado {
    public static void main(String[] args) {
        // Se crea una instancia del supermercado
        supermercado supermercado = new supermercado();

        // Runnable para simular el comportamiento de un cliente
        Runnable cliente = () -> {
            // El nombre del cliente es el nombre del hilo
            String nombreCliente = Thread.currentThread().getName();
            System.out.println(nombreCliente + ": Voy a comprar.");
            try {
                // Simula que el cliente tarda entre 0 y 2 segundos en comprar
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                // Manejo de excepción si el hilo es interrumpido
                Thread.currentThread().interrupt();
            }
            // El cliente entra a la cola para pagar
            supermercado.entrarCliente(nombreCliente);
        };

        // Runnable para simular el comportamiento de la cajera
        Runnable cajera = () -> {
            // La cajera atiende a los clientes de forma indefinida
            while (true) {
                // La cajera atiende al siguiente cliente disponible en la cola
                String clienteAtendido = supermercado.atenderCliente();
                if (clienteAtendido != null) {
                    // La cajera realiza el cobro
                    System.out.println("Cajera: Cobrando a " + clienteAtendido);
                }
            }
        };

        // Se crea un hilo para la cajera y se inicia
        Thread cajeraHilo = new Thread(cajera, "Cajera");
        cajeraHilo.start();

        // Se crean 20 clientes, cada uno con un hilo propio
        for (int i = 1; i <= 20; i++) {
            // Cada cliente es un hilo independiente que simula la compra
            new Thread(cliente, "Cliente " + i).start();
        }
    }
}
