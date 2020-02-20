import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        char[][] data = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                data[i][j] = '_';
            }
        }

        data[7][0] = 'q';
        data[7][2] = 'k';
        data[5][3] = 'Q';
        data[4][3] = 'P';
        data[3][4] = 'K';

        Board dumbBoard = new Board(data);
        System.out.println("# white pieces: " + dumbBoard.getWhiteTerritory().size());


        List<Move> result = dumbBoard.computeAllValidMoves(true);
        for (Move m : result) {
            System.out.println(m);
        }

        /*
        char[][] dummy = new char[1][1];
        Board dumbBoard = new Board(dummy);

        HashMap<Coord, Piece> dumbMap = new HashMap<>();

        Coord c = new Coord(2, 2);
        Piece pawn = new PiecePawn(c, true);

        Coord c1 = new Coord(0, 0);
        Piece queen = new PiecePawn(c1, true);

        dumbMap.put(c, pawn);
        dumbMap.put(c1, queen);

        dumbBoard.whiteTerritory = dumbMap;

        List<Move> result = dumbBoard.computeAllValidMoves(true);
        for (Move m : result) {
            System.out.println(m);
        }
         */
    }
}
