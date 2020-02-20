import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MoveDecider {
    final static int HMINIMAX_DEPTH = 4;
    final static int BOARD_SIZE = 8;

    public static void main(String[] args) throws IOException {
        char[][] boardData = readInput();
        boolean playWithWhitePiece = true;
        //TODO: depth is hardcoded here, it can be easily changed to be a input value, default to play with white piece
//        ExplorePolicy policyDist = new ExplorePolicyDistance();
//        AI aiDist = new AI(HMINIMAX_DEPTH, playWithWhitePiece, boardData, policyDist); //construct AI object with raw board data
//        Board bestNextBoardFoundDist = aiDist.runMinimax();
//        Move moveDist = new Move(bestNextBoardFoundDist.getMovedPiece(), bestNextBoardFoundDist.getMovedPiece().getCoord());
//        char[][] newBoardDataDist = aiDist.getNewBoardAfterMove(moveDist);

        ExplorePolicy policyValue = new ExplorePolicyValue();
        AI aiValue = new AI(HMINIMAX_DEPTH, playWithWhitePiece, boardData, policyValue); //construct AI object with raw board data
        Board bestNextBoardFoundValue = aiValue.runMinimax();
        if (bestNextBoardFoundValue == null)
            System.out.println("best is null");
        Move moveValue = new Move(bestNextBoardFoundValue.getMovedPiece(), bestNextBoardFoundValue.getMovedPiece().getCoord());
        if (bestNextBoardFoundValue.getMovedPiece() == null)
            System.out.println("best piece is null");
        else
            System.out.print("piece: " + bestNextBoardFoundValue.getMovedPiece().toString());
        if (bestNextBoardFoundValue.getMovedPiece().getCoord() == null)
            System.out.println("best coord is null");
        else
            System.out.print("coord: " + bestNextBoardFoundValue.getMovedPiece().getCoord().toString());
        char[][] newBoardDataValue = aiValue.getNewBoardAfterMove(moveValue);

//        printOutput(moveDist, newBoardDataDist);
        printOutput(moveValue, newBoardDataValue);

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


    private static void printOutput(Move move, char[][] newBoardData) {
        System.out.println("===== Printing result =====: ");
        System.out.println(move.toString());
        System.out.println("------------------>>>>");
        //todo: print newBoardData
        System.out.println(changeBoardDataToString(newBoardData));

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
