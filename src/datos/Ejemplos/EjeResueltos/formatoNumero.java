package datos.Ejemplos.EjeResueltos;

public class formatoNumero {
    public static void main(String[] args) {
        try {
            String str = "abc";
            int number = Integer.parseInt(str);
            System.out.println("Número: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        }
    }
}
