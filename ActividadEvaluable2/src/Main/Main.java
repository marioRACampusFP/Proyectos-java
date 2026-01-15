package Main;

import Modelo.Genero;
import Modelo.Libro;
import dao.GeneroDAO;
import dao.LibroDAO;
import servicios.ServiciosBookWorld;
import BD.Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static GeneroDAO generoDAO = new GeneroDAO();
    private static LibroDAO libroDAO = new LibroDAO();
    private static ServiciosBookWorld servicio = new ServiciosBookWorld();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- BookWorld Menu ---");
            System.out.println("1. Listar Géneros");
            System.out.println("2. Insertar Género");
            System.out.println("3. Modificar Género");
            System.out.println("4. Eliminar Género");
            System.out.println("5. Listar Libros");
            System.out.println("6. Insertar Libro");
            System.out.println("7. Modificar Libro");
            System.out.println("8. Eliminar Libro");
            System.out.println("9. Insertar Género y Libros (transacción)");
            System.out.println("10. Actualizar stock de un libro (procedimiento almacenado)");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                
                case 1:
                    generoDAO.getAllGeneros().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Nombre Género: ");
                    String nombre = sc.nextLine();
                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();
                    generoDAO.insertGenero(new Genero(0, nombre, desc));
                    break;
                case 3:
                    System.out.print("ID Género a modificar: ");
                    int idModG = sc.nextInt(); sc.nextLine();
                    Genero gMod = generoDAO.getGeneroById(idModG);
                    if (gMod != null) {
                        System.out.print("Nuevo nombre: ");
                        gMod.setNombreGenero(sc.nextLine());
                        System.out.print("Nueva descripción: ");
                        gMod.setDescripcionGenero(sc.nextLine());
                        generoDAO.updateGenero(gMod);
                    }
                    break;
                case 4:
                    System.out.print("ID Género a eliminar: ");
                    int idDelG = sc.nextInt(); sc.nextLine();
                    generoDAO.deleteGenero(idDelG);
                    break;

            
                case 5:
                    libroDAO.getAllLibros().forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Título: "); String titulo = sc.nextLine();
                    System.out.print("Autor: "); String autor = sc.nextLine();
                    System.out.print("ID Género: "); int idGenero = sc.nextInt();
                    System.out.print("Precio: "); double precio = sc.nextDouble();
                    System.out.print("Stock: "); int stock = sc.nextInt(); sc.nextLine();
                    libroDAO.insertLibro(new Libro(0, titulo, autor, generoDAO.getGeneroById(idGenero), precio, stock));
                    break;
                case 7:
                    System.out.print("ID Libro a modificar: "); int idModL = sc.nextInt(); sc.nextLine();
                    Libro lMod = libroDAO.getLibroById(idModL);
                    if (lMod != null) {
                        System.out.print("Nuevo título: "); lMod.setTitulo(sc.nextLine());
                        System.out.print("Nuevo autor: "); lMod.setAutor(sc.nextLine());
                        System.out.print("Nuevo ID Género: "); lMod.setGenero(generoDAO.getGeneroById(sc.nextInt())); sc.nextLine();
                        System.out.print("Nuevo precio: "); lMod.setPrecio(sc.nextDouble());
                        System.out.print("Nuevo stock: "); lMod.setStock(sc.nextInt()); sc.nextLine();
                        libroDAO.updateLibro(lMod);
                    }
                    break;
                case 8:
                    System.out.print("ID Libro a eliminar: "); int idDelL = sc.nextInt(); sc.nextLine();
                    libroDAO.deleteLibro(idDelL);
                    break;

       
                case 9:
                    System.out.print("Nombre nuevo Género: ");
                    String nombreG = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descG = sc.nextLine();
                    Genero g = new Genero(0, nombreG, descG);

                    List<Libro> listaLibros = new ArrayList<>();
                    System.out.print("¿Cuántos libros quieres agregar al género? ");
                    int n = sc.nextInt(); sc.nextLine();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Título libro " + (i+1) + ": "); String t = sc.nextLine();
                        System.out.print("Autor: "); String a = sc.nextLine();
                        System.out.print("Precio: "); double p = sc.nextDouble();
                        System.out.print("Stock: "); int s = sc.nextInt(); sc.nextLine();
                        listaLibros.add(new Libro(0, t, a, g, p, s));
                    }
                    if (servicio.insertarGeneroYLibros(g, listaLibros)) {
                        System.out.println("Transacción completada correctamente.");
                    } else {
                        System.out.println("Error en la transacción.");
                    }
                    break;

                case 10:
                    System.out.print("ID del libro: "); int idLibro = sc.nextInt();
                    System.out.print("Cantidad a restar del stock: "); int cantidad = sc.nextInt(); sc.nextLine();
                    actualizarStock(idLibro, cantidad);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    System.exit(0);
            }
        }
    }

    private static void actualizarStock(int idLibro, int cantidad) {
        String sql = "{CALL actualizarStock(?, ?)}";
        try (Connection con = Conexion.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idLibro);
            cs.setInt(2, cantidad);
            cs.executeUpdate();
            System.out.println("Stock actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        }
    }
}

