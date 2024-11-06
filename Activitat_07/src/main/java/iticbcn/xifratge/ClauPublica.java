package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

public class ClauPublica {

    // Mètode per generar un parell de claus (pública i privada) utilitzant RSA
    public KeyPair generaParellClausRSA() throws Exception {
        // Crea un generador de parells de claus usant l'algoritme RSA
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        // Inicialitza el generador per crear claus de 2048 bits (seguretat estàndard en RSA)
        keyPairGen.initialize(2048);

        // Genera el parell de claus (conté la clau pública i la clau privada) i el retorna
        return keyPairGen.generateKeyPair();
    }

    // Mètode per xifrar un missatge utilitzant una clau pública RSA
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        // Crea una instància de Cipher per realitzar operacions de xifratge amb RSA
        Cipher cipher = Cipher.getInstance("RSA");

        // Configura el Cipher en mode de xifratge (ENCRYPT_MODE) amb la clau pública proporcionada
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        // Converteix el missatge en un array de bytes i el xifra usant el Cipher configurat
        // Retorna el missatge xifrat com un array de bytes
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    // Mètode per desxifrar un missatge xifrat utilitzant una clau privada RSA
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        // Crea una instància de Cipher per realitzar operacions de desxifratge amb RSA
        Cipher cipher = Cipher.getInstance("RSA");

        // Configura el Cipher en mode de desxifratge (DECRYPT_MODE) amb la clau privada proporcionada
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);

        // Desxifra el missatge xifrat (array de bytes) i converteix el resultat en un array de bytes
        byte[] decryptedBytes = cipher.doFinal(msgXifrat);

        // Converteix els bytes desxifrats en un String usant UTF-8 i el retorna
        return new String(decryptedBytes, "UTF-8");
    }
}
