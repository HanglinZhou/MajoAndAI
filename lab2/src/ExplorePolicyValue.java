import java.util.List;
import java.util.PriorityQueue;

/***
 * This exploration policy let AI explore the board with a moved piece that has a higher value first.
 */
public class ExplorePolicyValue implements ExplorePolicy {
    @Override
    public void sortByExplorationOrder(List<Board> unsortedBoards) {
        PriorityQueue<Board> pq = new PriorityQueue<>((o1, o2) -> o2.getMovedPiece().getValue() - o1.getMovedPiece().getValue());
        for (Board b : unsortedBoards) {
            pq.offer(b);
        }
        unsortedBoards.clear();
        int index = 0;
        while (!pq.isEmpty()) {
            unsortedBoards.add(index, pq.poll());
            index++;
        }
    }
}
