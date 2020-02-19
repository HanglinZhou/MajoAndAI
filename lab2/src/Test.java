import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        char[][] dummy = new char[1][1];
        Board dumbBoard = new Board(dummy);

        HashMap<Coord, Piece> dumbMap = new HashMap<>();

        Coord c = new Coord(2, 2);
        Piece pawn = new PiecePawn(c, "pawn", true);

        Coord c1 = new Coord(0, 0);
        Piece queen = new PiecePawn(c1, "queen", true);

        dumbMap.put(c, pawn);
        dumbMap.put(c1, queen);

        dumbBoard.whiteTerritory = dumbMap;

        List<Move> result = dumbBoard.computeAllValidMoves(true);
        for (Move m : result) {
            System.out.println(m);
        }
    }
}
