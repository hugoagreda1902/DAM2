package PSP.ejerciciosResueltos;

import java.util.concurrent.Semaphore;

// Clase principal que representa un sistema de control de tráfico usando semáforos
public class semaforoVial {

    // Semáforo que regula el tráfico en dirección Norte-Sur, empieza activo (permiso = 1)
    private static final Semaphore semaforoNorteSur = new Semaphore(1);

    // Semáforo que regula el tráfico en dirección Este-Oeste, empieza inactivo (permiso = 0)
    private static final Semaphore semaforoEsteOeste = new Semaphore(0);

    // Clase interna que simula el comportamiento de un vehículo en el sistema
    static class Vehiculo extends Thread {
        private final String direccion; // Dirección del vehículo (Norte-Sur o Este-Oeste)
        private final Semaphore semaforoActual; // Semáforo correspondiente a la dirección actual
        private final Semaphore semaforoSiguiente; // Semáforo de la dirección opuesta

        // Constructor para inicializar un vehículo con su dirección y semáforos
        public Vehiculo(String direccion, Semaphore semaforoActual, Semaphore semaforoSiguiente) {
            this.direccion = direccion;
            this.semaforoActual = semaforoActual;
            this.semaforoSiguiente = semaforoSiguiente;
        }

        @Override
        public void run() {
            try {
                // Adquiere el permiso del semáforo actual, bloqueando si no está disponible
                semaforoActual.acquire();
                System.out.println("Vehículo de dirección " + direccion + " está cruzando.");

                // Simula el tiempo que tarda el vehículo en cruzar (1 segundo)
                Thread.sleep(1000);

                System.out.println("Vehículo de dirección " + direccion + " terminó de cruzar.");

                // Libera el semáforo de la dirección opuesta, permitiendo el paso
                semaforoSiguiente.release();
            } catch (InterruptedException e) {
                // En caso de interrupción, marca el hilo como interrumpido
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        // Crear y lanzar 10 vehículos de manera alternada entre ambas direcciones
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                // Vehículo de dirección Norte-Sur utiliza su semáforo y libera el de Este-Oeste
                new Vehiculo("Norte-Sur", semaforoNorteSur, semaforoEsteOeste).start();
            } else {
                // Vehículo de dirección Este-Oeste utiliza su semáforo y libera el de Norte-Sur
                new Vehiculo("Este-Oeste", semaforoEsteOeste, semaforoNorteSur).start();
            }
        }
    }
}