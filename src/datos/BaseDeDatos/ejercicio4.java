package datos.BaseDeDatos;

import java.sql.*;

public class ejercicio4 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/acceso";
        String user = "root";
        String password = "1234";

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // 1. Establecer la conexión
            con = DriverManager.getConnection(url,user,password);

            // 2. Prepara la llamada al procedimiento almacenado
            String procedimiento = "{CALL horasTrabajadas(?)}";
            cstmt = con.prepareCall(procedimiento);

            // 3. Establecer los parámetros del procedimiento almacenado
            cstmt.setString(1, "Proyecto1"); // Parámetro de entrada: Proyecto

            // 4. Ejecutar el procedimiento almacenado
            boolean hasResults = cstmt.execute();
            if (hasResults) {
                rs = cstmt.getResultSet();
                System.out.println("Los nombres son:" );
                while (rs.next()) {
                    int horasTrabajadas = rs.getInt(2);
                    String nombre = rs.getString("nombre");
                    System.out.println("Nombre: " + nombre + " - Horas: " + horasTrabajadas);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. Cerrar recursos
            try {
                if (cstmt != null) cstmt.close();
                if (con != null) con.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}