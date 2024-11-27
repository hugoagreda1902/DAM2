package PSP.ejerciciosResueltos.monitorBasico;

public class bancoConMonitor {
    public static void main(String[] args) {
        cuentaBancaria cuenta = new cuentaBancaria(1000); // Se crea una cuenta bancaria con un saldo inicial de 1000

        // Runnable que define la operación de depósito o retiro aleatorio para cada hilo
        Runnable operacion = () -> {
            for (int i = 0; i < 5; i++) { // Cada hilo realiza 5 operaciones
                if (Math.random() > 0.5) {
                    // Si el número aleatorio es mayor que 0.5, se realiza un depósito
                    cuenta.depositar((int) (Math.random() * 200)); // Deposita una cantidad aleatoria entre 0 y 200
                } else {
                    // Si el número aleatorio es menor o igual a 0.5, se realiza un retiro
                    cuenta.retirar((int) (Math.random() * 200)); // Retira una cantidad aleatoria entre 0 y 200
                }
                try {
                    // Se simula una espera aleatoria para no sobrecargar la CPU
                    Thread.sleep(100); // El hilo "duerme" por 100 milisegundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Si ocurre una interrupción, se maneja adecuadamente
                }
            }
        };

        // Se crean dos hilos, cada uno simulando una persona que realiza operaciones en la cuenta bancaria
        Thread persona1 = new Thread(operacion, "Persona 1");
        Thread persona2 = new Thread(operacion, "Persona 2");

        persona1.start(); // Se inicia el hilo de la Persona 1
        persona2.start(); // Se inicia el hilo de la Persona 2
    }
}
