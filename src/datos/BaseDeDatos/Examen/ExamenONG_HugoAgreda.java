package datos.BaseDeDatos.Examen;

import java.sql.*;
import java.util.Scanner;

public class ExamenONG_HugoAgreda {
    private static final String URL = "jdbc:mysql://localhost:3306/ONG";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int controlador = 0;
        while (controlador == 0) {
            System.out.println("¿Que quieres hacer?\n");
            System.out.println("1. Añadir nueva donación");
            System.out.println("2. Consultar número de socio");
            System.out.println("3. Consultar importe de donación");
            System.out.println("4. Consultar el importe entre fechas");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    anadirNuevaDonacion();
                    break;
                case 2:
                    consultarNumeroSocio();
                    break;
                case 3:
                    consultarImporteDonacion();
                    break;
                case 4:
                    consultarImporteFechas();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    controlador += 1;
                    break;
                default:
                    System.out.println("Opción no válida. Intentalo de nuevo");
            }
        }
    }

    // 1. Añadir una nueva donación de un socio.
    public static void anadirNuevaDonacion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIntroduce número de socio");
        int numero_socio = scanner.nextInt();

        System.out.println("\nIntroduce la cantidad donada (10 o 50)");
        int cant_donada = scanner.nextInt();

        System.out.println("\nIntroduce la fecha de donación (YY-MM-DD)");
        Date fecha = Date.valueOf(scanner.nextLine());

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO donaciones () VALUES (?,?,?)");

            pstmt.setInt(1, numero_socio);
            pstmt.setDouble(3, cant_donada);

        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
        }
    }

    // 2. Consultar el número de socios. La aplicación mostrará el número total de socios.
    public static void consultarNumeroSocio() {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String mostrar = "SELECT * FROM socios";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(mostrar);
            int contador = 0;

            while (rs.next()) {
                contador++;
            }
            System.out.println("Hay " + contador + " socios.\n");

        } catch (SQLException e) {
            System.err.println("Error");
        }
    }

    // 3. Consultar el importe total de las donaciones de un socio determinado. La aplicación mostrará el nombre del socio y la cantidad total de sus donaciones
    public static void consultarImporteDonacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIntroduce número de socio");
        int numero_socio = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String sql = "SELECT * FROM socios ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int numero = rs.getInt("numero_socio");
                if (numero == numero_socio) {
                    String nombre = rs.getString("nombre_socio");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
        }
    }

    //4. Consultar el importe total de las donaciones de todos los socios entre dos fechas dadas
    public static void consultarImporteFechas() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIntroduce la primera fecha (Menor)");
        String fecha1 = scanner.nextLine();


        System.out.println("\nIntroduce la segunda fecha (Mayor)");
        String fecha2 = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String mostrar = ("SELECT * FROM donaciones WHERE fecha_donacion BETWEEN ? AND ?");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(mostrar);





        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}