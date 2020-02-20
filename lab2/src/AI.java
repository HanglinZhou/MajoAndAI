import java.util.HashMap;

public class AI {
    int hMinimaxDepth;
    boolean playWithWhitePieces;
    Board initialBoard;
    ExplorePolicy policy;
    int numBoardsVisited = 0;
    char[][] initialBoardData;


    /***
     * Initialize the initial board and all other data field.
     * @param hMinimaxDepth
     * @param playWithWhitePieces
     * @param boardData
     */
    public AI(int hMinimaxDepth, boolean playWithWhitePieces, char[][] boardData, ExplorePolicy policy) {
        this.hMinimaxDepth = hMinimaxDepth;
        this.playWithWhitePieces = playWithWhitePieces;
        initialBoard = new Board(boardData);
        this.policy = policy;
        this.initialBoardData = boardData;
    }

    /***
     * Run H-Minimax search and return the best move.
     *
     * @return
     */
    public Move runMinimax() {
        //todo
        Move bestMove = null;
        return bestMove;
    }

    /***
     * Return the new board after the given move.
     * @param move
     * @return a char[][] representing the new board.
     */
    public char[][] getNewBoardAfterMove(Move move) {
        //todo: test
        char[][] newBoard = this.initialBoardData;
        Piece p = move.getPiece();
        Coord currCoord = p.getCoord();
        Coord newCoord = move.getNewCoord();

        // change curr Coord to blank, since the piece is moved away
        newBoard[currCoord.getRow()][currCoord.getCol()] = '_';

        // get marker of curr piece
        char marker;
        if (p.getTypename().equals("knight")) {
            marker = 'n';
        } else {
            marker = p.getTypename().charAt(0);
        }

        if (p.isWhitePiece()) {
            marker = Character.toUpperCase(marker);
        } else {
            marker = Character.toLowerCase(marker);
        }

        // change destination coord to marker of moved piece
        newBoard[newCoord.getRow()][newCoord.getCol()] = marker;


        return newBoard;
    }

    /***
     * min_a H-MINIMAX (Result(s,a), d+1) if Player(s)=MIN
     * @param currDepth
     * @param state
     * @param isWhitePiece
     * @return
     */
    private int H_min(int currDepth, Board state, boolean isWhitePiece) {
       //todo
        int minValue = 0;
        return minValue;
    }

    /***
     * max_a H-MINIMAX (Result(s,a), d+1) if Player(s)=MAX
     * @param currDepth
     * @param state
     * @param isWhitePiece
     * @return
     */
    private int H_max(int currDepth, Board state, boolean isWhitePiece) {
        //todo
        int maxValue = 0;
        return maxValue;
    }

    /***
     * The evaluation function f(n) = sum of all alive piece values +
     *                                sum of number of all possible moves +
     *                                number of protected pawns
     * @param state
     *
     * @return the evaluation value of the given state/board.
     */
    private int evaluate(Board state) {
        //todo
        int evaluatedValue = state.computeAllPieceValue(playWithWhitePieces) +
                             state.getNumAllValidMoves(playWithWhitePieces) +
                             state.getNumProtectedPawns(playWithWhitePieces);



        return evaluatedValue;
    }



}

