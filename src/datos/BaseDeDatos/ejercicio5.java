package datos.BaseDeDatos;
import java.sql.*;

public class ejercicio5 {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/acceso";
        String USUARIO = "root";
        String PASSWORD = "1234";
        Connection con = null;

        try {
          con = DriverManager.getConnection(URL,USUARIO,PASSWORD);
          con.setAutoCommit(false);

          Statement stmt = con.createStatement();

          // Obtener el saldo actual de la cuenta 1 antes de relizar la operación.
          ResultSet rs = stmt.executeQuery("SELECT saldo FROM cuentas WHERE id = 1");
          double saldoCuenta = 0;
          if (rs.next()) {
              saldoCuenta = rs.getDouble("saldo");
          }

          // Comprobar si el saldo es suficiente para realizar la transferencia
          if (saldoCuenta >= 500) {
              // Si el saldo es suficiente, proceder con la transferencia
              stmt.executeUpdate("UPDATE cuentas SET saldo = saldo - 500 WHERE id = 1");
              stmt.executeUpdate("UPDATE cuentas SET saldo = saldo - 500 WHERE id = 2");

              con.commit();
              System.out.println("Transferencia realizada con éxito. ");

              // Mostrar los saldos actualizados
              rs = stmt.executeQuery("SELECT * FROM cuentas WHERE id = 1 OR 2");
              while (rs.next()) {
                  int id = rs.getInt("id");
                  String titular = rs.getString("titular");
                  double saldo = rs.getDouble("saldo");
                  System.out.println("id: " + id + " saldo: " + saldo + " nombre: " + titular);
              }
          } else {
              // Si el saldo en insuficiente, hacer rollback y mostrar mensaje de error
              System.out.println("Saldo insuficiente.");
              con.rollback();
          }
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback(); // Si ocurre una excepción, se deshacen los cambios
                    System.out.println("Transfierencia no se puedo realizar. ");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();// Si ocurre otro error dentro del primer catch, se maneja aquí.
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true); // Vuelve a activar el modo de auto-commit.
                    con.close(); // Cierra la conexión a la base de datos.
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Si ocurre un error al cerrar la conexión, se imprime el error.
            }
        }
    }
}
