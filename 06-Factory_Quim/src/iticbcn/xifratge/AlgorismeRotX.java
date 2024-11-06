package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory {

    // Crea i retorna una instància de XifradorRotX
    @Override
    public iticbcn.xifratge.Xifrador creaXifrador() {
        return new XifradorRotX();
    }
}