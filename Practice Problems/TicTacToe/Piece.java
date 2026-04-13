public enum Piece {
    X('X'),
    O('O');

    private char value;

    Piece(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
