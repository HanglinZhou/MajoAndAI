import java.util.List;
import java.util.PriorityQueue;

public class ExplorePolicyValue implements ExplorePolicy {
    @Override
    public void sortByExplorationOrder(List<Board> unsortedBoards) {
        //todo
        PriorityQueue<Board> pq = new PriorityQueue<>((o1, o2) -> o2.getMovedPiece().getValue() - o1.getMovedPiece().getValue());
        for
    }
}
