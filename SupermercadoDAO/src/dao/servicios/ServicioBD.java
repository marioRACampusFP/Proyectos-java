package dao.servicios;


import java.util.List;
import java.util.Scanner;


import dao.RepositorioCategoriaDAO;
import dao.model.*;


public class ServicioBD {
	RepositorioCategoriaDAO repoCategorias;
	
    public ServicioBD() {
		this.repoCategorias = new RepositorioCategoriaDAO();
	}
    
	public void listarCategorias() {
        List<Categoria> categorias = repoCategorias.getList();
        for (Categoria c : categorias) {
        	System.out.println(c);
        }
    }
	
	public void buscarCategoriaPorId(Scanner lector) {
	    System.out.print("ID de la categoría: ");
	    int id = Integer.parseInt(lector.nextLine());


	    Categoria c = repoCategorias.findById(id);
	    if (c != null) {
	        System.out.println("Categoría encontrada: " + c);
	    } else {
	        System.out.println("No se encontró ninguna categoría con ID " + id);
	    }
	}


	public void anadirCategoria(Scanner lector) {
	    System.out.print("ID nueva categoría: ");
	    int id = Integer.parseInt(lector.nextLine());
	    System.out.print("Nombre de la categoría: ");
	    String nombre = lector.nextLine();


	    Categoria nueva = new Categoria();
	    nueva.setIdcategoria(id);
	    nueva.setCategoria(nombre);


	    boolean exito = repoCategorias.add(nueva);
	    if (exito) {
	        System.out.println("✅ Categoría añadida correctamente.");
	    } else {
	        System.out.println("❌ No se pudo añadir la categoría.");
	    }
	}




	public void actualizarCategoria(Scanner lector) {
	    System.out.print("ID de la categoría a actualizar: ");
	    int id = Integer.parseInt(lector.nextLine());
	    System.out.print("Nuevo nombre: ");
	    String nombre = lector.nextLine();


	    Categoria c = new Categoria();
	    c.setIdcategoria(id);
	    c.setCategoria(nombre);


	    boolean exito = repoCategorias.update(c);
	    if (exito) {
	        System.out.println("✅ Categoría actualizada correctamente.");
	    } else {
	        System.out.println("❌ No se pudo actualizar la categoría (puede que no exista).");
	    }
	}




	public void eliminarCategoria(Scanner lector) {
	    System.out.print("ID de la categoría a eliminar: ");
	    int id = Integer.parseInt(lector.nextLine());


	    boolean exito = repoCategorias.remove(id);
	    if (exito) {
	        System.out.println("✅ Categoría eliminada correctamente.");
	    } else {
	        System.out.println("❌ No se pudo eliminar la categoría (puede que no exista).");
	    }
	}




	public void listarProductosDeCategoria(Scanner lector) {
	    System.out.print("ID de la categoría: ");
	    int idCategoria = Integer.parseInt(lector.nextLine());


	    List<Producto> productos = repoCategorias.getList(idCategoria);
	    if (productos.isEmpty()) {
	        System.out.println("No hay productos para la categoría con ID " + idCategoria);
	    } else {
	        System.out.println("Productos de la categoría " + idCategoria + ":");
	        for (Producto p : productos) {
	            System.out.println(p);
	        }
	    }
	}
}

