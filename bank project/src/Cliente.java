import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;
    private String dni;    

    //Constructor para inicializar el cliente con nombre y DNI
    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }
    
    //Getter para obtener el nombre del cliente
    public String getNombre() {
    	return nombre;
    }
    
    //Getter para obtener el DNI del cliente
    public String getDni() {
    	return dni;
    }
    
    //Representación legible del cliente, útil al imprimir
    public String toString() {
    	return nombre + "(DNI: " + dni + ")";
    }
}
