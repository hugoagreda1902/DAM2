package datos.Ejemplos;

// Con try/catch
public class verificarEdad {
    public static void main(String[] args) {
        try {
            verificarEdad(15); // Intenta verificar la edad, pero no cumple la condición
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Captura la excepción y muestra el mensaje de error
        }
    }

    public static void verificarEdad(int edad) throws Exception {
        if (edad < 18) {
            throw new Exception("La edad debe ser mayor o igual a 18"); // Lanza la excepción si la edad es menor
        }
    }
}

/*
  1. El método verificarEdad(int edad) comprueba si la edad es mayor o igual a 18. Si no lo es, lanza una excepción con throw.
  2. En el método main, el bloque try llama a verificarEdad(15), lo que provocará que se lance la excepción.
  3. La excepción es capturada en el bloque catch, y se imprime el mensaje de error.
  Este patrón es útil para situaciones en las que quieres lanzar una excepción personalizada y luego capturarla en un bloque try/catch para manejarla adecuadamente.
*/