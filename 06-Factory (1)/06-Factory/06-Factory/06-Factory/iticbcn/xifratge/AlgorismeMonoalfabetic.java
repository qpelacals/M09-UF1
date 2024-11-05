package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {

    // Crea i retorna una instància de XifradorMonoalfabetic
    @Override
    public Xifrador creaXifrador() {
        return new XifradorMonoalfabetic();
    }
}