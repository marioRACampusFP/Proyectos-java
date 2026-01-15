package dao;

import Modelo.Venta;
import Modelo.Pedido;
import Modelo.Libro;
import BD.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private LibroDAO libroDAO = new LibroDAO();

    public boolean insertVenta(Venta venta) {
        String sql = "INSERT INTO Venta (idPedido, idLibro, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, venta.getPedido().getIdPedido());
            ps.setInt(2, venta.getLibro().getIdLibro());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getSubtotal());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertVenta: " + e.getMessage());
            return false;
        }
    }

    public boolean updateVenta(Venta venta) {
        String sql = "UPDATE Venta SET idPedido=?, idLibro=?, cantidad=?, subtotal=? WHERE idVenta=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, venta.getPedido().getIdPedido());
            ps.setInt(2, venta.getLibro().getIdLibro());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getSubtotal());
            ps.setInt(5, venta.getIdVenta());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updateVenta: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteVenta(int idVenta) {
        String sql = "DELETE FROM Venta WHERE idVenta=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleteVenta: " + e.getMessage());
            return false;
        }
    }

    public Venta getVentaById(int idVenta) {
        String sql = "SELECT * FROM Venta WHERE idVenta=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pedido pedido = pedidoDAO.getPedidoById(rs.getInt("idPedido"));
                Libro libro = libroDAO.getLibroById(rs.getInt("idLibro"));
                return new Venta(rs.getInt("idVenta"), pedido, libro, rs.getInt("cantidad"), rs.getDouble("subtotal"));
            }
        } catch (SQLException e) {
            System.out.println("Error getVentaById: " + e.getMessage());
        }
        return null;
    }

    public List<Venta> getAllVentas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pedido pedido = pedidoDAO.getPedidoById(rs.getInt("idPedido"));
                Libro libro = libroDAO.getLibroById(rs.getInt("idLibro"));
                lista.add(new Venta(rs.getInt("idVenta"), pedido, libro, rs.getInt("cantidad"), rs.getDouble("subtotal")));
            }
        } catch (SQLException e) {
            System.out.println("Error getAllVentas: " + e.getMessage());
        }
        return lista;
    }
}
