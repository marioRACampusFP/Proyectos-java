package Modelo;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private Genero genero;
    private double precio;
    private int stock;

    public Libro() {}

    public Libro(int idLibro, String titulo, String autor, Genero genero, double precio, int stock) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return idLibro + " - " + titulo + " | " + autor + " | " + genero.getNombreGenero() +
               " | Precio: $" + precio + " | Stock: " + stock;
    }
}
