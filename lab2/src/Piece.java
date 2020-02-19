import java.util.Arrays;

public abstract class Piece {

    Coord coord;
    String typename;

    /* to differentiate different pieces of same type. e.g. rook0, rook1 */
    int pieceId;

    /*  Valid directions (represented by a unit vector) for a piece:
        e.g. The king (k) moves one square in any direction, so validMoveDirections is:
        [[0,1],[0,-1][1,0],[-1,0][1,1],[1,-1],[-1,1],[-1,-1]]
     */
    int[][] validMoveDirections;

    /* How many scalar multiple of the direction can a piece move
    */
    int validMoveRange;

    /* queen-9, rook-5, bishop-3, knight-3, pawn-1*/
    int value;

    boolean isWhitePiece;


    public Piece(Coord coord, boolean isWhitePiece) {
        this.coord = coord;
        this.isWhitePiece = isWhitePiece;
        this.pieceId = 0;
    }

    public int getPieceId() {
        return pieceId;
    }

    public void setPieceId(int pieceId) {
        this.pieceId = pieceId;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int[][] getValidMoveDirections() {
        return validMoveDirections;
    }

    public void setValidMoveDirections(int[][] validMoveDirections) {
        this.validMoveDirections = validMoveDirections;
    }

    public int getValidMoveRange() {
        return validMoveRange;
    }

    public void setValidMoveRange(int validMoveRange) {
        this.validMoveRange = validMoveRange;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isWhitePiece() {
        return isWhitePiece;
    }

    public void setWhitePiece(boolean whitePiece) {
        isWhitePiece = whitePiece;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "coord=" + coord +
                ", typename='" + typename + '\'' +
                ", validMoveDirections=" + Arrays.toString(validMoveDirections) +
                ", validMoveRange=" + validMoveRange +
                ", value=" + value +
                ", isWhitePiece=" + isWhitePiece +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // check if o is an instance of Coord or not
        if (!(o instanceof Piece)) {
            return false;
        }

        Piece p = (Piece) o;

        return (this.typename == p.getTypename()) &&
               (this.pieceId == p.getPieceId() &&
               (this.isWhitePiece == p.isWhitePiece()));
    }
}
