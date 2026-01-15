import java.util.concurrent.*;

public class Principal {
    public static void main(String[] args) {
        GeneradorResumen generador = new GeneradorResumen();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea = generador::generar;

        // Ejecuta la tarea cada 2 minutos
        scheduler.scheduleAtFixedRate(tarea, 0, 2, TimeUnit.MINUTES);

        System.out.println("Programa iniciado. Se generará un resumen cada 2 minutos...");
        System.out.println("Presiona CTRL+C para detenerlo.");
    }
}
