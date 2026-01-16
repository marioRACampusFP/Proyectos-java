import java.io.Serializable;
import java.util.ArrayList;
public class Alumno implements Serializable {
   private static final long serialVersionUID = 1L;
   private String nombre;
   private int edad;
   private ArrayList<Calificacion> calificaciones;
   public Alumno(String nombre, int edad) {
       this.nombre = nombre;
       this.edad = edad;
       this.calificaciones = new ArrayList<>();
   }
   public void calificar(String asignatura, int nota) {
       this.calificaciones.add(new Calificacion(asignatura, nota));
   }
   public String getNombre() { return nombre; }
   public int getEdad() { return edad; }
   public ArrayList<Calificacion> getCalificaciones() { return calificaciones; }
   public double media() {
       if (calificaciones.isEmpty()) return 0.0;
       double suma = 0;
       for (Calificacion c : calificaciones) suma += c.getNota();
       return suma / calificaciones.size();
   }
}
