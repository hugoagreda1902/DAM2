package datos.BaseDeDatos;

import java.sql.*;
import java.util.Scanner;

public class ejercicio2 {
    private static final String URL = "jdbc:mysql://localhost:3306/estudiantes";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
    crearUsuario();
    }

    private static void crearUsuario() {
        try (Connection connection = DriverManager.getConnection(URL,USUARIO,PASSWORD)){
            Scanner sc = new Scanner(System.in);

            System.out.println("Ingrese el nombre del usuario: ");
            String inicio = sc.nextLine();
            System.out.println("Ingrese la contraseña: ");
            String contrasena = sc.nextLine();

            // Primero, comprobamos si el nombre de usuario existe
            String query = "SELECT * FROM usuario WHERE nombre_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inicio); // Evitar inyeccion SQL

            // Ejecutamos la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Si escontramos el nombre de usuario
            if (resultSet.next()) {
                String contrasenaUsuario = resultSet.getString("contraseña");
                // Compara la contraseña
                if (!contrasena.equals(contrasenaUsuario)) {
                    System.out.println("La contraseña no es correcta");
                } else {
                    System.out.println("Credenciales correctas");
                }
            } else {
                // Si no encontramos el nombre de usuario
                System.out.println("Usuario no encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
