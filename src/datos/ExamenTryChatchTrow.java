package datos;

import java.util.Scanner;

class AsientosNoDisponiblesException extends Exception{
    public AsientosNoDisponiblesException(String mensaje){
        super(mensaje);
    }
}
public class ExamenTryChatchTrow {
    public static void main(String[] args) throws AsientosNoDisponiblesException {
        int asientos = 5;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el número de asientos que quiere reservar: ");
        int numero = sc.nextInt();
        if(numero > asientos){
            throw new AsientosNoDisponiblesException("No hay suficientes asientos disponibles");
        } else {
            System.out.println("Has reservado " + numero + " quedan " + (numero - asientos) + " libres");
        }
    }
}
