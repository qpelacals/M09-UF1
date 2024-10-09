import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabètic {
    public static final char[] abc = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z' };
    public static char[] abcPermutat;

    public static void main(String[] args) {
        abcPermutat = permutaAbc(abc);

        String prova1 = "Tinc molta gana i molta son";
        String prova1Xifrat = xifraMonoAlfa(prova1);
        String prova2 = "Kingdom Come Deliverance 3";
        String prova2Xifrat = xifraMonoAlfa(prova2);
        System.out.printf("String inicial: %s%n", prova1);
        System.out.printf("String inicial xifrat: %s%n", prova1Xifrat);
        System.out.printf("String inicial desxifrat: %s%n", desxifraMonoAlfa(prova1Xifrat));
        System.out.println("");
        System.out.printf("String inicial: %s%n", prova2);
        System.out.printf("String inicial xifrat: %s%n", prova2Xifrat);
        System.out.printf("String inicial desxifrat: %s%n", desxifraMonoAlfa(prova2Xifrat));
    }

    public static char[] permutaAbc(char[] abc) {
        List<Character> abcList = new ArrayList<>();
        for (char lletra : abc) {
            abcList.add(lletra);
        }
        Collections.shuffle(abcList);
        char[] abcBarrejat = new char[abcList.size()];
        for (int i = 0; i < abcList.size(); i++) {
            abcBarrejat[i] = abcList.get(i);
        }
        return abcBarrejat;
    }

    public static String xifraMonoAlfa(String cadena) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            char lletraOriginal = lletra;
            lletra = Character.toLowerCase(lletra);

            boolean trobat = false;
            for (int j = 0; j < abc.length; j++) {
                if (abc[j] == lletra) {
                    char lletraXifrada = abcPermutat[j];
                    res.append(Character.isUpperCase(lletraOriginal) ? Character.toUpperCase(lletraXifrada) : lletraXifrada);
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                res.append(lletraOriginal);
            }
        }
        return res.toString();
    }

    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            char lletraOriginal = lletra;
            lletra = Character.toLowerCase(lletra);

            boolean trobat = false;
            for (int j = 0; j < abcPermutat.length; j++) {
                if (abcPermutat[j] == lletra) {
                    char lletraDesxifrada = abc[j];
                    res.append(Character.isUpperCase(lletraOriginal) ? Character.toUpperCase(lletraDesxifrada) : lletraDesxifrada);
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                res.append(lletraOriginal);
            }
        }
        return res.toString();
    }
}
