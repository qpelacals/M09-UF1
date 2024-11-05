package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {

    // Crea i retorna una instància de XifradorAES
    @Override
    public Xifrador creaXifrador() {
        return new XifradorAES();
    }
}