import java.util.ArrayList;
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
        data[5][2] = 'Q';
        data[4][3] = 'P';
        data[3][4] = 'K';

        Board dumbBoard = new Board(data);
        Coord dumbCoord = new Coord(4, 3);
        Coord dumbCoord2 = new Coord(5, 2);
        Coord dumbCoord3 = new Coord(3, 4);

        char[][] childrenData = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                childrenData[i][j] = data[i][j];
            }
        }

        childrenData[4][3] = '_';
        childrenData[5][3] = 'P';
        Board dumbChild1 = new Board(childrenData);
        dumbChild1.setParentBoard(dumbBoard);
        dumbChild1.setMovedPiece(dumbBoard.whiteTerritory.get(dumbCoord));

        childrenData[4][3] = 'P';
        childrenData[5][3] = '_';
        childrenData[5][2] = '_';
        childrenData[5][7] = 'Q';
        Board dumbChild2 = new Board(childrenData);
        dumbChild2.setParentBoard(dumbBoard);
        dumbChild2.setMovedPiece(dumbBoard.whiteTerritory.get(dumbCoord2));

        childrenData[5][2] = 'Q';
        childrenData[5][7] = '_';
        childrenData[3][4] = '_';
        childrenData[3][6] = 'K';
        Board dumbChild3 = new Board(childrenData);
        dumbChild3.setParentBoard(dumbBoard);
        dumbChild3.setMovedPiece(dumbBoard.whiteTerritory.get(dumbCoord3));

        List<Board> dumbChildren = new ArrayList<>();
        dumbChildren.add(dumbChild1);
        dumbChildren.add(dumbChild2);
        dumbChildren.add(dumbChild3);

        ExplorePolicy exp = new ExplorePolicyDistance();
        exp.sortByExplorationOrder(dumbChildren);

        for (Board b : dumbChildren) {
            System.out.println(b.getMovedPiece().typename);
        }
        //System.out.println("# white pieces: " + dumbBoard.getWhiteTerritory().size());
        /*
        int score = dumbBoard.computeAllPieceValue(true);
        System.out.println(score);
        score = dumbBoard.getNumAllValidMoves(true);
        System.out.println(score);
        score = dumbBoard.getNumProtectedPawns(true);
        System.out.println(score);



        List<Move> result = dumbBoard.computeAllValidMoves(true);
        for (Move m : result) {
            System.out.println(m);
        }

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
