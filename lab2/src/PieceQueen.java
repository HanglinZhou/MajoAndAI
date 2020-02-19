public class PieceQueen extends Piece {

    public PieceQueen(Coord coord, String type, boolean isWhitePiece) {
        super(coord, type, isWhitePiece);
        // valid move directions for a queen
        this.validMoveDirections = new int[8][2];
        this.validMoveDirections[0] = new int[]{1, 1};
        this.validMoveDirections[1] = new int[]{-1, 1};
        this.validMoveDirections[2] = new int[]{-1, -1};
        this.validMoveDirections[3] = new int[]{1, -1};
        this.validMoveDirections[4] = new int[]{1, 0};
        this.validMoveDirections[5] = new int[]{0, 1};
        this.validMoveDirections[6] = new int[]{-1, 0};
        this.validMoveDirections[7] = new int[]{0, -1};

        // How many squares can a queen move
        this.validMoveRange = 7;

        // value for queen
        this.value = 9;
    }


}
