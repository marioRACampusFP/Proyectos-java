import java.util.Scanner;


import dao.servicios.ServicioBD;


public class Principal {
    private static final Scanner lector = new Scanner(System.in);


    public static void main(String[] args) {
        String opcion;
        ServicioBD servicio = new ServicioBD();
       
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case "1":
                	servicio.listarCategorias();
                    break;
                case "2":
                	servicio.buscarCategoriaPorId(lector);
                    break;
                case "3":
                	servicio.anadirCategoria(lector);
                    break;
                case "4":
                	servicio.actualizarCategoria(lector);
                    break;
                case "5":
                	servicio.eliminarCategoria(lector);
                    break;
                case "6":
                	servicio.listarProductosDeCategoria(lector);
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            System.out.println(); // línea en blanco para separar ciclos
        } while (!opcion.equals("0"));
    }


    public static String mostrarMenu() {
        System.out.println("===== MENÚ PRINCIPAL =====");
        System.out.println("1. Listar todas las categorías");
        System.out.println("2. Buscar una categoría por id");
        System.out.println("3. Añadir una nueva categoría");
        System.out.println("4. Actualizar el nombre de una categoría");
        System.out.println("5. Eliminar una categoría");
        System.out.println("6. Listar productos de una categoría");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        return lector.nextLine().trim();
    }
}
