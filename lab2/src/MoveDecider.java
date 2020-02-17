import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MoveDecider {
    final static int HMINIMAX_DEPTH = 4;
    public static void main(String[] args) throws IOException {
        char[][] boardData = readInput();
        boolean playWithWhitePiece = true;
        //TODO: depth is hardcoded here, it can be easily changed to be a input value, default to play with white piece
        ExplorePolicy policyDist = new ExplorePolicyDistance();
        ExplorePolicy policyValue = new ExplorePolicyValue();
        AI aiDist = new AI(HMINIMAX_DEPTH, playWithWhitePiece, boardData, policyDist); //construct AI object with raw board data
        AI aiValue = new AI(HMINIMAX_DEPTH, playWithWhitePiece, boardData, policyValue); //construct AI object with raw board data

        Move moveDist = aiDist.runMinimax();
        Move moveValue = aiValue.runMinimax();

        char[][] newBoardDataDist = aiDist.getNewBoardAfterMove(moveDist);
        char[][] newBoardDataValue = aiDist.getNewBoardAfterMove(moveValue);

        printOutput(moveDist, newBoardDataDist);
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
     * @return a 2d array as the board,
     */
    public static char[][] readInput() throws IOException {
        //todo
        char[][] boardData = null;
        int doorCoord = 0;
        char blank = '_';

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String filename = reader.readLine();
        reader = new BufferedReader(new FileReader(filename));

        /*
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

*/
        return boardData;

    }


    private static void printOutput(Move move, char[][] newBoardData) {
        System.out.println(move.toString());
        //todo: print newBoardData
    }
}
