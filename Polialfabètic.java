import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabètic {

    public static final char[] abc = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z' };
    public static char[] abcPermutat;
    private static Random random;

    public static void main(String[] args) {
        String contrasenya = "mySecretPassword";
        inicialitzaAleatorietat(contrasenya);

        String prova1 = "Bondia dia estic cansat";
        String prova1Xifrat = xifraPoliAlfa(prova1, contrasenya);
        System.out.printf("Missatge inicial: %s%n", prova1);
        System.out.printf("Missatge xifrat: %s%n", prova1Xifrat);
        System.out.printf("Missatge desxifrat: %s%n", desxifraPoliAlfa(prova1Xifrat, contrasenya));
    }

    // Inicialitza la llavor de l'aleatorietat a partir de la contrasenya
    public static void inicialitzaAleatorietat(String contrasenya) {
        random = new Random(contrasenya.hashCode());
    }

    // Genera una permutació de l'alfabet utilitzant l'aleatorietat
    public static void permutaAlfabet() {
        List<Character> abcList = new ArrayList<>();
        for (char lletra : abc) {
            abcList.add(lletra);
        }
        Collections.shuffle(abcList, random);
        abcPermutat = new char[abcList.size()];
        for (int i = 0; i < abcList.size(); i++) {
            abcPermutat[i] = abcList.get(i);
        }
    }

    // Xifra un missatge utilitzant el xifratge polialfabètic
    public static String xifraPoliAlfa(String msg, String contrasenya) {
        inicialitzaAleatorietat(contrasenya); // Reinicia l'aleatorietat
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            char lletraOriginal = lletra;
            lletra = Character.toLowerCase(lletra);

            permutaAlfabet(); // Genera una nova permutació per a cada lletra

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

    // Desxifra un missatge xifrat utilitzant el xifratge polialfabètic
    public static String desxifraPoliAlfa(String msgXifrat, String contrasenya) {
        inicialitzaAleatorietat(contrasenya); // Reinicia l'aleatorietat
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletra = msgXifrat.charAt(i);
            char lletraOriginal = lletra;
            lletra = Character.toLowerCase(lletra);

            permutaAlfabet(); // Genera una nova permutació per a cada lletra

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
