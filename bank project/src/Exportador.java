import java.io.FileWriter;
import java.io.IOException;

public class Exportador {
    public static void exportarCSV(Cuenta cuenta, String ruta) {
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("Fecha,Tipo,Concepto,Cantidad,SaldoFinal\n");
            for (Movimiento m : cuenta.getMovimientos()) {
                writer.write(
                    m.getFecha() + "," +
                    m.getTipo() + "," +
                    m.getConcepto() + "," +
                    m.getCantidad() + "," +
                    m.getSaldoFinal() + "\n"
                );
            }
            System.out.println("Movimientos exportados correctamente a " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
}
