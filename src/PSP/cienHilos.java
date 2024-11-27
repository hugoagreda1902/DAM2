package PSP;

// Clase que representa un hilo que imprime un número
class HiloNumero extends Thread {
    private int numero;  // Atributo para almacenar el número que el hilo debe imprimir

    // Constructor que inicializa el número
    public HiloNumero(int numero) {
        this.numero = numero;
    }

    // Método que se ejecuta cuando el hilo comienza
    @Override
    public void run() {
        // Imprime el número asignado al hilo
        System.out.println("Hilo número: " + numero);
    }
}

public class cienHilos {
    public static void main(String[] args) {
        // Bucle para crear y ejecutar 100 hilos
        for (int i = 1; i <= 100; i++) {
            // Crear un nuevo hilo y arrancarlo
            new HiloNumero(i).start();
        }
    }
}
