/*
H) Crea un proyecto java llamado Almacén, según la siguiente descripción:
En un almacén hay inicialmente 10 galletas:
•	Existen dos tipos de hilos, los monstruos de las galletas y el almacenero.
•	Creamos 3 monstruos y un almacenero.
•	Tanto los monstruos como el almacenero entran en el almacén cuando
no hay nadie, es decir, entran de uno en uno.
•	Un monstruo come un número de galletas aleatorio, entre 1 y 10 (pero
para cada monstruo, el número es fijo). Si el número es mayor que las
galletas que quedan se comerá todas las que estén disponibles.
•	El almacenero entra al almacén y si no quedan galletas repone otras 10.

Para ver el proceso, el almacenero y los monstruos, irán imprimiendo
mensajes de lo que van haciendo.
El programa termina cuando el almacenero repone 10 veces las galletas y
se han comido todas.

 */
import java.util.concurrent.Semaphore;

public class ejercicioHSemaforos {

    private int galletas = 10; // Galletas iniciales
    private int reposiciones = 0; // Veces que el almacenero repone
    private static final int MAX_REPOSICIONES = 10;

    private final Semaphore mutex = new Semaphore(1); // Controla el acceso exclusivo al almacén
    private final Semaphore galletasDisponibles = new Semaphore(0); // Controla cuando hay galletas disponibles
    private final Semaphore espacioParaReposicion = new Semaphore(1); // Controla cuando es necesario reponer

    public static void main(String[] args) {
        ejercicioHSemaforos almacen = new ejercicioHSemaforos();
        almacen.iniciar();
    }

    public void iniciar() {
        // Crear y arrancar los hilos
        Thread almacenero = new Thread(new Almacenero(), "Almacenero");
        Thread monstruo1 = new Thread(new Monstruo(3), "Monstruo 1");
        Thread monstruo2 = new Thread(new Monstruo(5), "Monstruo 2");
        Thread monstruo3 = new Thread(new Monstruo(7), "Monstruo 3");

        almacenero.start();
        monstruo1.start();
        monstruo2.start();
        monstruo3.start();
    }

    class Monstruo implements Runnable {
        private final int galletasQueCome;

        public Monstruo(int galletasQueCome) {
            this.galletasQueCome = galletasQueCome;
        }

        @Override
        public void run() {
            while (reposiciones < MAX_REPOSICIONES) {
                try {
                    galletasDisponibles.acquire(); // Espera a que haya galletas disponibles

                    mutex.acquire(); // Acceso exclusivo al almacén
                    int galletasComidas = Math.min(galletasQueCome, galletas);
                    galletas -= galletasComidas;
                    System.out.println(Thread.currentThread().getName() + " ha comido " + galletasComidas + " galletas. Galletas restantes: " + galletas);

                    if (galletas == 0) {
                        espacioParaReposicion.release(); // Notifica al almacenero que puede reponer
                    }

                    mutex.release(); // Libera el acceso al almacén
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000); // Simula el tiempo entre visitas
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Almacenero implements Runnable {
        @Override
        public void run() {
            while (reposiciones < MAX_REPOSICIONES) {
                try {
                    espacioParaReposicion.acquire(); // Espera hasta que sea necesario reponer

                    mutex.acquire(); // Acceso exclusivo al almacén
                    galletas = 10;
                    reposiciones++;
                    System.out.println("Almacenero ha repuesto las galletas. Reposiciones realizadas: " + reposiciones);

                    for (int i = 0; i < galletas; i++) {
                        galletasDisponibles.release(); // Libera permisos para las galletas
                    }

                    mutex.release(); // Libera el acceso al almacén
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(500); // Simula el tiempo de reposición
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/*
Explicación (Semáforos):
Semáforo mutex:

Controla el acceso exclusivo al almacén para evitar conflictos entre los monstruos y el almacenero.
Semáforo galletasDisponibles:

Controla cuántas galletas están disponibles para que los monstruos las consuman.
Semáforo espacioParaReposicion:

Indica al almacenero que puede reponer cuando no hay galletas.
Lógica de Monstruos:

Los monstruos consumen galletas si están disponibles.
Notifican al almacenero si se quedan sin galletas.
Lógica del Almacenero:

Reponen galletas solo cuando no hay disponibles.
El programa finaliza tras 10 reposiciones.
 */
