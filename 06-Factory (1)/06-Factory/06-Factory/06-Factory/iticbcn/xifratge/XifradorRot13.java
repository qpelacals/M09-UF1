package iticbcn.xifratge;

public class XifradorRot13 implements Xifrador {
    private static char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static char[] ABC = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public String xifraRot13(String cadena) {
        String res = "";
        for (int i = 0; i < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if (Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ABC.length; j++) {
                        if (cadena.charAt(i) == ABC[j]) {
                            if (j + 13 >= ABC.length) {
                                res += ABC[j + 13 - ABC.length];
                            } else {
                                res += ABC[j + 13];
                            }
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abc.length; j++) {
                        if (cadena.charAt(i) == abc[j]) {
                            if (j + 13 >= abc.length) {
                                res += abc[j + 13 - abc.length];
                            } else {
                                res += abc[j + 13];
                            }
                            break;
                        }
                    }
                }
            } else {
                res += cadena.charAt(i);
            }
        }
        return res;
    }

    public String desxifraRot13(String cadena) {
        String res = "";
        for (int i = 0; i < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if (Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ABC.length; j++) {
                        if (cadena.charAt(i) == ABC[j]) {
                            if (j - 13 < 0) {
                                res += ABC[j - 13 + ABC.length];
                            } else {
                                res += ABC[j - 13];
                            }
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abc.length; j++) {
                        if (cadena.charAt(i) == abc[j]) {
                            if (j - 13 < 0) {
                                res += abc[j - 13 + abc.length];
                            } else {
                                res += abc[j - 13];
                            }
                            break;
                        }
                    }
                }
            } else {
                res += cadena.charAt(i);
            }
        }
        return res;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        String resultatXifrat = xifraRot13(msg); // Utilitza xifraRot13 sense clau per defecte
        return new TextXifrat(resultatXifrat.getBytes()); // Crea TextXifrat a partir del text xifrat en bytes
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        String textXifrat = new String(xifrat.getBytes()); // Converteix bytes a String
        return desxifraRot13(textXifrat); // Desxifra el text
    }
}