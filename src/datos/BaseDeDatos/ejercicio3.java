package datos.BaseDeDatos;

import java.sql.*;

public class ejercicio3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/acceso";
        String user = "root";
        String password = "1234";

        Connection con = null;
        CallableStatement cstmt = null;

        try {
            // 1. Establecer la conexión
            con = DriverManager.getConnection(url,user,password);

            // 2. Prepara la llamada al procedimiento almacenado
            String procedimiento = "{CALL calcularSalarioTotal(?,?)}";
            cstmt = con.prepareCall(procedimiento);

            // 3. Establecer los parámetros del procedimiento almacenado
            cstmt.setString(1, "IT"); // Parámetro de entrada: Departamento
            cstmt.registerOutParameter(2, Types.DECIMAL); // Parámetro de salida: Salario Total

            // 4. Ejecutar el procedimiento almacenado
            cstmt.execute();

            // 5. Obtener el valor del parámetro de salida
            double salarioTotal = cstmt.getDouble(2);
            System.out.println("El salario total del departamento de IT es: " + salarioTotal);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. Cerrar recursos
            try {
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
