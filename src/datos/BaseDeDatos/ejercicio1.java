package datos.BaseDeDatos;

import java.sql.*;

public class ejercicio1 {
    private static final String URL = "jdbc:mysql://localhost:3306/acceso";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";
    public static void main(String[] args) {
        // actualizar();
        consultaTabla();

    }
// Pedir al usuario que de que estudiante quiere modificar la edad y la edad 
    private static void consultaTabla() {
        try (Connection connection = DriverManager.getConnection(URL,USUARIO,PASSWORD)){
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM estudiantes";
            statement.executeQuery(query);

            ResultSet resultSet = statement.getResultSet();
            System.out.println("Nombres de los estudiantes.");


            while (resultSet.next()) {
                System.out.printf("Nombre : %s.%n", resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Siguiente método es para actualizar
    private static void actualizar() {
        try (Connection connection = establecerConexion()) {
            Statement statement = connection.createStatement();
            String query = "UPDATE estudiantes SET nombre = 'Andres' WHERE nombre = 'Antonio'";
            statement.executeUpdate(query);

            System.out.println("Lineas afectadas: " + statement.getUpdateCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection establecerConexion() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        return connection;
    }
}

