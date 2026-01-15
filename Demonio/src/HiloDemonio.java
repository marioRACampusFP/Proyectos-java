
public class HiloDemonio extends Thread {
   public HiloDemonio() {
       setDaemon(true);
   }
   @Override
   public void run() {
       try {
           while (true) {
               System.out.println("Hilo demonio ejecutándose en segundo plano...");
               Thread.sleep(1000);
           }
       } catch (InterruptedException e) {
           System.out.println("Hilo demonio interrumpido.");
       }
   }
}
