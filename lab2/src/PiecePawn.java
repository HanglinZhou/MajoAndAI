public class PiecePawn extends Piece {

    static int id = 0;  // keep track of how many pawns has been added;

    public PiecePawn(Coord coord, boolean isWhitePiece) {
        super(coord, isWhitePiece);
        this.typename = "pawn";

        // valid move directions for a pawn
        this.validMoveDirections = new int[2][2];
        this.validMoveDirections[0] = new int[]{1, 1};
        this.validMoveDirections[1] = new int[]{-1, 1};

        // How many scalar multiple of the direction can a pawn move
        this.validMoveRange = 1;

        // value for pawn
        this.value = 1;

        // assign and increment id
        this.pieceId = id;
        id++;
    }


}
