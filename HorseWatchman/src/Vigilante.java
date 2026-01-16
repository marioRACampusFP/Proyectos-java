public class Vigilante extends Thread {

    @Override
    public void run() {
        int contador = 1;
        while (true) {
            System.out.println("Tarea de vigilancia nº " + contador++);
            try {
                Thread.sleep(100); // pausa de 100 ms entre mensajes
            } catch (InterruptedException e) {
                System.out.println("El vigilante fue interrumpido.");
            }
        }
    }
}
