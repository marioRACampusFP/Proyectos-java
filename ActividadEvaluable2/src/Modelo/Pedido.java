package Modelo;

import java.util.Date;

public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private Date fecha;
    private double total;

    public Pedido() {}

    public Pedido(int idPedido, Cliente cliente, Date fecha, double total) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return idPedido + " - Cliente: " + cliente.getNombre() + " " + cliente.getApellido() +
               " | Fecha: " + fecha + " | Total: $" + total;
    }
}
