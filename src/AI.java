import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class AI {
    Vehicle redCar;
    int doorColumn; //door row = 0
    Board initialBoard;
    HashSet<Board> visitedBoards;
    PriorityQueue<Board> frontier;
    TrafficJamPuzzleHeuristic myHeuristic;

    /**
     * Constructor of the AI object, where all initializations take place.
     *
     * @param boardData
     * @param doorCoord
     */
    public AI(int[][] boardData, int[][] doorCoord, TrafficJamPuzzleHeuristic h) {
        //todo
        frontier = new PriorityQueue<>((o1, o2) -> o1.getCost() - o2.getCost());
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
        return new ArrayList<>();
    }

    /**
     * Print.
     *
     * @param solution
     */
    public void printPath(List<AIAction> solution) {
    }

    /**
     * Transition model.
     *
     * @param currBoard
     * @param action
     * @return the successor board (state) as the result of the successor function
     */
    private Board successorFunction(Board currBoard, AIAction action) {
        //assign computed f(n) in here
        //todo
        return null;
    }

    /**
     * Test if redCar occupies the cell with the door on one of its edges (i.e., the
     * red car can leave the parking lot)
     * @param currBoard
     * @return
     */
    private boolean passGoalTest(Board currBoard) {
        //don't need to check the column position, the only valid row is 0 or 1 for a car
        if (redCar.getCoord()[0] < redCar.getLength())
            return true;
        return false;
    }
}
