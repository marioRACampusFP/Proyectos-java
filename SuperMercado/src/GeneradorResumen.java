import java.sql.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorResumen {

    public void generar() {
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement()) {

            // Consulta ventas totales por categoría
            String sql = """
                    SELECT c.categoria, SUM(d.precio * d.unidades * (1 - d.descuento)) AS total_ventas
                    FROM detalle d
                    JOIN producto p ON d.idproducto = p.idproducto
                    JOIN categoria c ON p.idcategoria = c.idcategoria
                    GROUP BY c.categoria
                    """;

            ResultSet rs = stmt.executeQuery(sql);

            // Crear carpeta data si no existe
            File folder = new File("data");
            if (!folder.exists()) folder.mkdirs();

            // Nombre de fichero con fecha y hora
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy HH mm");
            String nombreArchivo = "data/resumen " + LocalDateTime.now().format(dtf) + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
                writer.write("Resumen de ventas por categoría\n");
                writer.write("===============================\n");

                while (rs.next()) {
                    String categoria = rs.getString("categoria");
                    double total = rs.getDouble("total_ventas");
                    writer.write(String.format("%s: %.2f\n", categoria, total));
                }
            }

            System.out.println("Resumen generado: " + nombreArchivo);

            // Modificación de datos para que cambien los próximos resúmenes
            stmt.executeUpdate("UPDATE producto SET stock = stock - 1 WHERE idproducto = 1");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

