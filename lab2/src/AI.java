public class AI {
    int hMinimaxDepth;
    boolean playWithWhitePieces;
    Board initialBoard;

    /***
     * Initialize the initial board and all other data field.
     * @param hMinimaxDepth
     * @param playWithWhitePieces
     * @param boardData
     */
    public AI(int hMinimaxDepth, boolean playWithWhitePieces, char[][] boardData) {
        //todo
        this.hMinimaxDepth = hMinimaxDepth;
        this.playWithWhitePieces = playWithWhitePieces;
        initialBoard = new Board(boardData);
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
        //todo
        char[][] newBoard = null;
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
     * The evaluation function based on some heuristics.
     * @param state
     *
     * @return The first cell has the depth of the found terminal state or the
     * maximum depth (H). The second cell is the evaluated value of a given state/board.
     */
    private int[] evaluate(Board state) {
        //todo
        int evaluatedValue[] = null;

        return evaluatedValue;
    }


}

