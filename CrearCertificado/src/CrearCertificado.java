import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
public class CrearCertificado {
   public static void main(String[] args) {
       System.out.println("VAMOS A CREAR EL CERTIFICADO");
       try {
           // Generar par de claves
           KeyPairGenerator generadorDeClaves = KeyPairGenerator.getInstance("RSA");
           generadorDeClaves.initialize(2048); // Tamaño de la clave explícito
           KeyPair claves = generadorDeClaves.generateKeyPair();
           PrivateKey clavePrivada = claves.getPrivate();
           // Crear la firma
           Signature firmadorVerificador = Signature.getInstance("SHA1withRSA");
           firmadorVerificador.initSign(clavePrivada);
           byte[] mensajeSecreto = "amelia.gonzalez@campusfp.es/12345".getBytes();
           firmadorVerificador.update(mensajeSecreto);
           byte[] firmaDigital = firmadorVerificador.sign();
           System.out.println("Firma creada (Base64): " 
               + java.util.Base64.getEncoder().encodeToString(firmaDigital));
           // Guardar certificado (clave pública y firma) y clave privada
           guardarCertificado(new MensajeFirmado(claves.getPublic(), firmaDigital), clavePrivada);
       } catch (GeneralSecurityException e) {
           System.out.println("Error al crear el certificado");
           System.out.println("Excepción de tipo: " + e.getClass().getName());
           System.out.println(e.getMessage());
       }
   }
   private static void guardarCertificado(MensajeFirmado mensajeFirmado, PrivateKey clavePrivada) {
       try {
           // Guardar el certificado (firma y clave pública)
           FileOutputStream ficheroFirma = new FileOutputStream("firma.obj");
           ObjectOutputStream bufferFirma = new ObjectOutputStream(ficheroFirma);
           bufferFirma.writeObject(mensajeFirmado);
           bufferFirma.close();
           ficheroFirma.close();
           // Guardar la clave privada.
           // Está se necesitará para descifrar mensajes de los clientes.
           FileOutputStream ficheroClave = new FileOutputStream("clave.obj");
           ObjectOutputStream bufferClave = new ObjectOutputStream(ficheroClave);
           bufferClave.writeObject(clavePrivada);
           bufferClave.close();
           ficheroClave.close();
           System.out.println("El certificado y la clave privada se han guardado con éxito");
       } catch (IOException e) {
           System.out.println("Error al guardar el certificado");
           System.out.println("Excepción de tipo: " + e.getClass().getName());
           System.out.println(e.getMessage());
       }
   }
}
