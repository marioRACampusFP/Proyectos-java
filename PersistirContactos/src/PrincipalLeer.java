import java.io.EOFException;	
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


public class PrincipalLeer {
	public static void main(String args[])  {
		
		// Abrimos fichero agenda.dat para lectura
		FileInputStream file;
		ObjectInputStream buffer;
		try {
			file = new FileInputStream("agenda.dat");
			buffer = new ObjectInputStream(file);
		} catch (IOException e) {
			System.out.println("No se ha podido abrir la agenda de contactos");
			System.out.println(e.getMessage());
			return;
		}	
		
		// Leemos la lista de contactos
		boolean eof = false;
		Contacto c;
		while (!eof) {
			try {
				c = (Contacto) buffer.readObject();
				System.out.println(c);
			} catch (EOFException e1) {
				eof = true;
			} catch (IOException e2) {
				System.out.println("Error al leer los contactos de la agenda");
				System.out.println(e2.getMessage());
			} catch (ClassNotFoundException e3) {
				System.out.println("La clase Contacto no está cargada en memoria");
				System.out.println(e3.getMessage());
			}
		}
	
		// Cerramos el fichero
		try {
			buffer.close();
			file.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el fichero");
			System.out.println(e.getMessage());
		}
		
	}
}
