package datos.Ejemplos;

// Con throw
public class verificarEdad1 {
    public static void main(String[] args) {
        int edad = 15;
        if (edad < 18) {
            throw new IllegalArgumentException("Los pipiolines no pueden pasar.");
        }
    }
}

/*
  En este ejemplo, si se intenta asignar un valor negativo a la variable edad, se lanza una excepción IllegalArgumentException con un mensaje explicativo.
*/
