package datos.BaseDeDatos;

import java.sql.*;
import java.util.Scanner;

public class biblioteca {
    private static final String URL = "jdbc:mysql://localhost:3306/acceso";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("¿Que quieres hacer?\n");
            System.out.println("1. Comprar libros nuevos");
            System.out.println("2. Vender un libro");
            System.out.println("3. Consultar libros disponibles");
            System.out.println("4. Eliminar libros del inventario");
            System.out.println("5. Registrar devolucionesa");
            System.out.println("6. Llamar a un procedimiento almacenado");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    comprarLibrosNuevos();
                    break;
                case 2:
                    venderLibro();
                    break;
                case 3:
                    consultarLibrosDisponibles();
                    break;
                case 4:
                    eliminarLibrosDelInventario();
                    break;
                case 5:
                    registrarDevoluciones();
                    break;
                case 6:
                    // Procedimiento
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intentalo de nuevo");
            }
        }
    }

    // Comprar libros nuevos: Agregar nuevos libros al inventario.
    public static void comprarLibrosNuevos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el nombre del libro");
        String titulo = scanner.nextLine();

        System.out.println("Introduce el autor del libro");
        String autor = scanner.nextLine();

        System.out.println("Introduce el genero del libro");
        String genero = scanner.nextLine();

        System.out.println("Introduce el stock del libro");
        int stock = scanner.nextInt();

        System.out.println("Introduce el precio del libro");
        double precio = scanner.nextDouble();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO libros (titulo,autor,genero,stock,precio) VALUES (?,?,?,?,?)");

            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, genero);
            pstmt.setInt(4, stock);
            pstmt.setDouble(5, precio);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("El libro se ha agregado correctamente");
            } else {
                System.out.println("El libro no se ha agregado");
            }
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos " + e.getMessage());
        }
    }

    // Vender un libro: Disminuir el stock del libro vendido. En caso de que no haya suficientes ejemplares, se debe lanzar una excepción y la venta no debe realizarse.
    public static void venderLibro() {
        Scanner scanner = new Scanner(System.in);

        consultarLibrosDisponibles();
        System.out.println("\nIntroduce el ID del libro");
        int id = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            // Primero, obtenemos el título del libro con el ID proporcionado
            String consultaTitulo = "SELECT titulo, stock FROM libros WHERE id = ?";
            PreparedStatement pstmtConsulta = connection.prepareStatement(consultaTitulo);
            pstmtConsulta.setInt(1, id);
            ResultSet rs = pstmtConsulta.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                int stockActual = rs.getInt("stock");

                System.out.println("Título del libro: " + titulo);
                System.out.println("Stock actual: " + stockActual);
                System.out.println("Introduce cuántos quieres vender");

                int cantidad = scanner.nextInt();

                // Verificamos si hay suficiente stock
                if (cantidad > stockActual) {
                    System.out.println("No hay suficiente stock para vender " + cantidad + " ejemplares.");
                    venderLibro();
                } else {
                    // Actualizamos el stock
                    String sql = "UPDATE libros SET stock = stock - ? WHERE id = ?";
                    PreparedStatement pstmtUpdate = connection.prepareStatement(sql);
                    pstmtUpdate.setInt(1, cantidad);
                    pstmtUpdate.setInt(2, id);

                    int filasAfectadas = pstmtUpdate.executeUpdate(); // Ejecuta la actualización

                    if (filasAfectadas > 0) {
                        System.out.println("Se ha vendido correctamente " + cantidad + " ejemplares de " + titulo);
                    } else {
                        System.out.println("No se pudo realizar la venta.");
                    }
                }
            } else {
                System.out.println("No se encontró el libro con ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al interactuar con la base de datos: " + e.getMessage());
        }
    }

    // Consultar libros disponibles: Mostrar el inventario de libros, con la opción de consultar por género, autor o título.
    public static void consultarLibrosDisponibles() {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String mostrar = "SELECT * FROM libros";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(mostrar);

            System.out.printf("%-5s %-30s %-20s %-15s %-10s %-10s%n",
                    "ID", "Título", "Autor", "Género", "Stock", "Precio");

            // Iterar por cada fila del resultado
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio");

                // Imprimir la fila formateada
                System.out.printf("%-5d %-30s %-20s %-15s %-10d %-10.2f%n",
                        id, titulo, autor, genero, stock, precio);
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar libros");
        }
    }

    // Eliminar libros del inventario: Eliminar libros del inventario si ya no se van a vender.
    public static void eliminarLibrosDelInventario() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el título del libro que quieres eliminar: ");
        String titulo = sc.nextLine();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String sql = "DELETE FROM libros WHERE titulo = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1,titulo);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("El libro con título " + titulo + " ha sido eliminado del iventario");
            } else {
                System.out.println("No se encontró ningun libro con el nombre " + titulo);
            }
        } catch (SQLException ex) {
            System.err.println("Error al interactuar con la base de datos");
        }
    }

    // Registrar devoluciones: Permitir que los clientes devuelvan un libro.
    public static void registrarDevoluciones () {
        Scanner sc = new Scanner(System.in);

        consultarLibrosDisponibles();
        System.out.println("Introduce el id del libro que deseas devolver");
        int id = sc.nextInt();

        System.out.println("Introduce cuántos libros quieres devolver");
        int devolucion = sc.nextInt();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String sql = "UPDATE libros SET stock = stock + ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, devolucion);
            pstmt.setInt(2, id);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("La devolución se ha registrado correctamente");
            } else {
                System.out.println("No se ha encontrado un libro con el ID " + id);

            }

        } catch (SQLException ex) {
            System.err.println("Error al interactuar con la base de datos");
        }

    }
}
