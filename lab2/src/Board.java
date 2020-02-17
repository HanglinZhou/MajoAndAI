import java.util.HashMap;
import java.util.List;

public class Board {

    HashMap<Coord, Piece> whiteTerritory;
    HashMap<Coord, Piece> blackTerritory;

    int alpha;
    int beta;

    /***
     * Constructor for the initial board.
     *
     * @param boardData
     */
    public Board(char[][] boardData) {
        whiteTerritory = new HashMap<>();
        blackTerritory = new HashMap<>();
        alpha = 0;
        beta = 0;
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
        alpha = 0;
        beta = 0;


    }

    /***
     * Compute and return all valid moves for the given board.
     *
     * @return
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
