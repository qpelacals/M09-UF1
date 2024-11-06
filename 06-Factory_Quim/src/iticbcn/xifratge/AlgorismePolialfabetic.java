package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {

    // Crea i retorna una instància de XifradorPolialfabetic
    @Override
    public iticbcn.xifratge.Xifrador creaXifrador() {
        return new iticbcn.xifratge.XifradorPolialfabetic();
    }
}
