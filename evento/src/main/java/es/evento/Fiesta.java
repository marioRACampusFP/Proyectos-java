package es.evento;

public class Fiesta {
    private Local local;

    public Fiesta() {}

    public void setLocal(Local local) {
        this.local = local;
    }

    public void celebrar() {
        System.out.println("¡La fiesta ha comenzado en " + local.getNombre() + "!");
    }

    public void terminar() {
        System.out.println("La fiesta ha terminado.");
    }
}