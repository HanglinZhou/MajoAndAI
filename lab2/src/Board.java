import java.util.HashMap;
import java.util.List;

public class Board {
    final char BLANK = '_';
    HashMap<Coord, Piece> whiteTerritory;
    HashMap<Coord, Piece> blackTerritory;
    Board parentBoard;
    Piece movedPiece;
    int alpha;
    int beta;
    int score; //value/score
    int numAllValidMoves;

    /***
     * Constructor for the initial board.
     * Initialize all pieces (coord included), add pieces to whiteTerritory, blackTerritory
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

        for (int r = 0; r < boardData.length; r++) {
            for (int c = 0; c < boardData[r].length; c++) {
                if (boardData[r][c] != BLANK) {
                    char pieceChar = boardData[r][c];
                    Coord coord = new Coord(r,c);
                    Piece piece;
                    boolean isWhitePiece = Character.isUpperCase(pieceChar);
                    pieceChar = Character.toLowerCase(pieceChar);
                    switch (pieceChar) {
                        case 'k':
                            piece = new PieceKing();
                            break;
                        case 'q':

                            break;
                        case 'r':

                            break;
                        case 'b':

                            break;
                        case 'n':

                            break;
                        case 'p':

                            break;
                        default:
                            break;
                    }
                    if (Character.isLowerCase(boardData[r][c])) {

                    }
                }
            }
        }



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

    public HashMap<Coord, Piece> getWhiteTerritory() {
        return whiteTerritory;
    }

    public void setWhiteTerritory(HashMap<Coord, Piece> whiteTerritory) {
        this.whiteTerritory = whiteTerritory;
    }

    public HashMap<Coord, Piece> getBlackTerritory() {
        return blackTerritory;
    }

    public void setBlackTerritory(HashMap<Coord, Piece> blackTerritory) {
        this.blackTerritory = blackTerritory;
    }

    public Board getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(Board parentBoard) {
        this.parentBoard = parentBoard;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumAllValidMoves() {
        return numAllValidMoves;
    }

    public void setNumAllValidMoves(int numAllValidMoves) {
        this.numAllValidMoves = numAllValidMoves;
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
