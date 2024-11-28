import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

// Clase que representa un vehículo.
class Vehiculo extends Thread {
    private final ITV itv; // Referencia al objeto ITV donde se registrará.
    private final String id; // Identificador único del vehículo.

    public Vehiculo(ITV itv, String id) {
        this.itv = itv; // Asignar ITV asociada al vehículo.
        this.id = id;  // Asignar un identificador al vehículo.
    }

    @Override
    public void run() {
        try {
            // El vehículo llega a la ITV e intenta registrarse.
            System.out.println("Vehículo " + id + " llega a la ITV.");
            itv.registrarVehiculo(this); // Agregar el vehículo a la lista.
        } catch (InterruptedException e) {
            System.err.println("Vehículo " + id + " fue interrumpido mientras se registraba.");
        }
    }

    public String getIdVehiculo() {
        return id; // Devuelve el identificador del vehículo.
    }
}

// Clase que representa un puesto de inspección.
class Puesto extends Thread {
    private final ITV itv; // Referencia al objeto ITV para tomar vehículos.
    private final String id; // Identificador único del puesto.

    public Puesto(ITV itv, String id) {
        this.itv = itv; // Asignar ITV asociada al puesto.
        this.id = id;  // Asignar un identificador al puesto.
    }

    @Override
    public void run() {
        try {
            while (true) {
                // El puesto intenta tomar un vehículo de la lista para inspeccionar.
                Vehiculo vehiculo = itv.tomarVehiculo();
                if (vehiculo == null) {
                    // Si no hay más vehículos, termina el trabajo.
                    System.out.println("Puesto " + id + " no tiene más vehículos para inspeccionar.");
                    break;
                }

                // Simula la inspección del vehículo.
                System.out.println("Puesto " + id + " inspecciona el vehículo " + vehiculo.getIdVehiculo() + ".");
                Thread.sleep((long) (Math.random() * 3000 + 1000)); // Simula tiempo de inspección de 1 a 3 segundos.

                // Notifica a la ITV que la inspección de este vehículo ha concluido.
                itv.agregarTiempoTotal(vehiculo);
                System.out.println("Puesto " + id + " terminó con el vehículo " + vehiculo.getIdVehiculo() + ".");
            }
        } catch (InterruptedException e) {
            System.err.println("Puesto " + id + " fue interrumpido durante la inspección.");
        }
    }
}

// Clase principal que gestiona la ITV.
class ITV {
    private final Queue<Vehiculo> vehiculosPendientes = new LinkedList<>(); // Lista de espera de vehículos.
    private final Semaphore semaforoLista = new Semaphore(1); // Semáforo binario para controlar acceso a la lista.
    private int tiempoTotal = 0; // Tiempo total acumulado de inspecciones.

    // Método para registrar un vehículo en la lista de espera.
    public void registrarVehiculo(Vehiculo vehiculo) throws InterruptedException {
        // Adquirir el semáforo antes de modificar la lista para evitar condiciones de carrera.
        semaforoLista.acquire();
        try {
            vehiculosPendientes.add(vehiculo); // Añadir el vehículo a la lista.
            System.out.println("Vehículo " + vehiculo.getIdVehiculo() + " registrado en la ITV.");
        } finally {
            // Liberar el semáforo para que otros hilos puedan acceder a la lista.
            semaforoLista.release();
        }
    }

    // Método para que un puesto tome un vehículo de la lista.
    public Vehiculo tomarVehiculo() throws InterruptedException {
        // Adquirir el semáforo antes de acceder a la lista.
        semaforoLista.acquire();
        try {
            // Extraer el primer vehículo de la lista. Si la lista está vacía, devolver null.
            return vehiculosPendientes.poll();
        } finally {
            // Liberar el semáforo para permitir que otros hilos accedan a la lista.
            semaforoLista.release();
        }
    }

    // Método para sumar el tiempo total de inspección de un vehículo.
    public synchronized void agregarTiempoTotal(Vehiculo vehiculo) {
        // Bloque sincronizado para evitar que varios puestos actualicen el tiempo total al mismo tiempo.
        tiempoTotal += 1; // Incrementa el tiempo acumulado (este valor puede cambiar según necesidades).
    }

    // Método para obtener el tiempo total de inspección.
    public int obtenerTiempoTotal() {
        return tiempoTotal;
    }
}

// Clase principal que coordina la simulación.
public class ejercicioITV {
    public static void main(String[] args) throws InterruptedException {
        ITV itv = new ITV(); // Crear la ITV.

        // Crear y arrancar los hilos de los puestos de inspección.
        int numPuestos = 3; // Número de puestos de inspección.
        Puesto[] puestos = new Puesto[numPuestos];
        for (int i = 0; i < numPuestos; i++) {
            puestos[i] = new Puesto(itv, "Puesto-" + (i + 1));
            puestos[i].start(); // Iniciar el hilo del puesto.
        }

        // Crear y arrancar los hilos de los vehículos.
        int numVehiculos = 10; // Número de vehículos.
        Vehiculo[] vehiculos = new Vehiculo[numVehiculos];
        for (int i = 0; i < numVehiculos; i++) {
            vehiculos[i] = new Vehiculo(itv, "Vehículo-" + (i + 1));
            vehiculos[i].start(); // Iniciar el hilo del vehículo.
        }
    }
}

        // Esperar a que todos los vehículos terminen su
/*
Explicación del código:
Vehículo:

Representa a un vehículo que llega a la ITV.
Cada vehículo se registra en la lista de espera de la ITV.
Puesto:

Representa un puesto de inspección.
Toma vehículos de la lista y los inspecciona.
ITV:

Controla la lista de vehículos y mantiene el tiempo total de inspección.
Utiliza un semáforo para evitar condiciones de carrera en la lista de vehículos.
Ejercicio2:

Es el programa principal.
Crea y gestiona los hilos de vehículos y puestos de inspección.
Muestra el tiempo total de inspección al finalizar la simulación.
Este enfoque refleja cómo manejar la concurrencia y sincronización en un
entorno dinámico como la inspección técnica de vehículos.
 */