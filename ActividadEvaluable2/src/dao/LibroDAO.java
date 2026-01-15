package dao;

import Modelo.Libro;
import Modelo.Genero;
import BD.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private GeneroDAO generoDAO = new GeneroDAO();

    public boolean insertLibro(Libro libro) {
        String sql = "INSERT INTO Libro (titulo, autor, idGenero, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getGenero().getIdGenero());
            ps.setDouble(4, libro.getPrecio());
            ps.setInt(5, libro.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertLibro: " + e.getMessage());
            return false;
        }
    }

    public boolean updateLibro(Libro libro) {
        String sql = "UPDATE Libro SET titulo=?, autor=?, idGenero=?, precio=?, stock=? WHERE idLibro=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getGenero().getIdGenero());
            ps.setDouble(4, libro.getPrecio());
            ps.setInt(5, libro.getStock());
            ps.setInt(6, libro.getIdLibro());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updateLibro: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteLibro(int idLibro) {
        String sql = "DELETE FROM Libro WHERE idLibro=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleteLibro: " + e.getMessage());
            return false;
        }
    }

    public Libro getLibroById(int idLibro) {
        String sql = "SELECT * FROM Libro WHERE idLibro=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Genero genero = generoDAO.getGeneroById(rs.getInt("idGenero"));
                return new Libro(rs.getInt("idLibro"), rs.getString("titulo"), rs.getString("autor"), genero, rs.getDouble("precio"), rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println("Error getLibroById: " + e.getMessage());
        }
        return null;
    }

    public List<Libro> getAllLibros() {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Libro";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Genero genero = generoDAO.getGeneroById(rs.getInt("idGenero"));
                lista.add(new Libro(rs.getInt("idLibro"), rs.getString("titulo"), rs.getString("autor"), genero, rs.getDouble("precio"), rs.getInt("stock")));
            }
        } catch (SQLException e) {
            System.out.println("Error getAllLibros: " + e.getMessage());
        }
        return lista;
    }
}
