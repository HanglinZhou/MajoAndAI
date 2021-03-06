public class PiecePawn extends Piece {

    static int id = 0;  // keep track of how many pawns has been added;

    public PiecePawn(Coord coord, boolean isWhitePiece) {
        super(coord, isWhitePiece);
        this.typename = "pawn";

        // valid move directions for a pawn
        this.validMoveDirections = new int[3][2];

        // special moves
        this.validMoveDirections[0] = new int[]{1, 1};
        this.validMoveDirections[1] = new int[]{1, -1};

        //regular move
        this.validMoveDirections[2] = new int[]{1, 0};

        if (!isWhitePiece) {
            for (int[] dir : this.validMoveDirections) {
                for (int i = 0; i < dir.length; i++) {
                    dir[i] = dir[i] * (-1);
                }
            }
        }

        // How many scalar multiple of the direction can a pawn move
        this.validMoveRange = 1;

        // value for pawn
        this.value = 1;

        // assign and increment id
        this.pieceId = id;
        id++;

    }

    private PiecePawn(Coord newCoord, PiecePawn parent) {
        super(newCoord, parent.isWhitePiece);
        this.typename = parent.getTypename();
        this.validMoveDirections = parent.getValidMoveDirections().clone();
        this.validMoveRange = parent.getValidMoveRange();
        this.value = parent.getValue();
        this.pieceId = parent.getPieceId();

    }
    /***
     *
     * @return a deep copy of current piecePawn within new Coord
     */
    public Piece makePieceCopy(Coord newCoord) {
        Piece p = new PiecePawn(newCoord, this);
//        p.setPieceId(this.pieceId);
        return p;
    }

}
