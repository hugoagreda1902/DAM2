package datos.Excepciones;

public class arrayIndexOfBoundsException {
    // Crea un programa que intente acceder a un índice fuera del rango de un array y captura la excepción.
    public static void main(String[] args) {
        int [] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        try {
            System.out.println("El número que estás buscando es: " + array[9]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("El array no existe.");

        }

    }
}
