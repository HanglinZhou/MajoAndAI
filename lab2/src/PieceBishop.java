public class PieceBishop extends Piece {

    static int id = 0; // keep track of how many bishop has been added;

    public PieceBishop(Coord coord, String type, boolean isWhitePiece) {
        super(coord, type, isWhitePiece);
        // valid move directions for a bishop
        this.validMoveDirections = new int[4][2];
        this.validMoveDirections[0] = new int[]{1, 1};
        this.validMoveDirections[1] = new int[]{-1, 1};
        this.validMoveDirections[2] = new int[]{-1, -1};
        this.validMoveDirections[3] = new int[]{1, -1};

        // How many scalar multiple of the direction can a bishop move
        this.validMoveRange = 7;

        // value for bishop
        this.value = 3;

        // assign and increment id
        this.pieceId = id;
        id++;
    }


}
