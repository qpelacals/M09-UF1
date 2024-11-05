package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    public static final char[] abc = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z' };
    public char[] abcPermutat;
    public Random random;

    // Inicialitza la llavor de l'aleatorietat a partir de la contrasenya
    public void inicialitzaAleatorietat(String contrasenya) {
        random = new Random(contrasenya.hashCode());
    }

    // Genera una permutació de l'alfabet utilitzant l'aleatorietat
    public void permutaAlfabet() {
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
    public String xifraPoliAlfa(String msg, String clau) throws ClauNoSuportada {
        if (random == null) {
            random = new Random();
        }
        try {
            long valorClau = Long.parseLong(clau);
            random.setSeed(valorClau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau no vàlida per a XifradorPolialfabetic");
        }

        inicialitzaAleatorietat(clau); // Reinicia l'aleatorietat
        StringBuilder res = new StringBuilder();
        // Código para el proceso de cifrado
        return res.toString();
    }

    public String desxifra(String msgXifrat, String clau) throws ClauNoSuportada {
        if (random == null) {
            random = new Random();
        }
        try {
            long valorClau = Long.parseLong(clau);
            random.setSeed(valorClau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau no vàlida per a XifradorPolialfabetic");
        }

        inicialitzaAleatorietat(clau); // Reinicia l'aleatorietat
        StringBuilder res = new StringBuilder();
        // Código para el proceso de descifrado
        return res.toString();
    }


    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (random == null) {
            random = new Random();
        }
        try {
            long valorClau = Long.parseLong(clau);
            random.setSeed(valorClau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        String resultatXifrat = xifraPoliAlfa(msg, clau);
        return new TextXifrat(resultatXifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (random == null) {
            random = new Random();
        }
        try {
            long valorClau = Long.parseLong(clau);
            random.setSeed(valorClau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        String textXifrat = new String(xifrat.getBytes());
        return desxifra(textXifrat, clau);
    }

}
