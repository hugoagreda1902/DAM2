package datos.Excepciones;// Programa que intente dividir dos números enteros y capture la excepción si el divisor es cero

public class arithmeticException {
    public static void main(String[] args) {
        try {
            int num1 = 10;
             int num2 = 2;
            int result = num1 / num2;
            System.out.println("Resultado: " + result);
        }
        catch (ArithmeticException e) {
            System.out.println("Error: Division entre creo no está permitida");
        } finally {
            System.out.println("Ya te he dicho el error");
        }
    }
}
