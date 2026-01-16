import java.io.*;     
import java.util.Scanner; 

public class Principal {
	private static final String RUTA = "datos/cuenta.dat"; //
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		Cuenta cuenta = null;
		
		//Crear carpeta "datos" si no existe
		File carpeta = new File("datos");
		if (!carpeta.exists()) carpeta.mkdir();
		
		//Intentar cargar la cuenta desde disco
		File archivo = new File(RUTA);
		if (archivo.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
				cuenta = (Cuenta) ois.readObject(); 
				System.out.println("Cuenta cargada correctamente");
			} catch (Exception e) {
				System.out.println("Error al leer la cuenta, se creará una nueva");
			}
		}
		
		//Si no se pudo cargar, crear una nueva cuenta
		if (cuenta == null) {
			System.out.println("Introduce el nombre del cliente: ");
			String nombre = sc.nextLine();
			System.out.println("Introduce tu DNI: ");
			String dni = sc.nextLine();
			cuenta = new Cuenta(new Cliente(nombre, dni));
			System.out.println("Nueva cuenta creada para: " + cuenta.getCliente());
		}
		
		//Menú principal del banco
		int opcion;
		do {
			System.out.println("----Menú del banco----");
			System.out.println("1. Ingresar dinero");
			System.out.println("2. Retirar dinero");
			System.out.println("3. Consultar dinero");
			System.out.println("4. Ver movimientos");
			System.out.println("5. Exportar movimientos a CSV");
			System.out.println("0. Salir");
			System.out.println("Elige una opción: ");
			opcion = sc.nextInt();
			
			switch (opcion) {
				case 1: //Ingresar dinero
					System.out.println("Cantidad que quieres ingresar: ");
					double ingreso = sc.nextDouble();
					cuenta.ingresar(ingreso);
					System.out.println("Ingreso realizado");
					break;
					
				case 2: //Retirar dinero
					System.out.print("Cantidad a retirar: ");
					double retiro = sc.nextDouble();
					if (cuenta.retirar(retiro)) {
						System.out.println("Retirada realizada.");
					} else {
						System.out.println("Saldo insuficiente.");
					}
					break;
					
				case 3: //Consultar saldo
					System.out.println("Saldo actual: " + cuenta.getSaldo() + "€");
					break;
					
				case 4: //Mostrar movimientos
					System.out.println("Movimientos:");
					for (Movimiento m : cuenta.getMovimientos()) {
						System.out.println(m);
					}
					break;
					
				case 5:
				    Exportador.exportarCSV(cuenta, "datos/movimientos.csv");
				    break;
					
				case 0: //Salir
					System.out.println("Saliendo...");
					break;
					
				default:
					System.out.println("Opción no válida.");
			}
		} while (opcion != 0);
		
		//Guardar la cuenta en disco antes de salir
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA))) {
			oos.writeObject(cuenta); 
			System.out.println("Cuenta guardada en disco.");
		} catch (IOException e) {
			System.out.println("Error al guardar la cuenta: " + e.getMessage());
		}
	}
}

