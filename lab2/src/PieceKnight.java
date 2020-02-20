public class PieceKnight extends Piece {

    static int id = 0;  // keep track of how many knights has been added;

    public PieceKnight(Coord coord, boolean isWhitePiece) {
        super(coord, isWhitePiece);
        this.typename = "knight";

        // valid move directions for a knight
        this.validMoveDirections = new int[8][2];
        this.validMoveDirections[0] = new int[]{2, 1};
        this.validMoveDirections[1] = new int[]{1, 2};
        this.validMoveDirections[2] = new int[]{-1, 2};
        this.validMoveDirections[3] = new int[]{-2, 1};
        this.validMoveDirections[4] = new int[]{-2, -1};
        this.validMoveDirections[5] = new int[]{-1, -2};
        this.validMoveDirections[6] = new int[]{1, -2};
        this.validMoveDirections[7] = new int[]{2, -1};

        // How many scalar multiple of the direction can a knight move
        this.validMoveRange = 1;

        // value for knight
        this.value = 3;

        // assign and increment id
        this.pieceId = id;
        id++;
    }

    /***
     *
     * @return a deep copy of current Piece within new Coord
     */
    public Piece makePieceCopy(Coord coord) {
        Piece p = new PieceKnight(coord, this.isWhitePiece);
        p.setPieceId(this.pieceId);
        return p;
    }

}
