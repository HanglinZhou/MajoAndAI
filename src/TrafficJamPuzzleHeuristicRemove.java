import java.util.ArrayList;
import java.util.List;

/**
 * Remove all vertical cars and compute cost.
 */
public class TrafficJamPuzzleHeuristicRemove implements TrafficJamPuzzleHeuristic {
    Board board;

    @Override
    public int computeEstCost(Board board, Vehicle redCar) {

        //find which v are in front of redCar --> move up and check if any of them can only move vertically
        int rowOfRedCar = redCar.getCoord()[0]; // the row the red car is in
        int allCost = 0;
        List<Vehicle> allVehicles = board.getVehicles();
        int[][] actualBoard = board.getBoard();
        for (int row = 0; row < rowOfRedCar; row++) {
            int id = this.board.getBoard()[row][Board.getDoorColumn()];
            if (id != -1) {  // if that grid is occupied
                Vehicle vehicle = this.board.getVehicle(id);
                // if there exists a vehicle moving vertically in the same column with the red car,
                // the red car is never able to reach the door
                if (vehicle.movesVertically())
                    return Integer.MAX_VALUE;

                //move left and right, left
                int leftCost = tryMoveAndComputeCost(Vehicle.Direction.LEFT, vehicle, actualBoard, allVehicles);
                int rightCost = tryMoveAndComputeCost(Vehicle.Direction.RIGHT, vehicle, actualBoard, allVehicles);
                if (leftCost == Integer.MAX_VALUE && rightCost == Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                allCost += Math.min(leftCost, rightCost);
            }

        }

        return allCost;
    }

    private int tryMoveAndComputeCost(Vehicle.Direction dir, Vehicle vehicle, int[][] actualBoard, List<Vehicle> allVehicles) {
        int[] vCoord = vehicle.getCoord();
        int nextVId = -1;

        //base case: wall || blank
        if (dir == Vehicle.Direction.LEFT) {
            //wall
            if (vCoord[1] == 0)
                return Integer.MAX_VALUE;

            //blank
            if (!board.isGridOccupied(vCoord[0], vCoord[1] - 1)) {
                return 1;
            }

            //collide
            nextVId = actualBoard[vCoord[0]][vCoord[1] - 1];

        }
        //wall
        if (vCoord[1] == actualBoard.length - 1)
            return Integer.MAX_VALUE;

        //right is blank
        if (!board.isGridOccupied(vCoord[0], vCoord[1] + 1)) {
            return 1;
        }
        if (dir == Vehicle.Direction.RIGHT) {
            nextVId = actualBoard[vCoord[0]][vCoord[1] + 1];
        }
        int cost = tryMoveAndComputeCost(dir, allVehicles.get(nextVId), actualBoard, allVehicles);
        if (cost == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else return cost + 1;

    }
}

