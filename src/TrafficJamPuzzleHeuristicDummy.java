import java.util.List;

public class TrafficJamPuzzleHeuristicDummy implements TrafficJamPuzzleHeuristic{
    Board board;

    @Override
    public int computeEstCost(Board board, Vehicle redCar) {
    //find which v are in front of redCar --> move up and check if any of them can only move vertically
        int rowOfRedCar = redCar.getCoord()[0]; // the row the red car is in
        int allCost = 0;
        List<Vehicle> allVehicles = board.getVehicles();
        this.board = board;

        int[][] actualBoard = board.getBoard();
        int cost = 0;
        for (int row = 0; row < rowOfRedCar; row++) {
            int doorCol = Board.getDoorColumn();
            int id = this.board.getBoard()[row][doorCol];
            if (id != -1) {  // if that grid is occupied
                Vehicle vehicle = this.board.getVehicle(id);


                // if there exists a vehicle moving vertically in the same column with the red car,
                // the red car is never able to reach the door
                if (vehicle.movesVertically())
                    return Integer.MAX_VALUE;
                cost++;
            }
        }
        return cost;
    }
}
