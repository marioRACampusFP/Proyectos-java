package dao;

import Modelo.Cliente;
import BD.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean insertCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertCliente: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre=?, apellido=?, email=?, telefono=? WHERE idCliente=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updateCliente: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCliente(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE idCliente=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleteCliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente getClienteById(int idCliente) {
        String sql = "SELECT * FROM Cliente WHERE idCliente=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"));
            }
        } catch (SQLException e) {
            System.out.println("Error getClienteById: " + e.getMessage());
        }
        return null;
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono")));
            }
        } catch (SQLException e) {
            System.out.println("Error getAllClientes: " + e.getMessage());
        }
        return lista;
    }
}
