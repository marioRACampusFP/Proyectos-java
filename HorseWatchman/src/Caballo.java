public class Caballo extends Thread {

    public Caballo(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(getName() + " trota... paso " + i);
                Thread.sleep(300); // pausa de 300 ms entre pasos
            }
            System.out.println(getName() + " ha parado.");
        } catch (InterruptedException e) {
            System.out.println(getName() + " fue interrumpido.");
        }
    }
}
