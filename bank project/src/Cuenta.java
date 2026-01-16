import java.io.Serializable;
import java.util.ArrayList;  

public class Cuenta implements Serializable {
	private Cliente cliente;               
	private double saldo;                 
	private ArrayList<Movimiento> movimientos; 
	
	//Constructor que inicializa la cuenta con un cliente y saldo 0
	public Cuenta(Cliente cliente) {
		this.cliente = cliente;
		this.saldo = 0;
		this.movimientos = new ArrayList<>();
	}
	
	public void ingresar(double cantidad) {
	    if (cantidad > 0) {
	        saldo += cantidad; 
	        movimientos.add(new Movimiento("INGRESO", cantidad, saldo, "Ingreso en efectivo")); 
	    }
	}

	public boolean retirar(double cantidad) {
	    if (cantidad > 0 && saldo >= cantidad) {
	        saldo -= cantidad; 
	        movimientos.add(new Movimiento("RETIRADA", cantidad, saldo, "Retirada en efectivo")); 
	        return true;
	    }
	    return false; 
	}
	
	//Devuelve el saldo actual
	public double getSaldo() {
		return saldo;
	}
	
	//Devuelve la lista de movimientos
	public ArrayList<Movimiento> getMovimientos(){
		return movimientos;
	}
	
	//Devuelve el cliente propietario
	public Cliente getCliente() {
		return cliente;
	}
}
