package PSP;

// Clase que genera números pares
class NumerosPares extends Thread {
    @Override
    public void run() {
        // Bucle para imprimir los números pares entre 2 y 100 (inclusive)
        for (int i = 2; i <= 100; i += 2) {
            System.out.println("Par: " + i);  // Imprime el número par
        }
    }
}

// Clase que genera números impares
class NumerosImpares extends Thread {
    @Override
    public void run() {
        // Bucle para imprimir los números impares entre 1 y 99
        for (int i = 1; i < 100; i += 2) {
            System.out.println("Impar: " + i);  // Imprime el número impar
        }
    }
}

public class parImparCorregido {
    public static void main(String[] args) {
        // Creación de los hilos para números pares e impares
        Thread hilosPares = new NumerosPares();
        Thread hilosImpares = new NumerosImpares();

        // Inicia los hilos
        hilosPares.start();
        hilosImpares.start();
    }
}

