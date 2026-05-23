package Model;

public class Player {

    private final Piece piece;
    private final String name;

    public Piece getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }

    public Player(Piece piece, String name) {
        this.piece = piece;
        this.name = name;
    }

}
