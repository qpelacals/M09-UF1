package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {
    private byte[] bytes;

    // Constructor que rep un array de bytes
    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }

    // Retorna l'array de bytes
    public byte[] getBytes() {
        return bytes; // Retorna una còpia del array de bytes
    }

    // Sobreescriu el mètode toString per retornar el contingut en format de cadena
    @Override
    public String toString() {
        return Arrays.toString(bytes); // Representació com a string de l'array de bytes
    }
}