package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.model.Categoria;
import dao.model.Producto;


public class RepositorioCategoriaDAO 
	implements RepositorioDAO<Categoria, Integer>{


	@Override
	public boolean add(Categoria data) {
		String sql = "INSERT INTO CATEGORIA (idcategoria, categoria) VALUES (?, ?)";
		/* Utilizamos try-catch-resources, de modo que tanto el PreparedStatement
		   cómo el objeto Connection se cerrarán automáticamente al finalizar
		   la operación. */
		try (Connection con = FabricaDeConexiones.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, data.getIdcategoria());
			ps.setString(2, data.getCategoria());
			int n = ps.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


	@Override
	public boolean remove(Integer id) {
		String sql = "DELETE FROM CATEGORIA WHERE idcategoria = ?";
		try (Connection con = FabricaDeConexiones.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			int n = ps.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


	@Override
	public Categoria findById(Integer id) {
	    String sql = "SELECT * FROM CATEGORIA WHERE idcategoria = ?";
	    Categoria c = null;


	    try (Connection con = FabricaDeConexiones.getConnection();
	    	PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, id);


	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                c = new Categoria();
	                c.setIdcategoria(rs.getInt("idcategoria"));
	                c.setCategoria(rs.getString("categoria"));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return c;
	}




	@Override
	public boolean update(Categoria data) {
		String sql = "UPDATE CATEGORIA SET Categoria = ? WHERE idcategoria = ?";
		try (Connection con = FabricaDeConexiones.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, data.getCategoria());
			ps.setInt(2, data.getIdcategoria());
			int n = ps.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


	// SOBRECARGA DE MÉTODOS
	
	// Sin parámetro devuelve lista de categorías.
	@Override
	public List<Categoria> getList() {
	    String sql = "SELECT * FROM CATEGORIA";
	    List<Categoria> categorias = new ArrayList<>();


	    try (Connection con = FabricaDeConexiones.getConnection();
	    	PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {


	        while (rs.next()) {
	            Categoria c = new Categoria();
	            c.setIdcategoria(rs.getInt("idcategoria"));
	            c.setCategoria(rs.getString("categoria"));
	            categorias.add(c);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }


	    return categorias;
	}




	// Con parámetro devuelve productos de una categoría.
	public List<Producto> getList(Integer idCategoria) {
	    String sql = "SELECT * FROM PRODUCTO WHERE idcategoria = ?";
	    List<Producto> productos = new ArrayList<>();


	    try (Connection con = FabricaDeConexiones.getConnection();
	    	PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, idCategoria);


	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Producto p = new Producto();
	                p.setIdproducto(rs.getInt("idproducto")); // por si lo necesitas
	                p.setNombre(rs.getString("nombre"));
	                p.setMedida(rs.getString("medida"));
	                p.setStock(rs.getInt("stock"));
	                p.setPrecio(rs.getInt("precio"));


	                productos.add(p);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }


	    return productos;
	}
}
