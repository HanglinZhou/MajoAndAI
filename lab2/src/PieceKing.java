import java.util.Arrays;

public class PieceKing extends Piece {
    public PieceKing(Coord coord, boolean isWhitePiece) {
        super(coord, isWhitePiece);
        this.typename = "king";

        // valid move directions for a king
        this.validMoveDirections = new int[8][2];
        this.validMoveDirections[0] = new int[]{1, 1};
        this.validMoveDirections[1] = new int[]{-1, 1};
        this.validMoveDirections[2] = new int[]{-1, -1};
        this.validMoveDirections[3] = new int[]{1, -1};
        this.validMoveDirections[4] = new int[]{1, 0};
        this.validMoveDirections[5] = new int[]{0, 1};
        this.validMoveDirections[6] = new int[]{-1, 0};
        this.validMoveDirections[7] = new int[]{0, -1};

        // How many squares can a king move
        this.validMoveRange = 1;

        // king does not have a value
        this.value = 0;
    }


}
