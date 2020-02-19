import java.util.ArrayList;
import java.util.Collection;
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
        if (isWhitePlaying) {
            myTerritory = this.whiteTerritory;
        } else {
            myTerritory = this.blackTerritory;
        }

        // todo: king
        // for each coord, get the piece and all valid moves for that piece
        for (Coord coord : myTerritory.keySet()) {
            Piece p = myTerritory.get(coord);
            for (int[] dir : p.getValidMoveDirections()) {
                for (int c = 1; c <= p.getValidMoveRange(); c++) {
                    // compute destination coord by adding (scalar * direction) to curr coord
                    int newRow = p.getCoord().getRow() + c * dir[0];
                    int newCol = p.getCoord().getCol() + c * dir[1];

                    Coord destCoord = new Coord(newRow, newCol);
                    Move newMove = new Move(p, destCoord);
                    if (isMoveValid(newMove, myTerritory)) {
                        // if move valid, add move
                        validMoves.add(newMove);
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
    private boolean isMoveValid(Move move, HashMap<Coord, Piece> myTerritory) {
        //todo
        Piece p = move.getPiece();
        Coord c = move.getNewCoord();

        // if out of range of the board, return false
        if (c.isOutOfRange())
            return false;

        // if not out of range:
        // for each direction vector, and each scalar multiple of the vector
        // whether there exists a piece of own side that is blocking the way
        if (myTerritory.get(c) != null) {
            // if a piece of own side is blocking, stop moving in the curr direc
            break;
        } else {
            validMoves.add(new Move(p, destCoord));
        }

        return valid;
    }

}
