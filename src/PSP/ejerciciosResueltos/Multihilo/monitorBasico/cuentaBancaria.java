package PSP.ejerciciosResueltos.Multihilo.monitorBasico;

// Clase que simula una cuenta bancaria con métodos sincronizados para manejar depósitos y retiros.
class cuentaBancaria {

    private int saldo; // Saldo de la cuenta bancaria

    // Constructor para inicializar el saldo de la cuenta con un valor dado.
    public cuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método sincronizado para realizar depósitos en la cuenta
    public synchronized void depositar(int cantidad) {
        saldo += cantidad; // Se incrementa el saldo por la cantidad depositada
        // Se imprime en consola la acción realizada y el saldo actual después del depósito.
        System.out.println(Thread.currentThread().getName() + " depositó: " + cantidad);
        System.out.println("Saldo actual: " + saldo);
    }

    // Método sincronizado para realizar retiros de la cuenta
    public synchronized void retirar(int cantidad) {
        // Verifica si el saldo es suficiente para realizar el retiro
        if (saldo >= cantidad) {
            saldo -= cantidad; // Se decrementa el saldo por la cantidad retirada
            // Se imprime en consola la acción realizada y el saldo actual después del retiro.
            System.out.println(Thread.currentThread().getName() + " retiró: " + cantidad);
        } else {
            // Si el saldo es insuficiente, se muestra un mensaje en consola indicando el error.
            System.out.println(Thread.currentThread().getName() + " intentó retirar: " +
                    cantidad + " (Saldo insuficiente)");
        }
        // Se imprime el saldo actualizado en consola después de la operación
        System.out.println("Saldo actual: " + saldo);
    }
}
