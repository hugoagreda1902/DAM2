package PSP.JavaExercises;
public class BaseDeDatos {
    private static final Object monitor = new Object(); // Monitor para el acceso

    public static void accederBaseDeDatos(int hiloId) {
        synchronized (monitor) {
            System.out.println("Hilo " + hiloId + " accediendo a la base de datos.");
            try {
                Thread.sleep(2000); // Simula el tiempo de acceso
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hilo " + hiloId + " terminó de acceder a la base de datos.");
        }
    }

    public static void main(String[] args) {
        Runnable tarea = (Runnable) () -> {
            int hiloId = Integer.parseInt(Thread.currentThread().getName());
            accederBaseDeDatos(hiloId);
        };

        for (int i = 1; i <= 3; i++) {
            Thread hilo = new Thread(tarea, String.valueOf(i));
            hilo.start();
        }
    }
}
