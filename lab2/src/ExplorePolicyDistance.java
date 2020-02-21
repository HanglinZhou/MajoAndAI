import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/***
 * This exploration policy let AI first explore the board with its just moved piece traveling farthest
 */
public class ExplorePolicyDistance implements ExplorePolicy {
    @Override
    public void sortByExplorationOrder(List<Board> unsortedBoards) {
        PriorityQueue<Board> pq = new PriorityQueue<>(new BoardComparatorByTravelDist());
        for (Board b : unsortedBoards) {
            pq.offer(b);
        }
        unsortedBoards.clear();
        int index = 0;
        while (!pq.isEmpty()) {
            unsortedBoards.add(index, pq.poll());
            index++;
        };
    }

    class BoardComparatorByTravelDist implements Comparator<Board> {
        @Override
        public int compare(Board b1, Board b2) {
            if (b1.getTravelDistOfMovedPiece() < b2.getTravelDistOfMovedPiece()) {
                return 1;
            } else if (b1.getTravelDistOfMovedPiece() > b2.getTravelDistOfMovedPiece()) {
                return -1;
            }
            return 0;
        }
    }
}
