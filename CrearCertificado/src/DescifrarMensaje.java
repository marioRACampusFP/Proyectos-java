import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import javax.crypto.Cipher;
public class DescifrarMensaje {
   public static void main(String[] args) {
       File fichero = new File("clave.obj");
       if (fichero.exists()) {
           // Descifrar un mensaje si existe la clave privada
           PrivateKey clavePrivada = leerClavePrivada();
           if (clavePrivada != null) {
               String textoDescifrado = descifrarTexto(clavePrivada);
               System.out.println("Mensaje descifrado: " + textoDescifrado);
           }
       } else {
           System.out.println("No se encontró la clave privada. Por favor, crea un certificado primero.");
       }
   }
   private static PrivateKey leerClavePrivada() {
       try {
           FileInputStream ficheroClave = new FileInputStream("clave.obj");
           ObjectInputStream bufferClave = new ObjectInputStream(ficheroClave);
           PrivateKey clavePrivada = (PrivateKey) bufferClave.readObject();
           bufferClave.close();
           ficheroClave.close();
           return clavePrivada;
       } catch (IOException | ClassNotFoundException e) {
           System.out.println("Error al leer la clave privada");
           System.out.println("Excepción de tipo: " + e.getClass().getName());
           System.out.println(e.getMessage());
           return null;
       }
   }
   private static String descifrarTexto(PrivateKey clavePrivada) {
       try {
           File ficheroCifrado = new File("mensaje.obj");
           if (ficheroCifrado.exists()) {
               Cipher descifrador = Cipher.getInstance("RSA");
               descifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
               FileInputStream ficheroMensaje = new FileInputStream("mensaje.obj");
               ObjectInputStream bufferMensaje = new ObjectInputStream(ficheroMensaje);
               byte[] mensajeCifrado = (byte[]) bufferMensaje.readObject();
               bufferMensaje.close();
               ficheroMensaje.close();
               byte[] mensajeDescifrado = descifrador.doFinal(mensajeCifrado);
               return new String(mensajeDescifrado);
           } else {
               return "No hay mensaje que descifrar";
           }
       } catch (GeneralSecurityException | IOException | ClassNotFoundException e) {
           System.out.println("Error al descifrar el mensaje");
           System.out.println("Excepción de tipo: " + e.getClass().getName());
           System.out.println(e.getMessage());
           return null;
       }
   }
}
