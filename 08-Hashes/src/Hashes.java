import java.security.*;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0;
    private static final String CHARSET = "abcdefABCDEF1234567890!";

    // Mètode per calcular el hash SHA-512 amb salt
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());
        return HexFormat.of().formatHex(bytes);
    }

    // Mètode per calcular el hash PBKDF2 amb salt
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(hash);
    }

    // Mètode per fer força bruta
    public String forcaBruta(String algoritme, String hashObjectiu, String sal) throws Exception {
        npass = 0;
        char[] conjuntCaracters = "abcdefABCDEF1234567890!".toCharArray();

        /*
         * Cada bucle representa una posició en la contrasenya.
         * Es generen totes les combinacions possibles per a una longitud determinada.
         */

        char[] combinacioActual = new char[6];
        // Primera posició
        for (char primer : conjuntCaracters) { // a
            combinacioActual[0] = primer;

            // Es comprova la contrasenya generada
            String contrasenya = testPwd(algoritme, hashObjectiu, sal, combinacioActual, 0);
            if (contrasenya != null) return contrasenya;

            // Segona posició
            for (char segon : conjuntCaracters) { // aa, ab
                combinacioActual[1] = segon;
                contrasenya = testPwd(algoritme, hashObjectiu, sal, combinacioActual, 1);
                if (contrasenya != null) return contrasenya;

                // Tercera posició
                for (char tercer : conjuntCaracters) { // aaa, aba
                    combinacioActual[2] = tercer;
                    contrasenya = testPwd(algoritme, hashObjectiu, sal, combinacioActual, 2);
                    if (contrasenya != null) return contrasenya;

                    // Quarta posició
                    for (char quart : conjuntCaracters) { // aaaa, abaa
                        combinacioActual[3] = quart;
                        contrasenya = testPwd(algoritme, hashObjectiu, sal, combinacioActual, 3);
                        if (contrasenya != null) return contrasenya;

                        // Cinquena posició
                        for (char cinqué : conjuntCaracters) { // aaaaa, abaaa
                            combinacioActual[4] = cinqué;
                            contrasenya = testPwd(algoritme, hashObjectiu, sal, combinacioActual, 4);
                            if (contrasenya != null) return contrasenya;

                            // Sisena posició
                            for (char sisé : conjuntCaracters) { // aaaaaa, abaaaa
                                combinacioActual[5] = sisé;
                                contrasenya = testPwd(algoritme, hashObjectiu, sal, combinacioActual, 5);
                                if (contrasenya != null) return contrasenya;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private String testPwd(String algoritme, String hashObjectiu, String sal, char[] combinacio, int llargada) throws Exception {
        npass++;

        StringBuilder contrasenya = new StringBuilder();
        for (int i = 0; i <= llargada; i++) {
            contrasenya.append(combinacio[i]);
        }

        String textContrasenya = contrasenya.toString();

        String hashGenerat = algoritme.equals("SHA-512")
                ? getSHA512AmbSalt(textContrasenya, sal)
                : getPBKDF2AmbSalt(textContrasenya, sal);

        if (hashGenerat.equals(hashObjectiu)) {
            return textContrasenya;
        }
        return null;
    }


    // Mètode per calcular el temps transcorregut
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long segons = (millis / 1000) % 60;
        long minuts = (millis / (1000 * 60)) % 60;
        long hores = (millis / (1000 * 60 * 60)) % 24;
        long dies = millis / (1000 * 60 * 60 * 24);
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis % 1000);
    }
}
