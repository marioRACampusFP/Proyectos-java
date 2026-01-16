import java.io.Serializable;

public class Calificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String asignatura;
    private int nota;

    public Calificacion(String asignatura, int nota) {
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public int getNota() {
        return nota;
    }
}
