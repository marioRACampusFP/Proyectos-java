package dao;

import Modelo.Genero;
import BD.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public boolean insertGenero(Genero genero) {
        String sql = "INSERT INTO Genero (nombreGenero, descripcionGenero) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, genero.getNombreGenero());
            ps.setString(2, genero.getDescripcionGenero());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertGenero: " + e.getMessage());
            return false;
        }
    }

    public boolean updateGenero(Genero genero) {
        String sql = "UPDATE Genero SET nombreGenero=?, descripcionGenero=? WHERE idGenero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, genero.getNombreGenero());
            ps.setString(2, genero.getDescripcionGenero());
            ps.setInt(3, genero.getIdGenero());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updateGenero: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteGenero(int idGenero) {
        String sql = "DELETE FROM Genero WHERE idGenero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idGenero);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleteGenero: " + e.getMessage());
            return false;
        }
    }

    public Genero getGeneroById(int idGenero) {
        String sql = "SELECT * FROM Genero WHERE idGenero=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idGenero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Genero(rs.getInt("idGenero"), rs.getString("nombreGenero"), rs.getString("descripcionGenero"));
            }
        } catch (SQLException e) {
            System.out.println("Error getGeneroById: " + e.getMessage());
        }
        return null;
    }

    public List<Genero> getAllGeneros() {
        List<Genero> lista = new ArrayList<>();
        String sql = "SELECT * FROM Genero";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Genero(rs.getInt("idGenero"), rs.getString("nombreGenero"), rs.getString("descripcionGenero")));
            }
        } catch (SQLException e) {
            System.out.println("Error getAllGeneros: " + e.getMessage());
        }
        return lista;
    }
}
