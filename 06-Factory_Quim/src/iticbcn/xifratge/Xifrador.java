package iticbcn.xifratge;

public interface Xifrador {
    /**
     * Xifra un missatge amb una clau donada.
     *
     * @param msg El missatge a xifrar.
     * @param clau La clau de xifrat.
     * @return Un objecte de tipus TextXifrat que conté el missatge xifrat.
     * @throws ClauNoSuportada Si la clau no és compatible amb el xifratge.
     */
    public TextXifrat xifra (String msg, String clau) throws ClauNoSuportada;

    /**
     * Desxifra un missatge xifrat amb una clau donada.
     *
     * @param xifrat El text xifrat a desxifrar.
     * @param clau La clau de desxifrat.
     * @return El missatge desxifrat com una cadena de text.
     * @throws ClauNoSuportada Si la clau no és compatible amb el desxifratge.
     */
    public String desxifra (TextXifrat xifrat, String clau) throws ClauNoSuportada;
}
