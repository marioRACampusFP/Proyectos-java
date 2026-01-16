import java.io.*;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 

public class Movimiento implements Serializable {
    private String tipo; 
    private double cantidad; 
    private LocalDateTime fecha;
    private double saldoFinal; // saldo de la cuenta tras el movimiento
    private String concepto;   // opcional, lo añadimos para exportar mejor

    //Constructor que inicializa tipo, cantidad y saldo resultante
    public Movimiento(String tipo, double cantidad, double saldoFinal, String concepto) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.saldoFinal = saldoFinal;
        this.concepto = concepto;
    }

    public String getTipo() { return tipo; }
    public double getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
    public double getSaldoFinal() { return saldoFinal; }
    public String getConcepto() { return concepto; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + fecha.format(fmt) + "] " + tipo + ": " + cantidad + "€ (Saldo: " + saldoFinal + "€)";
    }
}
