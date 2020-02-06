import java.io.FileReader;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrafficJamPuzzleSolver {

    public static void main(String[] args) throws IOException {

        int[][] boardData = readInput();
        int doorCoord = boardData[boardData.length-1][boardData[0].length - 1];

        if (doorCoord == -1)
            return;
        for (int[] arr : boardData) {
            for(int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        TrafficJamPuzzleHeuristic h0 = new TrafficJamPuzzleHeuristicDummy();
        TrafficJamPuzzleHeuristic h1 = new TrafficJamPuzzleHeuristicRemove();
        TrafficJamPuzzleHeuristic h2 = new TrafficJamPuzzleHeuristicOverlap();

        AI ai_0 = new AI(boardData, h2); //initialize
        //ai_0.initialBoard.printBoard();
        Board terminalBoard1 = ai_0.AStarSearch();
        List<AIAction> solution0 = ai_0.buildPath(terminalBoard1);
        System.out.println(solution0.size());
        System.out.println("numBoardsExplored: " + ai_0.getNumBoardsExplored());
        System.out.println("numBoardsVisited: " + ai_0.getNumBoardsVisited());
        //ai_1.printPath(solution1);


        AI ai_1 = new AI(boardData, h1); //initialize
        //timer

        //Board terminalBoard1 = ai_1.AStarSearch();
        //List<AIAction> solution1 = ai_1.buildPath(terminalBoard1);
        //ai_1.printPath(solution1);

        //timer end
        AI ai_2 = new AI(boardData, h2); //initialize
        //timer

        //Board terminalBoard2 = ai_2.AStarSearch();
        //List<AIAction> solution2 = ai_2.buildPath(terminalBoard2);
        //ai_2.printPath(solution2);
        //timer end

    }

    /**
     * TODO: we don't have much sanity check here, assuming a valid input format
     * Read a txt file based on the (repo/)filename from the command line argument and parse it into a 2d array as
     * board data.
     *
     * file.txt format: "x"being a blank grid, # represent a number, which is the unique id assigned to each car.
     * an individual car is separated from other cars or blanks by a space
     * |n for  a n*n board/parking lot              |
     * |which row the door is on (index starts at 0)|
     * |   x x x x # # #                                  |
     * |   ...                                      |
     * |   ...                                      |
     * |   x x x x # # #                                  |
     *
     * @return a 2d array as the board, the door's column is stored at the last cell in the 2d array
     */
    public static int[][] readInput() throws IOException {
        int[][] boardData = null;
        int doorCoord = 0;
        char blank = 'x';

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String filename = reader.readLine();
        reader = new BufferedReader(new FileReader(filename));

        int n;
        int rowNum = -2; //-2, -1: the first and second line in the file, functions as a flag,
                         // other num as the row num of the input board
        String line;

        while((line = reader.readLine()) != null) {
            if (rowNum == -2) {
                n = Integer.parseInt(line);
                if (n < 0) {
                    System.out.println("Invalid parking lot size");
                    return null;
                }

                boardData = new int[n + 1][n];
                line = reader.readLine();
                doorCoord = Integer.parseInt(line);

                rowNum += 2;
                continue;
            }

            String[] row = line.split(" ");
            for (int i = 0; i < row.length; i++) {
                if (row[i].charAt(0) == blank)
                    boardData[rowNum][i] = -1;
                else
                    boardData[rowNum][i] = Integer.parseInt(row[i]);

            }
            rowNum++;

        }
        boardData[boardData.length-1][boardData.length-2] = doorCoord;

        return boardData;
    }
}
