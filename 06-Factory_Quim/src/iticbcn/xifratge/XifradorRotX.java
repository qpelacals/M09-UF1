package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private static char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static char[] ABC = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public String xifraRot13(String cadena, int num) {
        String res = "";
        for (int i = 0; i < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if (Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ABC.length; j++) {
                        if (cadena.charAt(i) == ABC[j]) {
                            if (j + num >= ABC.length) {
                                res += ABC[j + num - ABC.length];
                            } else {
                                res += ABC[j + num];
                            }
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abc.length; j++) {
                        if (cadena.charAt(i) == abc[j]) {
                            if (j + num >= abc.length) {
                                res += abc[j + num - abc.length];
                            } else {
                                res += abc[j + num];
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

    public String desxifraRot13(String cadena, int num) {
        String res = "";
        for (int i = 0; i < cadena.length(); i++) {
            if(Character.isLetter(cadena.charAt(i))) {
                if (Character.isUpperCase(cadena.charAt(i))) {
                    for (int j = 0; j < ABC.length; j++) {
                        if (cadena.charAt(i) == ABC[j]) {
                            if (j - num < 0) {
                                res += ABC[j - num + ABC.length];
                            } else {
                                res += ABC[j - num];
                            }
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abc.length; j++) {
                        if (cadena.charAt(i) == abc[j]) {
                            if (j - num < 0) {
                                res += abc[j - num + abc.length];
                            } else {
                                res += abc[j - num];
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

    public void forcaBrutaRotX(String cadena) {
        for (int h = 0; h < cadena.length();h++) {
            String res = "";
            for (int i = 0; i < cadena.length(); i++) {
                if (Character.isLetter(cadena.charAt(i))) {
                    if (Character.isUpperCase(cadena.charAt(i))) {
                        for (int j = 0; j < ABC.length; j++) {
                            if (cadena.charAt(i) == ABC[j]) {
                                if (j - h < 0) {
                                    res += ABC[j - h + ABC.length];
                                } else {
                                    res += ABC[j - h];
                                }
                                break;
                            }
                        }
                    } else {
                        for (int j = 0; j < abc.length; j++) {
                            if (cadena.charAt(i) == abc[j]) {
                                if (j - h < 0) {
                                    res += abc[j - h + abc.length];
                                } else {
                                    res += abc[j - h];
                                }
                                break;
                            }
                        }
                    }
                } else {
                    res += cadena.charAt(i);
                }
            }
            System.out.println(res);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int num;
        try {
            num = Integer.parseInt(clau);
            if (num < 0 || num > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        String resultatXifrat = xifraRot13(msg, num);
        return new TextXifrat(resultatXifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int num;
        try {
            num = Integer.parseInt(clau);
            if (num < 0 || num > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        String textXifrat = new String(xifrat.getBytes());
        return desxifraRot13(textXifrat, num);
    }
}
