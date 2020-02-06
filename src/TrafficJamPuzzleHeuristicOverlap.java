public class TrafficJamPuzzleHeuristicOverlap implements TrafficJamPuzzleHeuristic {
    Board currBoard;

    /* f(currBoard) = number of overlaps after moving all the vehicles blocking the red car
       from reaching the door for a minimum number of steps
       @Override
     */
    public int computeEstCost(Board board, Vehicle redCar) {
        this.currBoard = board;

        int rowOfRedCar = redCar.getCoord()[0]; // the row the red car is in

        for (int row = 0; row < rowOfRedCar; row++) {
            int id = board.getBoard()[row][board.getDoorColumn()];
            if (id != -1) {  // if that grid is occupied
                Vehicle vehicle = board.getVehicle(id);
                // if there exists a vehicle moving vertically in the same column with the red car,
                // the red car is never able to reach the door
                if (vehicle.movesVertically())
                    return Integer.MAX_VALUE;

                // if obstacle vehicle moves horizontally
                int numGridsToMoveLeft = numOfGridsToMove(vehicle, Vehicle.Direction.LEFT);
                int numGridsToMoveRight = numOfGridsToMove(vehicle, Vehicle.Direction.RIGHT);
            }

        }

        return 0;
    }

    /* Returns the number of overlapping grids after moving the specified vehicle to the
       specified direction
       @vehicle: vehicle to be moved
       @direction: direction to be moved towards
     */
    private int numOfOverlapGrids(Vehicle vehicle, Vehicle.Direction direction) {
        return 0;
    }

    /* Returns the min number of grids to be moved for the current vehicle to get
       out of the way in order for the red car to reach the door, to the specified direction
       @vehicle: vehicle to be moved
       @direction: direction to be moved towards
     */
    private int numOfGridsToMove(Vehicle vehicle, Vehicle.Direction direction) {
        int numSteps = 0;
        if (direction == Vehicle.Direction.LEFT) {
        } else {

        }
        return 0;
    }
}
