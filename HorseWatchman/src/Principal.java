public class Principal {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Debes indicar los nombres de tres caballos. Ejemplo:");
            System.out.println("java Principal Rayo Trueno Centella");
            return;
        }

        // Crear el hilo demonio
        Vigilante vigilante = new Vigilante();
        vigilante.setDaemon(true); // importante: lo marcamos como hilo demonio
        vigilante.start();

        // Crear los tres caballos
        Caballo c1 = new Caballo(args[0]);
        Caballo c2 = new Caballo(args[1]);
        Caballo c3 = new Caballo(args[2]);

        // Iniciar los caballos
        c1.start();
        c2.start();
        c3.start();

        // Esperar a que los tres terminen
        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
        }

        System.out.println("Todos los caballos han terminado su carrera.");
        System.out.println("Fin del programa.");
    }
}
