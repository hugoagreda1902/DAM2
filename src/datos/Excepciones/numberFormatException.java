package datos.Excepciones;

import java.util.Scanner;

public class numberFormatException {
    //  Realiza un programa que intente convertir una cadena de texto en un número entero y captura la excepción en caso de fallo.
    public static void main(String[] args) {
           try {
               Scanner sc = new Scanner(System.in);
               System.out.println("Introduzca un número entero: ");
               String nombre = sc.nextLine();
               int number = Integer.parseInt(nombre);
               System.out.println("Número:  " + number);
           } catch (NumberFormatException e) {
               System.out.println("Error!");
           }
    }
}
