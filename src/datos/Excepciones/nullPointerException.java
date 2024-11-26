package datos.Excepciones;

public class nullPointerException {
    // Crea un progama que intente acceder a un método que es null y captura la excepción
    public static void main(String[] args) {
        String nombre = null;
        try {
            int longitud = nombre.length();
            System.out.println("Correcto");
        } catch (NullPointerException e) {
            System.out.println("Incorrecto");
        }

    }
}
