import java.util.Arrays;

public class Piece {

    Coord coord;
    String type;

    /*  Valid directions (represented by a unit vector) for a piece:
        e.g. The king (k) moves one square in any direction, so validMoveDirections is:
        [[0,1],[0,-1][1,0],[-1,0][1,1],[1,-1],[-1,1],[-1,-1]]
     */
    int[][] validMoveDirections;

    /* How many squares can a piece move? e.g. k: 1
    */
    int validMoveRange;

    /* queen-9, rook-5, bishop-3, knight-3, pawn-1*/
    int value;

    boolean isWhitePiece;


    public Piece(Coord coord, String type, int[][] validMoveDirections, int validMoveRange, int value, boolean isWhitePiece) {
        this.coord = coord;
        this.type = type;
        this.validMoveDirections = validMoveDirections;
        this.validMoveRange = validMoveRange;
        this.value = value;
        this.isWhitePiece = isWhitePiece;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", type='" + type + '\'' +
                ", validMoveDirections=" + Arrays.toString(validMoveDirections) +
                ", validMoveRange=" + validMoveRange +
                ", value=" + value +
                ", isWhitePiece=" + isWhitePiece +
                '}';
    }
}
