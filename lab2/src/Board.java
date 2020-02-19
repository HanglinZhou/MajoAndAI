import java.util.ArrayList;
import java.util.Collection;
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
    Coord kingCoord; // keep track of king Coord

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
     * Iterate the corresponding Territory,
     * for each coord occupied by a piece, get the piece and its direction,
     *  based on current board and the possible destination of this piece,
     *  filter all invalid destinations and add all valid ones to the result list.
     *
     * @return all valid moves for all pieces
     */
    public List<Move> computeAllValidMoves(boolean isWhitePlaying) {
        //todo: test

        // store all the valid moves to be returned
        List<Move> validMoves = new ArrayList<>();

        HashMap<Coord, Piece> myTerritory;
        HashMap<Coord, Piece> enemyTerritory;
        if (isWhitePlaying) {
            myTerritory = this.whiteTerritory;
            enemyTerritory = this.blackTerritory;
        } else {
            myTerritory = this.blackTerritory;
            enemyTerritory = this.whiteTerritory;
        }

        // for each coord, get the piece and all valid moves for that piece
        for (Coord coord : myTerritory.keySet()) {
            Piece p = myTerritory.get(coord);
            // for each direction vector v of p, and each scalar multiple c of the vector,
            // check whether currCoord + v*c is valid move, and add all valid moves
            for (int[] dir : p.getValidMoveDirections()) {
                for (int c = 1; c <= p.getValidMoveRange(); c++) {
                    // compute destination coord by adding (scalar * direction) to curr coord
                    int newRow = p.getCoord().getRow() + c * dir[0];
                    int newCol = p.getCoord().getCol() + c * dir[1];

                    Coord destCoord = new Coord(newRow, newCol);
                    Move newMove = new Move(p, destCoord);

                    if (isMoveValid(newMove, isWhitePlaying)) {
                        // if move valid, add move
                        validMoves.add(newMove);
                    } else {
                        // if a move is not valid, stop in moving in this direction
                        break;
                    }
                }
            }
        }
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
    private boolean isMoveValid(Move move, boolean isWhitePlaying) {
        //todo: king
        Piece p = move.getPiece();
        Coord c = move.getNewCoord();

        HashMap<Coord, Piece> myTerritory;
        HashMap<Coord, Piece> enemyTerritory;
        Piece ownKing;

        if (isWhitePlaying) {
            myTerritory = this.whiteTerritory;
            enemyTerritory = this.blackTerritory;
            ownKing = myTerritory.get(kingWhite);
        } else {
            myTerritory = this.blackTerritory;
            enemyTerritory = this.whiteTerritory;
            ownKing = myTerritory.get(kingBlack);
        }

        // if out of range of the board, or coord occupied by own side, return false
        if (c.isOutOfRange() || myTerritory.get(c) != null)
            return false;

        // if current move exposes own king to be attacked by enemy, return false
        for (Coord enemyCoord : enemyTerritory.keySet()) {
            if (ifAttacking(enemyTerritory.get(enemyCoord), ownKing)) {
                return false;
            }
        }


        return true;
    }


    /***
     * Determine if attacker is attacking attackee
     * @return true if attacker is attacking attackee
     */
    private boolean ifAttacking(Piece attacker, Piece attackee) {
        HashMap<Coord, Piece> myTerritory;
        HashMap<Coord, Piece> enemyTerritory;
        if (attackee.isWhitePiece()) {
            myTerritory = whiteTerritory;
            enemyTerritory = blackTerritory;
        } else {
            myTerritory = blackTerritory;
            enemyTerritory = whiteTerritory;
        }

        int currRow = attacker.getCoord().getRow();
        int currCol = attacker.getCoord().getCol();

        // for each enemy piece, check whether a piece is attacking king
        for (int[] direc : attacker.getValidMoveDirections()) {
            for (int c = 1; c <= attacker.getValidMoveRange(); c++) {
                Coord newCoord = new Coord(currRow + c * direc[0], currCol + c * direc[1]);

                // if attacks attackee, returns true
                if (newCoord.equals(attackee.getCoord()))
                    return true;

                // if attack is out of range of board, or blocked by other piece than attackee, try another direction
                if (newCoord.isOutOfRange() || myTerritory.get(newCoord) != null || enemyTerritory.get(newCoord) != null) {
                    break;
                }
            }
        }
        return false;
    }

}
