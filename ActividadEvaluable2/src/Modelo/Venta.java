package Modelo;

public class Venta {
    private int idVenta;
    private Pedido pedido;
    private Libro libro;
    private int cantidad;
    private double subtotal;

    public Venta() {}

    public Venta(int idVenta, Pedido pedido, Libro libro, int cantidad, double subtotal) {
        this.idVenta = idVenta;
        this.pedido = pedido;
        this.libro = libro;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    @Override
    public String toString() {
        return "Venta ID: " + idVenta + " | Libro: " + libro.getTitulo() + " | Cantidad: " + cantidad + " | Subtotal: $" + subtotal;
    }
}
