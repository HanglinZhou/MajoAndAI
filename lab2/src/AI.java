import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AI {
    int hMinimaxDepth;
    boolean playWithWhitePieces;
    Board initialBoard;
    ExplorePolicy policy;

    public int getNumBoardsVisited() {
        return numBoardsVisited;
    }

    int numBoardsVisited = 0;
    char[][] initialBoardData;

    /***
     * Initialize the initial board and all other data field.
     * @param hMinimaxDepth
     * @param playWithWhitePieces
     * @param boardData
     */
    public AI(int hMinimaxDepth, boolean playWithWhitePieces, char[][] boardData, ExplorePolicy policy, int boardSize) {
        this.hMinimaxDepth = hMinimaxDepth;
        this.playWithWhitePieces = playWithWhitePieces;
        initialBoard = new Board(boardData);
        this.policy = policy;
        this.initialBoardData = new char[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                this.initialBoardData[r][c] = boardData[r][c];
            }
        }

    }

    /***
     * Run H-Minimax search and return the best move.
     * Note: here we assume the specified h > 0, so we do not do the cutoff test here.
     *
     * Logic here: start by calling H-max, which recursively calls H-min and itself.
     * @return bestNextBoardFound
     */
    public Board runMinimax() {
        //todo: change the signature
        //start with white chess and we are white chess --> use maximizer
        Board bestNextBoardFound = H_max(initialBoard.getExplorationDepth(), initialBoard, true,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestNextBoardFound;
    }

    /***
     * Return the new board after the given move.
     * @param move
     * @return a char[][] representing the new board.
     */
    public char[][] getNewBoardAfterMove(Move move) {
        //todo: test
        char[][] newBoard = this.initialBoardData;

        //iterate and find the original piece coord --> may need to look back to move, this is a bit hacky
        Coord currCoord = null;
        for (Map.Entry<Coord, Piece> e : initialBoard.getWhiteTerritory().entrySet()) {
            if (e.getValue().equals(move.getPiece()))
                currCoord = e.getKey();
        }
        Piece p = move.getPiece();
        Coord newCoord = move.getNewCoord();

        // change curr Coord to blank, since the piece is moved away
        newBoard[(Math.abs(1+currCoord.getRow()-8))][currCoord.getCol()] = '_';

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
        newBoard[(Math.abs(1+newCoord.getRow()-8))][newCoord.getCol()] = marker;


        return newBoard;
    }

    /***
     * min_a H-MINIMAX (Result(s,a), d+1) if Player(s)=MIN
     *(base case) terminal state: we set the depth of current board to be currDepth
     *  stalemate: one king left in territory each side; --> set borad score = 0
     *  numAllValidMoves = 0: we won (our enemy lose --> set borad score = Integer.Max_VALUE
     * (base case) cutoff test: we set the depth of current board to be currDepth
     * (recursive steps) ...
     * @param currDepth
     * @param state
     * @param isWhitePiece
     * @param alpha
     * @param beta
     * @return
     */
    private Board H_min(int currDepth, Board state, boolean isWhitePiece, int alpha, int beta) {
        //System.out.println("H-min start");
        numBoardsVisited++;
        List<Move> allValidMoves = state.computeAllValidMoves(isWhitePiece);
        int numAllValidMoves = allValidMoves.size();
        //base case 1: a terminal state is reached 1) stalemate (note: here we only consider one specific case: king vs king
        // 2) checkmate
        //TODO: redundancy
        if (stalemate(state)) {
            state.setExplorationDepth(currDepth);
            state.setScore(0);
            //System.out.println("min - returned in stalemate");
            return state;

        } else if (checkmate(numAllValidMoves)) {
            state.setExplorationDepth(currDepth);
            state.setScore(Integer.MAX_VALUE);
            //System.out.println("min - returned in checkmate, depth - " + currDepth);
            // System.out.println("movedpiece: " + state.movedPiece.getTypename() + " coord: " + state.movedPiece.getCoord());
            //System.out.println(state.getMovedPiece().typename);
            return state;
        }

        if (passCutoffTest(currDepth)) {
            //base case: the only place the
            state.setScore(evaluate(state));
            state.setExplorationDepth(currDepth);
            //System.out.println("min - returned in cuttoff");
            return state;
        }

        Board bestNextBoardFound = null;
        //this is the sorted list of states (based on our exploration policy) after all valid moves
        List<Board> statesAfterMove = getAllNewBoardAfterMove(state, allValidMoves);
        int stateScore = Integer.MAX_VALUE;
        //Question: should we set the initial score here?
        for (Board childState : statesAfterMove) {
            //System.out.println("min - just entered for look in : statesAfterMove");
            //better state found, update stateScore and bestNextBoardFound
            //TODO: check here and naming is awful
            Board bestNextNextBoardFound = H_max(currDepth+1, childState, !isWhitePiece, alpha, beta);
            if (stateScore >= bestNextNextBoardFound.getScore()) {
                //System.out.println("min - setting stateScore");
                stateScore = bestNextNextBoardFound.getScore();
                bestNextBoardFound = childState;
            }
            if (stateScore <= alpha)
                break; //stop exploring other children & return bestNextBoardFound
            beta = Math.min(stateScore, beta);

        }
        return bestNextBoardFound;

    }


    /***
     * max_a H-MINIMAX (Result(s,a), d+1) if Player(s)=MAX
     * (base case) terminal state: we set the depth of current board to be currDepth
     *  stalemate: one king left in territory each side; --> set borad score = 0
     *  numAllValidMoves = 0: we lost --> set borad score = Integer.MIN_VALUE
     * (base case) cutoff test: we set the depth of current board to be currDepth
     * (recursive steps) ...
     *
     * @param currDepth
     * @param state
     * @param isWhitePiece
     * @param alpha
     * @param beta
     * @return
     */
    private Board H_max(int currDepth, Board state, boolean isWhitePiece, int alpha, int beta) {
        //System.out.println("H-max start");
        numBoardsVisited++;
        List<Move> allValidMoves = state.computeAllValidMoves(isWhitePiece);
        int numAllValidMoves = allValidMoves.size();
        //base case 1: a terminal state is reached 1) stalemate (note: here we only consider one specific case: king vs king
        // 2) checkmate
        if (stalemate(state)) {
            state.setExplorationDepth(currDepth);
            state.setScore(0);
            //System.out.println("max - returned in stalemate");
            return state;

        } else if (checkmate(numAllValidMoves)) { //in this case, we are checkmated!
            state.setExplorationDepth(currDepth);
            state.setScore(Integer.MIN_VALUE);
            //System.out.println("max - returned in checkmate, depth - " + currDepth);
            return state;
        }

        if (passCutoffTest(currDepth)) {
            //base case: the only place the
            state.setScore(evaluate(state));
            state.setExplorationDepth(currDepth);
            //System.out.println("max - returned in cuttoff");
            return state;
        }

        Board bestNextBoardFound = null;
        //this is the sorted list of states (based on our exploration policy) after all valid moves
        List<Board> statesAfterMove = getAllNewBoardAfterMove(state, allValidMoves);
        int stateScore = Integer.MIN_VALUE;
        //Question: should we set the initial score here?
        for (Board childState : statesAfterMove) {
            //better state found, update stateScore and bestNextBoardFound
            //TODO: check here and naming is awful
            Board bestNextNextBoardFound = H_min(currDepth+1, childState, !isWhitePiece, alpha, beta);


//            //compare by depth also
//            if (bestNextBoardFound!= null) {
//                if (bestNextBoardFound.getExplorationDepth() > bestNextNextBoardFound.getExplorationDepth()) {
//                    if (bestNextNextBoardFound.getScore() < 0)
//                        continue; //lose
//                    stateScore = bestNextNextBoardFound.getScore();
//                }
//            }
            if (stateScore <= bestNextNextBoardFound.getScore()) {
                stateScore = bestNextNextBoardFound.getScore();
                bestNextBoardFound = childState;
            }
            if (stateScore >= beta)
                break; //stop exploring other children & return bestNextBoardFound
            alpha = Math.max(stateScore, alpha);

        }
        return bestNextBoardFound;
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


    /***
     * Pass cutoff test only if we reach the specific state with depth h.
     *
     * @param currDepth
     * @return pass the test of not
     */
    private boolean passCutoffTest(int currDepth) {
        return currDepth == hMinimaxDepth;
    }

    /***
     * Reached terminal state: stalemate!
     *
     * @param state
     * @return
     */
    private boolean stalemate(Board state) {
        return state.getWhiteTerritory().size() == 1 && state.getBlackTerritory().size() == 1;
    }

    /***
     * Reached terminal state: checkmate!
     *
     * @param numAllValidMoves
     * @return
     */
    private boolean checkmate(int numAllValidMoves) {
        return numAllValidMoves == 0;
    }

    /***
     * Given a state/board and all computed valid moves, we construct all new boards with the given move --> unsorted states
     * we sort the states based on given explorationPolicy
     * At the end, return a list of states in the order of our exploration policy (we explore whatever state at the front first).
     *
     * @param state
     * @param allValidMoves
     * @return sortedStates
     */
    private List<Board> getAllNewBoardAfterMove(Board state, List<Move> allValidMoves) {
        List<Board> statesAfterMove = new ArrayList<>();
        for (Move move : allValidMoves) {
            Board newState = state.makeMove(move);
            statesAfterMove.add(newState);
        }
        policy.sortByExplorationOrder(statesAfterMove);
        return statesAfterMove;
    }

}

