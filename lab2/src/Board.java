import java.util.HashMap;
import java.util.List;

public class Board {

    HashMap<Coord, Piece> whiteTerritory;
    HashMap<Coord, Piece> blackTerritory;
    Board parentBoard;
    Piece movedPiece;
    int alpha;
    int beta;
    int score; //value/score
    int numAllValidMoves;//todo

    /***
     * Constructor for the initial board.
     *
     * @param boardData
     */
    public Board(char[][] boardData) {
        whiteTerritory = new HashMap<>();
        blackTerritory = new HashMap<>();
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
        parentBoard = null;
        movedPiece = null;
        score = Integer.MIN_VALUE;
        //todo

    }

    /***
     * Constructor for other boards based on information from its parent.
     *
     * @param parentBoard
     * @param move
     */
    public Board(Board parentBoard, Move move) {
        //todo
        whiteTerritory = new HashMap<>();
        blackTerritory = new HashMap<>();
        this.parentBoard = parentBoard;
        alpha = 0;
        beta = 0;
        //movedPiece


    }

    /***
     * Compute and return all valid moves for the given board.
     * Iterate myTerritory,
     * for each piece, return range, dir
     *  based on current board and the coord of this piece, compute all valid moves for this piece
     *
     * @return all valid moves for all pieces
     */
    public List<Move> computeAllValidMoves() {
        //todo
        List<Move> validMoves = null;
        return validMoves;
    }

    public int computeAllPieceValue(boolean isWhitePiece) {
        //todo
        int sumPieceValue = 0;
        return sumPieceValue;
    }
    /***
     * Detemine if the move is valid.
     * @return
     */
    private boolean isMoveValid(Move move) {
        //todo
        boolean valid = false;
        return valid;
    }

}
