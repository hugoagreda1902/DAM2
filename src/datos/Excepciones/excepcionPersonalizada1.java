package datos.Excepciones;

import java.util.Scanner;

class SaldoInsufincienteException extends Exception {
    public SaldoInsufincienteException(String mensaje) {
        super(mensaje);
    }
}
public class excepcionPersonalizada1 {
    public static void main(String[] args) throws SaldoInsufincienteException {
        int saldo = 1000;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el saldo que quieres retirar: ");
        int dinero = sc.nextInt();
        if (dinero > saldo) {
            throw new SaldoInsufincienteException("No hay suficiente dinero");
        } else {
            System.out.println("Saldo es de " + (saldo - dinero));
        }
    }
    }
