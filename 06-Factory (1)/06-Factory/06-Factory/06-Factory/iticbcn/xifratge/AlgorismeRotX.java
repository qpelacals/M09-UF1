package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory {

    // Crea i retorna una instància de XifradorRotX
    @Override
    public Xifrador creaXifrador() {
        return new XifradorRotX();
    }
}