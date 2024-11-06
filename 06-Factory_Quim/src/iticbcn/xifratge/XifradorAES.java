package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "DaniDance";

    public byte[] xifraAES(String msg, String clau) throws Exception {
        try {
            // Obtenir els bytes del missatge
            byte[] msgBytes = msg.getBytes();

            // Genera un IV aleatori
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Generar hash de la clau
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] clauHash = digest.digest(clau.getBytes("UTF-8"));
            byte[] clauAes = Arrays.copyOf(clauHash, 16); // 128 bits per AES
            SecretKeySpec secretKey = new SecretKeySpec(clauAes, ALGORISME_XIFRAT);

            // Xifrar
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] xifrat = cipher.doFinal(msgBytes);

            // Combinar IV i missatge xifrat
            byte[] ivIMsgXifrat = new byte[iv.length + xifrat.length];
            System.arraycopy(iv, 0, ivIMsgXifrat, 0, iv.length);
            System.arraycopy(xifrat, 0, ivIMsgXifrat, iv.length, xifrat.length);

            return ivIMsgXifrat;
        } catch (Exception e) {
            System.err.println("Error de xifrat AES: " + e.getMessage()); // NOTA: Mostra l'error
            System.exit(1); // NOTA: Surt del programa
        }
        return null;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        try {
            // Extreure l'IV
            iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Extreure la part xifrada
            byte[] xifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

            // Generar hash de la clau
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] clauHash = digest.digest(clau.getBytes("UTF-8"));
            byte[] clauAes = Arrays.copyOf(clauHash, 16); // 128 bits per AES
            SecretKeySpec secretKey = new SecretKeySpec(clauAes, ALGORISME_XIFRAT);

            // Desxifrar
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] desxifrat = cipher.doFinal(xifrat);

            return new String(desxifrat);
        } catch (Exception e) {
            System.err.println("Error de desxifrat AES: " + e.getMessage()); // NOTA: Mostra l'error
            System.exit(1); // NOTA: Surt del programa
        }
        return null;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifratBytes = xifraAES(msg, clau); // Xifra el missatge
            return new TextXifrat(xifratBytes); // Retorna el resultat encapsulat en un TextXifrat
        } catch (Exception e) {
            throw new ClauNoSuportada("Error durant la xifratge AES: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] xifratBytes = xifrat.getBytes(); // Obté el byte[] del TextXifrat
            return desxifraAES(xifratBytes, clau); // Retorna el text desxifrat
        } catch (Exception e) {
            throw new ClauNoSuportada("Error durant la desxifratge AES: " + e.getMessage());
        }
    }
}
