package datos.Excepciones;
import java.util.Scanner;

class ContraseñaInvalidaExcepcion extends Exception {
    public ContraseñaInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}

public class excepcionPersonalizada {
    // Crea un método verificarContraseña que acepte una cadena de texto como parámetro y lance una excepción ContraseñaInvalidaException si la contraseña no contiene al menos 8 caracteres o no tiene algún número
    public static void verificar(String contra) throws ContraseñaInvalidaExcepcion {
        if (contra.length() < 8 || !contra.matches(".*\\d.*")) {
            throw new ContraseñaInvalidaExcepcion("Contraseña incorrecta");
        } else {
            System.out.println("Contraseña correcta");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dime la contrañe que quieras:");
        String contra = scanner.nextLine();
        try {
            verificar(contra);
        } catch (ContraseñaInvalidaExcepcion e) {
        }
    }
}

