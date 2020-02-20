public class PieceQueen extends Piece {
    static int id = 0; // keep track of how many king has been added;
    public PieceQueen(Coord coord, boolean isWhitePiece) {
        super(coord, isWhitePiece);
        this.typename = "queen";

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

        // assign and increment id
        this.pieceId = id;
        id++;
    }

    /***
     *
     * @return a deep copy of current Piece within new Coord
     */
    public Piece makePieceCopy(Coord coord) {
        Piece p = new PieceQueen(coord, this.isWhitePiece);
        p.setPieceId(this.pieceId);
        return p;
    }

}
