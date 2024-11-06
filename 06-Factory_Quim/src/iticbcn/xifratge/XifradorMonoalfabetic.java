package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    public static final char[] abc = "aàáäâãbcçdeèéëêfghiìíïîjklmnñoòóöôõpqrstuùúüûvwxyýÿz".toCharArray();
    private char[] abcPermutat;

    public XifradorMonoalfabetic() {
        abcPermutat = permutaAbc(abc);
    }

    public char[] permutaAbc(char[] abc) {
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

    public String xifraMonoAlfa(String cadena) throws ClauNoSuportada {

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

    public String desxifraMonoAlfa(String cadena) throws ClauNoSuportada {

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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String resultatXifrat = xifraMonoAlfa(msg);
        return new TextXifrat(resultatXifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String textXifrat = new String(xifrat.getBytes());
        return desxifraMonoAlfa(textXifrat);
    }
}
