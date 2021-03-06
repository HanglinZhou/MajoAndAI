import java.util.*;

public class Board {
    final char BLANK = '_';
    HashMap<Coord, Piece> whiteTerritory;
    HashMap<Coord, Piece> blackTerritory;
    Board parentBoard;
    Piece movedPiece;
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
        parentBoard = null;
        movedPiece = null;
        explorationDepth = 0;

        for (int r = 0; r < boardData.length; r++) {
            for (int c = 0; c < boardData[r].length; c++) {
                if (boardData[r][c] != BLANK) {
                    char pieceChar = boardData[r][c];
                    Coord coord = new Coord(boardData.length - 1 - r,c);
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
        //Q: make sure we can import gson for deep clone(ask Jon)
        /* TODO
        Gson gson = new Gson();
        String jsonString = gson.toJson(employeeMap);

        Type type = new TypeToken<HashMap<Integer, Employee>>(){}.getType();
        HashMap<Integer, Employee> clonedMap = gson.fromJson(jsonString, type);
*/
        whiteTerritory = deepCloneAndSetMovedPiece(parentBoard.getWhiteTerritory(), move);
        blackTerritory = deepCloneAndSetMovedPiece(parentBoard.getBlackTerritory(), move);


        // remove the captured piece from enemy territory
        if (move.getPiece().isWhitePiece()) {
            if (blackTerritory.containsKey(move.getNewCoord()))
                blackTerritory.remove(move.getNewCoord());
        } else {
            if (whiteTerritory.containsKey(move.getNewCoord()))
                whiteTerritory.remove(move.getNewCoord());
        }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /***
     *
     * @param isWhitePlaying
     * @return number of valid moves of current board given the side that is playing
     */
    public int getNumAllValidMoves(boolean isWhitePlaying) {
        return this.computeAllValidMoves(isWhitePlaying).size();
    }

    public void setNumAllValidMoves(int numAllValidMoves) {
        this.numAllValidMoves = numAllValidMoves;
    }

    public int getExplorationDepth() {
        return explorationDepth;
    }

    public void setExplorationDepth(int explorationDepth) {
        this.explorationDepth = explorationDepth;
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
        // store all the valid moves to be returned
        List<Move> validMoves = new ArrayList<>();

        HashMap<Coord, Piece> myTerritory;
        if (isWhitePlaying) {
            myTerritory = this.whiteTerritory;
        } else {
            myTerritory = this.blackTerritory;
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
                    // if new coord go out of the boarder of board, stop in current direction
                    if (destCoord.isOutOfRange())
                        break;
                    Move newMove = new Move(p, destCoord);
                    if (isMoveValid(newMove)) {
                        // if move valid, add move
                        validMoves.add(newMove);
                        if (this.isOccupiedByEnemyPiece(newMove))
                            break;

                    } else {
                        // if a move is not valid, stop in moving in this direction
                        break;
                    }
                }
            }
        }
        return validMoves;
    }

    /***
     *  For all alive pieces of the side that is playing, the the sum of each value
     * @return the alive piece values sum of the side playing
     */
    public int computeAllPieceValue(boolean isWhitePlaying) {
        int alivePieceValueSum = 0;

        HashMap<Coord, Piece> playerTerritory;
        if (isWhitePlaying) {
            playerTerritory = this.getWhiteTerritory();
        } else {
            playerTerritory = this.getBlackTerritory();
        }

        for (Piece p : playerTerritory.values()) {
            alivePieceValueSum += p.getValue();
        }

        return alivePieceValueSum;
    }

    /***
     * given the side that is playing, return the number of pawns that are protected of that side
     * @param isWhitePlaying
     * @return num protected pawns
     */
    public int getNumProtectedPawns(boolean isWhitePlaying) {
        int numProtectedPawns = 0;
        // for each alive pawn, check whether it is protected, and increment the result if it is
        HashMap<Coord, Piece> myTerritory = this.getMyTerritory(isWhitePlaying);

        for (Piece p : myTerritory.values()) {
            if (p.getTypename().equals("pawn") && this.isPawnProtected(p)) {
                numProtectedPawns++;
            }
        }

        return numProtectedPawns;
    }

    /***
     *
     * @param isWhitePlaying
     * @return the territory of the side that is playing
     */
    private HashMap<Coord, Piece> getMyTerritory(boolean isWhitePlaying) {
        if (isWhitePlaying) {
            return this.whiteTerritory;
        } else {
            return this.blackTerritory;
        }
    }

    /***
     *
     * @param pawn
     * @return whether the pawn is protected by other pieces on the same side
     */
    private boolean isPawnProtected(Piece pawn) {
        HashMap<Coord, Piece> myTerritory = this.getMyTerritory(pawn.isWhitePiece());
        // for each piece of the same side, check whether the piece protects the pawn
        for (Piece p : myTerritory.values()) {
            if (!p.equals(pawn)) {
                for (int[] direc : p.getValidMoveDirections()) {
                    for (int c = 1; c <= p.getValidMoveRange(); c++) {
                        Coord coordOnTheWay = new Coord(p.getCoord().getRow() + c*direc[0],
                                                         p.getCoord().getCol() + c*direc[1]);
                        Move move = new Move(p, coordOnTheWay);
                        if (this.isKingEndangered(move))
                            break; // if king is endangered by moving p, stop
                        if (this.getBlockingPiece(move) != null) {
                            // if something is blocking the way
                            if (this.getBlockingPiece(move).equals(pawn)) {
                                // if it is actually the pawn that is blocking the way, then the pawn is protected
                                return true;
                            }
                            // if other things are blocking the way, then pawn is not protected from this direction
                            break;
                        } // if nothing is blocking the way, keep going
                    }
                }

            }
        }
        // if no piece is protecting the pawn, return false
        return false;
    }

    /***
     * Detemine if the move is valid.
     * @return true if move is valid
     */
    private boolean isMoveValid(Move move) {
        // needs to check 2 things:
        // 1. whether new coord is blocked by own piece
        // 2. if not blocked, whether the move will place king in danger
        if (this.isBlockedByOwnPiece(move) || this.isKingEndangered(move))
            return false;

        // if the piece to move is a pawn, make sure the condition for special move
        // is there, otherwise return false
        if (move.getPiece().getTypename().equals("pawn")) {
            if (move.getNewCoord().getCol() != move.getPiece().getCoord().getCol()) {
                // if is indeed currently special move
                if (!this.isOccupiedByEnemyPiece(move))
                    return false;
            } else {
                if (this.isOccupiedByEnemyPiece(move)) {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * Determine whether king is endangered by making current move
     * @return true if king endangered, false if otherwise
     */
    public boolean isKingEndangered(Move move) {
        Piece myKing;
        boolean isWhitePlaying = move.getPiece().isWhitePiece();
        if (isWhitePlaying) {
            myKing = whiteTerritory.get(kingWhite);
        } else {
            myKing = blackTerritory.get(kingBlack);
        }

        // check after making current move, whether there exists an enemy piece that can immediately attack king
        Board newBoard = this.makeMove(move);

        HashMap<Coord, Piece> enemyTerritory;
        if (isWhitePlaying) {
            enemyTerritory = newBoard.getBlackTerritory();
            for (Coord cd : enemyTerritory.keySet()) {
            }
        } else {
            enemyTerritory = newBoard.getWhiteTerritory();
        }

        for (Coord cd : newBoard.whiteTerritory.keySet()) {
            //System.out.printf("(%s, %s)\n", newBoard.whiteTerritory.get(cd).getTypename(), cd.toString());
        }

        // for each enemy piece, check whether the piece attacks king immediately
        for (Coord c : enemyTerritory.keySet()) {
            Piece enemyPiece = enemyTerritory.get(c);
            int row = enemyPiece.getCoord().getRow();
            int col = enemyPiece.getCoord().getCol();

            // iterate each direction the enemy piece can go
            for (int[] direc : enemyPiece.getValidMoveDirections()) {
                // iterate through each range
                for (int r = 1; r <= enemyPiece.getValidMoveRange(); r++) {
                    Coord newCoord = new Coord(row + r*direc[0], col + r*direc[1]);
                    if (newCoord.isOutOfRange())
                        break;

                    Move mv = new Move(enemyPiece, newCoord);
                    if (newBoard.getBlockingPiece(mv) != null) {
                        if (/*newBoard.isOccupiedByEnemyPiece(mv)*/ newBoard.getBlockingPiece(mv).equals(myKing)) {
                            // if the enemy piece indeed attacks my king, returns true
                            return true;
                        } else {
                            break;

                        }
                    } // if enemy does not come across anything along the way, it keeps moving
                }
            }
        }
        // if no enemy piece can attack my king, then my king is not endangered
        return false;
    }

    /***
     * Given a move, check whether the destination is blocked by a piece.
     * @return the piece that is occupying the destination coord; null if not occupied
     */
    public Piece getBlockingPiece(Move move) {
        Coord destination = move.getNewCoord();
        if (whiteTerritory.get(destination) != null)
            return whiteTerritory.get(destination);
        if (blackTerritory.get(destination) != null)
            return blackTerritory.get(destination);

        // if not occupied
        return null;
    }

    /***
     * Determine whether the destination of current move is blocked by piece of own side
     * @param move
     * @return true if destination blocked by own piece, false otherwise
     */
    public boolean isBlockedByOwnPiece(Move move) {
        Piece p = this.getBlockingPiece(move);
        if (p == null || (p.isWhitePiece() != move.getPiece().isWhitePiece())) {
            // if not occupied or occupied by piece of other side
            return false;
        }
        return true;
    }

    /***
     * Determine whether the destination of current move is occupied by piece of enemy side
     * @param move
     * @return true if destination occupied by enemy piece, false otherwise
     */
    public boolean isOccupiedByEnemyPiece(Move move) {
        Piece p = this.getBlockingPiece(move);
        if (p == null || (p.isWhitePiece() == move.getPiece().isWhitePiece())) {
            if (p!=null) {
                /*
                System.out.print(p.getTypename() + " is blocking, and are both color - ");
                if (p.isWhitePiece())
                    System.out.println("white");
                else
                    System.out.println("black");
                 */

            }
            // if not occupied or occupied by own piece
            return false;
        }
        //System.out.printf("%s occupied by other side [%s] %s\n", move.newCoord.toString(), p.isWhitePiece,p.getTypename());
        return true;
    }

    /***
     * Make the move to the current board
     * @param move
     * @return the new board after making the move
     */
    public Board makeMove(Move move) {
        return new Board(this, move);
    }


    public double getTravelDistOfMovedPiece() {
        // get previous coord from parent board
        Coord prevCord = null;
        HashMap<Coord, Piece> myPrevTerritory = this.parentBoard.getMyTerritory(this.movedPiece.isWhitePiece());
        for (Coord c : myPrevTerritory.keySet()) {
            if (myPrevTerritory.get(c).equals(this.movedPiece)) {
                prevCord = c;
            }
        }

        return movedPiece.getCoord().computeDistance(prevCord);
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

            // typename: name of parent piece
            switch (typename) {
                case "king":
                    newPiece = parentPiece.makePieceCopy(coord);
                    if (isWhitePiece)
                        kingWhite = coord;
                    else
                        kingBlack = coord;
                    break;
                case "queen":
                    newPiece = parentPiece.makePieceCopy(coord);
                    break;
                case "rook":
                    newPiece = parentPiece.makePieceCopy(coord);
                    break;
                case "bishop":
                    newPiece = parentPiece.makePieceCopy(coord);
                    break;
                case "knight":
                    newPiece = parentPiece.makePieceCopy(coord);
                    break;
                case "pawn":
                    newPiece = parentPiece.makePieceCopy(coord);
                    break;
                default:
                    break;
            }

            if (parentPiece.equals(move.getPiece()))
                this.movedPiece = newPiece;
            newTerritory.put(coord, newPiece);
        }

        return newTerritory;
    }

}
