package PSP;
import java.util.concurrent.Semaphore;

public class museo {
    // Semáforo para controlar el aforo máximo del museo (máximo 5 turistas simultáneos)
    private static final Semaphore aforoMuseo = new Semaphore(5);

    // Semáforo para controlar el acceso exclusivo a la sala especial (solo 1 turista a la vez)
    private static final Semaphore salaEspecial = new Semaphore(1);

    public static void main(String[] args) {
        // Simulamos 10 turistas, cada uno será representado por un hilo
        for (int i = 1; i <= 10; i++) {
            int turistaId = i; // Guardamos el ID del turista para usarlo en el hilo
            new Thread(() -> visitarMuseo(turistaId)).start(); // Creamos y arrancamos un hilo para cada turista
        }
    }

    private static void visitarMuseo(int turistaId) {
        try {
            // Simula que el turista espera para entrar al museo
            System.out.println("Turista " + turistaId + " espera para entrar al museo.");
            aforoMuseo.acquire(); // Reduce el aforo disponible del museo (bloquea si está lleno)
            System.out.println("Turista " + turistaId + " ha entrado al museo.");

            // Simula que el turista espera para entrar a la sala especial
            System.out.println("Turista " + turistaId + " espera para entrar a la sala especial.");
            salaEspecial.acquire(); // Bloquea hasta que la sala especial esté disponible
            System.out.println("Turista " + turistaId + " está en la sala especial.");

            System.out.println("Turista " + turistaId + " ha salido de la sala especial.");

            salaEspecial.release(); // Libera la sala especial para que otro turista pueda entrar

            System.out.println("Turista " + turistaId + " ha salido del museo.");
            aforoMuseo.release(); // Libera un lugar en el aforo del museo

        } catch (InterruptedException e) {
            e.printStackTrace(); // Imprime el error si un hilo es interrumpido
        }
    }
}
