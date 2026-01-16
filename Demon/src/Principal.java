public class Principal {
   public static void main(String[] args) {
       HiloDemonio demonio = new HiloDemonio();
       demonio.start();
       System.out.println("Inicio del programa principal...");
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("Programa principal finalizado.");
   }
}
