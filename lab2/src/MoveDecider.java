import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MoveDecider {
    final static int HMINIMAX_DEPTH = 3;
    final static int BOARD_SIZE = 8;

    public static void main(String[] args) throws IOException {
        char[][] boardData = readInput();
        boolean playWithWhitePiece = true;
        //TODO: depth is hardcoded here, it can be easily changed to be a input value, default to play with white piece


        ExplorePolicy policyValue = new ExplorePolicyValue();
        AI aiValue = new AI(HMINIMAX_DEPTH, playWithWhitePiece, boardData, policyValue, BOARD_SIZE); //construct AI object with raw board data
        Board bestNextBoardFoundValue = aiValue.runMinimax();
        Move moveValue = new Move(bestNextBoardFoundValue.getMovedPiece(), bestNextBoardFoundValue.getMovedPiece().getCoord());
        char[][] newBoardDataValue = aiValue.getNewBoardAfterMove(moveValue);
        int numBoardsVisitedValue = aiValue.getNumBoardsVisited();

        ExplorePolicy policyDist = new ExplorePolicyDistance();
        AI aiDist = new AI(HMINIMAX_DEPTH, playWithWhitePiece, boardData, policyDist, BOARD_SIZE); //construct AI object with raw board data
        Board bestNextBoardFoundDist = aiDist.runMinimax();
        Move moveDist = new Move(bestNextBoardFoundDist.getMovedPiece(), bestNextBoardFoundDist.getMovedPiece().getCoord());
        char[][] newBoardDataDist = aiDist.getNewBoardAfterMove(moveDist);
        int numBoardsVisitedDist = aiDist.getNumBoardsVisited();

        printOutput(moveDist, newBoardDataDist, numBoardsVisitedDist);
        System.out.println("-------------distance exploration policy\n");

        printOutput(moveValue, newBoardDataValue, numBoardsVisitedValue);
        System.out.println("-------------value exploration policy\n");

    }



    /**
     * TODO: we don't have much sanity check here, assuming a valid input format
     * Read a txt file based on the (repo/)filename from the command line argument and parse it into a 2d array as
     * board data.
     *
     * file.txt format: "_" being a blank grid, pawn as p, king as k, rook as r, bishop as b, queen as q, knight as n.
     * White pieces are in capital letters.
     * Example:
     * [[‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘q’, ‘k’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘P’, ‘_’, ‘p’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘Q’, ‘P’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘_’, ‘P’, ‘P’, ‘_’],
        [‘_’, ‘_’, ‘_’, ‘_’, ‘R’, ‘_’, ‘K’, ‘_’]]
     *
     * @return a 2d array as the board
     */
    public static char[][] readInput() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String filename = reader.readLine();
        reader = new BufferedReader(new FileReader(filename));

        String line, rawData = "";
        while ((line = reader.readLine()) != null) {
            rawData += line;
        }

        //TODO: check out how Pattern compile() works
        rawData = rawData.replaceAll("\\[", "");
        rawData = rawData.replaceAll("\\]", "");
        rawData = rawData.replaceAll(",", "");
        rawData = rawData.replaceAll("‘", "");
        rawData = rawData.replaceAll("’", "");
        rawData = rawData.replaceAll(" ", "");

        int indexInRawData = 0;
        char[][] boardData = new char[BOARD_SIZE][BOARD_SIZE];

        for (int r = 0; r < boardData.length; r++) {
            for (int c = 0; c < boardData[r].length; c++) {
                boardData[r][c] = rawData.charAt(indexInRawData++);
            }
        }

        System.out.println("Initial board after parsing:");
        for (int r = 0; r < boardData.length; r++) {
            for (int c = 0; c < boardData[r].length; c++) {
                System.out.print(boardData[r][c] + " ");
            }
            System.out.println();
        }
        return boardData;

    }


    private static void printOutput(Move move, char[][] newBoardData, int numBoardsVisited) {
        System.out.println("===== Printing result =====: ");
        System.out.println(move.toString());
        System.out.println("------------------>>>>");
        //todo: print newBoardData
        System.out.println(changeBoardDataToString(newBoardData));
        System.out.println("number boards visited: " + numBoardsVisited);

    }

    //TODO: there might be a repeated print inside Board
    private static String changeBoardDataToString(char[][] newBoardData) {
        String ans = "";

        for (char[] row : newBoardData) {
            for (char c : row)
                ans += c + " ";
            ans += "\n";
        }

        return ans;
    }
}
