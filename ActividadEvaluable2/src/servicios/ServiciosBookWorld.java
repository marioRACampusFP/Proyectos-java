package servicios;

import dao.GeneroDAO;
import dao.LibroDAO;
import Modelo.Genero;
import Modelo.Libro;
import BD.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiciosBookWorld {

    private GeneroDAO generoDAO = new GeneroDAO();
    private LibroDAO libroDAO = new LibroDAO();

    // Insertar un genero y una lista de libros en una sola transaccion
    public boolean insertarGeneroYLibros(Genero genero, List<Libro> libros) {
        Connection con = null;
        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            // Insertar genero
            generoDAO.insertGenero(genero);

            // Obtener id generado
            List<Genero> generos = generoDAO.getAllGeneros();
            int idGenero = generos.get(generos.size() - 1).getIdGenero();
            genero.setIdGenero(idGenero);

            // Insertar libros
            for (Libro libro : libros) {
                libro.setGenero(genero);
                libroDAO.insertLibro(libro);
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en transaccion: " + e.getMessage());
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rollback: " + ex.getMessage());
            }
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error autoCommit: " + e.getMessage());
            }
        }
    }
}
