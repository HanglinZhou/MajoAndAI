import java.util.List;

public class TrafficJamPuzzleSolver {

    public static void main(String[] args) {
        int[][] boardData = null;
        int[][] doorCoord = readInput(boardData);
        TrafficJamPuzzleHeuristic h1 = new TrafficJamPuzzleHeuristicOverlap();
        TrafficJamPuzzleHeuristic h2 = new TrafficJamPuzzleHeuristicRemove();


        AI ai_1 = new AI(boardData, doorCoord, h1); //initialize
        //timer

        Board terminalBoard1 = ai_1.AStarSearch();
        List<AIAction> solution1 = ai_1.buildPath(terminalBoard1);
        ai_1.printPath(solution1);

        //timer end
        AI ai_2 = new AI(boardData, doorCoord, h2); //initialize
        //timer

        Board terminalBoard2 = ai_2.AStarSearch();
        List<AIAction> solution2 = ai_2.buildPath(terminalBoard2);
        ai_2.printPath(solution2);
        //timer end

    }

    public static int[][] readInput(int[][] boardData) {
        return null;
    }
}
