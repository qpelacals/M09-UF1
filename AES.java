import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "DaniDance";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora ïlla Ôtto"};

        for (String msg : msgs) {
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + Base64.getEncoder().encodeToString(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
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
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
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
    }
}
