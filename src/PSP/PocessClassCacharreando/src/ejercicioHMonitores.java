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
public class ejercicioHMonitores {

    private int galletas = 10; // Galletas iniciales
    private int reposiciones = 0; // Veces que el almacenero repone
    private static final int MAX_REPOSICIONES = 10;

    // Bloqueo para sincronizar acceso al almacén
    private final Object lock = new Object();

    public static void main(String[] args) {
        ejercicioHMonitores almacen = new ejercicioHMonitores();
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
                synchronized (lock) {
                    // Espera si no hay suficientes galletas
                    while (galletas == 0) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " esperando porque no hay galletas.");
                            lock.wait(); // Espera hasta que el almacenero reponga
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Come galletas
                    int galletasComidas = Math.min(galletasQueCome, galletas);
                    galletas -= galletasComidas;
                    System.out.println(Thread.currentThread().getName() + " ha comido " + galletasComidas + " galletas. Galletas restantes: " + galletas);

                    lock.notifyAll(); // Notifica a los demás hilos
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
                synchronized (lock) {
                    // Espera hasta que no haya galletas
                    while (galletas > 0) {
                        try {
                            lock.wait(); // Espera a que los monstruos consuman las galletas
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Repone galletas
                    galletas = 10;
                    reposiciones++;
                    System.out.println("Almacenero ha repuesto las galletas. Reposiciones realizadas: " + reposiciones);

                    lock.notifyAll(); // Notifica a los monstruos que hay galletas
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
Explicación (Monitores):
Sincronización:

Usamos un bloque synchronized en el objeto lock para controlar el acceso exclusivo al almacén.
Los hilos esperan (wait) si las condiciones no son adecuadas (por ejemplo, si no hay galletas o si todavía quedan galletas).
Se utiliza notifyAll para despertar a los hilos cuando la condición cambia.
Lógica de Monstruos:

Cada monstruo consume una cantidad fija de galletas.
Si no hay suficientes galletas, el monstruo espera hasta que el almacenero reponga.
Lógica del Almacenero:

El almacenero repone galletas solo cuando el almacén está vacío.
El programa termina cuando el almacenero ha repuesto 10 veces.
 */
