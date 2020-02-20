import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.google.gson.*;

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
    int explorationDepth; //this board occurs at which level of exploration
    /*
    Keep track of the coord of white king. We update this value if we decide to move King.
     */
    Coord kingWhite;

    /*
    Keep track of the coord of black king. We update this value if we decide to move King.
     */
    Coord kingBlack;

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
        explorationDepth = 0;
        //ask M: maybe we don't want to set the score here, as in the algo, it is set in minimax
        // score = Integer.MIN_VALUE;
        //todo: not tested

        for (int r = boardData.length - 1; r > -1; r--) {
            for (int c = 0; c < boardData[r].length; c++) {
                if (boardData[r][c] != BLANK) {
                    char pieceChar = boardData[r][c];
                    Coord coord = new Coord(r,c);
                    Piece piece = null;
                    boolean isWhitePiece = Character.isUpperCase(pieceChar);
                    pieceChar = Character.toLowerCase(pieceChar);
                    switch (pieceChar) {
                        case 'k':
                            piece = new PieceKing(coord,isWhitePiece);
                            if (isWhitePiece)
                                kingWhite = coord;
                            else
                                kingBlack = coord;
                            break;
                        case 'q':
                            piece = new PieceQueen(coord,isWhitePiece);
                            break;
                        case 'r':
                            piece = new PieceRook(coord,isWhitePiece);
                            break;
                        case 'b':
                            piece = new PieceBishop(coord,isWhitePiece);
                            break;
                        case 'n':
                            piece = new PieceKnight(coord,isWhitePiece);
                            break;
                        case 'p':
                            piece = new PiecePawn(coord,isWhitePiece);
                            break;
                        default:
                            break;
                    }

                    if (isWhitePiece)
                        whiteTerritory.put(coord, piece);
                    else
                        blackTerritory.put(coord, piece);
                }
            }
        }

    }

    /***
     * Constructor for other boards based on information from its parent.
     *
     *
     * @param parentBoard
     * @param move
     */
    public Board(Board parentBoard, Move move) {
        //todo

        this.parentBoard = parentBoard;
        alpha = 0;
        beta = 0;
        //Q: make sure we can import gson for deep clone(ask Park)
        /* TODO
        Gson gson = new Gson();
        String jsonString = gson.toJson(employeeMap);

        Type type = new TypeToken<HashMap<Integer, Employee>>(){}.getType();
        HashMap<Integer, Employee> clonedMap = gson.fromJson(jsonString, type);
*/
        whiteTerritory = deepCloneAndSetMovedPiece(parentBoard.getWhiteTerritory(), move);
        blackTerritory = deepCloneAndSetMovedPiece(parentBoard.getBlackTerritory(), move);

        //movedPiece
        Piece pieceInParentTerritory = move.getPiece();
        Coord newCoord = move.getNewCoord();


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

    /***
     * Deep clone the territory map.
     *
     * @param parentTerritory
     * @param move
     * @return new territory.
     */
    private HashMap<Coord,Piece> deepCloneAndSetMovedPiece(HashMap<Coord, Piece> parentTerritory, Move move) {
        HashMap<Coord, Piece> newTerritory = new HashMap<>();
        for (Map.Entry<Coord, Piece> e : parentTerritory.entrySet()) {
            Piece parentPiece = e.getValue();
            String typename = parentPiece.getTypename();

            Piece newPiece = null;
            boolean isWhitePiece = parentPiece.isWhitePiece();
            Coord coord = null;
            //?same piece
            if (parentPiece.equals(move.getPiece())) {
                coord = move.getNewCoord();
            } else {
                coord = parentPiece.getCoord();
            }

            switch (typename) {
                case "king":
                    newPiece = new PieceKing(coord,parentPiece.isWhitePiece());
                    if (isWhitePiece)
                        kingWhite = coord;
                    else
                        kingBlack = coord;
                    break;
                case "queen":
                    newPiece = new PieceQueen(coord,parentPiece.isWhitePiece());
                    break;
                case "rook":
                    newPiece = new PieceRook(coord,parentPiece.isWhitePiece());
                    break;
                case "bishop":
                    newPiece = new PieceBishop(coord,parentPiece.isWhitePiece());
                    break;
                case "knight":
                    newPiece = new PieceKnight(coord,parentPiece.isWhitePiece());
                    break;
                case "pawn":
                    newPiece = new PiecePawn(coord,parentPiece.isWhitePiece());
                    break;
                default:
                    break;
            }
            if (parentPiece.equals(move.getPiece()))
                movedPiece = newPiece;
            newTerritory.put(coord, newPiece);
        }

        return newTerritory;
    }

}
