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
        this.board = board;

        int[][] actualBoard = board.getBoard();
        for (int row = 0; row < rowOfRedCar; row++) {
            int doorCol = Board.getDoorColumn();
            int id = this.board.getBoard()[row][doorCol];

            if (id != -1) {  // if that grid is occupied
                Vehicle vehicle = this.board.getVehicle(id);


                // if there exists a vehicle moving vertically in the same column with the red car,
                // the red car is never able to reach the door
                if (vehicle.movesVertically())
                    return Integer.MAX_VALUE;

                //doorCol is a special case, need to let the startV walk in to a wall, or a collision, blank might undercount
                /*
                1. vLength = 2, col(head) == doorCol, cost = 2,
                    else cost = 1
                2. vLength = 3, col(head) == doorCol, cost = 3,
                                col(head+-1) == doorCol, cost = 2,
                                else cost = 1
                 */
                int leftCost = 0, rightCost = 0;
                //not the right most and right grid is empty, then go to special case move right
                int maxStep = vehicle.getLength();
                int curStep = 0;
                int tmpCost;
                while (doorCol + curStep < actualBoard.length - 1 && curStep < maxStep && board.isGridOccupied(row, doorCol
                        + curStep + 1)) {
                        if (vehicle.getCoord()[1] == doorCol) {
                            rightCost++;
                        }
                        curStep++;
                }
                if (doorCol >= actualBoard.length -1)
                    rightCost = Integer.MAX_VALUE;
//                else if (doorCol + curStep < actualBoard.length - 1 && !board.isGridOccupied(row, doorCol + curStep + 1)) {
//                tmpCost = tryMoveAndComputeCost(Vehicle.Direction.RIGHT, vehicle, actualBoard, allVehicles);
                tmpCost = tryMoveAndComputeCost(Vehicle.Direction.RIGHT, allVehicles.get(actualBoard[row][doorCol + curStep]), actualBoard, allVehicles);

                if (tmpCost == Integer.MAX_VALUE)
                    rightCost = tmpCost;
                else
                    rightCost += tmpCost;
//                }

                curStep = 0;
                while (doorCol - curStep >= 0 && curStep < maxStep && board.isGridOccupied(row, doorCol
                        - curStep - 1)) {
                    if (vehicle.getCoord()[1] == doorCol) {
                        leftCost++;
                    }
                    curStep++;
                }
                if (doorCol - curStep < 0)
                    leftCost = Integer.MAX_VALUE;
//                else if (doorCol - curStep >= 0 && !board.isGridOccupied(row, doorCol - curStep + 1)) {
                tmpCost = tryMoveAndComputeCost(Vehicle.Direction.LEFT, allVehicles.get(actualBoard[row][doorCol - curStep]), actualBoard, allVehicles);
                if (tmpCost == Integer.MAX_VALUE)
                    leftCost =tmpCost;
                else
                    leftCost += tmpCost;
//                }
                //move left and right, left
                //leftCost = tryMoveAndComputeCost(Vehicle.Direction.LEFT, vehicle, actualBoard, allVehicles);
                //rightCost = tryMoveAndComputeCost(Vehicle.Direction.RIGHT, vehicle, actualBoard, allVehicles);
                if (leftCost == Integer.MAX_VALUE && rightCost == Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                allCost += Math.min(leftCost, rightCost);
                System.out.println("left, right, min: " + leftCost + " " + rightCost + " " + Math.min(leftCost, rightCost));
                System.out.println("allCost: " + allCost);
            }

        }

        return allCost;
    }

    private int tryMoveAndComputeCost(Vehicle.Direction dir, Vehicle vehicle, int[][] actualBoard, List<Vehicle> allVehicles) {
        System.out.println("in recursion");
        int[] vCoord = vehicle.getCoord();
        int nextVId;

        System.out.printf("car %s w/ coord: %s, %s\n", vehicle.getId(), vCoord[0], vCoord[1]);
        if (dir == Vehicle.Direction.UP || dir == Vehicle.Direction.DOWN)
            return 0;
        //base case: wall || blank
        if (dir == Vehicle.Direction.LEFT) {
            //wall
            if (vCoord[1] == 0)
                return Integer.MAX_VALUE;

            //blank
            if (!board.isGridOccupied(vCoord[0], vCoord[1] - 1)) {
                return 0;
            }

            //collide
            nextVId = actualBoard[vCoord[0]][vCoord[1] - 1];

        } else {
            //wall
            if (vCoord[1]+ vehicle.getLength() == actualBoard.length)
                return Integer.MAX_VALUE;

            //right is blank
            if (!board.isGridOccupied(vCoord[0], vCoord[1] + vehicle.getLength())) {
                return 1;
            }
            nextVId = actualBoard[vCoord[0]][vCoord[1] + vehicle.getLength()];

        }
        int cost = tryMoveAndComputeCost(dir, allVehicles.get(nextVId), actualBoard, allVehicles);
        if (cost == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (nextVId == vehicle.getId())
            return cost;
        else return cost + 1;

    }
}

