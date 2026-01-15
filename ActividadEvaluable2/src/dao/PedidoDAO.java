package dao;

import Modelo.Pedido;
import Modelo.Cliente;
import BD.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public boolean insertPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido (idCliente, fecha, total) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pedido.getCliente().getIdCliente());
            ps.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setDouble(3, pedido.getTotal());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertPedido: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePedido(Pedido pedido) {
        String sql = "UPDATE Pedido SET idCliente=?, fecha=?, total=? WHERE idPedido=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pedido.getCliente().getIdCliente());
            ps.setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setDouble(3, pedido.getTotal());
            ps.setInt(4, pedido.getIdPedido());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updatePedido: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePedido(int idPedido) {
        String sql = "DELETE FROM Pedido WHERE idPedido=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deletePedido: " + e.getMessage());
            return false;
        }
    }

    public Pedido getPedidoById(int idPedido) {
        String sql = "SELECT * FROM Pedido WHERE idPedido=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = clienteDAO.getClienteById(rs.getInt("idCliente"));
                return new Pedido(rs.getInt("idPedido"), cliente, rs.getDate("fecha"), rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error getPedidoById: " + e.getMessage());
        }
        return null;
    }

    public List<Pedido> getAllPedidos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = clienteDAO.getClienteById(rs.getInt("idCliente"));
                lista.add(new Pedido(rs.getInt("idPedido"), cliente, rs.getDate("fecha"), rs.getDouble("total")));
            }
        } catch (SQLException e) {
            System.out.println("Error getAllPedidos: " + e.getMessage());
        }
        return lista;
    }
}
