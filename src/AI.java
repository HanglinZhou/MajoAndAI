import java.util.*;

public class AI {
    Board initialBoard;
    HashSet<Board> visitedBoards;
    PriorityQueue<Board> frontier;
    TrafficJamPuzzleHeuristic myHeuristic;

    /**
     * Constructor of the AI object, where all initializations take place.
     *
     * @param boardData raw data from reading input
     * @param h the heuristic to use
     */
    public AI(int[][] boardData, TrafficJamPuzzleHeuristic h) {
        frontier = new PriorityQueue<>((o1, o2) -> o1.getCost() - o2.getCost());
        myHeuristic = h;
        visitedBoards = new HashSet<>();

        // initiate the initial state/board, put it into the frontier
        Board initalBoard = new Board(boardData);
        frontier.add(initalBoard);

        System.out.println("AI initialized, board printing");
        initalBoard.printBoard();


    }

    /**
     * Start A* search with the heuristic given.
     *
     * @return the terminal board (state) if exists. If the board does not exist return null.
     */
    public Board AStarSearch() {
        //todo
        frontier.offer(initialBoard);
        while (!frontier.isEmpty()) {
            Board currBoard = frontier.poll();

            if (passGoalTest(currBoard))
                return currBoard;

            visitedBoards.add(currBoard);
            if (!visitedBoards.contains(currBoard)) {
                for (AIAction a : currBoard.computeValidActions()) {
                    Board successor = successorFunction(currBoard, a);
                    if (!visitedBoards.contains(successor))
                        frontier.offer(successor);

                }
            }
        }

        return null;
    }

    /**
     * Build the path (sequence of actions) based on the terminal board.
     *
     * @param terminalBoard
     * @return a list of action
     */
    public List<AIAction> buildPath(Board terminalBoard) {
        //todo
        Stack<Board> reverseBoards = new Stack<>();
        Stack<AIAction> reversedPath = new Stack<>();

        List<AIAction> path = new ArrayList<>();
        Board currentBoard = terminalBoard;
        reverseBoards.push(currentBoard);

        // from the terminal board, continue tracing back to the parent, until the initial state is reached,
        // and add the actions accordingly to reconstruct the path chosen
        while (currentBoard.parent != null) {
            // while the current board is not the initial state/board, add the action taken to reach current board
            currentBoard = currentBoard.parent;
            reverseBoards.push(currentBoard);
        }
        printPath(reverseBoards, path);

        return path;
    }

    /**
     * Print out the path according the input List of actions
     *
     * @param reversedBoards
     * @param path List of AIActions to be filled in
     */
    private void printPath(Stack<Board> reversedBoards, List<AIAction> path) {
        //todo

        // print initial board
        Board currentBoard = reversedBoards.pop();
        currentBoard.printBoard();

        while (!reversedBoards.isEmpty()) {
            currentBoard = reversedBoards.pop();

            path.add(currentBoard.getActionTaken());
            path.get(path.size() - 1).printAIAction();
            currentBoard.printBoard();
        }
    }

    /**
     * Transition model.
     *
     * @param currBoard
     * @param action
     * @return the successor board (state) as the result of the successor function
     */
    private Board successorFunction(Board currBoard, AIAction action) {
        //todo
        //construct successor board
        Board successor = new Board(currBoard, action);

        //compute cost f(n) = g(n) + h(n)
        int cost = currBoard.getCost() + 1 + myHeuristic.computeEstCost(successor, successor.getRedCar());
        successor.setCost(cost);
        return successor;
    }

    /**
     * Test if redCar occupies the cell with the door on one of its edges (i.e., the
     * red car can leave the parking lot)
     * @param currBoard
     * @return
     */
    private boolean passGoalTest(Board currBoard) {
        //don't need to check the column position, the only valid row is 0 or 1 for a car
        /*
        if (redCar.getCoord()[0] == 0)
            return true;
         */
        return false;
    }
}
