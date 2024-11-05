package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {

    // Crea i retorna una instància de XifradorPolialfabetic
    @Override
    public Xifrador creaXifrador() {
        return new XifradorPolialfabetic();
    }
}
