package PSP;

// Clase que maneja el contador compartido
class Contador {
    private int contador = 0; // Contador compartido

    // Método sincronizado para imprimir números pares
    public synchronized void printPar() {
        // Espera si el contador es impar
        while (contador % 2 != 0) {
            esperar();
        }
        System.out.println(contador + " Es número par");
        contador++; // Incrementa el contador
        notify(); // Notifica al hilo que imprime impares
    }

    // Método sincronizado para imprimir números impares
    public synchronized void printImpar() {
        // Espera si el contador es par
        while (contador % 2 == 0) {
            esperar();
        }
        System.out.println(contador + " Es número impar");
        contador++; // Incrementa el contador
        notify(); // Notifica al hilo que imprime pares
    }

    // Método para manejar la espera
    private void esperar() {
        try {
            wait(); // Libera el bloqueo y espera a ser notificado
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}

// Clase que representa el hilo para imprimir números pares
class Par extends Thread {
    private Contador contador;

    public Par(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i += 2) {
            contador.printPar(); // Llama al método para imprimir par
            dormir(); // Espera antes de continuar
        }
    }

    // Método para manejar la espera
    private void dormir() {
        try {
            Thread.sleep(25); // Espera 100 ms
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}

// Clase que representa el hilo para imprimir números impares
class
Impar extends Thread {
    private Contador contador;

    public Impar(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i += 2) {
            contador.printImpar(); // Llama al método para imprimir impar
            dormir(); // Espera antes de continuar
        }
    }

    // Método para manejar la espera
    private void dormir() {
        try {
            Thread.sleep(25); // Espera 100 ms
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}

// Clase principal para ejecutar el programa
public class parImpar {
    public static void main(String[] args) {
        Contador contador = new Contador(); // Crea un objeto contador
        new Par(contador).start(); // Inicia el hilo que imprime pares
        new Impar(contador).start(); // Inicia el hilo que imprime impares
    }
}
