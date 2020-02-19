public class PieceRook extends Piece {

    static int id = 0;  // keep track of how many rooks has been added;

    public PieceRook(Coord coord, boolean isWhitePiece) {
        super(coord, isWhitePiece);
        this.typename = "rook";

        // valid move directions for a rook
        this.validMoveDirections = new int[4][2];
        this.validMoveDirections[0] = new int[]{1, 0};
        this.validMoveDirections[1] = new int[]{0, 1};
        this.validMoveDirections[2] = new int[]{-1, 0};
        this.validMoveDirections[3] = new int[]{0, -1};

        // How many scalar multiple of the direction can a rook move
        this.validMoveRange = 7;

        // value for rook
        this.value = 5;

        // assign and increment id
        this.pieceId = id;
        id++;
    }


}
