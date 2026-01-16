public class Persona {
   private String nombre;
   private String telefono;
   public Persona(String nombre, String telefono) {
       this.nombre = nombre;
       this.telefono = telefono;
   }
   @Override
   public String toString() {
       return nombre + " - " + telefono;
   }
  
   @Override
   protected void finalize() throws Throwable {
       System.out.println("El objeto Persona va a ser destruido");
   }
}
