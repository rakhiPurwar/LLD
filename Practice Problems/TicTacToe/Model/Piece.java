package Model;

public enum Piece {
    X('X'),
    O('O');

    private final char value;

    Piece(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
