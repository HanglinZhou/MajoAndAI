public class Move {

    Piece piece;
    Coord newCoord;

    public Move(Piece piece, Coord newCoord) {
        this.piece = piece;
        this.newCoord = newCoord;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Coord getNewCoord() {
        return newCoord;
    }

    public void setNewCoord(Coord newCoord) {
        this.newCoord = newCoord;
    }

    @Override
    public String toString() {
        return "Move{" +
                "piece=" + piece +
                ", newCoord=" + newCoord +
                '}';
    }
}
