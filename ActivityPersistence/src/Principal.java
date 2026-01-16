import java.io.*;
import java.util.Scanner;

public class Principal {
    private static final String FILE_NAME = "alumno.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Alumno alumno = null;

        //Comprobar si ya existe el archivo
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                alumno = (Alumno) ois.readObject();
                System.out.println("Alumno cargado desde archivo.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error leyendo el archivo: " + e.getMessage());
            }
        } else {
            //Si no existe, pedir datos al usuario
            System.out.print("Introduce el nombre del alumno: ");
            String nombre = sc.nextLine();
            System.out.print("Introduce la edad del alumno: ");
            int edad = sc.nextInt();
            sc.nextLine();
            alumno = new Alumno(nombre, edad);
            System.out.println("Alumno creado.");
        }

        //Menú principal
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Añadir nueva calificación");
            System.out.println("2. Mostrar listado de calificaciones");
            System.out.println("3. Mostrar media de calificaciones");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la asignatura: ");
                    String asignatura = sc.nextLine();
                    System.out.print("Introduce la nota: ");
                    int nota = sc.nextInt();
                    sc.nextLine();
                    alumno.calificar(asignatura, nota);
                    System.out.println("Calificación añadida.");
                    break;

                case 2:
                    System.out.println("\nListado de calificaciones de " + alumno.getNombre() + ":");
                    if (alumno.getCalificaciones().isEmpty()) {
                        System.out.println("No hay calificaciones.");
                    } else {
                        alumno.getCalificaciones().forEach(c ->
                            System.out.println(c.getAsignatura() + " -> " + c.getNota())
                        );
                    }
                    break;

                case 3:
                    if (alumno.getCalificaciones().isEmpty()) {
                        System.out.println("No hay calificaciones para calcular la media.");
                    } else {
                        double suma = 0;
                        for (Calificacion c : alumno.getCalificaciones()) {
                            suma += c.getNota();
                        }
                        double media = suma / alumno.getCalificaciones().size();
                        System.out.println("La media de calificaciones es: " + media);
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 4);

        //Guardar el objeto de nuevo en el archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(alumno);
            System.out.println("Alumno guardado en archivo.");
        } catch (IOException e) {
            System.out.println("Error guardando el archivo: " + e.getMessage());
        }

        sc.close();
    }
}
