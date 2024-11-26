package datos.Ejemplos.EjeResueltos;

public class fueraArray {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Intento de acceder a un índice fuera del rango.");
        }
    }
}
