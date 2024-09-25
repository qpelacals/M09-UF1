public class Rot13 {
    private static char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static char[] ABC = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public static String xifraRot13(String cadena) {
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

    public static String desxifraRot13(String cadena) {
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

    public static void main(String[] args) {
        String prova1 = "Tinc molta gana i molta son";
        String prova1Xifrat = xifraRot13(prova1);
        String prova2 = "Kingdom Come Deliverance 3";
        String prova2Xifrat = xifraRot13(prova2);

        System.out.printf("String inicial: %s%n", prova1);
        System.out.printf("String inicial xifrat: %s%n", prova1Xifrat);
        System.out.printf("String inicial desxifrat: %s%n", desxifraRot13(prova1Xifrat));
        System.out.println("");
        System.out.printf("String inicial: %s%n", prova2);
        System.out.printf("String inicial xifrat: %s%n", prova2Xifrat);
        System.out.printf("String inicial desxifrat: %s%n", desxifraRot13(prova2Xifrat));
    }
}